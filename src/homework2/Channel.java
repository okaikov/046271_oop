package homework2;

import java.util.ArrayList;

public class Channel {

    private double limit;
    private double count;
    private ArrayList<Transaction> transactionBuffer;

    public Channel(double limit) {
        this.limit = limit;
        this.count = 0;
        this.transactionBuffer = new ArrayList<>();
    }


    public double getLimit() {
        return limit;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public void addTransaction(Transaction transaction){
        this.transactionBuffer.add(transaction);
    }

    public ArrayList<Transaction> getTransactionBuffer() {
        return transactionBuffer;
    }


}
