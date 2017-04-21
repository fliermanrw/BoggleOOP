package Model;
//https://gist.github.com/prettymuchbryce/3719435

import java.util.HashMap;

/**
 * Created by Ryan on 21-4-2017.
 */
public class Dictionary {
    private HashMap<Character,Node> roots = new HashMap<Character,Node>();

    /**
     * Search through the dictionary for a word.
     * @param string The word to search for.
     * @return Whether or not the word exists in the dictionary.
     */
    public boolean search(String string) {
        if (roots.containsKey(string.charAt(0))) {
            if (string.length()==1 && roots.get(string.charAt(0)).endOfWord) {
                return true;
            }
            return searchFor(string.substring(1),roots.get(string.charAt(0)));
        } else {
            return false;
        }
    }

    /**
     * Insert a word into the dictionary.
     * @param string The word to insert.
     */
    public void insert(String string) {
        if (!roots.containsKey(string.charAt(0))) {
            roots.put(string.charAt(0), new Node());
        }

        insertWord(string.substring(1),roots.get(string.charAt(0)));
    }

    //Recursive method that inserts a new word into the trie tree.
    private void insertWord(String string, Node node) {
        final Node nextChild;
        if (node.children.containsKey(string.charAt(0))) {
            nextChild = node.children.get(string.charAt(0));
        } else {
            nextChild = new Node();
            node.children.put(string.charAt(0), nextChild);
        }

        if (string.length() == 1) {
            nextChild.endOfWord = true;
            return;
        } else {
            insertWord(string.substring(1),nextChild);
        }
    }


    //Recursive method that searches through the Trie Tree to find the value.
    private boolean searchFor(String string, Node node) {
        if (string.length()==0) {
            if (node.endOfWord) {
                return true;
            } else {
                return false;
            }
        }

        if (node.children.containsKey(string.charAt(0))) {
            return searchFor(string.substring(1),node.children.get(string.charAt(0)));
        } else {
            return false;
        }
    }
}

class Node {
    public Node parent;
    public Boolean endOfWord = false; //Does this Node mark the end of a particular word?
    public HashMap<Character,Node> children = new HashMap<Character,Node>();
}
