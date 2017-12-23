package homework2;
import java.util.*;


public class BipartiteGraph<L> {


    private final String graphName;
    private HashMap<L, Node<L>> nodeHashmap; // <label, node>



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
        this.nodeHashmap = new HashMap<>();
        checkRep();
    }

    /**
     * @modifies graph named graphName
     * @effects Adds a black node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addBlackNode(L nodeLabel, Object blackNode) {
        checkRep();
        return addNode(nodeLabel, blackNode, Node.NodeColor.BLACK);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a white node represented by the L nodeName to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    public boolean addWhiteNode(L nodeLabel, Object whiteNode) {
        checkRep();
        return addNode(nodeLabel, whiteNode, Node.NodeColor.WHITE);
    }


    /**
     * @modifies graph named graphName
     * @effects Adds a node represented by the L nodeLabel to the graph named graphName.
     * if nodeName == null || node == null || node is in the graph: return false
     */
    private boolean addNode(L nodeLabel, Object node, Node.NodeColor nodeColor) {
        checkRep();
        if (nodeLabel == null){
            System.out.println("node label is null");
            return false;
        }
        if (graphContains(nodeLabel)){
            System.out.println("graph allready contains Node");
            return false;
        }

        Node<L> newNode = new Node<>(node, nodeLabel, nodeColor);
        this.nodeHashmap.put(nodeLabel, newNode);
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
        Node<L> parentNode = this.nodeHashmap.get(parentLabel);
        if (parentNode.childrenContainsLabel(childLabel)){
            System.out.println("there is already an edge from parent->child");
            return false;
        }

        // check if we have edge with label X from parent
        if (parentNode.childrenContainsEdgeLabel(edgeLabel)){
            System.out.println("there is already an edge from parent with edge label");
            return false;
        }

        // check if we have edge from some parent to child with label X
        Node<L> childNode = this.nodeHashmap.get(childLabel);
        if (childNode.parentsContainsEdgeLabel(edgeLabel)){
            System.out.println("there is already an edge to child with edge label");
            return false;
        }

        parentNode.addChild(childLabel, edgeLabel);
        childNode.addParent(parentLabel,edgeLabel);
        checkRep();
        return true;
    }

    public boolean removeEdge(L parentLabel, L childLabel, L edgeLabel) {
        //TODO
        checkRep();
        Node<L> parentNode = this.nodeHashmap.get(parentLabel);
        Node<L> childNode = this.nodeHashmap.get(childLabel);

        parentNode.removeChild(childLabel, edgeLabel);
        childNode.removeParent(parentLabel,edgeLabel);
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
        return listNodesByColor(Node.NodeColor.BLACK);
    }

    /**
     * @requires createGraph(graphName)
     * @return a space-separated list of the names of all the white nodes
     * 		   in the graph graphName, in alphabetical order.
     */
    public String listWhiteNodes() {
        //TODO
        checkRep();
        return listNodesByColor(Node.NodeColor.WHITE);
    }

    public ArrayList<L> getBlackNodes(){
        return getNodesByColor(Node.NodeColor.BLACK);
    }

    public ArrayList<L> getWhiteNodes(){
        return getNodesByColor(Node.NodeColor.WHITE);
    }


    private String listNodesByColor(Node.NodeColor nodeColor){
        //TODO
        checkRep();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<L> labels = getNodesByColor(nodeColor);
        for (L label : labels){
            nameList.add(label.toString());
        }
        java.util.Collections.sort(nameList);
        String result = String.join(" ", nameList);
        checkRep();
        return result;
    }

    private ArrayList<L> getNodesByColor(Node.NodeColor nodeColor){
        //TODO
        checkRep();
        ArrayList<L> labels = new ArrayList<>();
        for (Node<L> node : nodeHashmap.values()){
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
        ArrayList<L> result = this.nodeHashmap.get(parentLabel).getChildrenList();
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
        ArrayList<L> result = this.nodeHashmap.get(childLabel).getParentsList();
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
        Node<L> parentNode = this.nodeHashmap.get(parentLabel);
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
        Node<L> childNode = this.nodeHashmap.get(childLabel);
        checkRep();
        return childNode.getParentByEdgeLabel(edgeLabel);
    }




    public boolean graphContains(L nodeLabel){
        //TODO: check input
        checkRep();
        return this.nodeHashmap.containsKey(nodeLabel);
    }



    public Node.NodeColor getNodeColor(L nodeLabel){
        //TODO: check input, check if null, contains
//        assert (nodeLabel != null):
//                "node label is null";
//        assert (graphContains(nodeLabel)):
//                "node is not in the graph";
        checkRep();
        return this.nodeHashmap.get(nodeLabel).getNodeColor();
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
        return (getNodeColor(sourceLabel) == Node.NodeColor.BLACK && getNodeColor(destLabel) == Node.NodeColor.WHITE) ||
                (getNodeColor(sourceLabel) == Node.NodeColor.WHITE && getNodeColor(destLabel) == Node.NodeColor.BLACK);
    }

    public Node<L> getNodeByLabel(L label){
        //TODO
        checkRep();
        return this.nodeHashmap.get(label);
    }


    private void checkRep(){
        assert (this.graphName != null):
                "graph name is null";
        assert (this.nodeHashmap != null):
                "hashmap is null";


        for (Node<L> nodeA : this.nodeHashmap.values()){
            final List<Node<L>> reduecedNodeList = new ArrayList<>(this.nodeHashmap.values());
            reduecedNodeList.remove(nodeA);
            for (Node<L> nodeB : reduecedNodeList){
                assert (nodeA != nodeB):
                        "graph contains 2 nodes with the same label";
            }
            for (L parentLabel : nodeA.getParentsList()){
                Node<L> parentNode = this.nodeHashmap.get(parentLabel);
                assert (nodeA.getNodeColor() != parentNode.getNodeColor()):
                        "graph contains parent & child nodes with the same color";
            }
            for (L childLabel : nodeA.getChildrenList()){
                Node<L> childNode = this.nodeHashmap.get(childLabel);
                assert (nodeA.getNodeColor() != childNode.getNodeColor()):
                        "graph contains parent & child nodes with the same color";
            }
        }
    }
}
