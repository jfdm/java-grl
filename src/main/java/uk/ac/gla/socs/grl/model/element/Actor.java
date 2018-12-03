package uk.ac.gla.socs.grl.model.element;

import java.util.Set;
import java.util.HashSet;

/**
 *
 */
public class Actor<I, S> extends AbstractElement<I, S> implements Element<I, S> {

    private Set<IntentionalElement<I,S>> enclosedElements;

    public Actor(S satisfaction, I importance, String title) {
        super(DecompositionType.AND, satisfaction, importance, title);
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
}
