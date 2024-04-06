import edu.princeton.cs.algs4.StdIn;
public class Permutation {
    public static void main (String[] args){
        if (args.length == 1){
            int k = Integer.parseInt(args[0]);
            RandomizedQueue<String> random = new RandomizedQueue<>();
            while (!StdIn.isEmpty()){
                random.enqueue(StdIn.readString());
            }
            for (int j = 0; j < k; j++){
                System.out.println(random.dequeue() + "\n");
            }
        }
    }
    
}
