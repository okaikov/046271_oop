package homework2;

import java.util.ArrayList;

/**
 * Pipe is designated to move working objects from one node to another.
 *
 */
public abstract class Pipe<L,W> implements Simulatable<L>{ //L: label, W: working object

    // Abstraction Function: Pipe represents a pipe in the system with or without capacity.
    //                       The capacity is represented by this.capacity. Infinite capacity is represented by
    //                       this.capacity < 0.0. The label of the node that contains the pipe is represented by nodeLabel.
    //                       The working objects that are currently in the pipe are represented by workingObjectBuffer list.

    // Representation Invariant: nodeLabel != null, capacity != null, workingObjectBuffer != null.
    protected L nodeLabel;
    private double capacity;
    protected ArrayList<W> workingObjectBuffer;


    /**
     * @modifies this
     * @effects creates a pipe contained by node named nodeLabel, of infinite capacity and working objects in it.
     *          In case of nodeLabel is null the program will exit.
     */
    public Pipe(L nodeLabel){
        if (nodeLabel == null){
            System.err.println("Pipe: nodeLabel = null.");
            System.exit(1);
        }
        this.nodeLabel = nodeLabel;
        this.capacity = -1.0; //if capacity is negative, this means that there is no limit to capacity
        this.workingObjectBuffer = new ArrayList<>();
        checkRep();
    }
    /**
     * @modifies this
     * @effects creates a pipe labeled by nodeLabel, with capacity and working objects in it.
     */
    public Pipe(L nodeLabel, double capacity){
        if (nodeLabel == null){
            System.err.println("Pipe: nodeLabel = null.");
        }
        if (this.capacity < 0.0){
            System.err.println("capacity is negative.");
        }
        this.nodeLabel = nodeLabel;
        this.capacity = capacity;
        this.workingObjectBuffer = new ArrayList<>();
        checkRep();
    }

    private void checkRep(){
        assert nodeLabel != null : "nodeLabel is Null reference.";
        assert workingObjectBuffer != null : "workingObjectBuffer is Null reference.";
    }

}
