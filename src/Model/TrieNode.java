package Model;

import java.util.HashMap;

/**
 * Created by Ryan on 28-4-2017.
 */

// https://examples.javacodegeeks.com/core-java/trie-tutorial-java/
// Added comments to fully understand the code instead of just copying it from the interwebs!

public class TrieNode {
    private HashMap<Character, TrieNode> children;
    private String text;
    private boolean isWord;

    // Constructor of a new TrieNode
    public TrieNode(){
        children = new HashMap<Character, TrieNode>();
        text = "";
        isWord = false;
    }

    public TrieNode(String text){
        this();
        this.text = text;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void clearHashMap(){
        children = null;
        children = new HashMap<Character, TrieNode>();
    }

    public String getText(){
        return text;
    }

    public boolean isWord(){
        return isWord;
    }

    public void setIsWord(boolean word){
        isWord = word;
    }

    @Override
    public String toString(){
        return text;
    }
}
