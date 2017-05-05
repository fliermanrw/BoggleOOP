package Controller;

import Controller.BoggleController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by bilash on 2/20/15.
 */
public class Boggler {
    private final int FIELD_SIZE = 4;
    private char[][] board = new char[FIELD_SIZE][FIELD_SIZE];
    private DictNode root;

    public GridPane boggleboardfield;
    public Pane wordfield;
    BoggleController boggleController;

    @FXML
    protected void initialize() throws IOException {
        toDict();
        generateNewBoard();

    }

    public void SearchStart() throws IOException {
        long start = System.currentTimeMillis();

        boggleTrieDynamic(root, new char[50], 0);

        long end = System.currentTimeMillis();

        System.out.println("Total time spent = " + (end - start) + " milli seconds.");

        System.out.println("Done...");

    }

    public void ResetBoard() {
        boggleboardfield.getChildren().clear();
        generateNewBoard();
    }

    public void Testerx() {

    }

    public void generateNewBoard(){
        for (int row = 0; row < FIELD_SIZE; row++) {
            boggleboardfield.addRow(row);
            for (int col = 0; col < FIELD_SIZE; col++) {
                String content = String.valueOf(assignLetter());
                content = content.toLowerCase();

                board[row][col] = content.charAt(0);
                Button a = new Button();
                a.setMinWidth(70.0);
                a.setMinHeight(70.0);
                a.setText(content);

                a.setId(String.valueOf(row * FIELD_SIZE + col));
                boggleboardfield.addColumn(col, a);
            }


        }
    }

    public Character assignLetter(){
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        Random rd = new Random();
        return letters.charAt(rd.nextInt(letters.length()));
    }

    /*CLASSSS
    * CLASSSS
    * CLASSSS
    * */

    private class DictNode {
        public final char letter;
        public DictNode[] nextNodes = new DictNode[26];
        public boolean wordEnd;

        public DictNode(final char letter) {
            this.letter = letter;
        }



        public void insert(final String word) {
            DictNode node = root;

            char[] chars = word.toCharArray();
            System.out.println(node.nextNodes.length);

            for (int counter = 0; counter < chars.length; counter++) {
                    //System.out.println("hier ook nog");


                    if (node.nextNodes == null) {
                        node.nextNodes[chars[counter]] = new DictNode(chars[counter]);
                        System.out.println("inserted?");

                        if (counter == chars.length - 1) {
                            node.nextNodes[chars[counter]].wordEnd = true;
                            System.out.println("endword");
                        }
                    } else{
                        System.out.println("niet nul dus..");
                    }
                System.out.println("x");
                System.out.println(chars[counter]);

                //TODO dit gaat fout
                    //node = node.nextNodes[chars[counter]];
                    counter++;
            }


        }


        public boolean contains(final String word) {
            DictNode node = root;
            char[] letters = word.toCharArray();
            int counter = 0;
            while (counter < letters.length && node.nextNodes[letters[counter]] != null) {
                node = node.nextNodes[letters[counter]];
                counter++;
            }

            return (counter == letters.length) && node.wordEnd;

        }
    }

    public boolean isInBoard(final String word) {
        int[] dx = {1, 1, 0, -1, -1, -1, 0, 1};
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

        boolean[][][] dp = new boolean[50][FIELD_SIZE][FIELD_SIZE];
        char[] letters = word.toCharArray();

        for (int k = 0; k < letters.length; k++) {
            for (int i = 0; i < FIELD_SIZE; i++) {
                for (int j = 0; j < FIELD_SIZE; j++) {
                    if (k == 0) {
                        dp[k][i][j] = true;
                    }
                    else {
                        for (int l = 0; l < 8; l++) {
                            int x = i + dx[l];
                            int y = j + dy[l];

                            if ((x >= 0) && (x < FIELD_SIZE) && (y >= 0) && (y < FIELD_SIZE)
                                    && (dp[k - 1][x][y]) && (board[i][j] == letters[k])) {
                                dp[k][i][j] = true;
                                if (k == letters.length-1) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public void boggleTrieDynamic(DictNode node, char[] currentBranch, int currentHeight) {
        if (node == null) {
            return;
        }

        if (node.wordEnd && currentHeight > 3) {
            String word = new String(currentBranch, 0, currentHeight-1);
            boolean inBoard = isInBoard(word);
            if (inBoard) {
                System.out.println(word);
            }
        }

        for (int i = 0; i < node.nextNodes.length; i++) {
            if (node.nextNodes[i] != null) {
                currentBranch[currentHeight] = (char) (i + 'a');
                boggleTrieDynamic(node.nextNodes[i], currentBranch, currentHeight + 1);
            }
        }
    }


    public void toDict() throws IOException {
       /* Scanner sc = new Scanner(new File(""));
        root = new DictNode();
        while(sc.hasNext()){
            String line = sc.nextLine();
            root.insert(line);
        }*/

        String dictFile = "C:/Users/Ryan/IdeaProjects/BoggleOOP/src/dict.txt";
        List<String> words = Files.readAllLines(new File(dictFile).toPath(), StandardCharsets.UTF_8);

        root = new DictNode('\0');
        for (String word : words) {
            root.insert(word);
        }

    }
}