package homework4;

/**
 * OneByOne is an abstraction of scheduling policy to access members of two-dimensional matrix one by one
 * in incremental order (0, 1, 2, 3, ...).
 */
public class OneByOne extends Scheduler {

    // Abstraction Function: Represents a scheduler that allows to access members of two-dimensional matrix of size
    // (rows X columns) in incremental order. The next member to access is represented by nextIndex, the number of
    // elements in the matrix is represented by numOfElements.

    // Representation Invariant: 0 =< nextIndex < numOfElements,  numOfElements >= 1

    private int nextIndex = 0;
    private int numOfElements;

    /**
     * @modifies this
     * @effects Creates a new instance of OneByOne. If columns or rows is less than 1, prints an error.
     */
    public OneByOne (int rows, int columns){
        super(rows, columns);
        numOfElements = rows * columns;
    }

    /**
     * @modifies this
     * @effects Returns the next index in the matrix in incremental order.
     */
    @Override
    public int getNext() {
        checkRep();
        int nxtIdx = nextIndex;
        if (nextIndex < numOfElements) {
            nextIndex++;
        }
        checkRep();
        return nxtIdx;
    }

    /**
     * @effects Returns whether there are additional indexes that have not been accessed.
     */
    @Override
    public boolean hasNext() {
        checkRep();
        return (nextIndex < numOfElements);
    }

    /**
     * @effects Resets the scheduler.
     */
    @Override
    public void reset() {
        checkRep();
        nextIndex = 0;
        checkRep();
    }

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep(){
        assert numOfElements >= 1 : "Invalid number of elements.";
        assert (nextIndex >= 0 && nextIndex < numOfElements) : "Invalid nex index.";
    }
}
