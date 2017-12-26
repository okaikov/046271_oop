package homework2;
import java.util.*;


public class BipartiteGraph<L> {


    private final String graphName;
    private HashMap<L, Node<L>> nodeHashMap; // <label, node>



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
     * @modifies graph named graphName
     * @effects Adds a black node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
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
     * @modifies graph named graphName
     * @effects Adds a white node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
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
     * @modifies graph named graphName
     * @effects Adds a node represented by the L nodeLabel to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
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

    // TODO
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

    public ArrayList<L> getBlackNodes(){
        checkRep();
        final ArrayList<L> result = getNodesByColor(Node.NodeColor.BLACK);
        checkRep();
        return result;
    }

    public ArrayList<L> getWhiteNodes(){
        checkRep();
        final ArrayList<L> result = getNodesByColor(Node.NodeColor.WHITE);
        checkRep();
        return result;
    }

    private ArrayList<L> getNodesByColor(Node.NodeColor nodeColor){
        //TODO
        checkRep();
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
     * @requires createGraph(graphName) && createNode(parentName)
     * @return a list of the labels of the children of
     * 		   parentName in the graph graphName.
     */
    public ArrayList<L> listChildren(L parentLabel) {
        //TODO
        checkRep();
        ArrayList<L> result = this.nodeHashMap.get(parentLabel).getChildrenList();
        checkRep();
        return result;
    }

    /**
     * @requires createGraph(graphName) && createNode(childName)
     * @return a space-separated list of the names of the parents of
     * 		   childName in the graph graphName, in alphabetical order.
     */
    public ArrayList<L> listParents(L childLabel) {
        //TODO: Implement this method
        checkRep();
        ArrayList<L> result = this.nodeHashMap.get(childLabel).getParentsList();
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
        Node<L> parentNode = this.nodeHashMap.get(parentLabel);
        checkRep();
        return parentNode.getChildByEdgeLabel(edgeLabel);
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
        Node<L> childNode = this.nodeHashMap.get(childLabel);
        checkRep();
        return childNode.getParentByEdgeLabel(edgeLabel);
    }




    public boolean graphContains(L nodeLabel){
        //TODO: check input
        checkRep();
        final boolean result = this.nodeHashMap.containsKey(nodeLabel);
        checkRep();
        return result;
    }



    public Node.NodeColor getNodeColor(L nodeLabel){
        //TODO: check input, check if null, contains
//        assert (nodeLabel != null):
//                "node label is null";
//        assert (graphContains(nodeLabel)):
//                "node is not in the graph";
        checkRep();
        final Node.NodeColor result = this.nodeHashMap.get(nodeLabel).getNodeColor();
        checkRep();
        return result;
    }



    public boolean nodesDifferent(L sourceLabel, L destLabel){
        checkRep();
        assert (sourceLabel != null):
                "source label is null";
        assert (destLabel != null):
                "destination label is null";
        assert (graphContains(sourceLabel) && graphContains(destLabel)):
                "source/destination node is not in graph";

        final boolean result = (getNodeColor(sourceLabel) == Node.NodeColor.BLACK && getNodeColor(destLabel) == Node.NodeColor.WHITE) ||
                (getNodeColor(sourceLabel) == Node.NodeColor.WHITE && getNodeColor(destLabel) == Node.NodeColor.BLACK);
        checkRep();
        return result;
    }

    public Node<L> getNodeByLabel(L label){
        //TODO
        checkRep();
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
                assert (nodeA != nodeB):
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
