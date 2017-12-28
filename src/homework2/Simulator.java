package homework2;


/**
 * The Simulator is a system of pipes, filters and working objects.
 * Simulating the systems means simulating all the pipes and filters in the system.
 */
public class Simulator<Label, WorkObject> {
    // Abstraction Function: Simulator represents a system, consisting of pipes and channels that can be simulated.
    //                       The system is represented by Bitrpartite graph, where pipes are represented as black nodes
    //                       and filters are represented as white nodes.
    //
    // Representation Invariant: graph != null.
    private BipartiteGraph<Label> graph;


    public Simulator(String simName) {
        this.graph = new BipartiteGraph<>(simName);
        checkRep();
    }

    public void simulate(){
        checkRep();
        for (Label pipeLabel : this.graph.getBlackNodes()){
            Pipe<Label,WorkObject> pipe = (Pipe<Label, WorkObject>) graph.getNodeByLabel(pipeLabel).getNodeObject();
            pipe.simulate(graph);
        }
        for (Label filterLabel : this.graph.getWhiteNodes()){
            Filter<Label,WorkObject> filter = (Filter<Label, WorkObject>) graph.getNodeByLabel(filterLabel).getNodeObject();
            filter.simulate(graph);
        }
        checkRep();
    }


    public BipartiteGraph<Label> getGraph(){
        checkRep();
        return this.graph;
    }


    private void checkRep() {
        assert graph != null : "The sraph in Simulator is null.";
    }

}
