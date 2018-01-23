package homework4;

/**
 * Scheduler is an abstraction of policy to access members of two-dimensional matrix until all the members were accessed.
 */
public abstract class Scheduler {
    // Abstraction Function: Represents a scheduler that allows to access members of two-dimensional matrix of size (rows X columns)

    // Representation Invariant: rows >= 1, columns >= 1
    private int rows;
    private int columns;

    /**
     * @modifies this
     * @effects Creates a new instance of Scheduler. If columns or rows is less than 1, prints an error.
     */
    public Scheduler(int rows, int columns) {
        if(rows < 1 || columns < 1) {
            System.err.println("Invalid size");
            return;
        }
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * @effects Returns number of columns.
     */

    int getColumns(){
        checkRep();
        return columns;
    }

    /**
     * @effects Returns number of rows.
     */
    int getRows(){
        checkRep();
        return rows;
    }

    /**
     * @effects Returns the next index in the matrix.
     */
    abstract public int getNext();

    /**
     * @effects Returns whether there are additional indexes that have not been accessed.
     */
    abstract public boolean hasNext();

    /**
     * @effects Resets the scheduler.
     */
    abstract public void reset();

    /**
     * Checks the representation invariant. If it is violated, throws AssertionError.
     */
    private void checkRep() {
        assert rows >= 1 && columns >= 1 : "Invalid size.";
    }
}
