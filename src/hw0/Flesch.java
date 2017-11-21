package hw0;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Flesch {
    public static void main(String[] args) throws IOException {
        String sentenceDelimiter = "[\\.,;\\?\\!]";
        String wordDelimiter = "\\W";
        List<String> sentenceList = new ArrayList<>();
        List<String> wordList = new ArrayList<>();
        int numOfSyllables = 0;
        double flesch;

        checkArgs(args);
        String filePath = args[0];
        // read file
        String entireFileText = new Scanner(new File(filePath)).useDelimiter("\\A").next();
        String[] sentences = entireFileText.split(sentenceDelimiter);
        for (String sentence : sentences) {
            sentence = sentence.trim().replace("\n", "");
            if (!sentence.isEmpty()) {
                sentenceList.add(sentence);
            }
        }

        for (String sentence : sentenceList){
            String[] words= sentence.split(wordDelimiter);
            for (String word : words ) {
                if (!word.isEmpty()) {
                    wordList.add(word);
                }
            }
        }

        for (String word : wordList){
            numOfSyllables += getNumSyllables(word);
        }

        flesch = computeFlesch(sentenceList.size(), wordList.size(), numOfSyllables);
        System.out.println("flesch: " + flesch);
    }

    /**
     * @requires true
     * @modifies none
     * @effects none
     * @returns the Flesch index according to numSentences, numWords and numSyllables
     */
    private static double computeFlesch(int numSentences, int numWords, int numSyllables){
        return  206.835 - 84.6 * ((double) numSyllables / numWords) - 1.015 * ((double)numWords / numSentences);
    }

    /**
     * @requires word != NULL, word contains letters (not special characters)
     * @modifies none
     * @effects none
     * @returns the number of syllables in the word, at least 1.
     */
    private static int getNumSyllables(String word){
        String vowelsString = "aAeEiIoOuUyY";
        boolean foundSyllable = false;
        int numSyllables = 0;

        for (char c : word.toCharArray()){
            if (vowelsString.indexOf(c) >= 0){
                if (!foundSyllable){
                    foundSyllable = true;
                }
            } else {
                if (foundSyllable) {
                    numSyllables++;
                }
                foundSyllable = false;
            }
        }
        // If word ends with vowel, another syllable should be added, unless the last vowel is 'e'.
        if (foundSyllable && word.charAt(word.length() - 1) != 'e') {
            numSyllables++;
        }

        if (numSyllables == 0) {
            numSyllables = 1;
        }

        return numSyllables;
    }

    /**
     * @requires args != NULL
     * @modifies none
     * @effects Checks the number of arguments and that the file in the program argument exists.
     *          In case the number of arguments is not 1 or the file does not exist, prints the appropriate
     *          error message and exists.
     */
    private static void checkArgs(String[] args){
        if (args.length==0){
            System.out.println("Error: no filename was given");
            System.exit(0);
        } else if (args.length>1) {
            System.out.println("Error: more then 1 arguments were given");
            System.exit(0);
        }
        String filePath = args[0];
        File f = new File(filePath);
        if(!f.exists() || f.isDirectory()) {
            System.out.println("Error: file does not exist or is a directory");
            System.exit(0);
        }
    }
}
