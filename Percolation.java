import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private int opensites;
    private int n;
    private boolean [][] sites;
    private WeightedQuickUnionUF gridtop;
    private int virtualtop;
    private int virtualbottom;
 
    public Percolation(int n){
        if (n <= 0)
        throw new IllegalArgumentException();
        sites = new boolean [n][n];
        opensites = 0;
        gridtop = new WeightedQuickUnionUF(n*n+2);
        virtualbottom = n*n + 1;
        virtualtop = n * n;


    }
    public void open (int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        sites[row][col] = true;
        
        if (row == 1){
            gridtop.union(row,col);
        }
    

    }
    public boolean isOpen (int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        if (row <=n || row >=n || col >=n || col <=n){ 
        }

    }
    public boolean isFull(int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        
        
    }
    public int numberOfOpenSites(){
        return opensites;

    }
    public boolean percolates(){
        // check if it goes over the percolation number which is 0.597246

    }

}