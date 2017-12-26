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
        checkRep();
    }

    double getFee(){
        checkRep();
        return fee;
    }


    public ArrayList<Transaction> getCurrentTransactions() {
        checkRep();
        return new ArrayList<>(this.currentTransactions);

    }

    public void addToCurrentTransactions(Transaction transaction) {
        checkRep();
        this.currentTransactions.add(transaction);
        checkRep();
    }

    public void removeFromCurrentTransactions(Transaction transaction) {
        checkRep();
        this.currentTransactions.remove(transaction);
        checkRep();
    }

    public ArrayList<Transaction> getStorageBuffer() {
        checkRep();
        return new ArrayList<>(this.storageBuffer);
    }

    public void addToStorageBuffer(Transaction transaction) {
        checkRep();
        this.storageBuffer.add(transaction);
        checkRep();
    }

    @Override
    public void simulate(BipartiteGraph<String> graph) {
        checkRep();
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
        checkRep();
    }

    private void checkRep(){
        assert (this.nodeLabel != null):
                "Node label is null";
        assert (this.currentTransactions != null):
                "current transactions is null";
        assert (this.storageBuffer != null):
                "storage buffer is null";
        assert (this.fee >= 0):
                "fee < 0";
    }

}
