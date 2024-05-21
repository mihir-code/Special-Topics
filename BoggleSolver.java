

import edu.princeton.cs.algs4.SET;

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
            return null;
        }
        if (l == word.length()){
            return i;
        }
        char chat = word.charAt(l);
        return get(i.n[chat - 'A'], word, l+1);
    }

    private static class Cube {
        private int side = 0;
        private int[] adj = new int[8];
    }

    private void put(String word){
        if (word == null){
            throw new IllegalArgumentException();
        }
        b = add(b,word,0); // add to the root.

    }
    private boolean contains(String word){
        if(word == null){
            throw new IllegalArgumentException();
        }
        Node i = get(b,word,0);
        if (i == null){
            return false;
        }
        return i.w;
    }
    private void DFS(int j, StringBuilder before, SET<String> w, Node node){
        char chat = board[j];
        Node n = node.n[chat-'A'];
            // for the QU
        if (chat == 'Q' && n != null){
            n = n.n['U' - 'A'];
        }
        if(n == null){
            return;
        }
            if(chat == 'Q'){
                before.append("QU");
            }
            else{
                before.append(chat);
            }
            String sg = before.toString();
            if (n.w && before.length() > 2){
                w.add(sg);
            }
            t[j] = true;

            for(int ind = 0; ind < normal[j].side; ind++){
                int nt = normal[j].adj[ind];
                if(!t[nt]){
                    DFS(nt, new StringBuilder(before), w, n);
                }
            }
            t[j] = false;
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
            t = new boolean[row * col];
            compute(board);
            
        }
        for (int i = 0; i < row; i++){
            for (int x = 0; x < col; x++){
                int j = i * col + x;
                char chat = board.getLetter(i,x);
                this.board[j] = chat;
            }
        }
        SET<String> valid = DFS();
        return valid;

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
    private void compute(BoggleBoard board){
        normal = new Cube[row * col];
        for (int i = 0; i < row; i++){
            for (int x = 0; x < col; x++){
                int j = i * col + x;
                normal[j] = new Cube(); 
                int count = 0;
                for(int z = Math.max(0, i -1); z <=Math.min(row-1, i + 1);z++){
                    for(int h = Math.max(0, x -1); h<=Math.min(col-1, x + 1);h++){
                        if(!(z == i && h == x)){
                            normal[j].adj[count++] = z * col + h;
                        }
                    }   
                }
               normal[j].side = count;
            }
        }
    }
}