package homework2;

import java.util.ArrayList;

/**
 * Filter is designated to process the working objects from input pipes and send the results to the output pipes.
 * Filter has a storage buffer for worker objects.
 */
public abstract class Filter<L,W> implements Simulatable<L> { //L: label, W: working object
    // Abstraction Function: Filter represents a filter labeled by nodeLabel in the simulation system.
    //                       The currently processed working objects are represented by ArrayList currentWorkingObjects
    //                       of generic type W. The storage buffer is represented by ArrayList storageBuffer of working objects
    //                       of generic type W.
    //

    // Representation Invariant: nodeLabel != null, currentWorkingObjects != null, storageBuffer != null.

    protected L nodeLabel;
    protected ArrayList<W> currentWorkingObjects;
    protected ArrayList<W> storageBuffer;

    /**
     * @modifies this
     * @effects creates a filter, contained by node named by nodeLabel.
     */
    public Filter(L nodeLabel) {
        if (nodeLabel == null){
            System.err.println("Filter: nodeLabel = null");
            System.exit(1);
        }
        this.nodeLabel = nodeLabel;
        this.currentWorkingObjects = new ArrayList<>();
        this.storageBuffer = new ArrayList<>();
        checkRep();
    }

    private void checkRep(){
        assert nodeLabel != null : "Filter: nodeLabel = null";
        assert currentWorkingObjects != null : "Filter: currentWorkingObjects = null";
        assert storageBuffer != null : "Filter: storageBuffer = null";
    }
}
