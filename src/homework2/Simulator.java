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



}
