package uk.ac.gla.socs.grl.value.qualitative;

import uk.ac.gla.socs.grl.evaluation.*;

import uk.ac.gla.socs.grl.model.link.*;
import uk.ac.gla.socs.grl.model.link.Link;
import uk.ac.gla.socs.grl.model.element.*;
import uk.ac.gla.socs.grl.utility.*;
import uk.ac.gla.socs.grl.utility.Just;
import uk.ac.gla.socs.grl.utility.Maybe;
import uk.ac.gla.socs.grl.utility.Nothing;
import uk.ac.gla.socs.grl.value.qualitative.Importance;
import uk.ac.gla.socs.grl.value.qualitative.Contribution;
import uk.ac.gla.socs.grl.value.qualitative.Satisfaction;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.Optional;
import java.util.HashSet;

import java.util.stream.*;

public class QualitativeEvaluation extends AbstractEvaluationStrategy <Importance, Satisfaction, Contribution> {

    @Override
    public Satisfaction calculateDecompositions(Element<Importance,Satisfaction> node,
                                         List<Element<Importance,Satisfaction>> decomps)
    {
        Satisfaction result;
        DecompositionType dtype = node.getDecomposition().orElse(DecompositionType.AND);

        switch (dtype) {
        case XOR:
        case IOR:
            result = this.doDecompositionCalculationOR(decomps);
            break;
        default:
            result = this.doDecompositionCalculationAND(decomps);
            break;
        }
        return result;
    }

    @Override
    public Satisfaction calculateContributions(Element<Importance,Satisfaction> node,
                                               List<Edge<Element<Importance,Satisfaction>,Link<Contribution>>> contribs,
                                               Satisfaction init)
    {
        ContributionCounter counter = new ContributionCounter();
        counter.adjustCount(init);
        // Calculated Weighted Contribution

        for (Edge<Element<Importance, Satisfaction>,Link<Contribution>> edge : contribs) {
            Satisfaction s = edge.getDestination().getSatisfaction().orElse(Satisfaction.NONE);
            Maybe<Link<Contribution>> mclink = FromMaybe.optional(edge.getLabel());
            Contribution c = Contribution.UNKNOWN;

            if (mclink instanceof Just) {
                Link<Contribution> clink = ((Just<Link<Contribution>>) mclink).getValue();
                c = clink.getContribution().orElse(Contribution.UNKNOWN);
            }
            counter.adjustCount(this.weightedContribution(s,c));
        }
        if (counter.getUnknown() > 0) {
            return Satisfaction.UNKNOWN;
        } else {

            Satisfaction csd = this.compareSatisfiedDenied(counter.getSatisfied(),
                                                           counter.getDenied());

            Satisfaction cwsd = this.compareWeaklySatisfiedDenied(counter.getSatisfiedWeakly(),
                                                                  counter.getDeniedWeakly());

            Satisfaction res = this.combineContributions(csd,cwsd);
            return res;
        }
    }

    @Override
    public
    Satisfaction calculateDependencies(Element<Importance,Satisfaction> node,
                                       List<Element<Importance,Satisfaction>> deps,
                                       Satisfaction contribValue)
    {
        Satisfaction sdeps = this.doDecompositionCalculationAND(deps);
        Integer min_deps = this.toIntAND(sdeps);
        Integer min_cval = this.toIntAND(contribValue);
        return min_deps < min_cval ? sdeps : contribValue;
    }

    @Override
    public
    Satisfaction calculateActor(List<Element<Importance,Satisfaction>> enclosed)
    {
        ActorCounter counter = new ActorCounter();
        for (Element<Importance,Satisfaction> node : enclosed) {

            counter.adjustCount(this.weightedImportance(node.getImportance(), node.getSatisfaction().orElse(Satisfaction.UNKNOWN)));
        }

        if (counter.getConflict() > 0) {
            return Satisfaction.CONFLICT;
        } else if (counter.getUnknown() > 0) {
            return Satisfaction.UNKNOWN;
        } else {
            return this.combineContributions(this.compareSatisfiedDenied(counter.getSatisfied(),counter.getDenied()),
                                             this.compareWeaklySatisfiedDenied(counter.getSatisfiedWeakly(), counter.getDeniedWeakly()));
        }
    }

    private Satisfaction doDecompositionCalculationOR(List<Element<Importance,Satisfaction>> decomps) {
        Integer result = 0;
        for (Element<Importance, Satisfaction> e : decomps) {
            Satisfaction temp = e.getSatisfaction().orElse(Satisfaction.NONE);
            Integer temp_int = this.toIntOR(temp);
            result = temp_int > result ? temp_int : result;
        }
        return this.fromIntOR(result);
    }


    private Satisfaction doDecompositionCalculationAND(List<Element<Importance,Satisfaction>> decomps) {
        Integer result = 5;
        for (Element<Importance, Satisfaction> e : decomps) {
            Satisfaction temp = e.getSatisfaction().orElse(Satisfaction.NONE);
            Integer temp_int = this.toIntAND(temp);
            result = temp_int < result ? temp_int : result;
        }

        return this.fromIntAND(result);
    }

    private Satisfaction fromIntAND(Integer a) {
        switch (a) {
        case 0:
            return Satisfaction.DENIED;
        case 1:
            return Satisfaction.UNKNOWN;
        case 2:
            return Satisfaction.WEAKDENIED;
        case 3:
            return Satisfaction.NONE;
        case 4:
            return Satisfaction.WEAKSATISFACTION;
        case 5:
            return Satisfaction.SATISFACTION;
        default:
            return Satisfaction.CONFLICT;
        }
    }
    private Integer toIntAND (Satisfaction a) {
        switch (a) {
        case DENIED:
            return 0;
        case CONFLICT:
        case UNKNOWN:
            return 1;
        case WEAKDENIED:
            return 2;
        case NONE:
            return 3;
        case WEAKSATISFACTION:
            return 4;
        case SATISFACTION:
            return 5;
        default:
            return 1;
        }
    }

    private Satisfaction fromIntOR(Integer a) {
        switch (a) {
        case 0:
            return Satisfaction.DENIED;
        case 1:
            return Satisfaction.WEAKDENIED;
        case 2:
            return Satisfaction.NONE;
        case 3:
            return Satisfaction.WEAKSATISFACTION;
        case 4:
            return Satisfaction.UNKNOWN;
        case 5:
            return Satisfaction.SATISFACTION;
        default:
            return Satisfaction.UNKNOWN;
        }
    }
    private Integer toIntOR(Satisfaction a) {
        switch (a) {
        case DENIED:
            return 0;
        case WEAKDENIED:
            return 1;
        case NONE:
            return 2;
        case WEAKSATISFACTION:
            return 3;
        case CONFLICT:
        case UNKNOWN:
            return 4;
        case SATISFACTION:
            return 5;
        default:
            return 4;
        }
    }

    private int weightedContributionRow(Satisfaction s) {
        switch (s) {
        case DENIED:
            return 0;
        case WEAKDENIED:
            return 1;
        case WEAKSATISFACTION:
            return 2;
        case SATISFACTION:
            return 3;
        case CONFLICT:
            return 4;
        case UNKNOWN:
            return 5;
        case NONE:
        default:
            return 6;
        }
    }

    private int weightedContributionColumn(Contribution c) {
        switch (c) {
        case MAKES:
            return 0;
        case HELPS:
            return 1;
        case SOMEPOS:
            return 2;
        case UNKNOWN:
            return 3;
        case SOMENEG:
            return 4;
        case HURTS:
            return 5;
        case BREAKS:
        default:
            return 6;
        }
    }

    private static final Satisfaction[][] weighted_contribution =
    {
        {
            Satisfaction.DENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.NONE,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.SATISFACTION
        },

        {
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.NONE,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION
        },

        {
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.NONE,
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED
        },

        {
            Satisfaction.SATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.WEAKSATISFACTION,
            Satisfaction.NONE,
            Satisfaction.WEAKDENIED,
            Satisfaction.WEAKDENIED,
            Satisfaction.DENIED
        },

        {
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN
        },

        {
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN,
            Satisfaction.UNKNOWN
        },
        {
            Satisfaction.NONE,
            Satisfaction.NONE,
            Satisfaction.NONE,
            Satisfaction.NONE,
            Satisfaction.NONE,
            Satisfaction.NONE,
            Satisfaction.NONE
        }
      };

    private Satisfaction weightedContribution(Satisfaction s, Contribution c) {
        int ridx = this.weightedContributionRow(s);
        int cidx = this.weightedContributionColumn(c);

        return weighted_contribution[ridx][cidx];

    }

    private int combineContributionColumn(Satisfaction s) {

        switch (s) {
        case DENIED:
            return 0;
        case SATISFACTION:
            return 1;
        case CONFLICT:
            return 2;
        case NONE:
        default:
            return 3;
        }
    }
    private int combineContributionRow(Satisfaction s) {
        switch (s) {
        case WEAKDENIED:
            return 0;
        case WEAKSATISFACTION:
            return 1;
        case NONE:
        default:
            return 2;
        }
    }

    private static final Satisfaction[][] combine_contributions =
    {
        {
            Satisfaction.DENIED,   Satisfaction.WEAKSATISFACTION, Satisfaction.CONFLICT, Satisfaction.WEAKDENIED
        },

        {
            Satisfaction.WEAKDENIED, Satisfaction.SATISFACTION, Satisfaction.CONFLICT, Satisfaction.WEAKSATISFACTION
        },

        {
            Satisfaction.DENIED, Satisfaction.SATISFACTION, Satisfaction.CONFLICT, Satisfaction.NONE
        }
    };

    private Satisfaction combineContributions(Satisfaction column, Satisfaction row) {
        int ridx = this.combineContributionRow(row);
        int cidx = this.combineContributionColumn(column);

        return combine_contributions[ridx][cidx];
    }

    private Satisfaction compareSatisfiedDenied(int nrSatisfied, int nrDenied) {

        if (nrSatisfied > 0 && nrDenied > 0) {
            return Satisfaction.CONFLICT;
        } else if (nrSatisfied > 0 && nrDenied == 0) {
            return Satisfaction.SATISFACTION;
        } else if (nrDenied > 0 && nrSatisfied == 0) {
            return Satisfaction.DENIED;
        } else if (nrSatisfied == 0 && nrDenied == 0) {
            return Satisfaction.NONE;
        } else {
            return Satisfaction.NONE;
        }
    }

      private Satisfaction compareWeaklySatisfiedDenied(int nrSatisfiedW, int nrDeniedW) {

        if (nrSatisfiedW > nrDeniedW) {
            return Satisfaction.WEAKSATISFACTION;
        } else if (nrDeniedW > nrSatisfiedW) {
            return Satisfaction.WEAKDENIED;
        } else if (nrDeniedW == nrSatisfiedW) {
            return Satisfaction.NONE;
        } else {
            return Satisfaction.NONE;
        }
    }

    private int weightedImportanceColumn(Satisfaction s) {
        switch (s) {
        case DENIED:
            return 0;
        case WEAKDENIED:
            return 1;
        case WEAKSATISFACTION:
            return 2;
        case SATISFACTION:
            return 3;
        case CONFLICT:
            return 4;
        case UNKNOWN:
            return 5;
        case NONE:
            return 6;
        default:
            return 6;
        }
    }
    private int weightedImportanceRow(Importance s) {
        switch (s) {
        case HIGH:
            return 0;
        case MEDIUM:
            return 1;
        case LOW:
            return 2;
        case NONE:
            return 3;
        default:
            return 3;
        }
    }

    private static final Satisfaction[][] weighted_importance =
      {
          {
              Satisfaction.DENIED,
              Satisfaction.WEAKDENIED,
              Satisfaction.WEAKSATISFACTION,
              Satisfaction.SATISFACTION,
              Satisfaction.CONFLICT,
              Satisfaction.UNKNOWN,
              Satisfaction.NONE
          },
          {
              Satisfaction.WEAKDENIED,
              Satisfaction.WEAKDENIED,
              Satisfaction.WEAKSATISFACTION,
              Satisfaction.WEAKSATISFACTION,
              Satisfaction.CONFLICT,
              Satisfaction.UNKNOWN,
              Satisfaction.NONE
          },
          {
              Satisfaction.WEAKDENIED,
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.WEAKSATISFACTION,
              Satisfaction.CONFLICT,
              Satisfaction.UNKNOWN,
              Satisfaction.NONE
          },
          {
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.NONE,
              Satisfaction.NONE
          }

      };

    private Satisfaction weightedImportance(Importance row, Satisfaction column) {
        int ridx = this.weightedImportanceRow(row);
        int cidx = this.weightedImportanceColumn(column);

        return weighted_importance[ridx][cidx];
    }
}
