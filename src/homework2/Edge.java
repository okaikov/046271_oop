package homework2;


public class Edge<T,L> {

    private Vertex<T,L> destVertex;
    private L label;

    public Edge(Vertex<T,L> vertex, L label) {
        this.destVertex = vertex;
        this.label = label;
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