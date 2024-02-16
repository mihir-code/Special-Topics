import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.in;
import edu.prinecton.cs.algs4.SAP;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.Hashmap;


public class WordNet{

    private final Hashmap<Integer, String> idtosynsets;
    private final Hashmap<String, Bag<Integer>> nountoid;
    private final SAP sap;
    private final Digraph g;

    public WordNet(String synsets, String hypernyms){

        idtosynsets = new Hashmap<Integer, String>(); // stores synsets
        nountoid = new Hashmap<String, Bag<Integer>>(); // stores nouns and related ids
        int root = 0;
        for(int i = 0; i < g.V(); i++){
            if (g.outdegree(i) == 0){
                root++;
            }
            else{
                throw new IllegalArgumentException();
            }
        }

        DirectedCycle hc = new DirectedCycle(g);
        if (hc.hasCycle){
            throw new IllegalArgumentException();
        }
        

    }
    private int Syn(String synsets){ // for reading the 
        if (synsets == null){
            throw new IllegalArgumentException();
        }
        In in = new In(synsets);
        int count = 0;
        while (in.HasNextLine()){
            count++;
            String line = in.readline();
            String[] parts = line.split(","); // split by comma for parts
            int id = Integer.parseint(parts[0]);
            idtosynsets.put(id, parts[1]);
            String[] noun = parts[1].split(" "); //split by space for nouns
            for (String n: nouns){
                if(idtosynsets.get(n) != null){
                    Bag<Integer> syn = idtosynsets.get(n);
                    syn.add(n);
                }
                else{
                    Bag<Integer> syn = new Bag<Integer>(); // if it's null, we just proceed
                    syn.add(id);
                    idtosynsets.put(n,syn);
                }
            }
        }
        return count;
    }
    
    private void Hyp(String hypernyms){ // for reading the hypnyms 
        if (hypernyms == null){
            throw new IllegalArgumentException();
        }
        In in = new in(hypernyms);
        while (in.HasNextLine){
            String line = in.readString();
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
    public distance(String nounA, String nounB){
        length(nounA,nounB);

    }
    public String sap(String nounA, String nounB){
        /*for(int i = 1; i < noun.length; i++){
            if (HasPathTo(nounA, nounB) == true){
                return noun[i];
            }
            else if (dis)
        }
*/
        ancestor(nounA,nounB);
        
        
    }
    public static void main(String[] args){
        
    }
}