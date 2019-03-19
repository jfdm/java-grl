package uk.ac.gla.socs.grl.model.element;

import java.util.Objects;
import java.util.Optional;

/**
 * A Task specifies a particular way of doing something. When a task
 * is part of the decomposition of a (higher-level) task, this
 * restricts the higher-level task to that particular course of
 * action. Tasks can also be seen as the solutions in the target
 * system, which will address (or operationalize) goals and
 * softgoals. These solutions provide operations, processes, data
 * representations, structuring, constraints and agents in the target
 * system to meet the needs stated in the goals and softgoals.
 *
 */
public class Task<I, S> extends AbstractElement<I, S> implements ContributingElement<I,S>,
                                                                 ComplexElement<I,S>,
                                                                 IntentionalElement<I,S>,
                                                                 DependableElement<I,S>
{
    public Task(I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.empty(), importance, title);
    }
    public Task(S satisfaction, I importance, String title) {
        super(Optional.of(DecompositionType.AND), Optional.of(satisfaction), importance, title);
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
