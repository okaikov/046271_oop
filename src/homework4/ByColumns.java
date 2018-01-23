package homework4;

/**
 * ByColumns is an abstraction of scheduling policy to access members of two-dimensional matrix
 * by columns order (e.g. if row X column size is 5X5, the order is 0, 5, 10, 15, 20, 1, 6, 11, 16, 21, ...).
 */
public class ByColumns extends Scheduler {
    private int nextCol = 0;
    private int nextRow = 0;
    private int numCols;
    private int numRows;

    // Abstraction Function: Represents a scheduler that allows to access members of two-dimensional matrix of size
    // (rows X columns) in incremental order. The next member to access is represented by nextIndex, the number of
    // elements in the matrix is represented by numOfElements.

    // Representation Invariant: 0 =< nextIndex < numOfElements,  numOfElements >= 1

    public ByColumns (int rows, int columns){
        super(rows, columns);
        this.numCols = columns;
        this.numRows = rows;
    }


    @Override
    public int getNext() {
        int nxtIdx = nextRow * numCols + nextCol;
        if (nextCol < numCols && nextRow < numRows) {
            nextRow++;
            if(nextRow == numRows) {
                nextRow = 0;
                nextCol++;
            }
        }
        return nxtIdx;
    }

    @Override
    public boolean hasNext() {
        return (nextCol < numCols && nextRow < numRows);
    }

    @Override
    public void reset() {
        nextCol = 0;
        nextRow = 0;
    }

}
