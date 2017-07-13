package Controller;

import Model.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;


public class BoggleController {
    BoggleModel boggleModel;
    private char[][] board;
    private final int fieldSize = 4;
    public GridPane boggleboardfield;
    public Pane wordfield;
    HashSet<String> wordList;
    ArrayList<Character> neighbours = new ArrayList<>();



    @FXML
    protected void initialize() throws IOException {
        boggleModel.fillBoard();
        generateNewBoard();

    }

    public BoggleController() throws IOException {
        this.boggleModel = new BoggleModel(this, fieldSize);
        this.board = boggleModel.getBoard();
        this.wordList = boggleModel.getWordList();

    }

    private void generateNewBoard(){
        // get the generated char[][] board and create the same view in buttons
        boggleModel.resetBoard();
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
        //boggleModel.findInTree("appe");
        //boggleModel.findInTree("appel");
        boggleModel.findInTree("zwommen");
        boggleModel.findInTree("zwan");
        boggleModel.findInTree("a");
    }

    public void ResetBoardButton() {
        boggleboardfield.getChildren().clear();
        generateNewBoard();

    }

    public void TestButton() {
        //boggleModel.getNeighbours(2);
        boggleModel.allWordsSolver();

    }



}


