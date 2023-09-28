import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] grid;
    private int trials;

    public PercolationStats(int n, int trials){
        if (n <=0 || trials <=0)
        throw new IllegalAccessError();
        this.trials = trials;
        grid = new double[trials];
        for (int numexperiments = 0; numexperiments < trials; numexperiments++){
            Percolation x = new Percolation(n);
            int opensites = 0;
            while (!x.percolates()) {
                int row = StdRandom.uniformInt(1, n+1);
                int col = StdRandom.uniformInt(1, n+1);
                if (!x.isOpen(row, col)){
                    x.open(row,col);
                    opensites++;
                }
            }
            double num = (double)opensites /n * n;
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
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));

    }
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
        
    }
    public static void main (String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean is                    = " + stats.mean());
        System.out.println("stddev is                  = " +  stats.stddev());
        System.out.println("95% confidence interval    =  [" + stats.confidenceLo() + " , " + stats.confidenceHi() + "] ");
    }
}