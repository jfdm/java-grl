package uk.ac.gla.socs.grl.value.qualitative;

import uk.ac.gla.socs.grl.utility.FromMaybe;
import uk.ac.gla.socs.grl.utility.Maybe;
import uk.ac.gla.socs.grl.utility.Just;
import uk.ac.gla.socs.grl.utility.Nothing;

public final class ContributionCounter {
    private Integer nrSatisfied;
    private Integer nrSatisfiedWeakly;
    private Integer nrDeniedWeakly;
    private Integer nrDenied;
    private Integer nrUnknown;

    public ContributionCounter() {
       this.nrSatisfied = 0;
       this.nrSatisfiedWeakly = 0;
       this.nrDeniedWeakly = 0;
       this.nrDenied = 0;
       this.nrUnknown = 0;
    }

    public Integer getSatisfied () {return this.nrSatisfied; }
    public Integer getSatisfiedWeakly () {return this.nrSatisfiedWeakly; }
    public Integer getDenied () {return this.nrDenied; }
    public Integer getDeniedWeakly () {return this.nrDeniedWeakly; }
    public Integer getUnknown () {return this.nrUnknown; }

    public void adjustCount(Maybe<Satisfaction> ms) {
        if (ms instanceof Just) {
            Satisfaction s = ((Just<Satisfaction>) ms).getValue();
            this.adjustCount(s);
        }
    }

    public void adjustCount(Satisfaction s) {
        switch (s) {
        case SATISFACTION:
            this.nrSatisfied++;
            break;
        case DENIED:
            this.nrDenied++;
            break;
        case WEAKSATISFACTION:
            this.nrSatisfiedWeakly++;
            break;
        case WEAKDENIED:
            this.nrDeniedWeakly++;
            break;
        case UNKNOWN:
            this.nrUnknown++;
            break;
        default:
            break;
        }
    }
}
