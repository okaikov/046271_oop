package HW1;

public class CoinCollectionTb {
    public static void main(String[] args){
        Coin coin1 = new Coin(0.1);
        Coin coin2 = new Coin(0.5);
        Coin coin3 = new Coin(1);
        Coin coin4 = new Coin(5);
        Coin coin5 = new Coin(10);
        Coin coin6 = new Coin(10);

        CoinCollection coinCollection = new CoinCollection();
        System.out.println("added coin with value " + coin1.getValue() + ": " + coinCollection.addCoin(coin1));
        System.out.println("added coin with value " + coin2.getValue() + ": " + coinCollection.addCoin(coin2));
        System.out.println("added coin with value " + coin3.getValue() + ": " + coinCollection.addCoin(coin3));
        System.out.println("added coin with value " + coin4.getValue() + ": " + coinCollection.addCoin(coin4));
        System.out.println("added coin with value " + coin5.getValue() + ": " + coinCollection.addCoin(coin5));
        System.out.println("added coin with value " + coin6.getValue() + ": " + coinCollection.addCoin(coin6));


        System.out.println("coinCollection size: " + coinCollection.getCollectionSize());
        System.out.println("coinCollection total: " + coinCollection.getCollectionTotal());
        coinCollection.emptyCollection();
        System.out.println("coinCollection size: " + coinCollection.getCollectionSize());
        System.out.println("coinCollection total: " + coinCollection.getCollectionTotal());
    }
}
