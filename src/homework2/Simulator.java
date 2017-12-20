package homework2;

public class Simulator<L, WorkObj> {
    private BipartiteGraph<L> bipartiteGraph;

    public Simulator(String graphName) {
        this.bipartiteGraph = new BipartiteGraph<>(graphName);
    }

    public void Simulate(){
        for (L pipeLabel : this.bipartiteGraph.getBlackNodes()){
            Simulatable<L> pipe = (Simulatable<L>)bipartiteGraph.getVertexByLabel(pipeLabel).getObject();
            pipe.simulate(bipartiteGraph);
        }
        for (L filterLabel : this.bipartiteGraph.getWhiteNodes()){
            Simulatable<L> filter = (Simulatable<L>)bipartiteGraph.getVertexByLabel(filterLabel).getObject();
            filter.simulate(bipartiteGraph);
        }
    }


    /**
     * TODO: Taken from SimDriver
     * @requires createSimulator(simName)
     *           && channelName != null && channelName has
     *           not been used in a previous addChannel()  or
     *           addParticipant() call on this object
     *           limit > 0
     * @modifies simulator named simName
     * @effects Creates a new Channel named by the String channelName, with a limit, and add it to
     *          the simulator named simName.
     */
    public void addPipe(L pipeLabel, Object pipeObject) {
        if(pipeLabel == null) {
            System.out.println("pipeLabel is null.");
            return;
        }
        if(pipeObject == null) {
            System.out.println("pipeObject is null.");
            return;
        }
        bipartiteGraph.addBlackNode(pipeLabel, pipeObject);
    }

    /**     TODO: taken from SImDriver
     * @requires createSimulator(simName) && participantName != null
     *           && participantName has not been used in a previous addParticipant(), addChannel()
     *           call on this object
     *           fee > 0
     * @modifies simulator named simName
     * @effects Creates a new Participant named by the String participantName and add
     *          it to the simulator named simName.
     */
    public void addParticipant(L filterLabel, Object filterObject) {
        if(filterLabel == null) {
            System.out.println("pipeLabel is null.");
            return;
        }
        if(filterObject == null) {
            System.out.println("pipeObject is null.");
            return;
        }
        bipartiteGraph.addBlackNode(filterLabel, filterObject);
    }

    /** TODO: taken from SImDriver
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
    public void addEdge(L parentLabel, L childLabel, L edgeLabel) {
        if(parentLabel == null) {
            System.out.println("parentLabel is null.");
            return;
        }
        if(childLabel == null) {
            System.out.println("childLabel is null.");
            return;
        }
        if(edgeLabel == null) {
            System.out.println("edgeLabel is null.");
            return;
        }
        bipartiteGraph.addEdge(parentLabel, childLabel, edgeLabel);
    }
//
//    /**
//     * @requires createSimulator(simName) && addChannel(channelName)
//     *           A transaction Transaction != null
//     * @modifies channel named channelName
//     * @effects pushes the Transaction into the channel named channelName in the
//     *          simulator named simName.
//     */
//    public void sendTransaction(String simName, String channelName, Transaction tx) {
//        // TODO: Implement this method
//    }
//
//
//    /**
//     * @requires addChannel(channelName)
//     * @return a space-separated list of the Transaction values currently in the
//     *         channel named channelName in the simulator named simName.
//     */
//    public String listContents(String simName, String channelName) {
//        // TODO: Implement this method
//    }
//
//    /**
//     * @requires addParticipant(participantName)
//     * @return The sum of all  Transaction values stored in the storage of the participant participantName in the simulator simName
//     */
//    public double getParticipantBalace(String simName, String participantName) {
//        // TODO: Implement this method
//    }
//
//    /**
//     * @requires createSimulator(simName)
//     * @modifies simulator named simName
//     * @effects runs simulator named simName for a single time slice.
//     */
//    public void simulate(String simName) {
//        // TODO: Implement this method
//    }
//
//    /**
//     * Prints the all edges.
//     *
//     * @requires simName the sim name
//     * @effects Prints the all edges.
//     */
//    public void printAllEdges(String simName) {
//        // TODO: Implement this method
//    }
//
//
//

}
