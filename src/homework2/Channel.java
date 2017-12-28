package homework2;

import java.util.ArrayList;


/**
 * Channel is a pipe to transfer the transactions. It has a limit of the maximal sum of transaction values.
 * Channel is a pipe of infinite capacity.
 */
public class Channel extends Pipe<String, Transaction> implements Simulatable<String>{

    // Abstraction Function: Channel represents a channel for transferring the transactions. Limit is
    //                       represented by double limit. The transactions the are currently processed are represented by
    //                       transactionBuffer. The current sum of values of preformed transactions is represented by count.

    // Representation Invariant: nodeLabel != null, transactionBuffer != null, count <= this.limit, limit >= 0

    private final double limit;
    private double count;
    private ArrayList<Transaction> transactionBuffer;

    /**
     * @modifies this
     * @effects Create channel with limit limit, contained by node named nodeLabel with infinite capacity.
     */
    public Channel(double limit, String nodeLabel) {
        super(nodeLabel);
        if(limit < 0.0){
            System.err.println("Channel: limit < 0");
            System.exit(1);
        }
        this.limit = limit;
        this.count = 0;
        this.transactionBuffer = super.workingObjectBuffer;
        checkRep();
    }

    /**
     * @effects Returns the current limit.
     */
    public double getLimit() {
        checkRep();
        return limit;
    }

    /**
     * @effects Returns the sum of values of transactions that were transferred.
     */
    public double getCount() {
        checkRep();
        return count;
    }

    /**
     * @modifies this
     * @effects Adds transaction to transaction buffer and updates the count.
     */
    public void addTransaction(Transaction transaction){
        checkRep();
        this.transactionBuffer.add(transaction);
        this.count += transaction.getValue();
        checkRep();
    }

    /**
     * @effects return the list of transactionBuffer;
     */
    public ArrayList<Transaction> getTransactionBuffer() {
        checkRep();
        return transactionBuffer;
    }

    /**
     * @modifies this, graph
     * @effects Simulates this channel in a system modeled by graph.
     */
    @Override
    public void simulate(BipartiteGraph<String> graph) {
        checkRep();
        final Node<String> node = graph.getNodeByLabel(this.nodeLabel);
        ArrayList<String> childrenLabels = new ArrayList<>(node.getChildrenList());
        ArrayList<Transaction> toRemove = new ArrayList<>();
        Node<String> firstChildNode = graph.getNodeByLabel(childrenLabels.get(0));

        for (Transaction tx : this.transactionBuffer){
            ((Participant)firstChildNode.getNodeObject()).addToCurrentTransactions(tx);
            toRemove.add(tx);
        }
        transactionBuffer.removeAll(toRemove);
        checkRep();
    }

    private void checkRep() {
        assert (this.nodeLabel != null) :
                "Node label is null";
        assert (this.transactionBuffer != null) :
                "transaction buffer is null";
        assert (this.count >= 0 || this.count <= this.limit) :
                "count is not valid";
        assert (this.limit >= 0) :
                "limit < 0";
    }
}
