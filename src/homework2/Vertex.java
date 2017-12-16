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
    }

    public BipartiteGraph.VertexColor getVertexColor() {
        return this.vertexColor;
    }

    public boolean childrenContainsLabel(L childLabel){
        //TODO
        return childrenHashmap.containsKey(childLabel);
    }

    public boolean childrenContainsEdgeLabel(L edgeLabel){
        //TODO
        return childrenHashmap.containsValue(edgeLabel);
    }

    public boolean ParentsContainsLabel(L parentLabel){
        //TODO
        return parentsHashmap.containsKey(parentLabel);
    }

    public boolean parentsContainsEdgeLabel(L edgeLabel){
        //TODO
        return parentsHashmap.containsValue(edgeLabel);
    }

    public boolean addChild (L childLabel, L edgeLabel){
        //TODO
        childrenHashmap.put(childLabel, edgeLabel);
        return true;
    }

    public boolean removeChild (L childLabel, L edgeLabel){
        //TODO
        return childrenHashmap.remove(childLabel, edgeLabel);
    }

    public boolean addParent (L ParentLabel, L edgeLabel){
        //TODO
        parentsHashmap.put(ParentLabel, edgeLabel);
        return true;
    }

    public boolean removeParent (L ParentLabel, L edgeLabel){
        //TODO
        return parentsHashmap.remove(ParentLabel, edgeLabel);
    }

    public L getLabel(){
        return label;
    }

    public Set<L> getChildrenLabelList(){
        //TODO
        return childrenHashmap.keySet();
    }

    public Set<L> getParentsLabelList(){
        //TODO
        return parentsHashmap.keySet();
    }

    public L getChildByEdgeLabel(L edgeLabel) {
        //TODO

        return getVertexByEdgeLabel(edgeLabel, childrenHashmap.entrySet());
    }

    public L getParentByEdgeLabel(L edgeLabel) {
        //TODO

        return getVertexByEdgeLabel(edgeLabel, parentsHashmap.entrySet());
    }

    private L getVertexByEdgeLabel(L edgeLabel, Set<Map.Entry<L,L>> entrySet) {
        //TODO
        for (HashMap.Entry<L, L> entry : entrySet) {
            L iterChildLabel = entry.getKey();
            L iterEdgeLabel = entry.getValue();
            if (edgeLabel == iterEdgeLabel){
                return iterChildLabel;
            }
        }
        return null;
    }




}


