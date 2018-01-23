package homework4;

/**
 * OddEven is an abstraction of scheduling policy to access members of two-dimensional matrix one by one
 * in incremental order starting from even indexes and following odd indexes.
 * For example, if the matrix is of size rows=5, columns=6 (overall 30 members) the order would be:
 * 0, 2, 4, ... , 28, 1, 3, 5, ..., 29.
 */
public class OddEven extends Scheduler {
    // Abstraction Function: Represents a scheduler that allows to access members of two-dimensional matrix of size
    // (rows X columns) in incremental order starting from even indexes and following odd indexes. The next member to access is represented by nextIndex, the number of
    // elements in the matrix is represented by numOfElements. nextIndex can reach numOfElements - it will mean
    // that all the indexes have been accessed.

    // Representation Invariant: 0 =< nextIndex <= numOfElements + 1,  numOfElements >= 1 (the 2nd is checked by super)

    private int nextIndex = 0;
    private int numOfElements;

    /**
     * @modifies this
     * @effects Creates a new instance of OddEven. If columns or rows is less than 1, prints an error.
     */
    public OddEven (int rows, int columns){
        super(rows, columns);
        numOfElements = rows * columns;
        checkRep();
    }

    /**
     * @modifies this
     * @effects Returns the next index in the matrix in incremental order (first even, then odd).
     */
    @Override
    public int getNext() {
        checkRep();
        int nxtIdx = nextIndex;
        if (nextIndex < numOfElements) {
            nextIndex += 2;
        }
        if (numOfElements % 2 == 1) {
            if (nextIndex == numOfElements + 1) {
                nextIndex = 1;
            }
        }
        else {
            if (nextIndex == numOfElements) {
                nextIndex = 1;
            }
        }
        checkRep();
        return nxtIdx;
    }

    /**
     * @modifies this
     * @effects Returns the next index in the matrix in incremental order (first even, then odd).
     */
    @Override
    public boolean hasNext() {
        return nextIndex < numOfElements;
    }

    /**
     * @effects Resets the scheduler.
     */
    @Override
    public void reset() {
        nextIndex = 0;
    }

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep(){
        assert numOfElements >= 1 : "Invalid number of elements.";
        assert (nextIndex >= 0 && nextIndex <= numOfElements + 1) : "Invalid next index.";
    }

}
