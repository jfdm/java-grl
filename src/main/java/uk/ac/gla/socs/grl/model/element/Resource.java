package uk.ac.gla.socs.grl.model.element;

import java.util.Optional;

/**
 * A Resource is a physical or informational entity, for which the
 * main concern is whether it is available.
*/
public class Resource<I, S> extends AbstractElement<I, S> implements ComplexElement<I,S>,
                                                                     IntentionalElement<I,S>,
                                                                     DependableElement<I,S>
{

    public Resource(I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.empty(), importance, title);
    }
    public Resource(S satisfaction, I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.of(satisfaction), importance, title);
    }
}
