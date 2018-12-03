package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

/** A correlation link is the same as a contribution link except that
the correlation is not an explicit desire, but is a side-effect and
that correlations are only used with intentional elements and not with
indicators.
*/
public class CorrelationLink<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public CorrelationLink(Element<I, S> src, C contribution, Element<I, S> dest) {
        super(Optional.of(contribution), LinkKind.correlation, src, dest);
    }
}
