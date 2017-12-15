package homework2;

public class Edge<L> {

    private final L label;
    private final L destVertexLabel;


    public Edge(L vertexLabel, L edgeLabel) {
        this.destVertexLabel = vertexLabel;
        this.label = edgeLabel;
    }
//
//    public T getVertex() {
//        return vertex;
//    }
//
//    public void setVertex(T vertex) {
//        this.vertex = vertex;
//    }
//
//    public int getWeight() {
//        return weight;
//    }
//
//    public void setWeight(int weight) {
//        this.weight = weight;
//    }
//
//    public String toString(){
//
//        return "( "+ vertex + ", " + weight + " )";
//    }

}