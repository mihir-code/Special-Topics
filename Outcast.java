import edu.princeton.cs.algs4.WordNet;


public class Outcast{
    private final WordNet wordnet;
    public Outcast(WordNet wordnet){
        this.wordnet = wordnet;

    }
    public String outcast(String[] nouns){
        String out = noun[0];
        int s = 0;
        int sum = 0;
        for(int i = 0; i< nouns.length; i++){
            for(int j = 0; j < nouns.length; j++){
                if(!noun[i]==noun[j]){ 
                    s += wordnet.distance(noun[i],noun[j]);
                }
            }
            if (sum < s){
                s = sum;
                out = noun[i];

            }
        }
        return out;
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