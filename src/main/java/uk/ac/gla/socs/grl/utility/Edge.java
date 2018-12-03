package uk.ac.gla.socs.grl.utility;

import java.util.Optional;

/** A Simple data structure to represent an edge in a graph.
 *
 * @author Jan de Muijnck-Hughes
 * @param <V> The type for each vertex.
 * @param <E> The type for each edge.
 */
public class Edge<V, E> {

    private V src;
    private V dst;
    private Optional<E> label;

    public Edge(V src, V dst, Optional<E> label) {
        this.src = src;
        this.dst = dst;
        this.label = label;
    }

    /**
     * Return the vertex in the source position.
     */
    public V getSource(){ return this.src; }
    public V getDestination() { return this.dst; }
    public Optional<E> getLabel() {return this.label;}
    public E getLabelValue(E def) { return this.label.orElse(def); }
    public boolean hasLabel() { return this.label.isPresent(); }

}
