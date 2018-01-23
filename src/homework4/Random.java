package homework4;

import java.util.ArrayList;

/**
 * Random is an abstraction of scheduling policy to access members of two-dimensional matrix one by one
 * in random order.
 */
public class Random extends Scheduler{
    // Abstraction Function: Represents a scheduler that allows to access members of two-dimensional matrix of size
    // (rows X columns) in random order. The remaining indexes that have not been accessed are represented by
    // remainingIndexes ArrayList.

    // Representation Invariant: remainingIndexes != null,  numOfElements >= 1 (the 2nd is checked by super)
    private ArrayList<Integer> remainingIndexes = new ArrayList<>();


    /**
     * @modifies this
     * @effects Creates a new instance of Random. If columns or rows is less than 1, prints an error.
     */
    public Random (int rows, int columns){
        super(rows, columns);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Returns the next random index that has not been returned before.
     */
    @Override
    public int getNext() {
        checkRep();
        java.util.Random rnd = new java.util.Random();
        int idx = rnd.nextInt(remainingIndexes.size());
        checkRep();
        return remainingIndexes.remove(idx);
    }

    /**
     * @modifies this
     * @effects Returns the next index in the matrix in incremental order (first even, then odd).
     */
    @Override
    public boolean hasNext() {
        checkRep();
        return (!remainingIndexes.isEmpty());
    }

    /**
     * @effects Resets the scheduler.
     */
    @Override
    public void reset() {
        checkRep();
        remainingIndexes.clear();
        for (int idx = 0; idx < getRows() * getColumns(); idx++){
            remainingIndexes.add(idx);
        }
        checkRep();
    }

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep(){
        assert remainingIndexes != null : "remainingIndexes is a null reference.";
    }
}
