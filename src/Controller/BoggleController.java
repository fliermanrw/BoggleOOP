package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;



public class BoggleController {
    private final int fieldSize = 4;
    BoggleModel boggleModel;
    public GridPane boggleboardfield;
    public Pane wordfield;
    HashSet<String> wordList = new HashSet<>();
    ArrayList<ArrayList<Integer>> neighboursLocations = new ArrayList<>();
    ArrayList<ArrayList<Character>> neighboursCharacter = new ArrayList<>();
    char[][] board;


    Trie trie = new Trie();

    @FXML
    protected void initialize() throws IOException {
        boggleModel.fillBoard();
        generateNewBoard();
        WordList();
    }

    public BoggleController(){
        this.boggleModel = new BoggleModel(this, fieldSize);
        this.board = boggleModel.getBoard();
    }


    public void WordList() throws IOException {
        Scanner sc = new Scanner(new File("C:/Users/Ryan/IdeaProjects/BoggleOOP/src/dict.txt"));
        while(sc.hasNext()){
            String line = sc.nextLine();
            wordList.add(line); // still here to check if real word

        }

    }

    public HashSet<String> getWordList(){
        if(wordList.isEmpty()){
            try {
                WordList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordList;
    }



    private void generateNewBoard(){
        // get the generated char[][] board and create the same view in buttons
        char[][] board = boggleModel.getBoard();

        //loop and create the buttons
        for (int x = 0; x < fieldSize; x++) {
            boggleboardfield.addRow(x);
            for (int y = 0; y < fieldSize; y++) {
                Button a = new Button();
                a.setMinWidth(70.0);
                a.setMinHeight(70.0);
                a.setText(String.valueOf(board[x][y]));

                a.setId(String.valueOf(x * fieldSize + y));
                boggleboardfield.addColumn(y, a);
            }

        }
    }


    public void SearchStartButton() throws IOException {
        getAllNeighbours();
        inWordList();

    }

    public void ResetBoardButton() {
        boggleboardfield.getChildren().clear();
        generateNewBoard();
    }

    public void TestButton() {
        boggleModel.getNeighbours(2);

    }


    // Kijken welke woorden matchen met de eerste letter (id.getText())
    // Als
    private void inWordList() {
        for(int count = 0; count < fieldSize * fieldSize; count ++){

            ArrayList<Integer> localNeighbours = neighboursLocations.get(count);

            //converts arraylist with id numbers to assigned letters
            Button button = (Button) boggleboardfield.getChildren().get(count);
            Character startChar = button.getText().charAt(0);

            // tests
            System.out.println("Testing where we fail. StartChar = " + startChar);
            System.out.println(trie.find(String.valueOf(startChar) + "E"));


            //TODO HIER MOET DE RECURSIE GEDAAN WORDEN / OPGEROEPEN WORDEN
            if(trie.find(String.valueOf(startChar))){
                for(int neighbourCount = 0; neighbourCount < localNeighbours.size(); neighbourCount++){
                        System.out.println("In de tree ergens..");
                        if(trie.find(String.valueOf(startChar+localNeighbours.get(neighbourCount)))){
                            localNeighbours.get(neighbourCount);
                        }


                        System.out.println(trie.find(String.valueOf(startChar + localNeighbours.get(neighbourCount))));
                }
            }



        }

    }


    // tests to see the neighbours
    public void getAllNeighbours(){
        for(int count = 0; count < (fieldSize * fieldSize); count++) {
            neighboursLocations.add(boggleModel.getNeighbours(count));
        }
    }



}


