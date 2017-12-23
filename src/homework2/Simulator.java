package homework2;

public class Simulator<Label, WorkObject> {
    private BipartiteGraph<Label> graph;

    public Simulator(String simName) {
        this.graph = new BipartiteGraph<>(simName);
    }

    public void simulate(){
        for (Label pipeLabel : this.graph.getBlackNodes()){
            Simulatable<Label> pipe = (Simulatable<Label>) graph.getNodeByLabel(pipeLabel).getNodeObject();
            pipe.simulate(graph);
        }
        for (Label filterLabel : this.graph.getWhiteNodes()){
            Simulatable<Label> filter = (Simulatable<Label>) graph.getNodeByLabel(filterLabel).getNodeObject();
            filter.simulate(graph);
        }
    }


    public BipartiteGraph<Label> getGraph(){
        return this.graph;
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
    public void addPipe(Label pipeName, Pipe pipe) {
        //TODO
        this.graph.addBlackNode(pipeName, pipe);

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
    public void addFilter(Label filterName, Filter filter) {
        // TODO
        this.graph.addWhiteNode(filterName, filter);


    }

    public void addParticipant(Label filterLabel, Object filterObject) {
        if(filterLabel == null) {
            System.out.println("pipeLabel is null.");
            return;
        }
        if(filterObject == null) {
            System.out.println("pipeObject is null.");
            return;
        }
        graph.addBlackNode(filterLabel, filterObject);
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
    public void addEdge(Label parentLabel, Label childLabel, Label edgeLabel) {
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
        graph.addEdge(parentLabel, childLabel, edgeLabel);
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
