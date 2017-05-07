package Model;

import Controller.BoggleController;
import Controller.BoggleController.*;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;


/**
 * Created by Ryan on 21-4-2017.
 */
public class BoggleModel {
    private BoardModel boardModel;
    private BoggleController boggleController;
    private NeighbourModel neighbourModel;

    public BoggleModel(BoggleController boggleController, int fieldsize){
        this.boggleController = boggleController;

        this.boardModel = new BoardModel(this, fieldsize);
        this.neighbourModel = new NeighbourModel(this, fieldsize);
    }

    public Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        return letters.charAt(rd.nextInt(letters.length()));
    }

    public char[][] getBoard(){
        return boardModel.getBoard();
    }

    public void fillBoard(){
        boardModel.fillBoard();
    }

    public ArrayList<Integer> getNeighbours(int id){
        return neighbourModel.getNeighbours(id);
    }








}
