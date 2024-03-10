import edu.princeton.cs.algs4.Picture;
import java.awt.Color;
import java.lang.Math;


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
        return picture.width;

    }
    public int height(){
        return picture.height;

    }
    public double energy(int x, int y){
        if (x < 0 || x >= height()){
            throw new IllegalArgumentException();
        }
        if (y < 0 || y>= height()){
            throw new IllegalArgumentException();
        }
        double e = this.energy[x][y];

        if (Border(x,y)){
            return this.e[x][y] = 1000;    
        }
         double pixel = Math.sqrt(Xrgb(x, y) + Yrgb(x, y));
         return this.e[x][y] = pixel;    

    }
    public int[] findVerticalSeam(){

    }
    public int[] removeVerticalSeam(int[] seam){

    }
    public int[] findHorizontalSeam(){
        
    }
    public void removeHorizontalSeam(int[] seam){
        
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
// combine them into one
    private int Xrgb(int x, int y){
        int left = this.picture.getRBG(x - 1,y);
        int right = this.picture.getRBG(x + 1, y);

        int Xred = Math.abs(compRed(right)-compRed(left));
        int Xgreen = Math.abs(compGreen(right)-compGreen(left));
        int Xblue = Math.abs(compBlue(right)-compBlue(left)); 
        
        return Xred * Xred + Xgreen * Xgreen + Xblue * Xblue;
    }

    private int Yrgb(int x, int y){
        int bottom = this.picture.getRBG(x , y - 1);
        int top = this.picture.getRBG(x, y + 1);

        int Yred = Math.abs(compRed(top)-compRed(botton));
        int Ygreen = Math.abs(compGreen(top)-compGreen(bottom));
        int Yblue = Math.abs(compBlue(top)-compBlue(bottom)); 

        return Yred * Yred + Ygreen * Ygreen + Yblue * Yblue;


    }

}