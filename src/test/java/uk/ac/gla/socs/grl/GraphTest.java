package uk.ac.gla.socs.grl;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import io.atlassian.fugue.Pair;
import uk.ac.gla.socs.grl.utility.Graph;
import uk.ac.gla.socs.grl.utility.Edge;
import uk.ac.gla.socs.grl.utility.GraphFactory;

/**
 * @author Jan de Muijnck-Hughes
 */
public class GraphTest {
    @Test
    public void testGraphConstruction() {
        List<Integer> nodes = new LinkedList<>();
        nodes.add(1);
        nodes.add(2);
        nodes.add(3);
        nodes.add(4);

        Graph<Integer,String> g = new Graph<>();
        g.addEdge(1,2);
        g.addEdge(3,4);
        g.addEdge(4,1);

        for (Edge<Integer,String> edge : g.getEdgesFlatten()){
            assertTrue(nodes.contains(edge.getSource()));
            assertTrue(nodes.contains(edge.getDestination()));
        }
        for (Integer i : g.getNodes()) {
            assertTrue(nodes.contains(i));
        }

    }
}
