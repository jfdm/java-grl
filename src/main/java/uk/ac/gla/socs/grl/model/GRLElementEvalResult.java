package uk.ac.gla.socs.grl.model;

import uk.ac.gla.socs.grl.model.element.Element;

import java.util.Optional;

/**
 * Simple container to represent a node, it's value pre evaluation, and final evaluation results.
 *
 * @param <I>
 * @param <S>
 */
public class GRLElementEvalResult<I, S> {

    private Element<I, S> node;
    private Optional<S> original;
    private S result;

    public GRLElementEvalResult(Element<I, S> node, Optional<S> original, S result) {
        this.node = node;
        this.original = original;
        this.result = result;
    }

    public Element<I, S> getElement() {
        return node;
    }

    public Optional<S> getOriginal() {
        return this.original;
    }

    public S getResult() {
        return result;
    }
}
