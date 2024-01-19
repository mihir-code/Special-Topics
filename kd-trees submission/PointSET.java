import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET{
    private SET<Point2D> points;

    public PointSET(){
        points = new SET<Point2D>();
       }
    public boolean isEmpty(){ 
        if(points.isEmpty()){
            return true;
        }
    }
    public int size(){
        return points.size();

    }
    public void insert(Point2D p){
        if(p == null){
            throw new IllegalArgumentException();
        }
        if(!points.contains(p)){
            points.add(p);
        }


    }
    public boolean contains(Point2D p){
        if(p == null){
            throw new IllegalArgumentException();
        }
        return points.contains(p); // if the set contains it or not.

    }
    public void draw(){
        StdDraw.clear();
        StdDraw.setPenRadius(.01);
        StdDraw.setPenColor(StdDraw.BLACK);
        for(Point2D p : points){
            point.draw();
        }


    }
    public Iterable<Point2D> range(RectHV rect){
        if(rect == null){
            throw new IllegalArgumentException();
        }
        SET<Point2D> setrange = new SET<Point2D>();
        for (Point2D p : points){
            if (rect.contains(p)){
                setrange.add(p);
            }
        }
       return setrange;

    }
    public Point2D nearest(Point2D p){
        if(points.isEmpty()){
            return null;
        }
        Point2D  min = null;
        for(Point2D allpoints : points){
            if(min == null || allpoints.distanceSquaredTo(p) < min.distanceSquaredTo(p)){
                min = allpoints;
            }
        }
        return min;
        
        
    }
}