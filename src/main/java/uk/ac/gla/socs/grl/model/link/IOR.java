package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.DecompositionType;
import uk.ac.gla.socs.grl.model.element.Element;

/**
 * The satisfaction of one of the sub-intentional elements is
sufficient to achieve the target, but many sub-intentional elements
can be satisfied.
*/
public class IOR<I, S, C> extends Decomposition<I, S, C> implements Link<C> {

    public IOR(Element<I, S> src, Element<I, S> dest) {
        super(DecompositionType.IOR, src, dest);
    }
}
