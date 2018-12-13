package uk.ac.gla.socs.grl.model.link;

import java.util.Optional;
import uk.ac.gla.socs.grl.model.element.*;

/**
 * A ContributionLink link describes how a source intentional element
 * or source indicator contributes to the satisfaction of a
 * destination intentional element. A contribution is an effect that
 * is a primary desire during modelling, whereas a correlation
 * expresses knowllink about interactions between intentional elements
 * in different categories.
*/
public final class ContributionLink<I, S, C> extends AbstractLink<I, S, C> implements Link<C> {

    public ContributionLink(IntentionalElement<I, S> src,
                            C contribution,
                            ContributingElement<I, S> dest) {
        super(Optional.of(contribution), LinkKind.contribution, src, dest);
    }
}
