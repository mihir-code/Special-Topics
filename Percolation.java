import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private int opensites;
    private int n;
    private boolean [] sites;
    

    public Percolation(int n){
        if (n <= 0)
        throw new IllegalArgumentException();
        sites = new boolean [n*n];



    }
    public void open (int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegealArgumentException();

    }
    public boolean isOpen(int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegealArgumentException();

    }
    public boolean isFull(int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegealArgumentException();
        
        
    }
    public int numberOfOpenSites(){

    }
    public boolean percolates(){

    }

}