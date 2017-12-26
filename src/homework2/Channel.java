package homework2;

import java.util.ArrayList;

public class Channel extends Pipe<String, Transaction> implements Simulatable<String>{

    private final double limit;
    private double count;
    private ArrayList<Transaction> transactionBuffer;

    public Channel(double limit, String nodeLabel) {
        super(nodeLabel);
        this.limit = limit;
        this.count = 0;
        this.transactionBuffer = super.workingObjectBuffer;
        checkRep();
    }


    public double getLimit() {
        checkRep();
        return limit;
    }

    public double getCount() {
        checkRep();
        return count;
    }

    public void setCount(double count) {
        checkRep();
        this.count = count;
        checkRep();
    }

    public void addTransaction(Transaction transaction){
        checkRep();
        this.transactionBuffer.add(transaction);
        this.count += transaction.getValue();
        checkRep();
    }

    public ArrayList<Transaction> getTransactionBuffer() {
        checkRep();
        return transactionBuffer;
    }


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
