package homework2;

import java.util.ArrayList;

public class Participant implements Simulatable<String>{

    private String nodeLabel;
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

    public void removeFromCurrentTransactions(Transaction transaction) {
        this.currentTransactions.remove(transaction);
    }

    public ArrayList<Transaction> getStorageBuffer() {
        return new ArrayList<>(this.storageBuffer);
    }

    public void addToStorageBuffer(Transaction transaction) {
        this.storageBuffer.add(transaction);
    }


    public void simulate(){

    }


    @Override
    public void simulate(BipartiteGraph<String> graph) {
        final Node<String> node = graph.getVertexByLabel(this.nodeLabel);
        ArrayList<String> childrenLabels = new ArrayList<>(node.getChildrenLabelList());
        Channel channel = (Channel)graph.getVertexByLabel(childrenLabels.get(0)).getObject();

        for (Transaction tx : this.currentTransactions){
            if (tx.getDest() == this.nodeLabel){
                addToStorageBuffer(tx);
                removeFromCurrentTransactions(tx);
            }else {
                removeFromCurrentTransactions(tx);
                addToStorageBuffer(new Transaction(nodeLabel,this.fee));
                channel.addTransaction(new Transaction(tx.getDest(), tx.getValue()-this.fee));
            }
        }
    }


}
