package homework2;

import java.util.ArrayList;

public class Channel implements Simulatable<String>{

    private String nodeLabel;
    private final double limit;
    private double count;
    private ArrayList<Transaction> transactionBuffer;

    public Channel(double limit, String label) {
        this.limit = limit;
        this.count = 0;
        this.transactionBuffer = new ArrayList<>();
        this.nodeLabel = label;
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


    @Override
    public void simulate(BipartiteGraph<String> graph) {
        final Node<String> node = graph.getNodeByLabel(this.nodeLabel);
        ArrayList<String> childrenLabels = new ArrayList<>(node.getChildrenList());
        ArrayList<Transaction> toRemove = new ArrayList<>();
        Node<String> firstChildNode = graph.getNodeByLabel(childrenLabels.get(0));

        for (Transaction tx : this.transactionBuffer){
            ((Participant)firstChildNode.getNodeObject()).addToCurrentTransactions(tx);
            toRemove.add(tx);
        }

        transactionBuffer.removeAll(toRemove);


    }

}
