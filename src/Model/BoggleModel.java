package Model;

import Controller.BoggleController;
import Controller.BoggleController.*;
import javafx.scene.layout.GridPane;

import java.util.Random;


/**
 * Created by Ryan on 21-4-2017.
 */
public class BoggleModel {
    BoardModel boardModel;
    BoggleController boggleController;

    public BoggleModel(BoggleController boggleController, int fieldsize){
        this.boardModel = new BoardModel(this,fieldsize);
        this.boggleController = boggleController;
    }

    public Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        return letters.charAt(rd.nextInt(letters.length()));
    }

    public char[][] getBoard(){
        return boardModel.getBoard();
    }






}
