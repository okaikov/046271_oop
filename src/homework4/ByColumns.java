package homework4;

public class ByColumns extends Scheduler {
    private int nextCol = 0;
    private int nextRow = 0;
    private int numCols;
    private int numRows;

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
