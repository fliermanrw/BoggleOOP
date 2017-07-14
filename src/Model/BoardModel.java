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
class BoardModel {
    private final char[][] board;
    private final int fieldSize;
    private BoggleModel boggleModel;
    private boolean[][] isVisited;
    String boardLetters = "";


    BoardModel(BoggleModel boggleModel, int fieldSize) {
        this.boggleModel = boggleModel;

        this.board = new char[fieldSize][fieldSize];
        this.isVisited = new boolean[fieldSize][fieldSize];
        this.fieldSize = fieldSize;


    }

    void fillBoard(){
        for (int x = 0; x < fieldSize; x++) {
            for (int y = 0; y < fieldSize; y++) {

                char letter = boggleModel.assignLetter();
                board[x][y] = letter;

                boardLetters = boardLetters + letter;
            }
        }
    }

    void clearIsVisited(){
        isVisited = new boolean[fieldSize][fieldSize];
    }


    char[][] getBoard(){
        return board;
    }

    boolean[][] getIsVisited(){
        return isVisited;
    }

    String getBoardLetters(){
        return boardLetters;
    }


}
