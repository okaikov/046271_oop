package homework4;

public abstract class Scheduler {
    private int rows;
    private int columns;

    public Scheduler(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    int getColumns(){
        return columns;
    }

    int getRows(){
        return rows;
    }

    abstract public int getNext();

    abstract public boolean hasNext();

    abstract public void reset();
}
