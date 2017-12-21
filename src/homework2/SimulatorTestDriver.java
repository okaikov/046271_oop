package homework2;

import java.util.*;

/**
 * This class implements a testing driver for Simulator. The driver manages
 * Simulators for payment channels
 */
public class SimulatorTestDriver {

	private Map<String, Simulator<String, Transaction>> simulators;

	/**
	 * @modifies this
	 * @effects Constructs a new test driver.
	 */
	public SimulatorTestDriver() {
		this.simulators = new HashMap<>();
	}

	/**
	 * @requires simName != null
	 * @modifies this
	 * @effects Creates a new simulator named simName. The simulator's graph is
	 *          initially empty.
	 */
	public void createSimulator(String simName) {
		this.simulators.put(simName, new Simulator<>(simName));
	}

	/**
	 * @requires createSimulator(simName) 
     *           && channelName != null && channelName has
	 *           not been used in a previous addChannel()  or
	 *           addParticipant() call on this object
	 *           limit > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
	 *          the simulator named simName.
	 */
	public void addChannel(String simName, String channelName, double limit) {
		if (channelName == null){
			System.out.println("channel name is null.");
			return;
		}
		if(limit <= 0) {
			System.out.println("limit <= 0");
			return;
		}
		simulators.get(simName).getGraph().addBlackNode(channelName, new Channel(limit));
	}

	/**
	 * @requires createSimulator(simName) && participantName != null 
	 *           && participantName has not been used in a previous addParticipant(), addChannel()
	 *           call on this object
	 *           fee > 0
	 * @modifies simulator named simName
	 * @effects Creates a new Participant named by the String participantName and add
	 *          it to the simulator named simName.
	 */
	public void addParticipant(String simName, String participantName, double fee) {
        // TODO

        simulators.get(simName).getGraph().addWhiteNode(participantName, new Participant(fee));

	}

	/**
	 * @requires createSimulator(simName) && ((addPipe(parentName) &&
	 *           addFilter(childName)) || (addFilter(parentName) &&
	 *           addPipe(childName))) && edgeLabel != null && node named
	 *           parentName has no other outgoing edge labeled edgeLabel 
	 *           && node named childName has no other incoming edge labeled edgeLabel
	 * @modifies simulator named simName
	 * @effects Adds an edge from the node named parentName to the node named
	 *          childName in the simulator named simName. The new edge's label
	 *          is the String edgeLabel.
	 */
	public void addEdge(String simName, String parentName, String childName, String edgeLabel) {
        //TODO
	    simulators.get(simName).getGraph().addEdge(parentName, childName, edgeLabel);
	}

	/**
	 * @requires createSimulator(simName) && addChannel(channelName)
	 *           A transaction Transaction != null
	 * @modifies channel named channelName
	 * @effects pushes the Transaction into the channel named channelName in the
	 *          simulator named simName.
	 */
	public void sendTransaction(String simName, String channelName, Transaction tx) {
        // TODO
        Channel channel  = (Channel)simulators.get(simName).getGraph().getVertexByLabel(channelName).getObject();
        channel.addTransaction(tx);
    }
	
	
	/**
	 * @requires addChannel(channelName)
	 * @return a space-separated list of the Transaction values currently in the
	 *         channel named channelName in the simulator named simName.
	 */
	public String listContents(String simName, String channelName) {
        // TODO
        Channel channel  = (Channel)simulators.get(simName).getGraph().getVertexByLabel(channelName).getObject();
        ArrayList<String> strings = new ArrayList<>();
        for (Transaction tx : channel.getTransactionBuffer()){
            strings.add(tx.toString());
        }
        return String.join(" ", strings);

	}

	/**
	 * @requires addParticipant(participantName)
	 * @return The sum of all  Transaction values stored in the storage of the participant participantName in the simulator simName
	 */
	public double getParticipantBalace(String simName, String participantName) {
        Participant participant = (Participant) simulators.get(simName).getGraph().getVertexByLabel(participantName).getObject();
        double balance = 0;
        for (Transaction transaction : participant.getStorageBuffer()){
            balance += transaction.getValue();
        }
        return balance;
	}
	
	/**
	 * @requires createSimulator(simName)
	 * @modifies simulator named simName
	 * @effects runs simulator named simName for a single time slice.
	 */
	public void simulate(String simName) {
        simulators.get(simName).simulate();
	}

	/**
	 * Prints the all edges.
	 *
	 * @requires simName the sim name
	 * @effects Prints the all edges.
	 */
	public void printAllEdges(String simName) {
        // TODO
        final BipartiteGraph<String> graph = simulators.get(simName).getGraph();
        for (String nodeName : graph.getBlackNodes()){
            graph.getVertexByLabel(nodeName).getChildrenSortedString();
        }
        for (String nodeName : graph.getWhiteNodes()){
            graph.getVertexByLabel(nodeName).getChildrenSortedString();
        }
	}

}
