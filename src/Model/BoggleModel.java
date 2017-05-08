package Model;

import Controller.BoggleController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;


/**
 * Created by Ryan on 21-4-2017.
 */
public class BoggleModel {
    private BoardModel boardModel;
    private BoggleController boggleController;
    private BoggleSolverModel boggleSolverModel;
    private NeighbourModel neighbourModel;
    private Trie trie;
    private final int fieldSize;
    HashSet<String> wordList;

    public BoggleModel(BoggleController boggleController, int fieldsize) throws IOException {
        this.boggleController = boggleController;

        this.fieldSize = fieldsize;
        this.wordList = newWordList();
        this.boardModel = new BoardModel(this, fieldsize);
        this.neighbourModel = new NeighbourModel(this, fieldsize);
        this.boggleSolverModel = new BoggleSolverModel(this);
        this.trie = new Trie(this);
    }

    Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        return letters.charAt(rd.nextInt(letters.length()));
    }

    public char[][] getBoard(){
        return boardModel.getBoard();
    }

    public void resetBoard(){
        this.boardModel = new BoardModel(this, fieldSize);
        boardModel.fillBoard();
    }

    public void fillBoard(){
        boardModel.fillBoard();
    }

    public ArrayList<Character> getNeighbours(int id){
        return neighbourModel.getNeighbours(id);
    }

    public HashSet<String> newWordList() throws IOException {
        HashSet<String> words = new HashSet<>();
        Scanner sc = new Scanner(new File("C:/Users/Ryan/IdeaProjects/BoggleOOP/src/dict.txt"));
        while(sc.hasNext()){
            String line = sc.nextLine();
            words.add(line); // still here to check if real word
            System.out.println(line);

            //@TODO nu is de tree kapoet??
            //trie.add(line);

        }
        return words;

    }

    public HashSet<String> getWordList(){
        return wordList;
    }

    public void findInTree(String word){
        System.out.println(trie.find(word));
    }









}
