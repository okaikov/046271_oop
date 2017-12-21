package homework2;

import java.util.ArrayList;

public class Pipe<WorkObject> {
    private final int maxCapacity;
    private int currentCapacity;
    ArrayList<WorkObject> buffer;

    public Pipe(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.currentCapacity = 0;
        this.buffer = new ArrayList<>();
    }



}
