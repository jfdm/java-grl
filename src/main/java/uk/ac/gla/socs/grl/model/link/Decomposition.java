package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.ComplexElement;

import java.util.Optional;

public final class Decomposition<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public Decomposition(ComplexElement<I, S> src,
                         ComplexElement<I, S> dest) {
        super(Optional.empty(), LinkKind.decomposition, src, dest);
    }

}
