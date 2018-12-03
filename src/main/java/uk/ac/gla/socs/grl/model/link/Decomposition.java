package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.DecompositionType;
import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

public abstract class Decomposition<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    private DecompositionType type;

    public Decomposition(DecompositionType type, Element<I, S> src, Element<I, S> dest) {
        super(Optional.empty(), LinkKind.decomposition, src, dest);
        this.type = type;
    }

    public DecompositionType getDecompositionType() {
        return type;
    }
}
