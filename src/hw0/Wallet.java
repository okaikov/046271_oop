package hw0;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A wallet can contain a number of coins. There could be several coins of the same value,
 * but the same coin cannot appear in the wallet twice
 */
public class Wallet {
    private List<Coin> coinList;
    /**
     * @effects Creates a new empty wallet
     */
    public Wallet() {
    	this.coinList = new ArrayList<>();
    }


    /**
     * @modifies this
     * @effects Adds a coin to the wallet
     * @return true if the coin was successfully added to the wallet;
     * 		   false otherwise
     */
    public boolean addCoin(Coin coin) {
    	if (this.coinList.contains(coin)){
    	    return false;
        }
        this.coinList.add(coin);
    	return true;
    }

    /**
     * @modifies this
     * @effects tries to match at least the sum "sum" with coins in the wallet. 
	 *			If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double pay(double sum) {
        List<Coin> coinsToRemove = new ArrayList<>();

        double sumToPay = 0;
        for (Coin coin : this.coinList){
    	    sumToPay += coin.getValue();
    	    coinsToRemove.add(coin);
    	    if (sumToPay >= sum){
                this.coinList.removeAll(coinsToRemove);
    	        return sumToPay;
            }
        }
        return 0;
    }

    /**
     * @modifies this
     * @effects tries to match at least the sum "sum" with the minimum number of coins available from the wallet.
     * If transaction is possible, removes the paid coins from the wallet; else; changes nothing
     * @return the amount actually paid, 0 if amount could not be obtained
     */
    public double payMinimum(double sum) {
        double currentSum = 0;
        List<Coin> sortedCoinList = new ArrayList<>();
        sortedCoinList.addAll(coinList);
        sortedCoinList.sort(Comparator.comparing(Coin::getValue).reversed());
        List<Coin> coinsToRemove = new ArrayList<>();

        for (Coin coin : sortedCoinList){
            currentSum += coin.getValue();
            coinsToRemove.add(coin);
            if (currentSum >= sum) break;
        }

        this.coinList.removeAll(coinsToRemove);
        if (currentSum >= sum){
            return currentSum;
        }
        return 0;
    }



    /**
     * @return the current total value of coins in the wallet
     */
    public double getWalletTotal() {
        double walletTotal = 0;
        for (Coin coin : this.coinList){
            walletTotal += coin.getValue();
        }
        return walletTotal;
    }


    /**
     * @return the number of coins in the wallet
     */
    public int getWalletSize() {
    	return this.coinList.size();
    }


    /**
     * @modifies this
     * @effects Empties the the wallet. After this method is called,
	 * 			both getWalletSize() and getWalletTotal() will return 0
	 * 			if called
     */
    public void emptyWallet() {
    	this.coinList = new ArrayList<>();
    }


    /**
     * @return true if this wallet contains a coin with value "value"
     *  	   false otherwise
     */
    public boolean containsCoin(double value) {
    	for (Coin coin : coinList){
    	    if (coin.getValue() == value) {
    	        return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        Coin coin1 = new Coin(0.1);
        Coin coin2 = new Coin(0.5);
        Coin coin3 = new Coin(1);
        Coin coin4 = new Coin(5);
        Coin coin5 = new Coin(10);

        Wallet wallet = new Wallet();
        wallet.addCoin(coin1);
        wallet.addCoin(coin1); //check that we can't add the same coin again
        wallet.addCoin(coin2);
        wallet.addCoin(coin3);
        wallet.addCoin(coin4);
        System.out.println("wallet contains coin of value 10: " + wallet.containsCoin(10));
        wallet.addCoin(coin5);
        System.out.println("wallet contains coin of value 10: " + wallet.containsCoin(10));
        System.out.println("wallet total value: " + wallet.getWalletTotal());
        System.out.println("wallet size: " + wallet.getWalletSize());
        System.out.println("wallet pay minimum: " + wallet.payMinimum(11));
        System.out.println("wallet pay : " + wallet.pay(0.2));
    }




}
