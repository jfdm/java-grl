package uk.ac.gla.socs.grl.model.link;


import uk.ac.gla.socs.grl.model.link.Link;
import uk.ac.gla.socs.grl.model.link.LinkKind;
import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

public abstract class AbstractLink<I, S, C> implements Link<C> {

    private Optional<C> contribution;

    private LinkKind kind;
    private Element<I, S> src;
    private Element<I, S> dest;

    public AbstractLink(Optional<C> contribution, LinkKind kind, Element<I, S> src, Element<I, S> dest) {
        this.contribution = contribution;
        this.kind = kind;
        this.src = src;
        this.dest = dest;
    }

    public LinkKind getLinkKind() {
        return kind;
    }

    @Override
    public Optional<C> getContribution() {
        return this.contribution;
    }

    public Element<I, S> getSrc() {
        return this.src;
    }

    public Element<I, S> getDest() {
        return this.dest;
    }
}
