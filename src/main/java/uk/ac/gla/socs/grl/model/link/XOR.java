package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.DecompositionType;
import uk.ac.gla.socs.grl.model.element.Element;

/**
 * The satisfaction of one and only one of the sub-intentional
elements is necessary to achieve the target.
*/
public class XOR<I, S, C> extends Decomposition<I, S, C> implements Link<C> {

    public XOR(Element<I, S> src, Element<I, S> dest) {
        super(DecompositionType.XOR, src, dest);
    }
}
