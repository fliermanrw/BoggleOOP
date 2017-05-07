package Model;

import Controller.BoggleController;

/**
 * Created by Ryan on 7-5-2017.
 */
public class BoardModel {
    private final char[][] board;
    private final int fieldsize;
    BoggleModel boggleModel;


    public BoardModel(BoggleModel boggleModel, int fieldsize) {
        this.board = new char[fieldsize][fieldsize];
        this.fieldsize = fieldsize;
        this.boggleModel = boggleModel;
    }

    public void fillBoard(){
        for (int x = 0; x < fieldsize; x++) {
            for (int y = 0; y < fieldsize; y++) {

                char letter = boggleModel.assignLetter();
                board[x][y] = letter;
            }
        }
    }

    public char[][] getBoard(){
        return board;
    }


}
