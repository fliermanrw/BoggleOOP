package Model;

import java.util.*;

/**
 * Created by Ryan on 8-5-2017.
 */

public class BoggleSolverModel {
    BoggleModel boggleModel;
    HashSet<String> foundWords = new HashSet<>();
    long timeTaken;

    BoggleSolverModel(BoggleModel boggleModel) {
        this.boggleModel = boggleModel;
    }

    public void getAllPossibleWords(){
        long startTime = System.currentTimeMillis();
        for(int x = 0; x < 16; x++){
            solver(x);
        }


        // Show computing time
        long endTime = System.currentTimeMillis();
        timeTaken = endTime - startTime;

        System.out.println("The time for solving is: " + timeTaken + "MS");
    }


    // get all words for a letter
    public void solver(int id) {
        String boardLetters = boggleModel.getBoardLetters().toLowerCase();
        Character letter = boardLetters.charAt(id);


        HashSet<String> words = boggleModel.possibleWords;

        // for each word check if character[x] is a neighbour if the startLetter - id -
        for (String word : words) {
            int x = 1;
            // When word(0) == "a" and letter is "a".. check
            if (word.charAt(0) == letter) {
                List<Integer> usedIDs = new ArrayList<>();


                usedIDs.add(id);
                checkNeighbours(word, id, x, usedIDs);
            }
        }
    }

    // check the character for neighbours that match the word
    public void checkNeighbours(String word, int id, int x, List<Integer> usedIDs){
        ArrayList<Character> neighbours = charToLower(boggleModel.getNeighbours(id));
        ArrayList<Integer> neighbourIDs = boggleModel.getFieldIDs();


        // "while" word length > X, go into recursion
        if(word.length() > x) {
            // check if the character matches && if it has NOT already been used
            if ((neighbours.contains(word.charAt(x)))) {
                // check whether neighbours has word.charAt(x).. if true, we want to know what letter and where the letter is in the neighbours.
                // With that we can check in the neighboursID whether that ID is already in the usedIDs..
                int check = neighbourIDs.get(neighbours.indexOf(word.charAt(x)));

                // When there are more matches...
                if(check != neighbours.lastIndexOf(word.charAt(x))){
                    if(usedIDs.contains(check)){
                        check = neighbourIDs.get(neighbours.lastIndexOf(word.charAt(x)));
                    }
                }

                // when check not in usedIDs yet, we can of course use it and find its neighbours..
                if(!usedIDs.contains(check)) {
                    // increase X so word.charAt(x) will increase
                    x++;
                    id = check;

                    // recursion
                    usedIDs.add(check);
                    checkNeighbours(word, id, x, usedIDs);
                }
            }
        } else if((word.length() == x)){
                foundWords.add(word);
            } else {
            System.out.println("Klaar met zoeken..");

        }

    }


    // convert the upper case chars to lower..
    public ArrayList<Character> charToLower(ArrayList<Character> list){
        ArrayList<Character> lower = new ArrayList<>();
        for(Character x: list){
            x = Character.toLowerCase(x);
            lower.add(x);
        }
        return lower;
    }




}
