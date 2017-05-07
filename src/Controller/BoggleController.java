package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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


    Trie trie = new Trie();
    private boolean notFalse;

    @FXML
    protected void initialize() throws IOException {
        generateNewBoard();
        WordList();
    }

    public BoggleController(){
         this.boggleModel = new BoggleModel(this, fieldSize);

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
        char[][] board = boggleModel.getBoard();
        for (int x = 0; x < fieldSize; x++) {
            boggleboardfield.addRow(x);
            for (int y = 0; y < fieldSize; y++) {
                Button a = new Button();
                a.setMinWidth(70.0);
                a.setMinHeight(70.0);
                a.setText(String.valueOf(boggleModel.assignLetter()));

                a.setId(String.valueOf(x * fieldSize + y));
                boggleboardfield.addColumn(y, a);
            }

        }

        System.out.println(board);
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
        //TODO ieks
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
            neighboursLocations.add(findNeighbours(count));
        }
    }

    private ArrayList<Integer> findNeighbours(int id) {
        int temp;
        HashSet<Integer> noDups = new HashSet<>();


        // Linkerkant checks
        if ((id % fieldSize == 0)) {


            //linksboven
            if (id < fieldSize) {
                temp = id + 1;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + fieldSize + 1;
                noDups.add(temp);
                //System.out.println("Added: " + temp);
            }

            // linksonder
            if (id > (boggleboardfield.getChildren().size() - (fieldSize + 1))) {
                temp = id - fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + 1;
                noDups.add(temp);
                //System.out.println("added: " + temp);


            }

            //linksmidden
            if (!(id < fieldSize - 1) && !(id > (boggleboardfield.getChildren().size() - (fieldSize + 1)))) {
                System.out.println("hier komt ie");
                temp = id - fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + 1;
                noDups.add(temp);
                //System.out.println("added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + (fieldSize + 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);


            }
        }

        //Rechterzijkant Check
        if ((id % fieldSize == fieldSize - 1)) {
            System.out.println("hier mag ie komen!");
            if (id < fieldSize + 1) {
                temp = id - 1;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + fieldSize - 1;
                noDups.add(temp);
                //System.out.println("Added: " + temp);
            }

            if (id > (boggleboardfield.getChildren().size() - (fieldSize + 1))) {
                temp = id - 1;
                noDups.add(temp);
                //System.out.println("added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);
            }

            if (!(id < fieldSize + 1) && !(id > (boggleboardfield.getChildren().size() - (fieldSize + 1)))) {
                temp = id - (fieldSize + 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id - 1;
                noDups.add(temp);
                //System.out.println("added: " + temp);

                temp = id + (fieldSize - 1);
                noDups.add(temp);
                //System.out.println("Added: " + temp);

                temp = id + (fieldSize);
                noDups.add(temp);
                //System.out.println("Added: " + temp);


            }
        }

        // Binnengetallen...
        if ((id > fieldSize - 1) && (id < (boggleboardfield.getChildren().size() - fieldSize)) && ((id % fieldSize != 0)) && ((id % fieldSize != fieldSize - 1))) {
            temp = id - (fieldSize + 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - (fieldSize);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - (fieldSize - 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - 1;
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + (fieldSize - 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + (fieldSize);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + (fieldSize + 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);
        }

        // Bovengetallen
        if ((id < fieldSize) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            temp = id - 1;
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + (fieldSize - 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + (fieldSize);
            noDups.add(temp);
           // System.out.println("added: " + temp);

            temp = id + (fieldSize + 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);
        }

        // Ondergetallen
        if ((id > (boggleboardfield.getChildren().size() - fieldSize)) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            temp = id - (fieldSize + 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - (fieldSize);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - (fieldSize - 1);
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id - 1;
            noDups.add(temp);
            //System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
           // System.out.println("added: " + temp);


        }

        System.out.println("xxxxxxxxxxxxxxxxxxxxx");

        ArrayList<Integer> neighbours = new ArrayList<>();

        // Omzetten naar letters
        for (Object value : noDups) {
            Button button = (Button) boggleboardfield.getChildren().get((Integer) value);
            neighbours.add(Integer.valueOf(button.getId()));

        }

        return neighbours;

    }

    public ArrayList<Character> neighbourToCharacter(ArrayList<Integer> neighbourLocations){
        ArrayList<Character> neighbourCharacter = new ArrayList<>();
        for (Integer neighbourLocation : neighbourLocations) {
            Button button = (Button) boggleboardfield.getChildren().get(neighbourLocation);
            String temp = button.getText();
            neighbourCharacter.add(temp.charAt(0));
        }

        return neighbourCharacter;
    }

    public int getFieldSize() {
        return fieldSize;
    }
}


