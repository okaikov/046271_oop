package homework2;

public class Simulator<L> {
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




}
