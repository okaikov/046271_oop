package homework4;

public class OneByOne extends Scheduler {

    private int nextIndex = 0;
    private int numOfElements;

    public OneByOne (int rows, int columns){
        super(rows, columns);
        numOfElements = rows * columns;
    }


    @Override
    public int getNext() {
        int nxtIdx = nextIndex;
        if (nextIndex < numOfElements) {
            nextIndex++;
        }
        return nxtIdx;
    }

    @Override
    public boolean hasNext() {
        return (nextIndex < numOfElements);
    }

    @Override
    public void reset() {
        nextIndex = 0;
    }

}
