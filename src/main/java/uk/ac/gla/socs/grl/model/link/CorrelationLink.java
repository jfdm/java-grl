package uk.ac.gla.socs.grl.model.link;

import uk.ac.gla.socs.grl.model.element.*;

import java.util.Optional;

/** A correlation link is the same as a contribution link except that
the correlation is not an explicit desire, but is a side-effect and
that correlations are only used with intentional elements and not with
indicators.
*/
public final class CorrelationLink<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public CorrelationLink(IntentionalElement<I, S> src,
                           C contribution,
                           ContributingElement<I, S> dest) {
        super(Optional.of(contribution), LinkKind.correlation, src, dest);
    }
}
