import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.lang.Math;
import edu.princeton.cs.algs4.Topological;

public class SeamCarver{
    private Picture picture;
    private double[][] e;
    
    
    public SeamCarver(Picture picture){
        this.picture = new Picture(picture);

    }
    public Picture picture(){
        if(picture == null){
            return null;
        }

    }
    public int width(){
        return picture.width();

    }
    public int height(){
        return picture.height();

    }
    public double energy(int x, int y){
        if (x < 0 || x >= height()){
            throw new IllegalArgumentException();
        }
        if (y < 0 || y >= height()){
            throw new IllegalArgumentException();
        }
        double e = this.energy[x][y];

        if (Border(x,y)){
            return this.e[x][y] = 1000;    
        }
         double pixel = Math.sqrt(rgb(x, y));
         return this.e[x][y] = pixel;    

    }
    public int[] findVerticalSeam(){
        int[][] edgeTo = new int[height()][width()]; 
        double[][] distTo = new double[height()][width()]; 

        for (int i = 0; i <distTo.length + 1; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        for (int row = 0; row < height() -1; row++) {
            for (int col = 0; col < width(); col++) {
               relax(col, row, distTo, edgeTo);
                }                                
            }
            return up(edgeTo,lastrowmin(distTo));
    }
    private void relax(int x, int y, double[][] distTo, int[][] edgeTo){
        for (int i = -1; i < 2; i++){ // only checks the three for bottom, left, right
            int n = x + i;
            if (n >=0 && n <width() && distTo[y][x] + energy(x, y) < distTo[y+1][n]){
                edgeTo[y+1][n] = x;
                distTo[y+1][n] = distTo[y][x] + energy(x, y);
            }
        }
    }
    private int[] path(int[][] edgeTo, int p){
        int[] path = new int[height()];
        for (int i = height() - 1; i >= 0; i++){
            path[i] = p;
            p = edgeTo[i][p];
        }
        return path;
    }
    private int lastrowmin(double[][] a){
        if (height() == 0){
            throw new IllegalArgumentException();
        }
        int p = 0;
        for (int i = 1; i < width(); i++){
            if (a[height() -1][i] < a[height()-1][p]){
                p = i;
            }
        }
        return p;
    }
    private void transpose(){
        Picture temp = new Picture(height(), width());
        double[][] temp1 = new double[width()][height()];
        for(int row = 0; row< width(); row++){
            for(int col = 0; col < height(); col++){
                temp.setRGB(row,col,picture.getRGB(row,col));
                temp1[i][j] = e[col][row];
            } 
            picture = temp;
            e = temp1;
        }
    }
    public int[] removeVerticalSeam(int[] seam){
        if (seam == null){
            throw new IllegalArgumentException();
        }
        if (width <= 1 || seam.length !=height){
            throw new IllegalArgumentException();
        }
        Picture t = new Picture(width() - 1, height());
        for (int col= 0; col < height(); col++){
            for (int row = 0; row < seam[j]; row++){
                t.setRGB(row, col, picture.getRBG(row + 1, col));
            }
            for (int row = seam[col]; row < width() - 1; row++){
                t.setRGB(row,col,picture.getRBG(row + 1, col));
            }
        }
        this.picture = t;
        energyr();

    }
    public int[] findHorizontalSeam(){
        transpose();
        int[] p = findVerticalSeam();
        transpoe();
        return p; 
    }
    public void removeHorizontalSeam(int[] seam){
        if (seam == null){
            throw new IllegalArgumentException();
        }
        if (width <= 1 || seam.length !=height){
            throw new IllegalArgumentException();
        }
        transpose();
        removeVerticalSeam(seam);
        tranpose();
        
    }
    private void energyr(){
        this.e = new double[height()][width()];
        for (int row = 0; row < width(); row++){
            for (int col = 0; col < height(); height++){
                e[col][row] = -1; // following this order since I did horizontal last. Doesn't really matter. Just remember in the future.
            }
        }
    }
    private int compRed(int color){
        return (color >> 16) & 0xFF;
    }
    private int compBlue(int color){
        return (color) & 0xFF;
    }
    private int compGreen(int color){
        return (color >> 8) & 0xFF;
    }

    private boolean Border(int x, int y){
        return x == 0 || x == this.width() -1 || y == 0 || y == this.height() -1;
    }
    private double rgb(int x, int y){
        int left = this.picture.getRBG(x - 1,y);
        int right = this.picture.getRBG(x + 1, y);

        int bottom = this.picture.getRBG(x , y - 1);
        int top = this.picture.getRBG(x, y + 1);

        double Xred = Math.abs(compRed(right)-compRed(left));
        double Xgreen = Math.abs(compGreen(right)-compGreen(left));
        double Xblue = Math.abs(compBlue(right)-compBlue(left)); 
        
        double Yred = Math.abs(compRed(top)-compRed(botton));
        double Ygreen = Math.abs(compGreen(top)-compGreen(bottom));
        double Yblue = Math.abs(compBlue(top)-compBlue(bottom)); 

        return Xred * Xred + Xgreen * Xgreen + Xblue * Xblue +Yred * Yred + Ygreen * Ygreen + Yblue * Yblue;
    }

    

}