package uk.ac.gla.socs.grl.model;

import uk.ac.gla.socs.grl.model.link.Link;
import uk.ac.gla.socs.grl.model.element.Element;
import uk.ac.gla.socs.grl.utility.Graph;

import java.util.LinkedList;
import java.util.List;

public class GRLEvaluationStrategy<I, S, C> {

    public List<GRLElementEvalResult<I, S>> evaluate(Graph<Element<I, S>, Link<C>> model) {
        return new LinkedList<>();
    }
}
