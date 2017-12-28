package homework2;

import java.util.ArrayList;
import java.util.Iterator;


/**
 The Participant class implements a filter that is an participant in the transaction transfer system.
 A participant has a fee (fee) which he charges if he is a mediator in a transaction between
 other participants. When a participant receives a transaction, the transaction destination can be the participant himself, or
 another participant in the system. If the transaction is intended for him, he will store it in his storage buffer.
 Otherwise, two new transactions will be created. One designated for him which it will keep in buffer, and another transaction
 intended to be the original transaction destination, where its value shall be the amount of the original transfer after deduction of the fee.
 */
public class Participant extends Filter<String,Transaction> implements Simulatable<String>{

    // Abstraction Function: Participant represents a Participant withh fee represented by Double fee, currently processed transactions are represented by ArrayList currentTransactions,
    //                       and storage buffer is represented by ArrayList of transactions storageBuffer.

    // Representation Invariant: nodeLabel != null && currentTransactions != null && storageBuffer != null && fee >= 0

    final private double fee;
    private ArrayList<Transaction> currentTransactions;
    private ArrayList<Transaction> storageBuffer;

    /**
     *
     * @modifies this
     * @effects creates a new participant
     */
    Participant(double fee, String nodeLabel){
        super(nodeLabel);
        this.fee = fee;
        this.currentTransactions = super.currentWorkingObjects;
        this.storageBuffer = super.storageBuffer;
        checkRep();
    }

    /**
     * @modifies this
     * @effects Adds a new transaction to current transactions.
     */
    public void addToCurrentTransactions(Transaction transaction) {
        checkRep();
        this.currentTransactions.add(transaction);
        checkRep();
    }

    /**
     * @modifies this
     * @effects Removes a new transaction to current transactions.
     */
    public void removeFromCurrentTransactions(Transaction transaction) {
        checkRep();
        this.currentTransactions.remove(transaction);
        checkRep();
    }

    /**
     * @modifies this
     * @effects returns the storage buffer.
     */
    public ArrayList<Transaction> getStorageBuffer() {
        checkRep();
        return new ArrayList<>(this.storageBuffer);
    }

    /**
     * @modifies this
     * @effects adds a transaction to the storage buffer.
     */
    public void addToStorageBuffer(Transaction transaction) {
        checkRep();
        this.storageBuffer.add(transaction);
        checkRep();
    }


    /**
     * @modifies this, graph
     * @effects Simulates this participant in a system modeled by graph.
     */
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
