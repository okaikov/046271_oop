package homework4;

import java.util.ArrayList;

public class Random extends Scheduler{

    private ArrayList<Integer> remainingIndexes = new ArrayList<>();

    public Random (int rows, int columns){
        super(rows, columns);
    }


    @Override
    public int getNext() {
        java.util.Random rnd = new java.util.Random();
        int idx = rnd.nextInt(remainingIndexes.size());
        return remainingIndexes.remove(idx);
    }

    @Override
    public boolean hasNext() {
        return (!remainingIndexes.isEmpty());
    }

    @Override
    public void reset() {
        remainingIndexes.clear();
        for (int idx = 0; idx < getRows() * getColumns(); idx++){
            remainingIndexes.add(idx);
        }
    }

}
