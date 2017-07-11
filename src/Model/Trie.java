package Model;

import javafx.scene.*;
import Controller.BoggleController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by Ryan on 26-4-2017.
 */

//https://examples.javacodegeeks.com/core-java/trie-tutorial-java/
// Added comments to fully understand the code instead of just copying it from the interwebs!

public class Trie {
    private TrieNode root;
    private int size;
    private BoggleController boggleController;
    private BoggleModel boggleModel;
    private HashSet<String> wordList;


    Trie(BoggleModel boggleModel) throws IOException {
        this.size = 0;

        this.root = new TrieNode();
        this.boggleModel = boggleModel;

    }

    // Adds a new word to the Trie
    boolean add(String word){
        TrieNode trie = root;
        if(trie == null || word == null){
            return false;
        }

        char[] chars = word.toCharArray(); // separates the String into characters
        int counter = 0;

        while(counter < chars.length) {
            Set childs = trie.getChildren().keySet();

            // if word NOT yet in Trie, otherwise it will return false
            if (!childs.contains(chars[counter])) {
                insertChar(trie, chars[counter]);

                //
                if (counter == chars.length - 1) {
                    getChild(trie, chars[counter]).setIsWord(true);
                    size++;
                    return true;
                }
            }

            trie = getChild(trie, chars[counter]);
            if (trie.getText().equals(word) && !trie.isWord()) {
                trie.setIsWord(true);
                size++;

                return true;
            }

            counter++;
        }
            // word already in Trie so returns false
            return false;
    }

    // Check if the character(s) are present in the Trie
    // @TODO hij vindt nu ook nog dat "appe" goed is als "appel" een woord is..
    boolean find(String string) {
        Map<Character, TrieNode> children = root.getChildren();
        string = string.toLowerCase();
        TrieNode t = null;
        String endWord = "";
        boolean isWord;


        for(int i = 0; i < string.length(); i++){
            char c = string.charAt(i);

            if(children.containsKey(c)) {
                System.out.println(c);
                t = children.get(c);
                children = t.getChildren();
                endWord += String.valueOf(c);
            } else {
                return false;
            }
        }



        System.out.println("to check..:" + endWord);
        return true;
    }
    
    public boolean remove(String string) {
        return findNode(root, string);
    }

    // Wat doet ie hier precies?
    private TrieNode getChild(TrieNode trie, Character character) {
        return trie.getChildren().get(character);

    }

    // DINGEN
    private TrieNode insertChar(TrieNode trie, Character character) {
        if (trie.getChildren().containsKey(character)) {
            return null;
        }

        TrieNode next = new TrieNode(trie.getText() + character.toString());
        trie.getChildren().put(character, next);

        return next;

    }

    // FUNCTIE
    private boolean findNode(TrieNode trie, String string) {
        Map<Character, TrieNode> children = root.getChildren();
        TrieNode parent = null;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (children.containsKey(c)) {
                parent = trie;
                trie = children.get(c);
                children = trie.getChildren();

                if (trie.getText().equals(string)) {
                    parent.getChildren().remove(c);
                    trie = null;
                    return true;

                }
            }
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    
}



