package homework2;

import java.util.*;

/**
 * {@code Node} is an object that be part of a graph. Node has "children" nodes and "parent" nodes.
 * The properties of {@code Node} are Color that can be black or white and label, that can be of any type {@code L}.
 * In addition {@code Node} can contain an object of any class.
 *
 */
public class Node<L> {

    // Abstraction Function: Represents a node of color this.NodeColor that has children edges and nodes and parent edges and nodes.
    //                       The children edges and nodes are represented by childrenHashmap with labels of children nodes as keys and labels of children edges as values.
    //                       The parent edges and nodes are represented by parentHashmap with labels of parent nodes as keys and labels of parent edges as values.
    //                       The represented node is labeled by this.label. The represented node can contain an object of any actual type, represented by this.object of declared type Object.

    // Representation Invariant: childrenHashmap != null, parentsHashmap != null, label != null, nodeColor != null, childrenList != null, parentsList != null, the labels of children nodes
    //                           are unique, the labels of children edges are unique, the labels of parent nodes are unique, the labels of parent edges are unique.
    protected enum NodeColor {
        BLACK, WHITE
    }

    private Object nodeObject;
    private HashMap<L,L> childrenHashmap; // key: children label, value: edge label>
    private HashMap<L,L> parentsHashmap; // key: parent label, value: edge label>
    private L label;
    private NodeColor nodeColor;
    private ArrayList<L> childrenList;
    private ArrayList<L> parentsList;



    /**
     * @modifies this
     * @effects Creates a new node named label with color nodeColor and nodeObject . The node is initially empty. if label == null || nodeColor == null, do not create the node.
     */
    public Node(Object nodeObject, L label, NodeColor nodeColor) {
        if (label == null || nodeColor == null){
            return;
        }
        this.nodeObject = nodeObject;
        this.label = label;
        this.childrenHashmap = new HashMap<>();
        this.parentsHashmap = new HashMap<>();
        this.nodeColor = nodeColor;
        this.childrenList = new ArrayList<>();
        this.parentsList = new ArrayList<>();
        checkRep();
    }

    /**
     * @effects return the node object
     */
    public Object getNodeObject() {
        checkRep();
        final Object result = this.nodeObject;
        checkRep();
        return result;
    }

    /**
     * @modifies this
     * @effects set the node's object to nodeObject.
     */
    public void setNodeObject(Object nodeObject) {
        checkRep();
        this.nodeObject = nodeObject;
        checkRep();
    }

    /**
     * @effects return the node's color
     */
    public NodeColor getNodeColor() {
        checkRep();
        return this.nodeColor;
    }

    /**
     * @effects return true if one of node's children contain childLabel, else return false
     */
    public boolean childrenContainsLabel(L childLabel){
        if (childLabel == null){
            return false;
        }
        checkRep();
        final boolean result = this.childrenHashmap.containsKey(childLabel);
        checkRep();
        return result;
    }

    /**
     * @effects return true if one of node's children edges contain edgeLabel , else return false.
     */
    public boolean childrenContainsEdgeLabel(L edgeLabel){
        if (edgeLabel == null){
            return false;
        }
        checkRep();
        final boolean result = this.childrenHashmap.containsValue(edgeLabel);
        checkRep();
        return result;
    }

    /**
     * @effects return true if one of node's parents contains label ParentLabel. else return false.
     */
    public boolean ParentsContainsLabel(L parentLabel){
        if (parentLabel == null){
            return  false;
        }
        checkRep();
        final boolean result = this.parentsHashmap.containsKey(parentLabel);
        checkRep();
        return result;
    }

    /**
     * @effects return true if one of node's parent edges contains label edgeLabel, else return false.
     */
    public boolean parentsContainsEdgeLabel(L edgeLabel){
        if (edgeLabel == null){
            return false;
        }
        checkRep();
        final boolean result = this.parentsHashmap.containsValue(edgeLabel);
        checkRep();
        return result;
    }
    /**
     * @modifies this
     * @effects return true and add a new child to the node. return false if  childLabel == null || edgeLabel == null.
     */
    public boolean addChild (L childLabel, L edgeLabel){
        if (childLabel == null || edgeLabel == null){
            return false;
        }
        checkRep();
        this.childrenHashmap.put(childLabel, edgeLabel);
        this.childrenList.add(childLabel);
        checkRep();
        return true;
    }

    /**
     * @modifies this
     * @effects return true and remove a child from the node. return false if  childLabel == null || edgeLabel == null.
     */

    public boolean removeChild (L childLabel, L edgeLabel){
        if (childLabel == null || edgeLabel == null){
            return false;
        }
        checkRep();
        this.childrenHashmap.remove(childLabel, edgeLabel);
        this.childrenList.remove(childLabel);
        checkRep();
        return true;
    }

    /**
     * @modifies this
     * @effects return true and add a parent to the node. return false if  parentLabel == null || edgeLabel == null.
     */
    public boolean addParent (L parentLabel, L edgeLabel){
        if (parentLabel == null || edgeLabel == null){
            return false;
        }
        checkRep();
        this.parentsHashmap.put(parentLabel, edgeLabel);
        this.parentsList.add(parentLabel);
        checkRep();
        return true;
    }

    /**
     * @modifies this
     * @effects return true and remove a parent from the node. return false if  parentLabel == null || edgeLabel == null.
     */
    public boolean removeParent (L parentLabel, L edgeLabel){
        if (parentLabel == null || edgeLabel == null){
            return false;
        }
        checkRep();
        this.parentsHashmap.remove(parentLabel, edgeLabel);
        this.parentsList.remove(parentLabel);
        checkRep();
        return true;
    }

    /**
     * @effects return node's children list
     */
    public ArrayList<L> getChildrenList() {
        checkRep();
        final ArrayList<L> result = this.childrenList;
        checkRep();
        return result;
    }

    /**
     * @effects return node's parents list
     */
    public ArrayList<L> getParentsList() {
        checkRep();
        final ArrayList<L> result = this.parentsList;
        checkRep();
        return result;
    }

    /**
     * @effects return node's label
     */
    public L getLabel(){
        checkRep();
        final L result = this.label;
        checkRep();
        return result;
    }

    /**
     * @modifies this
     * @effects if one of node's children contain an edge with label: edgeLabel, return the child label. if edgeLabel == null, return null.
     */
    public L getChildByEdgeLabel(L edgeLabel) {
        if (edgeLabel == null){
            return null;
        }
        checkRep();
        final L result = getNodeByEdgeLabel(edgeLabel, this.childrenHashmap.entrySet());
        checkRep();
        return result;
    }

    /**
     * @modifies this
     * @effects if one of node's parents contain an edge with label: edgeLabel, return the parent label. if edgeLabel == null, return null.
     */
    public L getParentByEdgeLabel(L edgeLabel) {
        if (edgeLabel == null){
            return null;
        }
        checkRep();
        final L result = getNodeByEdgeLabel(edgeLabel, this.parentsHashmap.entrySet());
        checkRep();
        return result;
    }

    private L getNodeByEdgeLabel(L edgeLabel, Set<Map.Entry<L,L>> entrySet) {
        if (edgeLabel == null || entrySet == null){
            return null;
        }
        checkRep();
        for (HashMap.Entry<L, L> entry : entrySet) {
            L iterChildLabel = entry.getKey();
            L iterEdgeLabel = entry.getValue();
            if (edgeLabel == iterEdgeLabel){
                checkRep();
                return iterChildLabel;
            }
        }
        checkRep();
        return null;
    }


    private void checkRep(){
        assert (this.childrenHashmap != null):
                "Node children hashmap is null";
        assert (this.parentsHashmap != null):
                "Node parents hashmap is null";
        assert (this.label != null):
                "Node label is null";
        assert ((this.nodeColor == NodeColor.BLACK) || this.nodeColor == NodeColor.WHITE):
                "Node contains invalid color";

        // Check if the labels of the parent nodes and edges are unique.
        for (L nodeLabelA : this.parentsHashmap.keySet()) {

            final List<L> reducedList = new ArrayList<>(this.parentsHashmap.keySet());
            reducedList.remove(nodeLabelA);

            for (L nodeLabelB : reducedList) {
                assert (nodeLabelA != nodeLabelB) :
                        "Node parents contains 2 parents with the same label";
                assert (this.parentsHashmap.get(nodeLabelA) != this.parentsHashmap.get(nodeLabelB)) :
                        "Node parents contains 2 parents with the same edge label";
            }
        }

        // Check if the labels of the children nodes and edges are unique.
        for (L nodeLabelA : this.childrenHashmap.keySet()) {

            final List<L> reducedList = new ArrayList<>(this.childrenHashmap.keySet());
            reducedList.remove(nodeLabelA);

            for (L nodeLabelB : reducedList) {
                assert (nodeLabelA != nodeLabelB) :
                        "Node children contains 2 children with the same label";
                assert (this.childrenHashmap.get(nodeLabelA) != this.childrenHashmap.get(nodeLabelB)):
                        "Node children contains 2 children with the same edge label";
            }
        }
    }
}


