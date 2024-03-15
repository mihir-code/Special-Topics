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
        int num;
        for (int i = 0; i <e.length + 1; i++){
            distTo[i] = Double.POSITIVE_INFINITY;
            distTo[0] = 0.0;
        }

        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(); col++) {
                // make three if statements that checks for the energy below, to the right, and the left.
                
            }
        }
    }
    public int[] removeVerticalSeam(int[] seam){
        if (seam == null){
            throw new IllegalArgumentException();
        }

    }
    public int[] findHorizontalSeam(){
        
    }
    public void removeHorizontalSeam(int[] seam){
        if (seam == null){
            throw new IllegalArgumentException();
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
    private int rgb(int x, int y){
        int left = this.picture.getRBG(x - 1,y);
        int right = this.picture.getRBG(x + 1, y);

        int bottom = this.picture.getRBG(x , y - 1);
        int top = this.picture.getRBG(x, y + 1);

        int Xred = Math.abs(compRed(right)-compRed(left));
        int Xgreen = Math.abs(compGreen(right)-compGreen(left));
        int Xblue = Math.abs(compBlue(right)-compBlue(left)); 
        
        int Yred = Math.abs(compRed(top)-compRed(botton));
        int Ygreen = Math.abs(compGreen(top)-compGreen(bottom));
        int Yblue = Math.abs(compBlue(top)-compBlue(bottom)); 

        return Xred * Xred + Xgreen * Xgreen + Xblue * Xblue +Yred * Yred + Ygreen * Ygreen + Yblue * Yblue;
    }

    

}