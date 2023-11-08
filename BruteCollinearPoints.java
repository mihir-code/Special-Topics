import java.util.Arrays;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;


public class BruteCollinearPoints {
    private LineSegment[] segment;
        
    

public BruteCollinearPoints(Point[] points){
    for (int i = 0; i < points.length;i++){
        if(points[i] == null){
            throw new java.lang.IllegalArgumentException();
        }
    }
    for (int i = 0; i < points.length -1; i++){
        if (points[i].compareTo(points[i+1]) == 0){
            throw new java.lang.IllegalArgumentException();
        }
    }
    ArrayList<LineSegment> Seg = new ArrayList<>();
    Point[] pointsCopy = points.clone();
    Arrays.sort(pointsCopy); 
    for (int i = 0; i < pointsCopy.length - 3; i++){
        Point ivalue = pointsCopy[i];
        for (int j = i + 1; j < pointsCopy.length - 2; j++){
            Point jvalue = pointsCopy[j];
            for (int k = j + 1; k < pointsCopy.length - 1; k++){
                Point kvalue = pointsCopy[k];
                for (int x = k + 1; x < pointsCopy.length; x++){
                    Point xvalue = pointsCopy[x];
                    if (ivalue.slopeTo(jvalue) == ivalue.slopeTo(kvalue) && ivalue.slopeTo(jvalue)== ivalue.slopeTo(xvalue)){
                        Seg.add(new LineSegment(pointsCopy[i], pointsCopy[x]));

                        }
                    }
                }
            }
        } segment = Seg.toArray(new LineSegment[Seg.size()]);

        

    }
    public int numberOfSegments(){
        return segment.length;

    }
    public LineSegment[] segments(){
        return Arrays.copyOf(segment,segment.length);
        
    }
    /* 
    public static void main(String[] args) {

        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
    
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
    
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }   */ 
}
    

