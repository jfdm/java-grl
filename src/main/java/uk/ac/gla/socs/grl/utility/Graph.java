package uk.ac.gla.socs.grl.utility;

import io.atlassian.fugue.Pair;

import java.util.*;

/** A Simple directed graph implementation.
 *
 * It is sometimes better to model a graph ourselves, than rely on a
 * third party module.
 *
 * @author Jan de Muijnck-Hughes
 * @param <V> The type for each vertex.
 * @param <E> The type for each edge.
 */
public class Graph<V, E> {

    /**
     * Use an adjacency list representation, mapping source vertex to multiple destination vertices.
     */
    private Map<V, Set<Pair<V, Optional<E>>>> core = null;

    public Graph() {
        this.core = new HashMap<>();
    }

    public V addNode(V node) {
        if (this.core.containsKey(node)) {
            return node;
        } else {
            this.core.put(node, new HashSet<>());
            return node;
        }
    }


    public void addEdge(V from, V to) {
        this.addLabelledEdge(from, to, Optional.empty());
    }

    public void addLabelledEdge(V from, V to, Optional<E> label) {
        Pair<V, Optional<E>> edge = new Pair<>(to, label);
        Set<Pair<V, Optional<E>>> edges = this.core.get(from);

        if (edges == null) {
            edges = new HashSet<>();
        }

        edges.add(edge);
        this.core.put(from, edges);
        this.addNode(to);

    }

    /**
     * Return a a list of all edges in the graph.
     *
     * The list returned is a pair of the src and dst vertices and a
     * possible label.
     *
     * @return
     */
    public Set<Edge<V,E>> getEdgesFlatten() {
        Set<Edge<V, E>> result = new HashSet<>();
        for (V node : this.core.keySet()) {
            Set<Pair<V, Optional<E>>> edges = this.core.get(node);

            if (edges != null) {

                for (Pair<V, Optional<E>> edge : edges) {
                    result.add(new Edge (node, edge.left(), edge.right()));
                }
            }
        }

        return result;

    }


    public Set<V> getNodes() {
        return this.core.keySet();
    }

}
