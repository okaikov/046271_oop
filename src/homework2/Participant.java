package homework2;

import java.util.ArrayList;
import java.util.Iterator;

public class Participant extends Filter<String,Transaction> implements Simulatable<String>{

    final private double fee;
    private ArrayList<Transaction> currentTransactions;
    private ArrayList<Transaction> storageBuffer;

    Participant(double fee, String nodeLabel){
        super(nodeLabel);
        this.fee = fee;
        this.currentTransactions = super.currentWorkingObjects;
        this.storageBuffer = super.storageBuffer;
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

    public void removeFromCurrentTransactions(Transaction transaction) {
        this.currentTransactions.remove(transaction);
    }

    public ArrayList<Transaction> getStorageBuffer() {
        return new ArrayList<>(this.storageBuffer);
    }

    public void addToStorageBuffer(Transaction transaction) {
        this.storageBuffer.add(transaction);
    }

    @Override
    public void simulate(BipartiteGraph<String> graph) {
        final Node<String> node = graph.getNodeByLabel(this.nodeLabel);
        ArrayList<String> childrenLabels = new ArrayList<>(node.getChildrenList());
        System.out.println("Simulating Participant " + nodeLabel);


        for (Iterator<Transaction> it = this.currentTransactions.iterator(); it.hasNext();){
            Transaction tx = it.next();
            if (tx.getDest().equals(this.nodeLabel)){ // Is the transaction directed to this?
                addToStorageBuffer(tx);
                it.remove();
                removeFromCurrentTransactions(tx);
            }else {
                if(childrenLabels.isEmpty()) {
                    continue;
                }
                Channel channel = (Channel)graph.getNodeByLabel(childrenLabels.get(0)).getNodeObject();
                if ((tx.getValue() + channel.getCount() > channel.getLimit()) || // The channel limit is too small
                        (tx.getValue() <= fee)){
                    addToStorageBuffer(tx);
                    it.remove();
                    removeFromCurrentTransactions(tx);
                } else {
                    it.remove();
                    removeFromCurrentTransactions(tx);
                    addToStorageBuffer(new Transaction(nodeLabel, this.fee));
                    channel.addTransaction(new Transaction(tx.getDest(), tx.getValue() - this.fee));
                }
            }
        }
    }
}
