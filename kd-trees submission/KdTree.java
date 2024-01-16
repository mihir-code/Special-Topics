import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Node;
import edu.princeton.cs.algs4.SET;

public class KdTree{
    private Node root = null; // very important.
    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node lb; // left bottom need both for vertical and horizontal
        private Node rt; // right top
        private int size = 0;

    }
    public Node(Point2D p, RectHV rect, Node lb, Node rt, int size){
        p = this.p;
        rect = this.rect;
        size = this.size;
        lb = null; // not sure if not setting it would result in a problem. 
        rt = null; 
    }
    public boolean isEmpty(){
        return root == null;

    }
    public int size(){
        return size(root);


    }
    public void insert(Point2D p){
        if (p == null){
            throw new IllegalArgumentException();
        }
        return insert(Node p2, Point2d p);

        

    }
    private void insert(Node p2, Point2D p){
        if(p2 == null){
            return new Node(p, null, null, null);
        }
        size++;
        int compare = compare(p, p2.p) // simply comparing two points won't work.
        else if(compare < 0){
            p.lb = insert(p.lb);
        }
        else if (compare > 0){
            p.rt = insert(p.rt);
        }
        else{
            p2.p = p;
        }
        return p2;

    }
    private double compare(Point2D p, Point2D p1){ // the range is from 0 to 1
        if (p.x() < p1.x()){
            return -1;
        }
        else if (p.x() > p1.x()){
            return 1;
        }
        else{
            if(p.y() < p1.y()){
                return -1;
            }
            else if(p.y() > p1.y()){
                return 1;
            }
            }
        }
        return 0;
    }
    public boolean contains(Point2D p){
        if(!insert(Point2d p)){
            return false;
        }
        else{
            return true;
        }


    }
    private boolean contains(Point2D p, boolean splitvert, )
    public void draw(){
        StdDraw(p);
        StdDraw(rect);


    }
    public Iterable<Point2d> range(RectHV rect){

    }
    public Point2D nearest(Point2D p){
        
    }
}