package homework2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex<L> {
    private Object object;
    private HashMap<L,L> childrenHashmap; //<children label, edge label>
    private HashMap<L,L> parentsHashmap; //<parents label, edge label>
    private L label;
    private BipartiteGraph.VertexColor vertexColor;


    public Vertex(Object object, L label, BipartiteGraph.VertexColor vertexColor) {
        this.object = object;
        this.label = label;
        this.childrenHashmap = new HashMap<>();
        this.parentsHashmap = new HashMap<>();
        this.vertexColor = vertexColor;
        checkRep();
    }

    public BipartiteGraph.VertexColor getVertexColor() {
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
        childrenHashmap.put(childLabel, edgeLabel);
        checkRep();
        return true;
    }

    public boolean removeChild (L childLabel, L edgeLabel){
        //TODO
        checkRep();
        return childrenHashmap.remove(childLabel, edgeLabel);
    }

    public boolean addParent (L ParentLabel, L edgeLabel){
        //TODO
        checkRep();
        parentsHashmap.put(ParentLabel, edgeLabel);
        checkRep();
        return true;
    }

    public boolean removeParent (L ParentLabel, L edgeLabel){
        //TODO
        checkRep();
        return parentsHashmap.remove(ParentLabel, edgeLabel);
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
        assert (this.object != null):
                "vertex object is null";
        assert (this.childrenHashmap != null):
                "vertex children hashmap is null";
        assert (this.parentsHashmap != null):
                "vertex parents hashmap is null";
        assert (this.label != null):
                "vertex label is null";
        assert ((this.vertexColor == BipartiteGraph.VertexColor.BLACK) || this.vertexColor == BipartiteGraph.VertexColor.WHITE):
                "vertex contains invalid color";

        for (L vertexLabelA : this.parentsHashmap.keySet()) {
            for (L vertexLabelB : this.parentsHashmap.keySet()) {
                assert (vertexLabelA != vertexLabelB) :
                        "vertex parents contains 2 parents with the same label";
                assert (this.parentsHashmap.get(vertexLabelA) != this.parentsHashmap.get(vertexLabelB)) :
                        "vertex parents contains 2 parents with the same edge label";
            }
        }

        for (L vertexLabelA : this.childrenHashmap.keySet()) {
            for (L vertexLabelB : this.childrenHashmap.keySet()) {
                assert (vertexLabelA != vertexLabelB) :
                        "vertex children contains 2 children with the same label";
                assert (this.childrenHashmap.get(vertexLabelA) != this.childrenHashmap.get(vertexLabelB)):
                        "vertex children contains 2 children with the same edge label";
            }

        }
    }

}


