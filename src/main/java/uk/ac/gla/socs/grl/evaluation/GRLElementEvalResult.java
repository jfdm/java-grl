package uk.ac.gla.socs.grl.evaluation;

import uk.ac.gla.socs.grl.model.element.Element;
import uk.ac.gla.socs.grl.utility.FromMaybe;
import uk.ac.gla.socs.grl.utility.Maybe;
import uk.ac.gla.socs.grl.utility.Nothing;

import java.util.Optional;

/**
 * Simple container to represent a node, it's value pre evaluation, and final evaluation results.
 *
 * @param <I>
 * @param <S>
 */
public class GRLElementEvalResult<I, S> {

    private Element<I, S> node;
    private Maybe<S> original;
    private Maybe<S> result;

    public GRLElementEvalResult(Element<I, S> node) {
        this(node, new Nothing<S> (), new Nothing<S> ());
    }

    public GRLElementEvalResult(Element<I, S> node, Optional<S> original) {
        this(node,FromMaybe.optional(original), new Nothing<S> ());
    }


    public GRLElementEvalResult(Element<I, S> node, Optional<S> original, Optional<S> result) {
        this(node,FromMaybe.optional(original), FromMaybe.optional(result));
    }


    public GRLElementEvalResult(Element<I, S> node, Maybe<S> original, Maybe<S> result) {
        this.node = node;
        this.original = original;
        this.result = result;
    }

    public Element<I, S> getElement() {
        return node;
    }

    public Maybe<S> getOriginal() {
        return this.original;
    }

    public Maybe<S> getResult() {
        return result;
    }
}
