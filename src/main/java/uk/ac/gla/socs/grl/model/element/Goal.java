package uk.ac.gla.socs.grl.model.element;

/**
 * A (hard) Goal is a condition or state of affairs in the world that
 * the stakeholders would like to achieve. How the goal is to be
 * achieved is not specified, allowing alternatives to be
 * considered. A goal can be either a business goal or a system
 * goal. A business goal expresses goals regarding the business or
 * state of the business affairs the individual or organization wishes
 * to achieve. A system goal expresses goals the target system should
 * achieve and generally describes the functional requirements of the
 * target information system.
 */
public class Goal<I, S> extends AbstractElement<I, S> implements IntentionalElement<I,S> {

    public Goal(S satisfaction, I importance, String title) {
        super(DecompositionType.AND, satisfaction, importance, title);
    }
}
