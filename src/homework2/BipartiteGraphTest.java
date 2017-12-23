package homework2;

import static org.junit.Assert.*;
import org.junit.Test;


/**
 * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
 */
public class BipartiteGraphTest {

	@Test
    public void testExample() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");
        
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n2");
        driver.addEdge("graph1", "n1", "n2", "edge");
        
        //check neighbors
        assertEquals("wrong black nodes", "n1", driver.listBlackNodes("graph1"));
        assertEquals("wrong white nodes", "n2", driver.listWhiteNodes("graph1"));
        assertEquals("wrong children", "n2", driver.listChildren ("graph1", "n1"));
        assertEquals("wrong children", "", driver.listChildren ("graph1", "n2"));
        assertEquals("wrong parents", "", driver.listParents ("graph1", "n1"));
        assertEquals("wrong parents", "n1", driver.listParents ("graph1", "n2"));
    }

    @Test
    public void checkNullInput() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", null);
        assertEquals("Error null node", "", driver.listBlackNodes("graph1"));
        driver.addWhiteNode("graph1", null);
        assertEquals("Error null node", "", driver.listWhiteNodes("graph1"));

        driver.addBlackNode("graph1", "b1");
        driver.addWhiteNode("graph1", "w1");
        driver.addEdge("graph1", "b1", "w1", null);
        driver.addEdge("graph1", null, "w1", "e1");
        driver.addEdge("graph1", "b1", null, "e1");
        assertEquals("eror add null edge", "", driver.listChildren("graph1", "b1"));
        assertEquals("eror add null edge", "", driver.listParents("graph1", "w1"));
    }

    @Test
    public void checkAddEdgeToSameColor() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "b1");
        driver.addBlackNode("graph1", "b2");
        driver.addWhiteNode("graph1", "w1");
        driver.addWhiteNode("graph1", "w2");

        driver.addEdge("graph1", "b1", "b2", "e1");
        assertEquals("eror edge to same color", "", driver.listChildren("graph1", "b1"));
        assertEquals("eror edge to same color", "", driver.listParents("graph1", "b2"));

        driver.addEdge("graph1", "w1", "w2", "e1");
        assertEquals("eror edge to same color", "", driver.listChildren("graph1", "w1"));
        assertEquals("eror edge to same color", "", driver.listParents("graph1", "w2"));
    }

    @Test
    public void checkAddNodeSameLabel() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "n1");
        driver.addBlackNode("graph1", "n1");
        driver.addWhiteNode("graph1", "n1");
        assertEquals("eror add node with same label", "n1", driver.listBlackNodes("graph1"));
        assertEquals("eror add node with same label", "", driver.listWhiteNodes("graph1"));

        driver.addWhiteNode("graph1", "n2");
        driver.addWhiteNode("graph1", "n2");
        driver.addBlackNode("graph1", "n2");
        assertEquals("eror add node with same label", "n1", driver.listBlackNodes("graph1"));
        assertEquals("eror add node with same label", "n2", driver.listWhiteNodes("graph1"));
    }

    @Test
    public void checkAddEdgeToMissingNode() {
        BipartiteGraphTestDriver driver = new BipartiteGraphTestDriver();
        driver.createGraph("graph1");

        driver.addBlackNode("graph1", "b1");
        driver.addWhiteNode("graph1", "w1");
        driver.addEdge("graph1", "b1", "w2", "e1");
        driver.addEdge("graph1", "b2", "w1", "e1");
        assertEquals("eror add edge to missing node", "", driver.listChildren("graph1", "b1"));
        assertEquals("eror add edge to missing node", "", driver.listParents("graph1", "w1"));
    }
}
