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
            double num = (double)opensites/ n * n;
            grid[numexperiments] = num;
        }

    }
    public double mean(){
        return StdStats.mean(grid);
        

    }
    public double stddev(){
        return StdStats.stddev(grid);
        
    }
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / Math.sqrt(count));

    }
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(count));
        
    }
    public static void main (String[] args){
        System.out.println("stddez is = " + stddev());
        System.out.println("mean is = " + mean);
        System.out.println("95% confidence interval is = " + confidenceLo + " , " + confidenceHi);
    }
}