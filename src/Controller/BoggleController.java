package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


public class BoggleController {
    private final int fieldSize = 4;
    public GridPane boggleboardfield;
    public Pane wordfield;



    @FXML
    protected void initialize() {
        generateNewBoard();
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


    public void SearchStart(ActionEvent actionEvent) {
        //TODO xx
    }

    public void ResetBoard(ActionEvent actionEvent) {
        boggleboardfield.getChildren().clear();
        generateNewBoard();
    }

    public void Testerx(ActionEvent actionEvent) {
        System.out.println("Lettertje: " + boggleboardfield.getChildren().get(4));

        Node node = boggleboardfield.getChildren().get(4);

        if(node instanceof Button){
            Button button = (Button) node;
            System.out.println(button.getText());
        } else{
            System.out.println("Niks kunnen vinden x");
        }
        System.out.println("xxxxxx");
        System.out.println(findNeighbours(6));
        System.out.println(findNeighbours(1));

    }

    public ArrayList<Integer> findNeighbours(int id) {
        int temp;
        HashSet noDups = new HashSet();

        /*for (int cnt = 0; cnt < boggleboardfield.getChildren().size(); cnt++) {
            int x = Integer.parseInt(boggleboardfield.getChildren().get(cnt).getId());
            System.out.println(x);
        }*/

        // Linkerkant checks
        if((id % fieldSize == 0)){

            if(id < fieldSize-1) {
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

            if(id > (boggleboardfield.getChildren().size() -fieldSize)) {
                temp = id - 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);
            }

            if(!(id < fieldSize-1) && !(id > (boggleboardfield.getChildren().size() -fieldSize))){
                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id + 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id + (fieldSize -1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);


            }
        }

        //Rechterzijkant Check
        if((id % fieldSize == fieldSize-1)){

            if(id < fieldSize-1) {
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

            if(id > (boggleboardfield.getChildren().size() -fieldSize)) {
                temp = id - 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize -1);
                noDups.add(temp);
                System.out.println("Added: " + temp);
            }

            if(!(id < fieldSize-1) && !(id > (boggleboardfield.getChildren().size() - fieldSize))){
                temp = id - (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - fieldSize;
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - 1;
                noDups.add(temp);
                System.out.println("added: " + temp);

                temp = id + (fieldSize);
                noDups.add(temp);
                System.out.println("Added: " + temp);

                temp = id - (fieldSize + 1);
                noDups.add(temp);
                System.out.println("Added: " + temp);

            }
        }

        // Binnengetallen...
        if((id > fieldSize-1) && (id < (boggleboardfield.getChildren().size() - fieldSize)) && ((id % fieldSize != 0)) && ((id % fieldSize != fieldSize -1))){
            temp = id - (fieldSize + 1);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize);
            noDups.add(temp);
            System.out.println("added: " + temp);

            temp = id - (fieldSize -1);
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
        if((id < fieldSize) && (id % fieldSize != 0) && id % fieldSize != fieldSize-1){
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
        if((id > (boggleboardfield.getChildren().size() - fieldSize)) && (id % fieldSize != 0) && id % fieldSize != fieldSize-1){
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

        return new ArrayList<Integer>(noDups);
    }
}

