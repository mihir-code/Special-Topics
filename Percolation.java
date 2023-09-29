import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation{
    private int opensites;
    private final int n;
    private final boolean [][] sites;
    private final WeightedQuickUnionUF gridtop;
    private final int virtualtop;
    private final int virtualbottom;
 
    public Percolation(int n){
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        sites = new boolean [n][n];
        opensites = 0;
        gridtop = new WeightedQuickUnionUF(n*n+2);
        virtualbottom = n * n + 1;
        virtualtop = 0;
        this.n = n;


    }
    
    private int index(int row, int col){
        return n *(row-1) + col;
    }

    public void open (int row, int col){
        if (row > n || row <=0 || col > n || col <= 0){
            throw new IllegalArgumentException();
        }
        sites[row-1][col-1] = true;
        ++opensites;
        
        if (row == 1){
            gridtop.union(index(row,col), virtualtop);
        }
        if (row == n){
            gridtop.union(index(row,col), virtualbottom);
        
        }
        if (row > 1 && isOpen(row - 1,col)){
            gridtop.union(index(row,col), index(row - 1,col));
        }
        if (col > 1 && isOpen(row, col - 1)){
            gridtop.union(index(row,col), index(row, col - 1));

        }
        if (row < n && isOpen(row + 1, col)){
            gridtop.union(index(row,col), index(row +1, col));
        }

        if (col < n && isOpen(row,col + 1)){
            gridtop.union(index(row,col), index(row, col + 1));
        }
    }

    public boolean isOpen (int row, int col){
        if (row > n || row <=0 || col > n || col <= 0){
            throw new IllegalArgumentException();
        }
        return sites [row-1][col-1];
    }

    public boolean isFull(int row, int col){
        if ((row > 0 && row <= n) && (col > 0 && col <=n)){
            return gridtop.find(virtualtop) == gridtop.find(index(row,col));
        }
        else throw new IllegalArgumentException();
    }

    public int numberOfOpenSites(){
        return opensites;
    }

    public boolean percolates(){
        return gridtop.find(virtualtop) == gridtop.find(virtualbottom);
    }

}