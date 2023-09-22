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
        virtualbottom = n * n + 1;
        virtualtop = n * n;


    }
    private int index(int row, int col){
        return n *(row-1) + col;
    }
    public void open (int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        sites[row-1][col-1] = true;
        
        if (row == 1){
            gridtop.union(index(row,col), opensites);
        }
        if (row == virtualbottom){
            gridtop.union(index(row,col), opensites);
        }
        if (row-1,col){
            gridtop.union(index(row-1,col), opensites);
        }
        if (row +1, col){
            gridtop.union(index(row+1,col), opensites);
        }
        if (row, col -1){
            gridtop.union(index(row,col-1), opensites);
        }
        if (row, col+1){
            gridtop.union(index(row,col+1), opensites);
        }
    

    }
    public boolean isOpen (int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        if (row <=n || row >=n || col >=n || col <=n){ 
            return true;
        }

    }
    public boolean isFull(int row, int col){
        if (row > n || row < 1 || col > n || col < 1)
        throw new IllegalArgumentException();
        if (!isOpen){
            return true;
        }
        
        
    }
    public int numberOfOpenSites(){
        return opensites;

    }
    public boolean percolates(){
        if (isOpen == true){
            return true;
        } 
       

    }

}