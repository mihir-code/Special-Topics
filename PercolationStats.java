import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double[] grid;
    private final int numexperiments;

    public PercolationStats(int n, int trials) { 
        if (n <=0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        numexperiments = trials;
        grid = new double[numexperiments];
        for (int i = 0; i < numexperiments; i++){
            Percolation x = new Percolation(n);
            int opensites = 0;
            while (!x.percolates()) {
                int row = StdRandom.uniformInt(n) + 1;
                int col = StdRandom.uniformInt(n) + 1;
                if (!x.isOpen(row, col)){
                    x.open(row,col);
                    opensites++;
                }
            }
            double num = (double) opensites/ (n*n);
            grid[i] = num;
        }

    }
    public double mean(){
        return StdStats.mean(grid);
        

    }
    public double stddev(){
        return StdStats.stddev(grid);
        
    }
    public double confidenceLo(){
        return mean() - ((1.96 * stddev()) / Math.sqrt(numexperiments));

    }
    public double confidenceHi(){
        return mean() + ((1.96 * stddev()) / Math.sqrt(numexperiments));
        
    }
    public static void main (String[] args){
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);

        System.out.println("mean is = " + stats.mean());
        System.out.println("stddev is = " + stats.stddev());
        System.out.println("95% confidence interval = " + "[" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}