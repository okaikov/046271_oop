package homework2;

import static org.junit.Assert.*;
import org.junit.Test;

public class SimulatorTest {
    /**
     * BipartiteGraphTest contains JUnit block-box unit tests for BipartiteGraph.
     */
    @Test
    public void testExample() {
        SimulatorTestDriver driver = new SimulatorTestDriver();
        driver.createSimulator("sim1");
        driver.addParticipant("sim1", "p1", 1.0);
        driver.addParticipant("sim1", "p2", 1.0);
        driver.addParticipant("sim1", "p3", 1.0);
        driver.addParticipant("sim1", "p4", 1.0);
        driver.addParticipant("sim1", "p5", 1.0);
        driver.addParticipant("sim1", "p6", 1.0);
        driver.addChannel("sim1", "c1", 100.0);
        driver.addChannel("sim1", "c2", 100.0);
        driver.addChannel("sim1", "c3", 100.0);
        driver.addEdge("sim1", "p1", "c1", "e1");
        driver.addEdge("sim1", "p2", "c1", "e2");
        driver.addEdge("sim1", "p3", "c2", "e3");
        driver.addEdge("sim1", "c1", "p4", "e4");
        driver.addEdge("sim1", "c2", "p5", "e5");
        driver.addEdge("sim1", "p4", "c3", "e6");
        driver.addEdge("sim1", "p5", "c3", "e7");
        driver.addEdge("sim1", "c3", "p6", "e8");

        Transaction tx1 = new Transaction("p6", 10.0);
        Transaction tx2 = new Transaction("p6", 20.0);
        Transaction tx3 = new Transaction("p6", 15.0);
        driver.sendTransaction("sim1", "c1", tx1);
        driver.sendTransaction("sim1", "c2", tx2);
        driver.sendTransaction("sim1", "c3", tx3);

        // driver.getParticipantBalace();
        // driver.listContents();
        // driver.printAllEdges();
        driver.printAllEdges("sim1");

        assertEquals("error listContents", "10.0", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "20.0", driver.listContents("sim1", "c2"));
        assertEquals("error listContents", "15.0", driver.listContents("sim1", "c3"));

        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p1")));
        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p2")));
        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p3")));
        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p4")));
        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p5")));
        assertEquals("error getParticipantBalace", "0.0", Double.toString(driver.getParticipantBalace("sim1", "p6")));

        driver.simulate("sim1");

        assertEquals("error listContents", "", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "", driver.listContents("sim1", "c2"));
        assertEquals("error listContents", "", driver.listContents("sim1", "c3"));

        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p1"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p2"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p3"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p4"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p5"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p6"));

        driver.simulate("sim1");

        assertEquals("error listContents", "", driver.listContents("sim1", "c1"));
        assertEquals("error listContents", "", driver.listContents("sim1", "c2"));
        assertEquals("error listContents", "9.0 19.0", driver.listContents("sim1", "c3"));

        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p1"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p2"));
        assertEquals("error getParticipantBalace", "0.0", driver.getParticipantBalace("sim1", "p3"));
        assertEquals("error getParticipantBalace", "1.0", driver.getParticipantBalace("sim1", "p4"));
        assertEquals("error getParticipantBalace", "1.0", driver.getParticipantBalace("sim1", "p5"));
        assertEquals("error getParticipantBalace", "15.0", driver.getParticipantBalace("sim1", "p6"));





    }
}
