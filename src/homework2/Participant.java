package homework2;

import java.util.ArrayList;

public class Participant {
    final private double fee;
    private ArrayList<Transaction> currentTransactions;
    private ArrayList<Transaction> storageBuffer;

    Participant(double fee){
        this.fee = fee;
        this.currentTransactions = new ArrayList<>();
        this.storageBuffer = new ArrayList<>();
    }

    double getFee(){
        return fee;
    }


    public ArrayList<Transaction> getCurrentTransactions() {
        return new ArrayList<>(this.currentTransactions);

    }

    public void addToCurrentTransactions(Transaction transaction) {
        this.currentTransactions.add(transaction);
    }

    public ArrayList<Transaction> getStorageBuffer() {
        return new ArrayList<>(this.storageBuffer);
    }

    public void addToStorageBuffer(Transaction transaction) {
        this.storageBuffer.add(transaction);
    }


    public void simulate(){

    }





}
