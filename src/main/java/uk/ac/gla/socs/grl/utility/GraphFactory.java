package uk.ac.gla.socs.grl.utility;

import java.util.Set;

/**
 * Construct a graph using these workers.
 */
public final class GraphFactory {

    /**
     * from a given edge list.
     */
    public static <V,E> Graph<V,E> fromEdgeList(Set<Edge<V,E>> edges) {
        Graph<V,E> graph = new Graph<>();
        for (Edge<V,E> edge : edges) {
            graph.addLabelledEdge(edge.getSource(),
                                  edge.getDestination(),
                                  edge.getLabel());
        }
        return graph;
    }
}
