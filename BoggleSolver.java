import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;


public class BoggleSolver{

    private static final int let = 26; // needs to be static to make the reference
    private int row;
    private int col;
    private Cube[] normal;
    private Node b;
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
    private void DFS(int j, StringBuilder before, SET<String> w, Node n){
        char chat = board[j];
        Node node = n.n[c-'A'];
            // for the QU
        if (chat == 'Q' && node != null){
            node = node.node['U' - 'A'];
            if(node == null){
                return;
            }
            if(chat == 'Q'){
                before.append("QU");
            }
            else{
                before.append(chat);
            }
            String cur = before.toString();
            if (node.w && before.length() > 2){
                w.add(cur);
            }
            t[j] = true;

            for(int ind = 0; ind < normal[j].side; ind++){
                int nt = normal[j].adj[ind];
                if(!t[nt]){
                    DFS(nt, before, word, node);
                }
            }
            t[j] = false;
            // getting rid of QU
            if(chat == 'Q'){
                before.setLength(before.length() - 2); 
            }
            else{
                before.setLength(before.length() - 1);
            }
        }
    }
    
    private SET<String> DFS() {
        SET<String> w = new SET<String>();
        for (int x = 0; x < row * col; x++){
            DFS(x,new StringBuilder(), w, b);
        }
        return w;

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
            this.board = new char[row * col];
            marked = new boolean[row * col];
            
        }
        for (int i = 0; i < row; i++){
            for (int x = 0; x < col; x++){
                int j = i * col + x;
                char chat = board.getLetter(i,x);
                this.board[j] = chat;
            }
        }

    }
    public int scoreOf(String word){

        if (!contains(word)){
            return 0;
        }
        else if(word.length() < 3){
            return 0;
        }
        else if(word.length() < 5){
            return 1;
        }
        else if(word.length() == 5){
            return 2;
        }
        else if(word.length() == 6){
            return 3;
        }
        else if(word.length() == 7){
            return 5;
        }
        else{
            return 11;
        }

    }
}