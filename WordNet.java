import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Bag;

import java.util.HashMap;

public class WordNet{

    private final HashMap<Integer, String> idtosynsets;
    private final HashMap<String, Bag<Integer>> nountoid;
    private final SAP sap;
    private final Digraph g;

    public WordNet(String synsets, String hypernyms){

        idtosynsets = new HashMap<Integer, String>(); // stores synsets
        nountoid = new HashMap<String, Bag<Integer>>(); // stores nouns and related ids

        int s = Syn(synsets);
        g = new Digraph(s);
        sap = new SAP(g);
        Hyp(hypernyms);

        int root = 0;
        for(int i = 0; i < s; i++){
            if (g.outdegree(i) == 0){
                root++;
            }
        if(root !=1){
                throw new IllegalArgumentException();
            }
        }

        DirectedCycle hc = new DirectedCycle(g);
        if (hc.hasCycle()){
            throw new IllegalArgumentException();
        }
    }
    private int Syn(String synsets){ // for reading the 
        if (synsets == null){
            throw new IllegalArgumentException();
        }
        In in = new In(synsets);
        int s = 0;
        while (in.hasNextLine()){
            s++;
            String line = in.readLine();
            String[] parts = line.split(","); // split by comma for parts
            int id = Integer.parseInt(parts[0]);
            idtosynsets.put(id, parts[1]);
            String[] noun = parts[1].split(" "); //split by space for nouns
            for (String n: noun){
                if(nountoid.get(n) != null){
                    Bag<Integer> syn = nountoid.get(n);
                    syn.add(id);
                }
                else{
                    Bag<Integer> syn = new Bag<Integer>(); // if it's null, we just proceed
                    syn.add(id);
                    nountoid.put(n,syn);
                }
            }
        }
        return s;
    }
    
    private void Hyp(String hypernyms){ // for reading the hypnyms 
        if (hypernyms == null){
            throw new IllegalArgumentException();
        }
        In in = new In(hypernyms);
        while (in.hasNextLine()){
            String line = in.readLine();
            String[] parts = line.split(",");
            int id = Integer.parseInt(parts[0]);
            for(int i = 1; i < parts.length; i++){
                int p = Integer.parseInt(parts[i]);
                g.addEdge(id, p);
            }
        }

    }
    public boolean isNoun(String word){
        if (word == null){
            throw new IllegalArgumentException();
        }
        return nountoid.containsKey(word);
    }
    public Iterable<String> nouns(){
        return nountoid.keySet();
         

    }
    public int distance(String nounA, String nounB){
        if (nountoid.containsKey(nounA)){
            throw new IllegalArgumentException();
        }
        if (nountoid.containsKey(nounB)){
            throw new IllegalArgumentException();
        }
        Bag<Integer> nA = nountoid.get(nounA);
        Bag<Integer> nB = nountoid.get(nounB);

        return sap.length(nA,nB);

    }
    public String sap(String nounA, String nounB){
        if (nountoid.containsKey(nounA)){
            throw new IllegalArgumentException();
        }
        if (nountoid.containsKey(nounB)){
            throw new IllegalArgumentException();
        }
        Bag<Integer> nA = nountoid.get(nounA);
        Bag<Integer> nB = nountoid.get(nounB);

        int path = sap.ancestor(nA,nB);
        String id = idtosynsets.get(path);

        return id;
        
        
    }
    public static void main(String[] args){
        
    }
}