import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import java.util.Iterator;
import edu.princeton.cs.algs4.StdDraw;
import 

/*
public class Point2D implements Comparable <Point2D>{
    public Point2D(double x, double y){

    }
    public double x(){ // x-coordinate

    }
    public double y(){ // y-coordinate

    } 
    public double distanceTo(Point2D that){

    }
    public distanceSquaredTo(Point2D that){ // slope?

    }
    public int compareTo(Point2D that){

    }
    public boolean equals(Object that){

    }
    public void draw(){

    }
    public String toString(){

    }

}
*/
/*
public class RectHV{
    public RectHV(double xmin, double ymin, double xmax, double ymax){ //make triangle

    }
    public double xmin(){

    }
    public double ymin(){

    }
    public double xmax(){

    }
    public double ymax(){

    }
    public boolean contains(Point2D p){

    }
    public boolean intersects(RectHv that){

    }
    public double distanceTo(Point2D p){

    }
    public double distanceSquaredTo(Point2D p){

    }
    public boolean equals(Object that){

    }
    public void draw(){
        
    }
    public String toString(){

    }
}
*/
public class PointSET{
    public PointSET(){
       private SET<Point2D> points;

       public SET(){
        points = new SET<Point2D>();
       }
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
        StdDraw.pointSET(points);


    }
    public Iterable<Point2D> range(RectHV rect){
        if(rect == null){
            throw new IllegalArgumentException();
        }
        Set<Point2D> setrange = new SET<Point2D>();
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
        Point2D near = null;
        double min = Double.Max_value;
        for(Point2D allpoints : points){
            double actdistance = allpoints.distanceTo(p);
            if(actdistance < min){
                near = allpoints;
                min = actdistance;
            }
        }
        return near;
        
        
    }
}