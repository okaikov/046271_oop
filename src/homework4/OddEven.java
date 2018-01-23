package homework4;

public class OddEven extends Scheduler {

    private int nextIndex = 0;
    private int numOfElements;

    public OddEven (int rows, int columns){
        super(rows, columns);
        numOfElements = rows * columns;
    }


    @Override
    public int getNext() {
        int nxtIdx = nextIndex;
        if (nextIndex < numOfElements) {
            nextIndex += 2;
        }
        if (nextIndex > numOfElements) {
            nextIndex = 1;
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
