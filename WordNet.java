import edu.princeton.cs.algs4.BFS;
import edu.princeton.cs.algs4.in;
import edu.prinecton.cs.algs4.SAP;
import edu.princeton.cs.algs4.Digraph;


public class WordNet{

    private final Hashmap<String, Bag<Integer>> tracker;
    private final Hashmap<Integer, String> store;
    private final SAP sap;
    private final Digraph g;

    public WordNet(String synsets, String hypernyms){

        store = new Hashmap<Integer, String>(); // stores synsets
        tracker = new Hashmap<String, Bag<Integer>>(); // stores nouns and related ids

    }
    private int Syn(String synsets){ // for reading the synsets
        In in = new In(synsets);
        int count = 0;
        while (in.HasNextLine()){
            count++;
            String line = in.readline();
            String[] parts = line.split(","); // split by comma for parts
            int id = Integer.parseint(parts[0]);
            store.put(id, parts[1]);
            String[] noun = parts[1].split(" "); //split by space for nouns
            for (String n: nouns){
                if(store.get(n) != null){
                    Bag<Integer> syn = store.get(n);
                    syn.add(n);
                }
                else{
                    Bag<Integer> syn = new Bag<Integer>();
                    syn.add(id);
                    store.put(n,syn);
                }
            }
            count++;
        }
        return count;
    }
    
    private void Hyp(String hypernyms){ // for reading the hypnyms 
        In in = new in(hypernyms);
        while (in.HasNextLine){
            int line = in.readInt();
            int[] parts = line.split();
            int id = Integer.parseInt(parts[0]);
            
        }

    }
    public Iterable<String> nouns(){
         

    }
    public distance(String nounA, String nounB){

    }
    public String sap(String nounA, String nounB){
        
    }
    public static void main(String[] args){
        
    }
}