package uk.ac.gla.socs.grl.model;

import uk.ac.gla.socs.grl.model.link.*;
import uk.ac.gla.socs.grl.model.element.*;
import uk.ac.gla.socs.grl.utility.*;

import java.util.List;
import java.util.Set;
import java.util.Optional;

/**
 * A GRL model instance.
 */
public class GRLSpec<I, S, C> {

    private Graph<Element<I, S>, Link<C>> model;
    private String name;
    private C defaultContribution;
    private I defaultImportance;
    private S defaultSatisfaction;

    public GRLSpec(String name, I defaultImportance, S defaultSatisfaction, C defaultContribution) {
        this.defaultContribution = defaultContribution;
        this.defaultSatisfaction = defaultSatisfaction;
        this.defaultImportance = defaultImportance;
        this.name = name;
        this.model = new Graph<>();
    }

    public String getName() {
        return name;
    }

    public Set<Element<I,S>> getElements() {
        return this.model.getNodes();
    }

    public Set<Edge<Element<I,S>,Link<C>>> getLinks() {
        return this.model.getEdgesFlatten();
    }

    public void addCorrelation(Element<I, S> src, Optional<C> contrib, Element<I, S> dst) {
        Link<C> c = new CorrelationLink<>(src, contrib.orElse(this.defaultContribution), dst);
        this.model.addLabelledEdge(dst, src, Optional.of(c));
    }

    public void addContribution(Element<I, S> src, Optional<C> contrib, Element<I, S> dst) {
        Link<C> c = new ContributionLink<>(src, contrib.orElse(this.defaultContribution), dst);
        this.model.addLabelledEdge(dst, src, Optional.of(c));
    }


    public void addDependency(Element<I, S> src,
                              Element<I, S> dst) {
        this.model.addEdge(dst,src);
    }

    public void addXORDecomp(Element<I, S> src,
                             List<Element<I, S>> ends) {
        this.addDecomposition(DecompositionType.XOR, src, ends);
    }

    public void addIORDecomp(Element<I, S> src,
                             List<Element<I, S>> ends) {
        this.addDecomposition(DecompositionType.IOR, src, ends);
    }

    public void addAndDecomp(Element<I, S> src,
                             List<Element<I, S>> ends) {
        this.addDecomposition(DecompositionType.AND, src, ends);
    }

    private void addDecomposition(DecompositionType type,
                                  Element<I, S> src,
                                  List<Element<I, S>> ends) {

        for (Element<I, S> dst : ends) {

            Link<C> edge = null;
            switch (type) {
                case IOR:
                    edge = new IOR<>(src, dst);
                    break;
                case AND:
                    edge = new AND<>(src, dst);
                    break;
                case XOR:
                    edge = new IOR<>(src, dst);
                    break;
                default:
                    edge = new AND<>(src, dst);
                    break;
            }
            this.model.addLabelledEdge(src, dst, Optional.of(edge));
        }
    }

    public Element<I, S> addElement(Element<I, S> node) {
        if (node instanceof Actor) {
            Actor<I,S> a = (Actor) node;
            for (IntentionalElement<I,S> e : a.getElements()) {
                this.model.addNode(e);
            }
        }
        return this.model.addNode(node);
    }

    public void addElements(List<Element<I,S>> nodes) {
        for (Element<I,S> n : nodes) {
            this.model.addNode(n);
        }
    }

    public void addElements(Element<I,S>[] nodes) {
        for (Element<I,S> n : nodes) {
            this.model.addNode(n);
        }

    }

    public Actor<I, S> mkDefaultActor(String title) {
        return (Actor<I, S>) this.addElement(new Actor<>(this.defaultSatisfaction, this.defaultImportance, title));

    }

    public Belief<I, S> mkDefaultBelief(String title) {
        return (Belief<I, S>) this.addElement(new Belief<>(this.defaultSatisfaction, this.defaultImportance, title));

    }


    public Goal<I, S> mkDefaultGoalString(String title) {
        return (Goal<I, S>) this.addElement(new Goal<>(this.defaultSatisfaction, this.defaultImportance, title));

    }

    public SoftGoal<I, S> mkDefaultSoftGoal(String title) {
        return (SoftGoal<I, S>) this.addElement(new SoftGoal<>(this.defaultSatisfaction, this.defaultImportance, title));
    }

    public Resource<I, S> mkDefaultResource(String title) {
        return (Resource<I, S>) this.addElement(new Resource<>(this.defaultSatisfaction, this.defaultImportance, title));
    }
    public Task<I, S> mkDefaultTask(String title) {
        return (Task<I, S>) this.addElement(new Task<>(this.defaultSatisfaction, this.defaultImportance, title));
    }

    public List<GRLElementEvalResult<I, S>> evaluate(GRLEvaluationStrategy<I, S, C> strategy) {
        return strategy.evaluate(this.model);
    }
}
