package uk.ac.gla.socs.grl.utility;

import io.atlassian.fugue.Pair;

import java.util.*;
import java.util.Map.Entry;

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
    private Map<Integer, Set<Pair<Integer, Optional<E>>>> core = null;

    /**
     * Keep a record of mappings between node identifiers and node values.
     */
    private Map<Integer,V> legend = null;

    private Integer seed = 0;

    public Graph() {
        this.core = new HashMap<>();
        this.legend = new HashMap<>();
    }

    public Pair<Integer,V> addNode(V node) {
        if (this.legend.containsValue(node)) {
            Maybe<Integer> mID = this.getID(node);

            if (mID instanceof Just) {
                Integer id = ((Just<Integer>) mID).getValue();
                return new Pair<Integer,V>(id,node);
            } else {
                Integer id = this.seed;
                this.legend.put(id,node);
                this.core.put(id, new HashSet<>());
                this.seed += 1;
                return new Pair<Integer,V>(id,node);
            }
        } else {
            Integer id = this.seed;
            this.legend.put(id,node);
            this.core.put(id, new HashSet<>());
            this.seed += 1;
            return new Pair<Integer,V>(id,node);
        }
    }

    public Optional<V> updateNode(Integer id, V n) {
        V old = this.legend.remove(id);
        if (old != null) {
            this.legend.put(id,n);
            return Optional.of(n);
        }

        return Optional.empty();
    }

    public void addEdge(V from, V to) {
        this.addLabelledEdge(from, to, Optional.empty());
    }

    public Maybe<V> getNodeByID(Integer id) {
        V node = this.legend.get(id);

        return (node == null) ? new Nothing<>() : new Just<>(node);

    }
    private Maybe<Integer> getID(V node) {
        for (Entry<Integer,V> kv : this.legend.entrySet()) {
            if (kv.getValue().equals(node)) {
                return new Just<>(kv.getKey());
            }
        }
        return new Nothing<>();
    }

    public void addLabelledEdge(V from, V to, Optional<E> label) {
        Maybe<Integer> mFromID = this.getID(from);
        Maybe<Integer> mToID   = this.getID(to);
        Integer fromID;
        Integer toID;

        Pair<Integer, Optional<E>> edge;
        Set<Pair<Integer, Optional<E>>> edges;

        if (mFromID instanceof Just) {
            fromID = ((Just<Integer>) mFromID).getValue();
            edges = this.core.get(fromID);
        } else {
            Pair<Integer,V> p = this.addNode(from);
            fromID = p.left();
            edges = new HashSet<>();
        }

        if (mToID instanceof Just) {
            toID = ((Just<Integer>) mToID).getValue();
            edge = new Pair<>(toID, label);
        } else {
            Pair<Integer,V> p = this.addNode(to);
            toID = p.left();
            edge = new Pair<>(toID,label);
        }

        edges.add(edge);
        this.core.put(fromID, edges);
    }

    public Set<Edge<Integer,E>> getChildrenID(Integer id) {
        Set<Edge<Integer,E>> children = new HashSet<>();

        Set<Pair<Integer,Optional<E>>> edges = this.core.get(id);

        if (edges != null){
            for (Pair<Integer, Optional<E>> edge : edges) {
                children.add(new Edge<Integer,E>(id,edge.left(),edge.right()));
            }
        }
        return children;

    }

    public Set<Edge<V,E>> getChildren(Integer id) {
        Set<Edge<V,E>> children = new HashSet<>();

        Set<Pair<Integer, Optional<E>>> edges = this.core.get(id);

        if (edges != null) {
            Maybe<V> src = this.getNodeByID(id);
            for (Pair<Integer, Optional<E>> edge : edges) {
                Maybe<V> dest = this.getNodeByID(edge.left());

                if (src instanceof Just) {
                    if (dest instanceof Just) {
                        V src_prime = ((Just<V>) src).getValue();
                        V dst_prime = ((Just<V>) dest).getValue();
                        children.add(new Edge<V,E>( src_prime
                                                  , dst_prime
                                                  , edge.right()));
                    }
                }
            }
        }
        return children;
    }


    /**
     * Get all children of the node.
     *
     * Returns an empty set if node has not children or not an element in the graph.
     */
    public Set<Edge<V,E>> geChildren(V node) {
        Maybe<Integer> mID = this.getID(node);
        if (mID instanceof Just) {
            Integer id = ((Just<Integer>) mID).getValue();
            return this.getChildren(id);

        } else {
            return new HashSet<>();
        }
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
        for (Integer k : this.legend.keySet()) {
            Set<Edge<V,E>> edges = this.getChildren(k);

            if (edges != null) {
                result.addAll(edges);
            }
        }

        return result;

    }


    public Collection<V> getNodes() {
        return this.legend.values();
    }

    public Set<Integer> getNodeIDs() {
        return this.legend.keySet();
    }

}
