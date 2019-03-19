package uk.ac.gla.socs.grl.model.element;

import java.util.Objects;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;

/**
 *
 */
public class Actor<I, S> extends AbstractElement<I, S> implements DependableElement<I,S>,
                                                                  Element<I, S>
{

    private Set<IntentionalElement<I,S>> enclosedElements;

    public Actor(I importance, String title) {
        super(Optional.empty(), Optional.empty(), importance, title);
        this.enclosedElements = new HashSet<>();
    }

    public boolean addElement(IntentionalElement<I,S> element) {
        return this.enclosedElements.add(element);
    }

    public boolean addElements(Set<IntentionalElement<I,S>> elements) {
        return this.enclosedElements.addAll(elements);
    }
    public Set<IntentionalElement<I,S>> getElements() {
        return this.enclosedElements;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }

        if (!super.equals(o)) {
            return false;
        }
        if (o instanceof Actor) {
            Actor a = (Actor) o;
            return Objects.equals(this.enclosedElements, a.getElements());

        } else return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(enclosedElements);
    }
}
