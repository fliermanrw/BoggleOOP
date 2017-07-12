package Model;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Ryan on 8-5-2017.
 */
public class BoggleSolverModel {
    BoggleModel boggleModel;
    boolean isWord;
    HashSet<String> foundWords = new HashSet<>();

    BoggleSolverModel(BoggleModel boggleModel) {
        this.boggleModel = boggleModel;
    }

    public void getPossibleWords(){
        /*for(int x = 0; x < 16; x++){
            solver(x);
        }*/
        solver(1);
        System.out.println("Dit zijn de woorden: "+ foundWords);
    }

    // get all words for the FIRST letter
    // TODO implement all letters..
    public void solver(int id) {
        List<Integer> usedIDs = new ArrayList<>();
        id = 5;


        String boardLetters = boggleModel.getBoardLetters().toLowerCase();
        Character letter = boardLetters.charAt(id);


        HashSet<String> words = boggleModel.possibleWords;

        // for each word check if character[x] is a neighbour if the startLetter - id -
        for (String word : words) {
            int x = 1;
            // When word(0) == "A" and letter is "A".. check
            if (word.charAt(0) == letter) {
                System.out.println("KANS");
                checkNeighbours(word, id, x, usedIDs);
            } else{
                //System.out.println("HELAAS! Woord is: " + word + ".. en letter is: " + letter);
                //;System.out.println("NEIN");
            }
        }
    }

    // check the character for neighbours that match the word
    public void checkNeighbours(String word, int id, int x, List<Integer> usedIDs){
        // get the list met neighbours
        ArrayList<Character> neighbours = charToLower(boggleModel.getNeighbours(id));

        System.out.println("Laten we het eens proberen..");
        System.out.println(neighbours);
        

        if(word.length() > x) {
            // check if the character matches && if it has NOT already been used
            if ((neighbours.contains(word.charAt(x)) && !usedIDs.contains(neighbours.indexOf(word.charAt(x))))) {
                // add the current ID to EXCLUDE in next search
                usedIDs.add(neighbours.indexOf(word.charAt(x)));

                // increase X so word.charAt(x) will increase
                x++;

                // debug reasons
                System.out.println("Dit zijn de used IDs: " + usedIDs);

                // recursion
                checkNeighbours(word, id, x, usedIDs);

            } else {
                // nothing found so done
                System.out.println("Kloar met zoeken..");
            }
        } else if(word.length() == x){
                foundWords.add(word);
                System.out.println("Woord gevonden: " + word);
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
