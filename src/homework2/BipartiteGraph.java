package homework2;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class BipartiteGraph<L> {

    protected enum VertexColor {
        BLACK, WHITE
    }

    private final String graphName;
    private HashMap<L, Vertex<L>> vertexHashmap; // <label, vertex>



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
        checkRep();
    }

    /**
     * @modifies graph named graphName
     * @effects Adds a black node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addBlackNode(L nodeLabel, Object blackNode) {
        checkRep();
        return addNode(nodeLabel, blackNode, VertexColor.BLACK);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a white node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addWhiteNode(L nodeLabel, Object whiteNode) {
        checkRep();
        return addNode(nodeLabel, whiteNode, VertexColor.WHITE);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a node represented by the L nodeLabel to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    private boolean addNode(L nodeLabel, Object node, VertexColor vertexColor) {
        checkRep();
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
        checkRep();
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
        checkRep();
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
        checkRep();
        return true;
    }

    public boolean removeEdge(L parentLabel, L childLabel, L edgeLabel) {
        //TODO
        checkRep();
        Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
        Vertex<L> childVertex = this.vertexHashmap.get(childLabel);

        parentVertex.removeChild(childLabel, edgeLabel);
        childVertex.removeParent(parentLabel,edgeLabel);
        checkRep();
        return true;
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the black nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listBlackNodes() {
        //TODO
        checkRep();
        return listNodesByColor(VertexColor.BLACK);
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes() {
        //TODO
        checkRep();
        return listNodesByColor(VertexColor.WHITE);
    }


    private String listNodesByColor(VertexColor vertexColor){
        //TODO
        checkRep();
        ArrayList<String> nameList = new ArrayList<>();
        for (Vertex<L> vertex : vertexHashmap.values()){
            if (vertex.getVertexColor() == vertexColor){
                nameList.add(vertex.getLabel().toString());
            }
        }
        java.util.Collections.sort(nameList);
        String result = String.join(" ", nameList);
        checkRep();
        return result;
    }

    /**
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a space-separated list of the names of the children of
     * 		   parentName in the graph graphName, in alphabetical order.
     */
    public String listChildren(String parentLabel) {
        //TODO
        checkRep();
        String result = listAndSortNames(this.vertexHashmap.get(parentLabel).getChildrenLabelList());
        checkRep();
        return result;
    }

    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public String listParents(String childLabel) {
        //TODO: Implement this method
        checkRep();
        String result = listAndSortNames(this.vertexHashmap.get(childLabel).getParentsLabelList());
        checkRep();
        return result;
    }

    private String listAndSortNames(Set<L> labelSet){
        //TODO
        checkRep();
        ArrayList<String> nameList = new ArrayList<>();
        for (L label : labelSet){
            nameList.add(label.toString());
        }
        java.util.Collections.sort(nameList);
        String result = String.join(" ", nameList);
        checkRep();
        return result;

    }


    /**
     * @requires addEdge(graphName, parentName, str, edgeLabel) for some
     * 			 string str
     * @return the name of the child of parentName that is connected by the
     * 		   edge labeled edgeLabel, in the graph graphName.
     */
    public L getChildByEdgeLabel(L parentLabel, L edgeLabel) {
        //TODO
        checkRep();
        Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
        checkRep();
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
        checkRep();
        Vertex<L> childVertex = this.vertexHashmap.get(childLabel);
        checkRep();
        return childVertex.getParentByEdgeLabel(edgeLabel);
    }




    public boolean graphContains(L nodeLabel){
        //TODO: check input
        checkRep();
        return this.vertexHashmap.containsKey(nodeLabel);
    }



    public VertexColor getNodeColor(L nodeLabel){
        //TODO: check input, check if null, contains
//        assert (nodeLabel != null):
//                "node label is null";
//        assert (graphContains(nodeLabel)):
//                "node is not in the graph";
        checkRep();
        return this.vertexHashmap.get(nodeLabel).getVertexColor();
    }



    public boolean nodesDifferent(L sourceLabel, L destLabel){
        checkRep();
        assert (sourceLabel != null):
                "source label is null";
        assert (destLabel != null):
                "destination label is null";
        assert (graphContains(sourceLabel) && graphContains(destLabel)):
                "source/destination node is not in graph";

        checkRep();
        return (getNodeColor(sourceLabel) == VertexColor.BLACK && getNodeColor(destLabel) == VertexColor.WHITE) ||
                (getNodeColor(sourceLabel) == VertexColor.WHITE && getNodeColor(destLabel) == VertexColor.BLACK);
    }

    private void checkRep(){
        assert (this.graphName != null):
                "graph name is null";
        assert (this.vertexHashmap != null):
                "hashmap is null";
        for (Vertex<L> vertexA : this.vertexHashmap.values()){
            for (Vertex<L> vertexB : this.vertexHashmap.values()){
                assert (vertexA != vertexB):
                        "graph contains 2 vertexes with the same label";
            }
            for (L parentLabel : vertexA.getParentsLabelList()){
                Vertex<L> parentVertex = this.vertexHashmap.get(parentLabel);
                assert (vertexA.getVertexColor() != parentVertex.getVertexColor()):
                        "graph contains parent & child vertexes with the same color";
            }
            for (L childLabel : vertexA.getChildrenLabelList()){
                Vertex<L> childVertex = this.vertexHashmap.get(childLabel);
                assert (vertexA.getVertexColor() != childVertex.getVertexColor()):
                        "graph contains parent & child vertexes with the same color";
            }
        }
    }
}
