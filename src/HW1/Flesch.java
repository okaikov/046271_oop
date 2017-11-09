package HW1;

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
        int numOfVowels = 0;
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
            numOfVowels += getNumVowels(word);
        }

        flesch = computeFlesch(sentenceList.size(),wordList.size(),numOfVowels);
        System.out.println("flesch: " + flesch);
    }

    private static double computeFlesch(int numSentences, int numWords, int numVowels){
        return  206.835 - 84.6*((double)numVowels/numWords) - 1.015*((double)numWords/numSentences);
    }

    private static int getNumVowels(String word){
        String vowelsString = "aAeEiIoOuUyY";
        boolean foundVowel = false;
        int numVowels = 0;

        for (char c : word.toCharArray()){
            if (vowelsString.indexOf(c)>=0){
                if (foundVowel){
                    continue;
                }else{
                    foundVowel = true;
                }
            }else{
                if (foundVowel) numVowels++;
                foundVowel = false;
            }
        }
        if (foundVowel && word.charAt(word.length()-1) != 'e') numVowels++;

        if (numVowels == 0) numVowels = 1;
        return numVowels;
    }

    private static void checkArgs(String[] args){
        if (args.length==0){
            System.out.println("Error: no filename was given");
            System.exit(0);
        }else if (args.length>1){
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
