package homework2;

import java.util.ArrayList;

public class Filter<WorkObject> {

    private WorkObject currentWorkObject;
    private ArrayList<WorkObject> tempBuffer;
    private ArrayList<WorkObject> storageBuffer;

    public Filter() {
        this.currentWorkObject = null;
        this.tempBuffer = new ArrayList<>();
        this.storageBuffer = new ArrayList<>();
    }




}
