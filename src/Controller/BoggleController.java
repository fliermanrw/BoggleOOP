package Controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.Random;


public class BoggleController {
    public GridPane boggleboardfield;
    public Pane wordfield;


    @FXML
    protected void initialize() {
        for (int row = 0; row < 4; row++) {
            boggleboardfield.addRow(row);
            for (int col = 0; col < 4; col++) {
                Button a = new Button();
                a.setMinWidth(100.0);
                a.setMinHeight(100.0);
                a.setText(String.valueOf(assignLetter()));

                //a.setId(Integer.toString(boggleboardfield.rowColToInt(row, col)));
                boggleboardfield.addColumn(col, a);
            }

        }
    }

    public Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";


        Random rd = new Random();

        Character returnLetter = letters.charAt(rd.nextInt(letters.length()));

        return returnLetter;
    }


}

