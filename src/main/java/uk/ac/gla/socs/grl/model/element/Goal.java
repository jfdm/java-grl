package uk.ac.gla.socs.grl.model.element;

import java.util.Objects;
import java.util.Optional;

/**
 * A (hard) Goal is a condition or state of affairs in the world that
 * the stakeholders would like to achieve. How the goal is to be
 * achieved is not specified, allowing alternatives to be
 * considered. A goal can be either a business goal or a system
 * goal. A business goal expresses goals regarding the business or
 * state of the business affairs the individual or organization wishes
 * to achieve. A system goal expresses goals the target system should
 * achieve and generally describes the functional requirements of the
 * target information system.
 */
public class Goal<I, S> extends AbstractElement<I, S> implements ContributingElement<I,S>,
                                                                 ComplexElement<I,S>,
                                                                 DependableElement<I,S>,
                                                                 IntentionalElement<I,S>
{

    public Goal(S satisfaction, I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.of(satisfaction), importance, title);
    }

    public Goal(I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.empty(), importance, title);
    }

        @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
