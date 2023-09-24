import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] grid;
    private int count;

    public PercolationStats(int n, int trials){
        if (n <=0 || trials <=0)
        throw new IllegalAccessError();
        count = trials;
        grid = new double[count];
        for (int numexperiments = 0; numexperiments < count; numexperiments++){
            Percolation x = new Percolation(n);
            int opensites = 0;
            while (!x.percolates()) {
                int row = StdRandom.uniformInt(1, n+1);
                int col = StdRandom.uniformInt(1, n+1)
                if (!x.isOpen(row, col)){
                    x.open(row,col);
                    opensites++;
                }
            }
        }

    }
    public double mean(){
        

    }
    public double stddev(){
        
    }
    public double confidenceLo(){

    }
    public double confidenceHi(){
        
    }
    public static void main (String[] args){
        System.out.println("stddez is = " + stddez);
        System.out.println("mean is = " + mean);
        System.out.println("95% confidence interval is = " + confidenceLo + " , " + confidenceHi);
    }
}