package Model;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Ryan on 8-5-2017.
 */
public class NeighbourModel {
    BoggleModel boggleModel;
    final int fieldSize;
    HashSet<Integer> noDups = new HashSet<>();

    public NeighbourModel(BoggleModel boggleModel, int fieldSize) {
        this.boggleModel = boggleModel;
        this.fieldSize = fieldSize;
    }

    public ArrayList<Integer> getNeighbours(int id) {
        int temp;
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
                System.out.println("hier komt ie");
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

        System.out.println("xxxxxxxxxxxxxxxxxxxxx");

        ArrayList<Integer> neighbours = new ArrayList<>();

        // Omzetten naar letters
        // TODO omzetten(?)
        for (Object value : noDups) {
            //Button button = (Button) id.getChildren().get((Integer) value);
            //neighbours.add(Integer.valueOf(button.getId()));

        }

        return neighbours;

    }

    private void addtoNeighbours(int fieldNumber){
        noDups.add((fieldNumber));
        System.out.println(fieldNumber);

    }
}
