package Controller;

import com.sun.prism.image.Coords;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.util.Scanner;

/**
 * Created by Robert on 21-4-2017.
 */
public class startSearch {
    private ArrayList<String> WordList = new ArrayList<>();

    public void WordList() throws IOException {
        //Path Robert: C:/Users/Robert/Desktop/Studie/Thema 2.3/OOP3/OOP3 inlever/BoggleOOP/src/dict.txt"
        Scanner sc = new Scanner(new File("C:/Users/Robert/Desktop/Studie/Thema 2.3/OOP3/OOP3 inlever/BoggleOOP/src/dict.txt"));
        while(sc.hasNext()){
            String line = sc.nextLine();
            WordList.add(line);
            System.out.println("added " + line);
        }
        System.out.println(WordList.size());
    }

    public ArrayList<String> getWordList() throws IOException {

           if (WordList == null) {
               WordList();
           }
           return WordList;

    }






    ////////// ROBERTS CODE
    /*public void start() {
        Task<Void> runTask = new Task<Void>() {
            @Override
            public Void call() throws Exception{
                BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
                String line;

                while((line = br.readLine()) != null){
                    line = line.toUpperCase();


//                    currentWord = findWord(line);
                    if(currentWord != null){
                        System.out.println(Arrays.toString(currentWord));
//                        Platform.runLater(() -> updateDisplay());
//                        Thread.sleep(2000);
                    }
                }
                br.close();

                return null;
            }
        };
        Thread th = new Thread(runTask);
        th.setDaemon(true);
        th.start();

    }*/




   /* public Coords[] findWord(String word){
        List<Coords> options = findLetter(word.charAt(0));

        for(int i=0; i <options.size(); i++){
            List<Coords> chain = new ArrayList<Coords>();

            chain.add(options.get(i));

            chain = findChain(chain, word, 1);

            if (chain != null && chain.size() == word.length()) {
                Coords[] r = new Coords[chain.size()];
                return chain.toArray(r);
            }
        }
        return null;
    }
    public List<Coords> findChain(List<Coords> chain, String word, int c) {
        if (c >= word.length()) {
            return chain;
        }

        List<Coords> options = findNext(chain, word.charAt(c));
        c++;

        for (int i = 0; i < options.size(); i++) {
            List<Coords> next = new ArrayList<Coords>(chain);
            next.add(options.get(i));

            next = findChain(next, word, c);
            if (next != null) {
                return next;
            }
        }

        return null;
    }*/

    /*public List<Coords> findLetter(char letter) {
        List<Coords> options = new ArrayList<Coords>();

        for (int x = 0; x < X; x++) {
            for (int y = 0; y < Y; y++) {
                if (grid[x][y] == letter) {
                    options.add(new Coords(x, y));
                }
            }
        }

        return options;
    }
    public List<Coords> findNext(List<Coords> chain, char letter) {
        Coords last = chain.get(chain.size()-1);

        List<Coords> options = new ArrayList<Coords>();

        for (int x = last.x-1; x <= last.x+1; x++) {
            if (x < 0 || x >= X) continue;

            for (int y = last.y-1; y <= last.y+1; y++) {
                if (y < 0 || y >= Y) continue;
                if (x == last.x && y == last.y) continue;

                if (grid[x][y] == letter) {
                    Coords next = new Coords(x, y);

                    boolean alreadyUsed = false;

                    for (Coords c : chain) {
                        if (c.x == next.x && c.y == next.y) alreadyUsed = true;
                    }

                    if (alreadyUsed) continue;

                    options.add(next);
                }
            }
        }
        return options;
    }*/
}
