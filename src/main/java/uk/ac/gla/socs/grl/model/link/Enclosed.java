package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

/**
 * A cheat to model internally enclosed elements within an actor.
 */
public final class Enclosed<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public Enclosed(Element<I, S> src, Element<I, S> dest) {
        super(Optional.empty(), LinkKind.enclosure, src, dest);
    }
}
