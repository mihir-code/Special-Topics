import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Node;
import edu.princeton.cs.algs4.SET;

public class KdTree{
    private Node root = null; // very important.
    private static class Node{
        private Point2D p;
        private RectHV rect;
        private boolean splitvert
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
        splitvert = splitvert;
    }
    public boolean isEmpty(){
        return root == null;

    }
    public int size(){
        return size(root); // check again
    }
    public void insert(Point2D p){
        if (p == null){
            throw new IllegalArgumentException();
        }
        return insert(p2, p,!splitvert);
    }
    private Node insert(Node p2, Point2D p, boolean splitvert){
        if(p2 == null){
            return new Node(p, true);
        }
        size++;
        int compare = compare(p, p2.p, splitvert) // simply comparing two points won't work.
        else if(compare < 0){
            p.lb = insert(p.lb, p, !splitvert);
        }
        else if (compare > 0){
            p.rt = insert(p.rt, p, !splitvert);
        }
        else{
            p2.p = p;
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
    public boolean contains(Point2D p){
        return contains(p,p2, !splitvert) != null;
    }
    private Node contains(Point2D p, Node p2, boolean splitvert){
        boolean splitvert = false;
        if (p2 == null){
            return false;
        }
        int compare = compare(p, p2.p, splitvert);
        if (compare < 0){
            return contains(p2.lb, p, !splitvert);
        }
        else if (compare > 0){
            return contains(p2.rt, p, !splitvert);
        }
        return p2;

    }
    public void draw(){
        if(splitvert == true){
            StdDraw()
        }

       


    }
    public Iterable<Point2d> range(RectHV rect){

    }
    public Point2D nearest(Point2D p){
        
    }
}
