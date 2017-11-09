package HW1;

/**
 * A coin collection contains coins. Coins can only be of value 0.10, 0.5, 1, 5, 10
 * Each coin VALUE can only appear in the collection once (e.g only one coin with value 5)
 */
public class CoinCollection {
        private Wallet wallet;
    /**
     * @effects Creates a new coin collection
     */
    public CoinCollection() {
         wallet = new Wallet();
    }
    
    /**
     * @modifies this
     * @effects Adds a coin to the collection
     * @return true if the coin was successfully added to the collection;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
        if (wallet.containsCoin(coin.getValue())) return false;
        wallet.addCoin(coin);
        return true;
    }
    
    /**
     * @return the current value of the collection
     */
    public double getCollectionTotal() {
        return wallet.getWalletTotal();
    }
    
    /**
     * @return the number of coins in the collection
     */
    public int getCollectionSize() {
    	return wallet.getWalletSize();
    }
    
    /**
     * @modifies this
     * @effects Empties the collection
     */
    public void emptyCollection() {
        wallet.emptyWallet();
    }

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
