package homework2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


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
    public boolean addEdge(L parentLabel, L childLabel, L edgeLabel) {
        if (parentLabel == null){
            System.out.println("parent label is null");
            return false;
        }
        if (childLabel == null){
            System.out.println("child label is null");
            return false;
        }
        if (edgeLabel == null){
            System.out.println("edge label is null");
            return false;
        }
        if (!graphContains(parentLabel) || !graphContains(childLabel)){
            System.out.println("parent/child node is not in graph");
            return false;
        }
        if (getNodeColor(parentLabel) == getNodeColor(childLabel)){
            System.out.println("trying to add edge to a wrong color, must be different color");
            return false;
        }
        // check if we have some edge from parent -> child
        Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
        if (parentVertex.childrenContainsLabel(childLabel)){
            System.out.println("there is already an edge from parent->child");
            return false;
        }

        // check if we have edge with label X from parent
        if (parentVertex.childrenContainsEdgeLabel(edgeLabel)){
            System.out.println("there is already an edge from parent with edge label");
            return false;
        }

        // check if we have edge from some parent to child with label X
        Vertex<L> childVertex = this.vertexHashmap.get(childLabel);
        if (childVertex.parentsContainsEdgeLabel(edgeLabel)){
            System.out.println("there is already an edge to child with edge label");
            return false;
        }

        parentVertex.addChild(childLabel, edgeLabel);
        childVertex.addParent(parentLabel,edgeLabel);
        return true;
    }

    public boolean removeEdge(L parentLabel, L childLabel, L edgeLabel) {
        //TODO

        Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
        Vertex<L> childVertex = this.vertexHashmap.get(childLabel);

        parentVertex.removeChild(childLabel, edgeLabel);
        childVertex.removeParent(parentLabel,edgeLabel);
        return true;
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes() {
        //TODO
        return listNodesByColor(VertexColor.BLACK);
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes() {
        //TODO
        return listNodesByColor(VertexColor.WHITE);
    }


    private String listNodesByColor(VertexColor vertexColor){
        //TODO
        ArrayList<String> nameList = new ArrayList<>();
        for (Vertex<L> vertex : vertexHashmap.values()){
            if (vertex.getVertexColor() == vertexColor){
                nameList.add(vertex.getLabel().toString());
            }
        }
        java.util.Collections.sort(nameList);
        return String.join(" ", nameList);
    }

    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String parentLabel) {
        //TODO
        return listAndSortNames(this.vertexHashmap.get(parentLabel).getChildrenLabelList());
    }

    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String childLabel) {
        //TODO: Implement this method
        return listAndSortNames(this.vertexHashmap.get(childLabel).getParentsLabelList());
    }

    private String listAndSortNames(Set<L> labelSet){
        //TODO

        ArrayList<String> nameList = new ArrayList<>();
        for (L label : labelSet){
            nameList.add(label.toString());
        }
        java.util.Collections.sort(nameList);
        return String.join(" ", nameList);

    }


    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public L getChildByEdgeLabel(L parentLabel, L edgeLabel) {
        //TODO

        Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
        return parentVertex.getChildByEdgeLabel(edgeLabel);
    }

    /**
     * @requires addEdge(graphName, str, childName, edgeLabel) for some
     * 			 string str
     * @return the name of the parent of childName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public L getParentByEdgeLabel(L childLabel, L edgeLabel) {
        //TODO

        Vertex<L> childVertex = this.vertexHashmap.get(childLabel);
        return childVertex.getParentByEdgeLabel(edgeLabel);
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
