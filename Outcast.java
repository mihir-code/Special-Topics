import edu.princeton.cs.algs4.WordNet;


public class Outcast{
    private final WordNet wordnet;
    public Outcast(WordNet wordnet){
        this.wordnet = wordnet;

    }
    public String outcast(String[] nouns){
        String out = noun[0];
        int sum = Integer.MAX_VALUE;
        for(int i = 1; i < nouns.length; i++){
            s += wordnet.distance(noun[0],noun[i]);
            if (sum < s){
                return sum;
            }
        }

    }
    public static void main (String[] args){
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
        In in = new In(args[t]);
        String[] nouns = in.readAllStrings();
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));

        }
    }
}