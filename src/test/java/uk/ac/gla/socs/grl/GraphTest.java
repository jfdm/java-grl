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

        assertTrue(g.getNodes().size() == 4);

        for (Edge<Integer,String> edge : g.getEdgesFlatten()){
            assertTrue(nodes.contains(edge.getSource()));
            assertTrue(nodes.contains(edge.getDestination()));
        }
        for (Integer i : g.getNodes()) {
            assertTrue(nodes.contains(i));
        }

    }

   @Test
    public void testGraphConstruction1() {
        List<String> nodes = new LinkedList<>();
        nodes.add("1");
        nodes.add("2");
        nodes.add("3");
        nodes.add("4");

        Graph<String,String> g = new Graph<>();
        g.addEdge("1","2");
        g.addEdge("3","4");
        g.addEdge("4","1");

        assertTrue(g.getNodes().size() == 4);

        for (Edge<String,String> edge : g.getEdgesFlatten()){
            assertTrue(nodes.contains(edge.getSource()));
            assertTrue(nodes.contains(edge.getDestination()));
        }
        for (String i : g.getNodes()) {
            assertTrue(nodes.contains(i));
        }

    }

    @Test
    public void testGraphConstruction2() {
        List<Person> nodes = new LinkedList<>();
        nodes.add(new Person("1",1));
        nodes.add(new Person("2",2));
        nodes.add(new Person("3",3));
        nodes.add(new Person("4",4));

        Graph<Person,String> g = new Graph<>();
        g.addEdge(new Person ("1", 1), new Person ("2", 2));
        g.addEdge(new Person ("3", 3), new Person ("3", 3));
        g.addEdge(new Person ("4", 4), new Person ("1", 1));

        assertTrue(g.getNodes().size() == 4);

        for (Edge<Person,String> edge : g.getEdgesFlatten()){
            assertTrue(nodes.contains(edge.getSource()));
            assertTrue(nodes.contains(edge.getDestination()));
        }
        for (Person i : g.getNodes()) {
            assertTrue(nodes.contains(i));
        }

    }

    class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() { return this.name; }
        public int getAge() { return this.age; }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Person)) {
                return false;
            }
            Person p = (Person) o;
            return this.age == p.age
                && Objects.equals(this.name, p.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name,age);
        }
    }
}
