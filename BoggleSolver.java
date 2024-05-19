import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class BoggleSolver{

    private static final int let = 26; // needs to be static to make the reference
    private int row;
    private int col;
    private Cube[] normal;
    private Node node;
    private char[] board;
    private boolean[] t;
    
    private static class Node{
        private boolean w;
        private Node[] n = new Node[let];
    }
    private Node add(Node i, String word, int l){
        if (i == null){
            i = new Node();
        }
        if (l == word.length()){
            i.w = true;
        }
        else{
            char chat = word.charAt(l);
            i.n[chat - 'A'] = add(i.n[chat - 'A'], word, l + 1); // need single characters to denote a character
        }
        return i;
    }

    private Node get(Node i, String word, int l){
        if (i == null){
            i = new Node();
        }
        if (l == word.length()){
            return i;
        }
        char chat = word.charAt(l);
        Node g = get(i.n[chat - 'A'], word, l + 1);
        return g;
    }

    private static class Cube {
        private int side = 0;
        private int[] adj = new int[8];
    }

    private void put(String word){
        if (word == null){
            throw new IllegalArgumentException();
        }
        root = add(root,word,0); // add to the root.

    }
    private boolean contains(String word){
        if(word == null){
            throw new IllegalArgumentException();
        }
        Node i = get(root,word,0);
        if (i == null){
            return false;
        }
        return i.w;
    }
    public BoggleSolver(String[] dictionary){
        if(dictionary == null){
            throw new IllegalArgumentException();            
        }
        for(int i = 0; i < dictionary.length; i++){
            put(dictionary[i]);
        }

    }
    public Iterable<String> getAllValidWords(BoggleBoard board){
        if (board == null){
            throw new IllegalArgumentException();
        }
        if (row != board.rows() || col != board.cols()) {
            row = board.rows();
            col = board.cols();
        }

    }
    public int scoreOf(String word){

    }
}