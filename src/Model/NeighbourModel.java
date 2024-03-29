package Model;

import java.util.ArrayList;
import java.util.HashSet;


class NeighbourModel {
    private BoggleModel boggleModel;
    private final int fieldSize;
    private HashSet<Integer> noDups;
    private ArrayList<Integer> fieldIDs;

    NeighbourModel(BoggleModel boggleModel, int fieldSize) {
        this.boggleModel = boggleModel;
        this.fieldSize = fieldSize;
        this.noDups = new HashSet<>();
        this.fieldIDs = new ArrayList<>();
    }

    ArrayList<Character> getNeighbours(int id) {
        // clear previous noDups
        noDups = new HashSet<>();
        fieldIDs = new ArrayList<>();

        int totalFieldSize = fieldSize * fieldSize;

        // Linkerkant checks
        if ((id % fieldSize == 0)) {

            //linksboven
            if (id < fieldSize) {
                addtoNeighbours(id + 1);
                addtoNeighbours(id + fieldSize);
                addtoNeighbours(id + fieldSize + 1);
            }

            // linksonder
            if (id > (totalFieldSize - (fieldSize + 1))) {
                addtoNeighbours(id - fieldSize);
                addtoNeighbours(id - (fieldSize - 1));
                addtoNeighbours(id + 1);


            }

            //linksmidden
            if (!(id < fieldSize - 1) && !(id > (totalFieldSize - (fieldSize + 1)))) {
                addtoNeighbours(id - fieldSize);
                addtoNeighbours( id - (fieldSize - 1));
                addtoNeighbours(id + 1);
                addtoNeighbours(id + fieldSize);
                addtoNeighbours(id + (fieldSize + 1));


            }
        }

        //Rechterzijkant Check
        if ((id % fieldSize == fieldSize - 1)) {
            if (id < fieldSize + 1) {
                addtoNeighbours(id - 1);
                addtoNeighbours(id + fieldSize);
                addtoNeighbours(id + fieldSize - 1);
            }

            if (id > (totalFieldSize - (fieldSize + 1))) {
                addtoNeighbours(id - 1);
                addtoNeighbours(id - (fieldSize));
                addtoNeighbours(id - (fieldSize - 1));
            }

            if (!(id < fieldSize + 1) && !(id > (totalFieldSize - (fieldSize + 1)))) {
                addtoNeighbours(id - (fieldSize + 1));
                addtoNeighbours(id - fieldSize);
                addtoNeighbours(id -1);
                addtoNeighbours(id + (fieldSize -1));
                addtoNeighbours(id + (fieldSize));


            }
        }

        // Binnengetallen...
        if ((id > fieldSize - 1) && (id < (totalFieldSize - fieldSize)) && ((id % fieldSize != 0)) && ((id % fieldSize != fieldSize - 1))) {
            addtoNeighbours(id - (fieldSize + 1));
            addtoNeighbours(id - (fieldSize));
            addtoNeighbours(id - (fieldSize - 1));
            addtoNeighbours(id - 1);
            addtoNeighbours(id + 1);
            addtoNeighbours(id + (fieldSize - 1));
            addtoNeighbours(id + (fieldSize));
            addtoNeighbours(id + (fieldSize + 1));
        }

        // Bovengetallen
        if ((id < fieldSize) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            addtoNeighbours(id - 1);
            addtoNeighbours(id + 1);
            addtoNeighbours(id + (fieldSize - 1));
            addtoNeighbours(id + (fieldSize));
            addtoNeighbours(id + (fieldSize + 1) );
        }

        // Ondergetallen
        if ((id > (totalFieldSize - fieldSize)) && (id % fieldSize != 0) && id % fieldSize != fieldSize - 1) {
            addtoNeighbours(id - (fieldSize + 1));
            addtoNeighbours(id - (fieldSize) );
            addtoNeighbours(id - (fieldSize - 1) );
            addtoNeighbours(id - 1);
            addtoNeighbours(id + 1);
        }

        ArrayList<Character> neighbours = new ArrayList<>();
        ArrayList<Character> temp = new ArrayList<>();

        char[][] board = boggleModel.getBoard();

        for(int x = 0; x < fieldSize; x++){
            for(int y = 0; y < fieldSize; y++){
                temp.add(board[x][y]);

            }
        }

        for(int value: noDups){
            neighbours.add(temp.get(value));
            fieldIDs.add(value);
        }

        return neighbours;

    }

    private void addtoNeighbours(int fieldNumber){
        noDups.add((fieldNumber));

    }

    public ArrayList<Integer> getFieldIDs(){
        return fieldIDs;
    }

}
