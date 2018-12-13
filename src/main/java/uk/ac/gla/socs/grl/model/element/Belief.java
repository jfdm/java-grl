package uk.ac.gla.socs.grl.model.element;

import java.util.Optional;

/**
 * A Belief is used to represent design rationale. Beliefs make it
 * possible for domain characteristics to be considered and properly
 * reflected in the decision-making process, hence facilitating later
 * review, justification and change of the system, as well as
 * enhancing traceability.
 *
 */
public class Belief<I, S> extends AbstractElement<I, S> implements IntentionalElement<I,S> {

    public Belief(I importance, S satisfaction, String title) {
        super(Optional.empty(), Optional.of(satisfaction), importance, title);
    }

    public Belief(I importance, String title) {
        super(Optional.empty(), Optional.empty(), importance, title);
    }
}
