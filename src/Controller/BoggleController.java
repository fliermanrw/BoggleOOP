package Controller;

import Model.BoggleModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class BoggleController {
    startSearch startSearch;
    BoggleModel boggleModel;
    private final int fieldSize = 4;
    public GridPane boggleboardfield;
    public Pane wordfield;

    ArrayList<Integer> exclusions = new ArrayList<>();

    public BoggleController(){
        this.boggleModel = new BoggleModel();
        this.startSearch = new startSearch();
    }

    @FXML
    protected void initialize() {
        generateNewBoard();
        this.startSearch = new startSearch();
    }

    public GridPane getBoggleBoardField(){
        return boggleboardfield;
    }


    private void generateNewBoard(){
        for (int row = 0; row < fieldSize; row++) {
            boggleboardfield.addRow(row);
            for (int col = 0; col < fieldSize; col++) {
                Button a = new Button();
                a.setMinWidth(70.0);
                a.setMinHeight(70.0);
                a.setText(String.valueOf(assignLetter()));

                a.setId(String.valueOf(row * fieldSize + col));
                boggleboardfield.addColumn(col, a);
            }

        }
    }


    private Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        Random rd = new Random();

        return letters.charAt(rd.nextInt(letters.length()));
    }


    public void SearchStart() throws IOException {
        startSearch.WordList();
        boggleModel.print();


    }

    public void ResetBoard() {
        boggleboardfield.getChildren().clear();
        generateNewBoard();
    }

    public void Testerx() {
        System.out.println("Lettertje: " + boggleboardfield.getChildren().get(4));

        Node node = boggleboardfield.getChildren().get(4);

        if(node instanceof Button){
            Button button = (Button) node;
            System.out.println(button.getText());
        } else{
            System.out.println("Niks kunnen vinden x");
        }
        System.out.println(" - ");

        // Make sure the ArrayList is not empty b
        exclusions.add(fieldSize + 1);

        System.out.println(findNeighbours(9));
        System.out.println(findNeighbours(9));


    }

    private ArrayList<String> findNeighbours(int id) {
        int temp;
        HashSet<Integer> noDups = new HashSet<>();


        // Linkerkant checks
        if ((id % fieldSize == 0)) {


            //linksboven
            if (id < fieldSize) {
                temp = id + 1;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + fieldSize + 1;
                noDups.add(temp);
                System.out.println("Added: " + temp);
            }

            // linksonder
            if (id > (boggleboardfield.getChildren().size() - (fieldSize + 1))) {
                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + 1;
                noDups.add(temp);
                System.out.println("added: " + temp);


            }

            //linksmidden
            if (!(id < fieldSize - 1) && !(id > (boggleboardfield.getChildren().size() - (fieldSize + 1)))) {
                System.out.println("hier komt ie");
                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);


            }
        }

        //Rechterzijkant Check
        if ((id % fieldSize == fieldSize - 1)) {
            System.out.println("hier mag ie komen!");
            if (id < fieldSize + 1) {
                temp = id - 1;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + fieldSize - 1;
                noDups.add(temp);
                System.out.println("Added: " + temp);
            }

            if (id > (boggleboardfield.getChildren().size() - (fieldSize + 1))) {
                temp = id - 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize - 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);
            }

            if (!(id < fieldSize + 1) && !(id > (boggleboardfield.getChildren().size() - (fieldSize + 1)))) {
                temp = id - (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id + (fieldSize - 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + (fieldSize);
                noDups.add(temp);
                System.out.println("Added: " + temp);


            }
        }

        // Binnengetallen...
        if ((id > fieldSize - 1) && (id < (boggleboardfield.getChildren().size() - fieldSize)) && ((id % fieldSize != 0)) && ((id % fieldSize != fieldSize - 1))) {
            temp = id - (fieldSize + 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize - 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - 1;
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize - 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize + 1);
            noDups.add(temp);
            System.out.println("added: " + temp);
        }

        // Bovengetallen
        if ((id < fieldSize) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            temp = id - 1;
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize - 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + (fieldSize + 1);
            noDups.add(temp);
            System.out.println("added: " + temp);
        }

        // Ondergetallen
        if ((id > (boggleboardfield.getChildren().size() - fieldSize)) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            temp = id - (fieldSize + 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize - 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - 1;
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id + 1;
            noDups.add(temp);
            System.out.println("added: " + temp);


        }


        System.out.println("xxxxxxxxxxxxxxxxxxxxx");
        System.out.println(boggleboardfield.getChildren().size());

        ArrayList<String> neighbours = new ArrayList<>();

        // Omzetten naar letters
        for (Object value : noDups) {
            Button button = (Button) boggleboardfield.getChildren().get((Integer) value);
            neighbours.add(button.getText());

        }

        exclusions.add(id);
        return neighbours;

    }

}

