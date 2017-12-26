package homework2;

import java.util.ArrayList;

public abstract class Pipe<L,W> implements Simulatable<L>{ //L: label, W: working object
    protected L nodeLabel;
    private double capacity;
    protected ArrayList<W> workingObjectBuffer;

    public Pipe(L nodeLabel){
        this.nodeLabel = nodeLabel;
        this.capacity = -1.0; //if capacity is negative, this means that there is no limit to capacity
        this.workingObjectBuffer = new ArrayList<>();
    }

    public Pipe(L nodeLabel, double capacity){
        this.nodeLabel = nodeLabel;
        this.capacity = capacity;
        this.workingObjectBuffer = new ArrayList<>();
    }

}
