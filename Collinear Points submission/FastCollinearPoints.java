import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.ArrayList;


public class FastCollinearPoints {
    private LineSegment[] segment;

    public FastCollinearPoints(Point[] points){
        for (int i = 0; i < points.length; i++){
            if (points[i] == null){
                throw new java.lang.IllegalArgumentException();
            }
        }
        if (points == null){
            throw new java.lang.IllegalArgumentException();
        }

        ArrayList<LineSegment> Seg = new ArrayList<>();
        Point[] copy = Arrays.copyOf(points, points.length); // sorted order
        Point[] copy2 = Arrays.copyOf(points, points.length); // original order
        Arrays.sort(copy2);
        duplicate(copy2);       
        for (int i = 0; i < copy2.length - 1; i++){
            Point org = copy2[i];
            Point begin = null;
            Arrays.sort(copy);
            int count = 1;
            Arrays.sort(copy, org.slopeOrder());
            for (int k = 0; k < copy.length - 1; k++){
                if (copy[k].slopeTo(org) == copy[k + 1].slopeTo(org)){
                    count++;
                    if(count == 2){ // realizes collinear point works
                        begin = copy[k];
                        count++;
                    }
                    else if (count >= 4 && k + 1 == copy.length - 1){
                        if(begin.compareTo(org) > 0){
                            Seg.add(new LineSegment(org, copy[k +1]));
                        }
                        count = 1;
                    }
                }
                else if (count >= 4){
                    if(begin.compareTo(org) > 0){
                        Seg.add(new LineSegment(org, copy[k]));
                    }
                    count = 1;
                }
                else{
                    count = 1;
                }


            }
         
         }
         segment = Seg.toArray(new LineSegment[Seg.size()]);

    }
    private void duplicate(Point[] points){
        for (int i = 0; i < points.length - 1; i++){
            if (points[i].compareTo(points[i+1]) == 0){
                throw new java.lang.IllegalArgumentException();

            }
        }
    }
    public int numberOfSegments(){
        return segment.length;

    }
    public LineSegment[] segments(){
        return Arrays.copyOf(segment, numberOfSegments());
        
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
    */
}
