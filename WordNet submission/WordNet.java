/**
 * @author Mihir Motukuri attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 */
// Making sure that I got all the base cases was really hard.
// I had to review the guidelines over and over again as the one I forgot about was the outdegree one.
// It also took me a really long time to realize that my root checker was checking the condition immediately, messing up the code.
// After realizing this mistake, it counts the roots first and then throws the exception.

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
        Hyp(hypernyms);

        int root = 0;
        for(int i = 0; i < s; i++){
            if (g.outdegree(i) == 0){
                root++;
            if(root !=1){
                throw new IllegalArgumentException();
            }
        }
    }

       DirectedCycle hc = new DirectedCycle(g);
        if (hc.hasCycle()){
            throw new IllegalArgumentException();
        }
        sap = new SAP(g);
        

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
            String[] part = line.split(",");
            int id = Integer.parseInt(part[0]);
            for(int i = 1; i < part.length; i++){
                int p = Integer.parseInt(part[i]);
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
        if (!nountoid.containsKey(nounA)){
            throw new IllegalArgumentException();
        }
        if (!nountoid.containsKey(nounB)){
            throw new IllegalArgumentException();
        }
        Bag<Integer> nA = nountoid.get(nounA);
        Bag<Integer> nB = nountoid.get(nounB);

        return sap.length(nA,nB);

    }
    public String sap(String nounA, String nounB){
        if (!nountoid.containsKey(nounA)){
            throw new IllegalArgumentException();
        }
        if (!nountoid.containsKey(nounB)){
            throw new IllegalArgumentException();
        }
        Bag<Integer> nA = nountoid.get(nounA);
        Bag<Integer> nB = nountoid.get(nounB);

        int path = sap.ancestor(nA,nB);
        return idtosynsets.get(path); 
    }
    public static void main(String[] args){
        
    }
}