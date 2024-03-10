import edu.princeton.cs.algs4.Picture;
import jawa.awt.Color;


public class SeamCarver{
    private Picture picture;
    private double[][] energy;
    
    
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

        if (energy !)



    }
    public int[] findHorizontalSeam(){
        
    }
    public int[] findVerticalSeam(){

    }
    public void removeHorizontalSeam(int[] seam){
        
    }

    private boolean Border(int x, int y){
        return x == 0 || x == this.width() -1 || y == 0 || y == this.height() -1; 

    }
}