package uk.ac.gla.socs.grl.evaluation;

import uk.ac.gla.socs.grl.model.link.*;
import uk.ac.gla.socs.grl.model.element.Element;
import uk.ac.gla.socs.grl.model.element.Actor;
import uk.ac.gla.socs.grl.utility.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Queue;
import java.util.HashSet;

import java.util.stream.*;

public abstract class AbstractEvaluationStrategy<I, S, C> {

    protected abstract S calculateDecompositions(Element<I,S> node, List<Element<I,S>> decomps);
    protected abstract S calculateContributions(Element<I,S> node, List<Edge<Element<I,S>,Link<C>>> contribs, S s);
    protected abstract S calculateDependencies(Element<I,S> node, List<Element<I,S>> deps, S s);
    protected abstract S calculateActor(List<Element<I,S>> enclosed);

    private S calculateSatisfaction(Element<I,S> node,
                                    Set<Edge<Element<I,S>,Link<C>>> children,
                                    Graph<Element<I, S>, Link<C>> model) {

        List<Element<I,S>> decomps = new LinkedList<>();
        List<Edge<Element<I,S>,Link<C>>> contribs = new LinkedList<>();
        List<Edge<Element<I,S>,Link<C>>> correls  = new LinkedList<>();
        List<Element<I,S>> deps = new LinkedList<>();
        List<Element<I,S>> enclosed = new LinkedList<>();

        for (Edge<Element<I,S>,Link<C>> edge : children) {
            Element<I,S> c = edge.getDestination();

            Maybe<Link<C>> mlink = FromMaybe.optional(edge.getLabel());

            if (mlink instanceof Just) {
                Link<C> link = ((Just<Link<C>>) mlink).getValue();

                if (link instanceof ContributionLink) {
                    contribs.add(edge);
                } else if (link instanceof CorrelationLink) {
                    correls.add(edge);
                }else if (link instanceof Decomposition) {
                    decomps.add(c);
                } else if (link instanceof Dependency) {
                    deps.add(c);
                } else if (link instanceof Enclosed) {
                    enclosed.add(c);
                } else { }
            }
        }

        if (node instanceof Actor) {
            return this.calculateActor(enclosed);
        } else {
            S sdecs = this.calculateDecompositions(node, decomps);
            correls.addAll(contribs);
            S scors = this.calculateContributions(node, correls,sdecs);
            S sdeps = this.calculateDependencies(node, deps,scors);
            return sdeps;
        }
    }


    /**
     * Evaluate the given model.
     */
    public List<GRLElementEvalResult<I, S>> evaluate(Graph<Element<I, S>, Link<C>> model) {

        List <GRLElementEvalResult<I,S>> results = new LinkedList<>();

        Queue<Integer> nodes = new LinkedList<>();
        nodes.addAll(model.getNodeIDs());

        do {
            Integer id = nodes.poll();
            if (id != null) {
                Maybe<Element<I,S>> eprime = model.getNodeByID(id);

                if (eprime instanceof Just) {

                    Element<I,S> e = ((Just<Element<I,S>>) eprime).getValue();
                    Maybe<S> ms = FromMaybe.optional(e.getSatisfaction());

                    if (ms instanceof Just) {

                        results.add(new GRLElementEvalResult<I,S>(e,ms,ms));

                    } else {
                        Set<Edge<Element<I,S>,Link<C>>> children = model.getChildren(id);
                        if (children.isEmpty()) {
                            results.add(new GRLElementEvalResult<I,S>(e));

                        } else {
                            boolean allSatisfied = true;
                            for (Edge<Element<I,S>,Link<C>> edge : children) {

                                Maybe<S> me = FromMaybe.optional(edge.getDestination().getSatisfaction());
                                if (me instanceof Just) {
                                    S sa = ((Just<S>) me).getValue();
                                    allSatisfied = allSatisfied && true;
                                } else {
                                    allSatisfied = allSatisfied && false;
                                }

                            }
                            if (allSatisfied) {
                                S s = this.calculateSatisfaction(e,children,model);
                                e.setSatisfaction(s);
                                // update graph
                                results.add(new GRLElementEvalResult<I,S>(e,ms, new Just<S>(s)));
                                model.updateNode(id,e);
                            } else {
                                // add to the end of the queue
                                nodes.add(id);
                            }
                        }
                    }
                }
            }
        } while (nodes.size() > 0);

        return results;
    };

}
