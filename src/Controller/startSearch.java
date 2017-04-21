package Controller;

import com.sun.prism.image.Coords;
import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Robert on 21-4-2017.
 */
public class startSearch {
    private Coords[] currentWord;
    public void start() {
        Task<Void> runTask = new Task<Void>() {
            @Override
            public Void call() throws Exception{
                BufferedReader br = new BufferedReader(new FileReader("dict.txt"));
                String line;
                while((line = br.readLine()) != null){
                    line = line.toUpperCase();

//                    currentWord = findWord(line);
                    if(currentWord != null){
                        System.out.println(currentWord.toString());
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

    }

//    public Coords[] findWord(String word){
//        List<Coords> options = findLetter(word.charAt(0));
//
//        for(int i=0; i <options.size(); i++){
//            List<Coords> chain = new ArrayList<Coords>();
//
//            chain.add(options.get(i));
//
//            chain = findChain(chain, word, 1);
//
//            if (chain != null && chain.size() == word.length()) {
//                Coords[] r = new Coords[chain.size()];
//                return chain.toArray(r);
//            }
//        }
//        return null;
//    }
}
