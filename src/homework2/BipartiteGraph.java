package homework2;


import java.util.ArrayList;
import java.util.HashMap;

public class BipartiteGraph<B,W,L> {

    public enum VertexColor {
        BLACK, WHITE
    }

    private HashMap<L, Vertex<B,L>> blackVertexHashmap;
    private HashMap<L, Vertex<W,L>> whiteVertexHashmap;
    private String graphNmae;


    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially empty. assert if graphName == null.
     */
    public BipartiteGraph(String graphName) {
        assert (graphName != null):
                "graphName is null";
        this.graphNmae = graphName;
        this.blackVertexHashmap = new HashMap<L, Vertex<B, L>>();
        this.whiteVertexHashmap = new HashMap<L, Vertex<W, L>>();
    }

    /**
     * @modifies graph named graphName
     * @effects Adds a black node represented by the L nodeName to the graph named graphName.
     */
    public void addBlackNode(L nodeLabel, B blackNode) {
        assert (nodeLabel != null):
                "node label is null";
        assert (blackNode != null):
                "node is null";
        assert (graphContains(nodeLabel) == false):
                "graph allready contains Vertex: " + nodeLabel;

        Vertex<B,L> blackVertex = new Vertex<>(blackNode,nodeLabel);
        this.blackVertexHashmap.put(nodeLabel, blackVertex);
    }

    /**
     * @modifies graph named graphName
     * @effects Adds a white node represented by the L nodeName to the graph named graphName.
     */
    public void addWhiteNode(L nodeLabel, W whiteNode) {
        assert (nodeLabel != null):
                "node label is null";
        assert (whiteNode != null):
                "node is null";
        assert (graphContains(nodeLabel) == false):
                "graph allready contains Vertex: " + nodeLabel;

        Vertex<W,L> whiteVertex = new Vertex<>(whiteNode,nodeLabel);
        this.whiteVertexHashmap.put(nodeLabel, whiteVertex);
    }

    /**
     * @effects return true if graph contains a node with label: nodeLabel.
     */
    public Boolean graphContains(L nodeLabel) {
        assert (nodeLabel != null):
                "node label is null";
        if (this.blackVertexHashmap.containsKey(nodeLabel) || this.whiteVertexHashmap.containsKey(nodeLabel)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @requires createGraph(graphName)
     *           && ((addBlackNode(parentName) && addWhiteNode(childName))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel
     * @modifies graph named graphName
     * @effects Adds an edge from the node parentName to the node childName
     * 			in the graph graphName. The new edge's label is the String
     * 			edgeLabel.
     */
    public void addEdge(L sourceLabel, L destLabel, L edgeLabel) {


        assert (sourceLabel != null):
                "source label is null";
        assert (destLabel != null):
                "destination label is null";
        assert (edgeLabel != null):
                "edge label is null";
        assert (graphContains(sourceLabel) && graphContains(destLabel)):
                "source/destination node is not in graph";
        assert (nodesDifferent(sourceLabel, destLabel)):
                "trying to add edge to a wrong color";





    }

    public VertexColor getNodeColor(L nodeLabel){
        assert (nodeLabel != null):
                "node label is null";
        assert (graphContains(nodeLabel)):
                "node is not in the graph";

        if (this.whiteVertexHashmap.containsKey(nodeLabel)){
            return VertexColor.WHITE;
        }else return VertexColor.BLACK;
    }



    public boolean nodesDifferent(L sourceLabel, L destLabel){
        assert (sourceLabel != null):
                "source label is null";
        assert (destLabel != null):
                "destination label is null";
        assert (graphContains(sourceLabel) && graphContains(destLabel)):
                "source/destination node is not in graph";

        return (getNodeColor(sourceLabel) == VertexColor.BLACK && getNodeColor(destLabel) == VertexColor.WHITE) ||
                (getNodeColor(sourceLabel) == VertexColor.WHITE && getNodeColor(destLabel) == VertexColor.BLACK);
    }



//
//    public void add(V vertex, ArrayList<Edge<V,L>> connectedVertices) {
//        // Add the new vertex to the adjacencyList with it's list of connected
//        // nodes
//        adjacencyList.put(vertex, connectedVertices);
//        vertexList.add(vertex);
//        // If this is an undirected graph, every edge needs to represented
//        // twice, once in the added vertex's list and once in the list of each
//        // of the vertex's connected to the added vertex
//
//        for (Edge<V,L> vertexConnectedToAddedVertex : connectedVertices) {
//            ArrayList<Edge<V,L>> correspondingConnectedList = adjacencyList.get(vertexConnectedToAddedVertex.getVertex());
//            // The added vertex's connections might not be represented in
//            // the Graph yet, so we implicitly add them
//            if (correspondingConnectedList == null) {
//                adjacencyList.put(vertexConnectedToAddedVertex.getVertex(), new ArrayList<Edge<V,L>>());
//                vertexList.add(vertexConnectedToAddedVertex.getVertex());
//                correspondingConnectedList = adjacencyList.get(vertexConnectedToAddedVertex.getVertex());
//            }
//        }
//    }
//
//    public boolean addArc(V source, V end, int weight) {
//        if (!adjacencyList.containsKey(source)) {
//            ArrayList<Edge<V,L>> tempList = new ArrayList<Edge<V,L>>();
//            tempList.add(new Edge<V,L>(end, weight));
//            add(source, tempList);
//            return true;
//        }
//
//        if (!adjacencyList.containsKey(end)) {
//            ArrayList<Edge<V,L>> tempList = new ArrayList<Edge<V,L>>();
//            add(end, tempList);
//        }
//
//
//        adjacencyList.get(source).add(new Edge<V,L>(end, weight));
//        return true;
//    }
//
//
//    /**
//     * This method returns a list of all adjacent vertices of the give vertex without weight
//     *
//     * @param vertex the source vertex
//     * @return an array list containing the vertices
//     */
//    public ArrayList<V> getAdjacentVertices(V vertex){
//        ArrayList<V> returnList = new ArrayList<V>();
//        for (Edge<V,L> edge : adjacencyList.get(vertex)) {
//            returnList.add(edge.getVertex());
//        }
//        return returnList;
//    }
//
//    public double getDistanceBetween(V source, V end){
//        for (Edge<V,L> edge : adjacencyList.get(source)) {
//            if (edge.getVertex() == end){
//                return edge.getWeight();
//            }
//        }
//        return Double.POSITIVE_INFINITY;
//    }
//
//    public ArrayList<V> getVertexList() {
//        return vertexList;
//    }
//
//    public String toString() {
//        String s = "";
//        for (V vertex : vertexList) {
//            s += vertex.toString();
//            s += " : ";
//            s += adjacencyList.get(vertex);
//            s += "\n";
//        }
//        return s;
//    }
}
