package uk.ac.gla.socs.grl.model.element;


/**
 * A Resource is a physical or informational entity, for which the
 * main concern is whether it is available.
*/
public class Resource<I, S> extends AbstractElement<I, S> implements IntentionalElement<I,S> {

    public Resource(S satisfaction, I importance, String title) {
        super(DecompositionType.AND, satisfaction, importance, title);
    }
}
