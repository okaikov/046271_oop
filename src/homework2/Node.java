package homework2;

import java.util.*;

public class Node<L> {
    protected enum NodeColor {
        BLACK, WHITE
    }

    private Object object;
    private HashMap<L,L> childrenHashmap; //<children label, edge label>
    private HashMap<L,L> parentsHashmap; //<parents label, edge label>
    private L label;
    private NodeColor nodeColor;
    private ArrayList<L> childrenList;
    private ArrayList<L> parentsList;

    public Node(Object object, L label, NodeColor nodeColor) {
        this.object = object;
        this.label = label;
        this.childrenHashmap = new HashMap<>();
        this.parentsHashmap = new HashMap<>();
        this.nodeColor = nodeColor;
        this.childrenList = new ArrayList<>();
        this.parentsList = new ArrayList<>();
        checkRep();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public NodeColor getNodeColor() {
        checkRep();
        return this.nodeColor;
    }

    public boolean childrenContainsLabel(L childLabel){
        //TODO
        checkRep();
        return childrenHashmap.containsKey(childLabel);
    }

    public boolean childrenContainsEdgeLabel(L edgeLabel){
        //TODO
        checkRep();
        return childrenHashmap.containsValue(edgeLabel);
    }

    public boolean ParentsContainsLabel(L parentLabel){
        //TODO
        checkRep();
        return parentsHashmap.containsKey(parentLabel);
    }

    public boolean parentsContainsEdgeLabel(L edgeLabel){
        //TODO
        checkRep();
        return parentsHashmap.containsValue(edgeLabel);
    }

    public boolean addChild (L childLabel, L edgeLabel){
        //TODO
        checkRep();
        this.childrenHashmap.put(childLabel, edgeLabel);
        this.childrenList.add(childLabel);
        checkRep();
        return true;
    }

    public boolean removeChild (L childLabel, L edgeLabel){
        //TODO
        checkRep();
        childrenHashmap.remove(childLabel, edgeLabel);
        this.childrenList.remove(childLabel);
        checkRep();
        return true;
    }

    public boolean addParent (L parentLabel, L edgeLabel){
        //TODO
        checkRep();
        this.parentsHashmap.put(parentLabel, edgeLabel);
        this.parentsList.add(parentLabel);
        checkRep();
        return true;
    }

    public boolean removeParent (L parentLabel, L edgeLabel){
        //TODO
        checkRep();
        parentsHashmap.remove(parentLabel, edgeLabel);
        this.parentsList.remove(parentLabel);
        return true;
    }

    public ArrayList<L> getChildrenList() {
        checkRep();
        return this.childrenList;
    }

    public ArrayList<L> getParentsList() {
        checkRep();
        return parentsList;
    }

    public L getLabel(){
        checkRep();
        return label;
    }

    public L getChildByEdgeLabel(L edgeLabel) {
        //TODO
        checkRep();
        return getNodeByEdgeLabel(edgeLabel, childrenHashmap.entrySet());
    }

    public L getParentByEdgeLabel(L edgeLabel) {
        //TODO
        checkRep();
        return getNodeByEdgeLabel(edgeLabel, parentsHashmap.entrySet());
    }

    private L getNodeByEdgeLabel(L edgeLabel, Set<Map.Entry<L,L>> entrySet) {
        //TODO
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


