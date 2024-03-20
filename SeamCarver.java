import edu.princeton.cs.algs4.Picture;
import java.util.NoSuchElementException;
import java.lang.Math;


public class SeamCarver{
    private Picture picture;
    private double[][] e;
    
    
    public SeamCarver(Picture picture){
        if(picture ==  null){
            throw new IllegalArgumentException();
        }
        this.picture = new Picture(picture);
        e = new double[picture.height()][picture.width()];
        energyr();

    }
    public Picture picture(){
        return new Picture(picture);

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
        if(e[y][x] >= 0){
            return e[y][x];
        }

        if (Border(x,y)){
            e[y][x] = 1000.0;    
        }
        else{
            double val = energyarray(picture.getRGB(x - 1,y), picture.getRGB(x + 1,y)) + 
            energyarray(picture.getRGB(x,y - 1), picture.getRGB(x, y + 1));
            val = Math.sqrt(val);
            e[y][x] = val;

        } 
        return e[y][x]; 

    }
    public int[] findVerticalSeam(){
        double[][] distTo = new double[height()][width()]; 
        
        for (int row = 1; row < height(); row++) { //ignoring the first row.
            for (int col = 0; col < width(); col++) {
                distTo[row][col] = Double.POSITIVE_INFINITY;
                }                                 
            }
            int[][] edgeTo = new int[height()][width()];
        for (int col = 0; col < height() - 1; col++){
            for(int row = 0; row < width(); row++){
                relax(row, col, distTo, edgeTo);
            }
        }
        return path(edgeTo, lastrowmin(distTo));
    }
    private void relax(int x, int y, double[][] distTo, int[][] edgeTo){
        for (int i = -1; i < 2; i++){ // only checks the three for bottom, left, right
            int n = x + i;
            if (n >= 0 && n < width() && distTo[y][x] + energy(x, y) < distTo[y + 1][n]){
                edgeTo[y+1][n] = x;
                distTo[y+1][n] = distTo[y][x] + energy(x, y);
            }
        }
    }
    private int[] path(int[][] edgeTo, int p){
        int[] path = new int[height()];
        for (int i = height() - 1; i >= 0; i--){
            path[i] = p;
            p = edgeTo[i][p];
        }
        return path;
    }
    private int lastrowmin(double[][] a){
        if (height() == 0){
            throw new NoSuchElementException();
        }
        int p = 0;
        for (int row = 1; row < width(); row++){
            if (a[height() -1][row] < a[height()-1][p]){
                p = row;
            }
        }
        return p;
    }
    private void transpose(){ 
        Picture temp = new Picture(height(), width());
        double[][] temp1 = new double[width()][height()];
        for(int row = 0; row< width(); row++){
            for(int col = 0; col < height(); col++){
                temp.setRGB(col, row, picture.getRGB(row,col));
                temp1[row][col] = e[col][row];
            } 
        }
        picture = temp;
        e = temp1;
    }
    public void removeVerticalSeam(int[] seam){
        if (seam == null || seam.length != height()){
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < height(); i++){
            if (seam[i] >= width() || seam[i] < 0){
                throw new IllegalArgumentException();
            }
            if (i > 0 && Math.abs(seam[i] - seam[i-1])> 1){
                throw new IllegalArgumentException();           
         }
        }
        if (width() <= 1){
            throw new IllegalArgumentException();
        }
        Picture t = new Picture(width() - 1, height());
        for (int col= 0; col < height(); col++){
            for (int row = 0; row < seam[col]; row++){
                t.setRGB(row, col, picture.getRGB(row, col)); 
            }
            for (int row = seam[col]; row < width() - 1; row++){
                t.setRGB(row, col, picture.getRGB(row + 1, col));
            }
        }
        this.picture = t;
        energyr();

    }
    public int[] findHorizontalSeam(){
        transpose();
        int[] path = findVerticalSeam();
        transpose();
        return path; 
    }
    public void removeHorizontalSeam(int[] seam){
        transpose();
        removeVerticalSeam(seam);
        transpose();
        
    }
    private void energyr(){
        this.e = new double[height()][width()];
        for (int row = 0; row < width(); row++){
            for (int col = 0; col < height(); col++){
                e[col][row] = -1.0; // following this order since I did horizontal last. Doesn't really matter. Just remember in the future.
            }
        }
    }

    private boolean Border(int x, int y){
        return x == 0 || x == width() - 1 || y == 0 || y == height() - 1;
    }
    private double energyarray(int col1, int col2){
        double x = 0.0;
        for (int i = 0; i < 24; i+=8){
            int hexadecimal = ((col1 >> i) & 0xFF) - ((col2 >> i)& 0xFF);
            x += Math.pow(hexadecimal, 2);
        }
        return x;
    } 

}