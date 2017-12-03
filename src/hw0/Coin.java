package hw0;

import java.util.Arrays;
import java.util.List;

/**
	A Coin can have a value of 0.1, 0.5, 1, 5, 10
 */
public class Coin {
    private double value;
    private List<Double> validCoinValues = Arrays.asList(0.1, 0.5, 1.0, 5.0, 10.0);
    /**
     * @requires value in {0.1, 0.5, 1, 5, 10}
     * @modifies this
     * @effects Creates and initializes new Coin with the value, value
     * 
     */
    public Coin(double value) {
        if(this.validCoinValues.contains(value)){
            this.value = value;
        } else {
            System.out.println("Error: value not valid");
            // how to handle the error?
        }
    }


    /**
     * @returns the value of the Coin
     */
    public double getValue() {
    	return this.value;
    }

    public static void main(String[] args){
        Coin coin1 = new Coin(0.1);
        Coin coin2 = new Coin(0.5);
        Coin coin3 = new Coin(1);
        Coin coin4 = new Coin(5);
        Coin coin5 = new Coin(10);
        Coin coin6 = new Coin(11);
        Coin coin7 = new Coin(-1);
    }
}


