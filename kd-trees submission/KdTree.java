import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Node;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private Node root = null; // very important.

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private boolean splitvert;
        private Node lb; // left bottom need both for vertical and horizontal
        private Node rt; // right top
        private int size = 0;

    }

    public Node(Point2D p, RectHV rect, Node lb, Node rt, int size, boolean splitvert){
        p = this.p;
        rect = this.rect;
        size = this.size;
        lb = null; // not sure if not setting it would result in a problem. 
        rt = null; 
        rect = this.rect;
    }

    public boolean isEmpty() {
        return root == null;

    }

    public int size() {
        return size(root); // check again
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return insert(p2, p, !splitvert);
    }

    private Node insert(Node p2, Point2D p, boolean splitvert, double xmin, double xmax, double ymin, double ymax){
        if(p2 == null){
            return new Node(p, new RectHV(xmin, xmax, ymin, ymax));
        }
        int compare = compare(p, p2.p, splitvert); // simply comparing two points won't work.
        p2.rectangle = new RectHV(xmin, xmax, ymin, ymax);
        if(compare < 0){
            if(splitvert){
                p2.lb = insert(p.lb, p, false, xmin, p2.p.x(), ymin, ymax); // blue
            } else{
                p2.lb = insert(p.lb, p, true, xmin, xmax, ymin, p2.p.y()); // red
            }
        }
        else if (compare > 0){ 
            if(splitvert){
              p2.rt = insert(p.rt, p, false, x.p.x(), xmax, ymin, ymax);  // blue
            }else{
              p2.rt = insert(p.rt, p, true, xmin, xmax, p2.p.y(), ymax);
            }

        return p2;
    }


    private double compare(Point2D p, Point2D p1, boolean splitvert){ // the range is from 0 to 1
        if(splitvert){
            if(p.x() < p1.x()){
                return -1;
            }
            else(p.x() > p1.x()){
                return 1;
            } 
        }
        else{
            if(p.y() < p1.y()){
                return -1;
            }
            else(p.y() > p1.y()){
                return 1;
            }
        }
        else{
            if (p.x() == p1.x() || p.y() == p1.y()){
                return 1;
            }
        }
        return 0;
    }

    public boolean contains(Point2D p) {
        return contains(p, p2, !splitvert) != null;
    }

    private Node contains(Point2D p, Node p2, boolean splitvert) {
        if (p2 == null) {
            return false;
        }
        int compare = compare(p, p2.p, splitvert);
        if (compare < 0) {
            return contains(p2.lb, p, !splitvert);
        } else if (compare > 0) {
            return contains(p2.rt, p, !splitvert);
        }
        return p2;

    }

    public void draw() {
        clear();
    }

    private void draw(Node p2, boolean splitvert) {
        if (p2 == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.1);
        if(splitvert){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
        }
        else{
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
        }

    }

    public Iterable<Point2d> range(RectHV rect) {

    }

    public Point2D nearest(Point2D p) {

    }
}
