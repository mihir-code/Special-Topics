import edu.princeton.cs.algs4.StdDraw;
import java.util.Comparator;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void draw(){
        StdDraw.point(x,y);

    }
    public void drawTo(Point that){
        StdDraw.line(this.x,this.y,that.x,that.y);

    }
    public String toString(){
        return "(" + x + ", " + y +")";


    }

    public int compareTo(Point that){
        if (y == 0 || x == 0){
            throw new java.lang.NullPointerException();
            

        }
        if (this.y < that.y){
            return -1;
        }
        if (this.y == that.y){
            if (this.x == that.x){
                return 0;
            }
            if (this.x < that.x){
                return 1;
            }
        }
        return 1;
    }
    public double slopeTo(Point that){
        double y = (this.y - that.y);
        double x = (this.x - that.x);
        double pinf = Double.POSITIVE_INFINITY;
        double ninf = Double.NEGATIVE_INFINITY;
        if (y == 0 && x == 0){ 
            return ninf;
        }
        if (y == 0){
            return 0;
        }
        if (x == 0){
            return pinf;
        }
        return y/x;
    }
    public Comparator<Point> slopeOrder(){
        return new SlopeOrder();
    }
    private class SlopeOrder implements Comparator<Point>{
        public int compare(Point a, Point b){
            double x = slopeTo(a);
            double y = slopeTo(b);
            if (x > y){
                return 1;
            }
            if (x < y){
                return -1;
            }
            return 0;

        }

    }

    
}
