package uk.ac.gla.socs.grl.model;

import uk.ac.gla.socs.grl.model.link.*;
import uk.ac.gla.socs.grl.model.element.*;
import uk.ac.gla.socs.grl.utility.*;
import uk.ac.gla.socs.grl.evaluation.*;

import io.atlassian.fugue.Pair;

import java.util.Collection;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.Optional;
import java.util.Queue;
import java.util.HashSet;


/**
 * A GRL model instance.
 */
public class GRLSpec<I, S, C> {

    private Graph<Element<I, S>, Link<C>> model;
    private String name;
    private I defaultImportance;

    public GRLSpec(String name, I defaultImportance) {
        this.defaultImportance = defaultImportance;

        this.name = name;
        this.model = new Graph<>();
    }

    public String getName() {
        return name;
    }

    public Collection<Element<I,S>> getElements() {
        return this.model.getNodes();
    }

    public Set<Edge<Element<I,S>,Link<C>>> getLinks() {
        return this.model.getEdgesFlatten();
    }

    public void addCorrelation(IntentionalElement<I, S> src,
                               C contrib,
                               ContributingElement<I, S> dst) {
        Link<C> c = new CorrelationLink<I,S,C>(src, contrib, dst);
        this.model.addLabelledEdge(dst, src, Optional.of(c));
    }

    public void addContribution(IntentionalElement<I, S> src,
                                C contrib,
                                ContributingElement<I, S> dst) {
        Link<C> c = new ContributionLink<I,S,C>(src, contrib, dst);
        this.model.addLabelledEdge(dst, src, Optional.of(c));
    }


    public void addDependency(DependableElement<I, S> src,
                              DependableElement<I, S> dst) {
        Link<C> c = new Dependency<I,S,C>(src,dst);
        this.model.addLabelledEdge(src,dst,Optional.of(c));
    }

    public void addDecomposition(ComplexElement<I, S> src,
                                 List<ComplexElement<I, S>> ends) {

        for (ComplexElement<I, S> dst : ends) {

            Link<C> edge = new Decomposition<I,S,C>(src, dst);

            this.model.addLabelledEdge(src, dst, Optional.of(edge));
        }
    }

    public Pair<Integer,Element<I, S>> addElement(Element<I, S> node) {
        if (node instanceof Actor) {
            Actor<I,S> a = (Actor<I,S>) node;
            for (IntentionalElement<I,S> e : a.getElements()) {
                this.model.addNode(e);
                this.model.addLabelledEdge(node, e, Optional.of(new Enclosed<I,S,C>(node,e)));
            }

        }
        return this.model.addNode(node);
    }

    public void addElements(List<Element<I,S>> nodes) {
        for (Element<I,S> n : nodes) {
            this.addElement(n);
        }
    }

    public void addElements(Element<I,S>[] nodes) {
        for (Element<I,S> n : nodes) {
            this.addElement(n);
        }

    }

    public Actor<I, S> mkDefaultActor(String title) {
        return new Actor<>(this.defaultImportance, title);

    }

    public Belief<I, S> mkDefaultBelief(String title) {
        return new Belief<>(this.defaultImportance, title);

    }


    public Goal<I, S> mkDefaultGoal(String title) {
        return new Goal<>(this.defaultImportance, title);

    }

    public SoftGoal<I, S> mkDefaultSoftGoal(String title) {
        return new SoftGoal<>(this.defaultImportance, title);
    }

    public Resource<I, S> mkDefaultResource(String title) {
        return new Resource<>(this.defaultImportance, title);
    }
    public Task<I, S> mkDefaultTask(String title) {
        return new Task<>(this.defaultImportance, title);
    }

    public List<GRLElementEvalResult<I, S>> evaluate(AbstractEvaluationStrategy<I, S, C> strategy) {
        return this.canEvaluate() ? strategy.evaluate(this.model) : (new LinkedList<>());
    }

    private boolean canEvaluate() {
        Queue<Integer> nodes = new LinkedList<>();
        nodes.addAll(model.getNodeIDs());

        boolean leaf_nodes_satisfied = true;
        for (Integer node : nodes) {
            Maybe<Element<I,S>> eprime = this.model.getNodeByID(node);
            Set<Edge<Element<I,S>,Link<C>>> children = this.model.getChildren(node);

            if (children.isEmpty() && eprime instanceof Just) {
                // We are leaf
                Element<I,S> e = ((Just<Element<I,S>>) eprime).getValue();
                leaf_nodes_satisfied = leaf_nodes_satisfied && e.getSatisfaction().isPresent();
            }
        }
        return leaf_nodes_satisfied;
    }
}
