package homework2;
import java.util.HashMap;


public class BipartiteGraph<L> {

    protected enum VertexColor {
        BLACK, WHITE
    }

    private final String graphName;
    private HashMap<L, Vertex<L>> vertexHashmap;



    /**
     * @modifies this
     * @effects Creates a new graph named graphName. The graph is initially empty. if graphName == null, terminates program.
     */
    public BipartiteGraph(String graphName) {
        if (graphName == null){
            System.out.println("graphName is null");
            System.exit(1);
        }

        this.graphName = graphName;
        this.vertexHashmap = new HashMap<>();
    }

    /**
     * @modifies graph named graphName
     * @effects Adds a black node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addBlackNode(L nodeLabel, Object blackNode) {
        return addNode(nodeLabel, blackNode, VertexColor.BLACK);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a white node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addWhiteNode(L nodeLabel, Object whiteNode) {
        return addNode(nodeLabel, whiteNode, VertexColor.WHITE);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a node represented by the L nodeLabel to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    private boolean addNode(L nodeLabel, Object node, VertexColor vertexColor) {
        if (nodeLabel == null){
            System.out.println("node label is null");
            return false;
        }
        if (node == null){
            System.out.println("node is null");
            return false;
        }
        if (graphContains(nodeLabel)){
            System.out.println("graph allready contains Vertex");
            return false;
        }

        Vertex<L> newVertex = new Vertex<>(node, nodeLabel, vertexColor);
        this.vertexHashmap.put(nodeLabel, newVertex);
        return true;
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
    public boolean addEdge(L sourceLabel, L destLabel, L edgeLabel) {
        if (sourceLabel == null){
            System.out.println("source label is null");
            return false;
        }
        if (destLabel == null){
            System.out.println("destination label is null");
            return false;
        }
        if (edgeLabel == null){
            System.out.println("edge label is null");
            return false;
        }
        if (!graphContains(sourceLabel) || !graphContains(destLabel)){
            System.out.println("source/destination node is not in graph");
            return false;
        }
        if (getNodeColor(sourceLabel) == getNodeColor(destLabel)){
            System.out.println("trying to add edge to a wrong color, must be diffrent color");
            return false;
        }
        // check if we have some edge from src -> dest
        // check if we have edge with label X from src
        // check if we have edge from some parent to dest with label X
        if (this.vertexHashmap.get(sourceLabel).getChildEdgeLabel(destLabel) != null){
            System.out.println("child edge already exists");
            return false;
        }
        Vertex<L> destVertex = this.vertexHashmap.get(destLabel);
        for (L parentLabel : destVertex.getParentsLabels()){
            if (destVertex.getParentEdgeLabel(parentLabel) != null){
                System.out.println("parent edge with same label already exists");
                return false;
            }

        }

        if (this.vertexHashmap.get(sourceLabel).getChildEdgeLabel(destLabel) != null){
            System.out.println("child edge already exists");
            return false;
        }






    }

    public boolean graphContains(L nodeLabel){
        //TODO: check input
        return this.vertexHashmap.containsKey(nodeLabel);
    }



    public VertexColor getNodeColor(L nodeLabel){
        //TODO: check input, check if null, contains
//        assert (nodeLabel != null):
//                "node label is null";
//        assert (graphContains(nodeLabel)):
//                "node is not in the graph";

        return this.vertexHashmap.get(nodeLabel).getVertexColor();
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
