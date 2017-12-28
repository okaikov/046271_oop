package homework2;
import java.util.*;

/**
 * BipartiteGraph is a directed labeled edged graph with nodes of two colors: black and white, in which only nodes of different colors
 * can be connected by edge. The graph has property Name.
 */

public class BipartiteGraph<L> {

    // Abstraction Function: Represents a bipartite graph, with name this.graphName and nodes, represented by nodeHashMap that maps from node's label to the node reference.
    //                       The edges of the graph are represented by Node class. Refer to Node.java.

    // Representation Invariant: graphName != null, nodeHashMap != null, the labels of the nodes are unique, there are no edges between nodes of the same color.


    private final String graphName;
    private HashMap<L, Node<L>> nodeHashMap; // key: node label, value: node



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
        this.nodeHashMap = new HashMap<>();
        checkRep();
    }

    /**
     * @modifies this
     * @effects Adds a black node represented by the L nodeLabel to the graph named graphName.
     * if nodeLabel == null || node == null || node is in the graph: return false.
     * if node was successfully added, return true.
     */
    public boolean addBlackNode(L nodeLabel, Object blackNode) {
        checkRep();
        if(nodeLabel == null){
            return false;
        }
        final boolean result = addNode(nodeLabel, blackNode, Node.NodeColor.BLACK);
        checkRep();
        return result;
    }


    /**
     * @modifies this
     * @effects Adds a white node represented by the L nodeLabel to the graph named graphName.
     * if nodeLabel == null || node == null || node is in the graph: return false.
     * if node was successfully added, return true.
     */
    public boolean addWhiteNode(L nodeLabel, Object whiteNode) {
        checkRep();
        if(nodeLabel == null){
            return false;
        }
        final boolean result = addNode(nodeLabel, whiteNode, Node.NodeColor.WHITE);
        checkRep();
        return result;
    }


    /**
     * @modifies this
     * @effects Adds a node represented by the L nodeLabel to the graph named graphName.
     * if nodeLabel == null || node == null || node is in the graph: return false.
     * if node was successfully added, return true.
     */
    private boolean addNode(L nodeLabel, Object node, Node.NodeColor nodeColor) {
        checkRep();
        if(nodeLabel == null || nodeColor == null){
            return false;
        }
        if (graphContains(nodeLabel)){
            return false;
        }
        Node<L> newNode = new Node<>(node, nodeLabel, nodeColor);
        this.nodeHashMap.put(nodeLabel, newNode);
        checkRep();
        return true;
    }


    /**
     * @modifies this
     * @effects Adds an edge from the node parentLabel to the node childLabel
     * 			in the graph graphLabel. The new edge's label is the L
     * 			edgeLabel.
     * 		    Returns true if ((addBlackNode(parentLabel) && addWhiteNode(childLabel))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && node named parentName has no other outgoing edge labeled
     * 				edgeLabel
     *           && node named childName has no other incoming edge labeled
     * 				edgeLabel.
     * 			Otherwise returns false.
     *
     */
    public boolean addEdge(L parentLabel, L childLabel, L edgeLabel) {
        checkRep();
        if (parentLabel == null || childLabel == null || edgeLabel == null){
            return false;
        }

        if (!graphContains(parentLabel) || !graphContains(childLabel)){
            System.err.println("parent/child node is not in graph");
            return false;
        }
        if (getNodeColor(parentLabel) == getNodeColor(childLabel)){
            System.err.println("trying to add edge to a wrong color, must be different color");
            return false;
        }
        // check if we have some edge from parent -> child
        Node<L> parentNode = this.nodeHashMap.get(parentLabel);
        if (parentNode.childrenContainsLabel(childLabel)){
            System.err.println("there is already an edge from parent->child");
            return false;
        }

        // check if we have edge with label X from parent
        if (parentNode.childrenContainsEdgeLabel(edgeLabel)){
            System.err.println("there is already an edge from parent with edge label");
            return false;
        }

        // check if we have edge from some parent to child with label X
        Node<L> childNode = this.nodeHashMap.get(childLabel);
        if (childNode.parentsContainsEdgeLabel(edgeLabel)){
            System.err.println("there is already an edge to child with edge label");
            return false;
        }

        parentNode.addChild(childLabel, edgeLabel);
        childNode.addParent(parentLabel,edgeLabel);
        checkRep();
        return true;
    }

    /**
     * @modifies this
     * @effects Removes an edge from the node parentLabel to the node childLabel
     * 			in the graph graphLabel.
     * 		    Returns true if ((addBlackNode(parentLabel) && addWhiteNode(childLabel))
     *              || (addWhiteNode(parentName) && addBlackNode(childName)))
     *           && edgeLabel != null
     *           && addEdge(parentLabel, childLabel, edgeLabel )
     * 			Otherwise returns false.
     *
     */
    public boolean removeEdge(L parentLabel, L childLabel, L edgeLabel) {
        checkRep();

        if(parentLabel == null || childLabel == null || edgeLabel == null){
            return false;
        }
        Node<L> parentNode = this.nodeHashMap.get(parentLabel);
        Node<L> childNode = this.nodeHashMap.get(childLabel);
        if(parentNode == null || childNode == null){
            System.err.println("Parent or child not found.");
            return false;
        }
        boolean removeChildSuccess = parentNode.removeChild(childLabel, edgeLabel);
        boolean removeParentSuccess = childNode.removeParent(parentLabel,edgeLabel);
        checkRep();
        return removeChildSuccess && removeParentSuccess;
    }

    /**
     * @effects Returns list of all the black nodes in the graph.
     */
    public ArrayList<L> getBlackNodes(){
        checkRep();
        final ArrayList<L> result = getNodesByColor(Node.NodeColor.BLACK);
        checkRep();
        return result;
    }

    /**
     * @effects Returns list of all the white nodes in the graph.
     */
    public ArrayList<L> getWhiteNodes(){
        checkRep();
        final ArrayList<L> result = getNodesByColor(Node.NodeColor.WHITE);
        checkRep();
        return result;
    }

    private ArrayList<L> getNodesByColor(Node.NodeColor nodeColor){
        checkRep();
        if (nodeColor == null){
            System.err.println("nodeColor is null");
            return null;
        }

        ArrayList<L> labels = new ArrayList<>();
        for (Node<L> node : this.nodeHashMap.values()){
            if (node.getNodeColor() == nodeColor){
                labels.add(node.getLabel());
            }
        }
        checkRep();
        return labels;
    }



    /**
     * @effects Return a list of the labels of the children of
     * 		   parentLabel in this.
     * 		   Return null if parentLabel == null or if there is no node labeled by parentLabel.
     */
    public ArrayList<L> listChildren(L parentLabel) {
        checkRep();
        if (parentLabel == null){
            System.err.println("parentLabel is null");
            return null;
        }
        if (!graphContains(parentLabel)){
            System.err.println("Parent not found");
            return null;
        }

        ArrayList<L> result = this.nodeHashMap.get(parentLabel).getChildrenList();
        checkRep();
        return result;
    }

    /**
     * @effects Return a list of the labels of the parents of
     * 		   childLabel in this.
     * 		   Return null if childLabel == null or if there is no node labeled by childLabel.
     */
    public ArrayList<L> listParents(L childLabel) {
        checkRep();
        if (childLabel == null){
            System.err.println("childLabel is null");
            return null;
        }
        if (!graphContains(childLabel)){
            System.err.println("Child not found");
            return null;
        }

        ArrayList<L> result = this.nodeHashMap.get(childLabel).getParentsList();
        checkRep();
        return result;
    }

    /**
     * @effects return the label of the child of parentLabel that is connected by the
     * 		    edge labeled edgeLabel, in this.
     * 		    return null if one of the inputs is null or node with parent label is not in this.
     */
    public L getChildByEdgeLabel(L parentLabel, L edgeLabel) {
        checkRep();
        if (parentLabel == null || edgeLabel == null){
            System.err.println("null input");
            return null;
        }
        if (!graphContains(parentLabel)){
            System.err.println("Parent not found");
            return null;
        }
        Node<L> parentNode = this.nodeHashMap.get(parentLabel);
        checkRep();
        return parentNode.getChildByEdgeLabel(edgeLabel);
    }

    /**
     * @effects return the label of the parents of childLabel that are connected by the
     * 		    edge labeled edgeLabel, in this.
     * 		    return null if one of the inputs is null or node with child label is not in this.
     */
    public L getParentByEdgeLabel(L childLabel, L edgeLabel) {
        checkRep();
        if (childLabel== null || edgeLabel == null){
            System.err.println("null input");
            return null;
        }
        if (!graphContains(childLabel)){
            System.err.println("Child not found");
            return null;
        }
        Node<L> childNode = this.nodeHashMap.get(childLabel);
        checkRep();
        return childNode.getParentByEdgeLabel(edgeLabel);
    }


    /**
     * @effects Return true if node labeled nodeLabel is in this. Else or if null input - false.
     */

    public boolean graphContains(L nodeLabel){
        checkRep();
        if (nodeLabel == null){
            System.err.println("null input");
            return false;
        }
        final boolean result = this.nodeHashMap.containsKey(nodeLabel);
        checkRep();
        return result;
    }


    /**
     * @effects Return the color of node labeled nodeLabel in this. Else or if null input - null.
     */
    public Node.NodeColor getNodeColor(L nodeLabel){
        checkRep();
        if (nodeLabel == null){
            System.err.println("null input");
            return null;
        }
        if (!graphContains(nodeLabel)){
            System.err.println("node not found");
            return null;
        }

        final Node.NodeColor result = this.nodeHashMap.get(nodeLabel).getNodeColor();
        checkRep();
        return result;
    }

    /**
     * @effects Return the node labeled label in this. If not in this or if null input - null.
     */
    public Node<L> getNodeByLabel(L label){
        checkRep();
        if (label == null){
            System.err.println("null input");
            return null;
        }
        if ((!graphContains(label))){
            System.err.println("node not found");
            return null;
        }

        final Node<L> result = this.nodeHashMap.get(label);
        checkRep();
        return result;
    }


    private void checkRep(){
        assert (this.graphName != null):
                "graph name is null";
        assert (this.nodeHashMap != null):
                "hashmap is null";


        for (Node<L> nodeA : this.nodeHashMap.values()){
            final List<Node<L>> reduecedNodeList = new ArrayList<>(this.nodeHashMap.values());
            reduecedNodeList.remove(nodeA);
            for (Node<L> nodeB : reduecedNodeList){
                assert (nodeA.getLabel() != nodeB.getLabel()):
                        "graph contains 2 nodes with the same label";
            }
            for (L parentLabel : nodeA.getParentsList()){
                Node<L> parentNode = this.nodeHashMap.get(parentLabel);
                assert (nodeA.getNodeColor() != parentNode.getNodeColor()):
                        "graph contains parent & child nodes with the same color";
            }
            for (L childLabel : nodeA.getChildrenList()){
                Node<L> childNode = this.nodeHashMap.get(childLabel);
                assert (nodeA.getNodeColor() != childNode.getNodeColor()):
                        "graph contains parent & child nodes with the same color";
            }
        }
    }
}
