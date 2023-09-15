import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String [] args){
        String input = "";
        for (int i = 1; !StdIn.isEmpty(); i++){
            String word =  StdIn.readString();
            if (StdRandom.bernoulli(1.0/i)) {
                input = word;
            }
        }
        StdOut.println(input);
    }

    


}
