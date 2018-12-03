package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

/**
 * Dependencies enable reasoning about how element definitions depend on
 * each other to achieve their goals. The satisfaction level of the
 * depender may be limited by the ability of the dependee to provide
 * the dependum to the depender.
 *
 */
public class Dependency<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public Dependency(Element<I, S> src, Element<I, S> dest) {
        super(Optional.empty(), LinkKind.dependency, src, dest);
    }

}
