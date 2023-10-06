import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int opensite;
    private final int n;
    private final boolean [][] grid;
    private final WeightedQuickUnionUF gridtop;
    private final WeightedQuickUnionUF backwash;
    private final int virtualtop;
    private final int virtualbottom;
 
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean [n][n];
        opensite = 0;
        gridtop = new WeightedQuickUnionUF(n*n+2);
        virtualbottom = n * n + 1;
        virtualtop = 0;
        this.n = n;
        backwash = new WeightedQuickUnionUF(n*n + 1);


    }
    
    private int index(int row, int col) {
        return n *(row-1) + col; 
    }

    public void open (int row, int col) {
        if (row > n || row <= 0 || col > n || col <= 0) {
            throw new IllegalArgumentException();
        }
        grid[row-1][col-1] = true;
        ++opensite;
        
        if (row == 1) {
            gridtop.union(index(row,col), virtualtop);
            backwash.union(index(row,col), virtualtop);
            
        }
        if (row == n) {
            gridtop.union(index(row,col), virtualbottom);
        
        }
        if (row > 1 && isOpen(row - 1,col)) {
            gridtop.union(index(row,col), index(row - 1,col));
            backwash.union(index(row,col), index(row - 1,col));            
        }
        if (col > 1 && isOpen(row, col - 1)) {
            gridtop.union(index(row,col), index(row, col - 1));
            backwash.union(index(row,col), index(row, col - 1));

        }
        if (row < n && isOpen(row + 1, col)) {
            gridtop.union(index(row,col), index(row +1, col));
            backwash.union(index(row,col), index(row +1, col));
        }

        if (col < n && isOpen(row,col + 1)) {
            gridtop.union(index(row,col), index(row, col + 1));
            backwash.union(index(row,col), index(row, col + 1));
        }
    }

    public boolean isOpen (int row, int col) {
        if (row > n || row <=0 || col > n || col <= 0) {
            throw new IllegalArgumentException();
        }
        return grid[row-1][col-1];
    }

    public boolean isFull(int row, int col){
        if ((row > 0 && row <= n) && (col > 0 && col <= n)) {
            return backwash.find(virtualtop) == backwash.find(index(row,col));
        }
        else throw new IllegalArgumentException();
    }

    public int numberOfOpenSites() {
        return opensite;
    }

    public boolean percolates() {
        return gridtop.find(virtualtop) == gridtop.find(virtualbottom);
    }

}