package homework2;

import java.text.Collator;
import java.util.*;

public class Vertex<L> {
    protected enum VertexColor {
        BLACK, WHITE
    }

    private Object object;
    private HashMap<L,L> childrenHashmap; //<children label, edge label>
    private HashMap<L,L> parentsHashmap; //<parents label, edge label>
    private L label;
    private VertexColor vertexColor;

    private Collection<String> childrenSortedNames;
    private String childrenSortedString;
    private Collection<String> parentsSortedNames;
    private String parentsSortedString;


    public Vertex(Object object, L label, VertexColor vertexColor) {
        this.object = object;
        this.label = label;
        this.childrenHashmap = new HashMap<>();
        this.parentsHashmap = new HashMap<>();
        this.vertexColor = vertexColor;
        this.childrenSortedNames = new TreeSet<>(Collator.getInstance());
        this.childrenSortedString = "";
        this.parentsSortedNames = new TreeSet<>(Collator.getInstance());
        this.parentsSortedString = "";
        checkRep();
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }


    public VertexColor getVertexColor() {
        checkRep();
        return this.vertexColor;
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
        this.childrenSortedNames.add(childLabel.toString());
        this.childrenSortedString = String.join(" ",this.childrenSortedNames);
        checkRep();
        return true;
    }

    public boolean removeChild (L childLabel, L edgeLabel){
        //TODO
        checkRep();
        this.childrenSortedNames.remove(childLabel.toString());
        this.childrenSortedString = String.join(" ",this.childrenSortedNames);
        checkRep();
        return childrenHashmap.remove(childLabel, edgeLabel); //TODO: extract variable, and put checkrep after
    }

    public boolean addParent (L parentLabel, L edgeLabel){
        //TODO
        checkRep();
        parentsHashmap.put(parentLabel, edgeLabel);
        this.parentsSortedNames.add(parentLabel.toString());
        this.parentsSortedString = String.join(" ",this.parentsSortedNames);
        checkRep();
        return true;
    }

    public boolean removeParent (L parentLabel, L edgeLabel){
        //TODO
        checkRep();
        this.parentsSortedNames.remove(parentLabel.toString());
        this.parentsSortedString = String.join(" ",this.parentsSortedNames);
        return parentsHashmap.remove(parentLabel, edgeLabel);
    }

    public String getChildrenSortedString() {
        checkRep();
        return childrenSortedString;
    }

    public String getParentsSortedString() {
        checkRep();
        return parentsSortedString;
    }

    public L getLabel(){
        checkRep();
        return label;
    }

    public Set<L> getChildrenLabelList(){
        //TODO
        checkRep();
        return childrenHashmap.keySet();
    }

    public Set<L> getParentsLabelList(){
        //TODO
        checkRep();
        return parentsHashmap.keySet();
    }

    public L getChildByEdgeLabel(L edgeLabel) {
        //TODO
        checkRep();
        return getVertexByEdgeLabel(edgeLabel, childrenHashmap.entrySet());
    }

    public L getParentByEdgeLabel(L edgeLabel) {
        //TODO
        checkRep();
        return getVertexByEdgeLabel(edgeLabel, parentsHashmap.entrySet());
    }

    private L getVertexByEdgeLabel(L edgeLabel, Set<Map.Entry<L,L>> entrySet) {
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
                "vertex children hashmap is null";
        assert (this.parentsHashmap != null):
                "vertex parents hashmap is null";
        assert (this.label != null):
                "vertex label is null";
        assert ((this.vertexColor == VertexColor.BLACK) || this.vertexColor == VertexColor.WHITE):
                "vertex contains invalid color";

        for (L vertexLabelA : this.parentsHashmap.keySet()) {

            final List<L> reducedList = new ArrayList<>(this.parentsHashmap.keySet());
            reducedList.remove(vertexLabelA);

            for (L vertexLabelB : reducedList) {
                assert (vertexLabelA != vertexLabelB) :
                        "vertex parents contains 2 parents with the same label";
                assert (this.parentsHashmap.get(vertexLabelA) != this.parentsHashmap.get(vertexLabelB)) :
                        "vertex parents contains 2 parents with the same edge label";
            }
        }

        for (L vertexLabelA : this.childrenHashmap.keySet()) {

            final List<L> reducedList = new ArrayList<>(this.childrenHashmap.keySet());
            reducedList.remove(vertexLabelA);

            for (L vertexLabelB : reducedList) {
                assert (vertexLabelA != vertexLabelB) :
                        "vertex children contains 2 children with the same label";
                assert (this.childrenHashmap.get(vertexLabelA) != this.childrenHashmap.get(vertexLabelB)):
                        "vertex children contains 2 children with the same edge label";
            }

        }
    }


}


