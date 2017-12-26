package homework2;

import java.util.ArrayList;

public abstract class Filter<L,W> implements Simulatable<L> { //L: label, W: working object
    protected L nodeLabel;
    protected ArrayList<W> currentWorkingObjects;
    protected ArrayList<W> storageBuffer;

    public Filter(L nodeLabel) {
        this.nodeLabel = nodeLabel;
        this.currentWorkingObjects = new ArrayList<>();
        this.storageBuffer = new ArrayList<>();
    }
}
