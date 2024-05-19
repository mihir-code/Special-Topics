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
            char c = word.charAt(l);
            i.n[c - 'A'] = add(i.n[c - 'A'], word, l + 1); // need single characters to denote a character
        }
        return i;
    }

    private Node get

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
    }
    public BoggleSolver(String[] dictionary){
        if(dictionary == null){
            throw new IllegalArgumentException();
        }

    }
    public Iterable<String> getAllValidWords(BoggleBoard board){

    }
    public int scoreOf(String word){

    }
}