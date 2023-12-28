import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Node;
import edu.princeton.cs.algs4.SET;

public class KdTree{
    private int size = 0;
    private Node root = null;
    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
    }
    public Node(Point2D p, RectHV rect, Node lb, Node rt){
        p = this.p;
        rect = this.rect;
        lb = this.lb;
        rt = this.rt;
    }
    public boolean isEmpty(){
        return root == null;

    }
    public int size(){
        return size;


    }
    public void insert(Point2D p){

    }
    public boolean contains(Point2D p){

    }
    public void draw(){

    }
    public Iterable<Point2d> range(RectHV rect){

    }
    public Point2D nearest(Point2D p){
        
    }
}