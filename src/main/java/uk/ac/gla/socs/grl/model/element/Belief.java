package uk.ac.gla.socs.grl.model.element;

/**
 * A Belief is used to represent design rationale. Beliefs make it
 * possible for domain characteristics to be considered and properly
 * reflected in the decision-making process, hence facilitating later
 * review, justification and change of the system, as well as
 * enhancing traceability.
 *
 */
public class Belief<I, S> extends AbstractElement<I, S> implements IntentionalElement<I,S> {

    public Belief(S satisfaction, I importance, String title) {
        super(DecompositionType.AND, satisfaction, importance, title);
    }
}
