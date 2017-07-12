package Model;

import Controller.BoggleController;

import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Created by Ryan on 21-4-2017.
 */
public class BoggleModel {
    private BoardModel boardModel;
    private BoggleController boggleController;
    private BoggleSolverModel boggleSolverModel;
    private NeighbourModel neighbourModel;
    public Trie trie;
    private final int fieldSize;
    HashSet<String> wordList;
    HashSet<String> possibleWords;

    public BoggleModel(BoggleController boggleController, int fieldsize) throws IOException {
        this.boggleController = boggleController;

        this.boardModel = new BoardModel(this, fieldsize);
        this.neighbourModel = new NeighbourModel(this, fieldsize);
        this.boggleSolverModel = new BoggleSolverModel(this);
        this.trie = new Trie(this);
        this.fieldSize = fieldsize;
        this.wordList = newWordList();
        this.possibleWords = newPossibleWords();
    }


    // create hashset that contains all possible words in the current board
    public HashSet<String> newPossibleWords() {
        HashSet<String> foundWords = new HashSet<>();
        String boardLetters = boardModel.getBoardLetters();

        // debug prints
        System.out.println("Finding all possible words!");
        System.out.println("Boardletters: " + boardLetters);
        int counter = 0;

        // no need to loop through the wordlist with double letters
        boardLetters = removeDuplicates(boardLetters);
        // to lower case, otherwise nothing is going to be found
        boardLetters = boardLetters.toLowerCase();

        // booleans for "a", "e", "o", "u" , "i"
        //boolean aPresent, ePresent, oPresent, uPresent, iPresent;
        boolean aPresent = isPresent(boardLetters, "a");
        boolean ePresent = isPresent(boardLetters, "e");
        boolean oPresent = isPresent(boardLetters, "o");
        boolean uPresent = isPresent(boardLetters, "u");
        boolean iPresent = isPresent(boardLetters, "i");

        //System.out.println("A, E, O, U, I" + aPresent + ePresent + oPresent + uPresent + iPresent);

        // Looping through the wordlist
        String az = "abcdefghijklmnopqrstuvwxyz";


        for (String word : wordList) {
            boolean wordPossible = true;

            for(int xx = 0; xx < boardLetters.length() ; xx++) {
                // if first index of the word matches the character
                if (word.charAt(0) == boardLetters.charAt(xx)){


                    //check if letter in word but not in boardLetters..
                    for(int yy =0; yy < az.length() ; yy++){
                        String temp = String.valueOf(az.charAt(yy));
                        if(!boardLetters.contains(temp) && word.contains(temp)){
                            wordPossible = false;
                        }
                    }

                    // when there is an A in the word, but none in the boardletters...
                    /*if(!aPresent && word.contains("a")){
                        wordPossible = false;
                    }
                    if(!ePresent && word.contains("e")){
                        wordPossible = false;
                    }

                    if(!oPresent && word.contains("o")){
                        wordPossible = false;
                    }
                    if(!uPresent && word.contains("u")){
                        wordPossible = false;
                    }
                    if(!iPresent && word.contains("i")){
                        wordPossible = false;
                    }*/

                    if(wordPossible){
                        counter++;

                        // add the word to the possibleWords HashSet
                        foundWords.add(word);

                        // Add the word to the tree
                        trie.addWord(word);
                    }

                }
            }

        }

        //foundWords.forEach(System.out::println);
        System.out.println("Found words: "+ counter);
        System.out.println("check if possible...");
        return foundWords;
    }


    private boolean isPresent(String word, String letter){
        return word.contains(letter);

    }

    // removes all double characters in the boardLetters
    private String removeDuplicates(String input){
        String result = "";
        for (int i = 0; i < input.length(); i++) {
            if(!result.contains(String.valueOf(input.charAt(i)))) {
                result += String.valueOf(input.charAt(i));
            }
        }
        return result;
    }

    // returns a randomized letter to create the board
    Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        return letters.charAt(rd.nextInt(letters.length()));
    }

    public char[][] getBoard(){
        return boardModel.getBoard();
    }


    // Resets the board with new letters and the new list of possible words
    public void resetBoard(){
        this.boardModel = new BoardModel(this, fieldSize);
        boardModel.fillBoard();
        this.possibleWords = newPossibleWords();
    }


    // fills the board with letters
    public void fillBoard(){
        boardModel.fillBoard();
    }


    public ArrayList<Character> getNeighbours(int id){
        return neighbourModel.getNeighbours(id);
    }


    public HashSet<String> newWordList() throws IOException {
        HashSet<String> words = new HashSet<>();
        // ROBERT
        // RYAN : C:/Users/Ryan/IdeaProjects/blok13/BoggleOOP/src.dict.txt
        Scanner sc = new Scanner(new File("C:/Users/Ryan/IdeaProjects/blok13/BoggleOOP/src/dict.txt"));
        while(sc.hasNext()){
            String line = sc.nextLine();

            words.add(line);
        }

        return words;
    }

    public HashSet<String> getWordList(){
        return wordList;
    }

    public void findInTree(String word){
        System.out.println(trie.getSize());
        System.out.println(trie.find(word));
    }









}
