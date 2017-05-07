package Model;

import Controller.BoggleController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.lang.Boolean.FALSE;

/**
 * Created by Ryan on 7-5-2017.
 */
public class BoardModel {
    private final char[][] board;
    private final int fieldSize;
    private BoggleModel boggleModel;
    private boolean[][] isVisited;


    public BoardModel(BoggleModel boggleModel, int fieldSize) {
        this.boggleModel = boggleModel;

        this.board = new char[fieldSize][fieldSize];
        this.isVisited = new boolean[fieldSize][fieldSize];
        this.fieldSize = fieldSize;


    }

    public void fillBoard(){
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {

                char letter = boggleModel.assignLetter();
                board[x][y] = letter;

            }
        }
    }

    public void clearIsVisited(){
        isVisited = new boolean[fieldSize][fieldSize];
    }


    public char[][] getBoard(){
        return board;
    }

    public boolean[][] getIsVisited(){
        return isVisited;
    }


}
