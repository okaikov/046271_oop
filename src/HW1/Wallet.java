package HW1;

import HW1.Coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A wallet can conatain a number of coins. There could be several coins of the same value, 
 * but the same coin cannot apear in the wallet twice
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
    	        for (Coin coinToRemove : coinsToRemove){
    	            this.coinList.remove(coinToRemove);
                }
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
        List<Coin> sortedCoinList = new ArrayList<>();
        Collections.copy(sortedCoinList, coinList);
        sortedCoinList.sort(Comparator.comparing(Coin::getValue).reversed());

        for (Coin coin : sortedCoinList){

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
    	    if (coin.getValue() == value) return true;
        }
        return false;
    }
}
