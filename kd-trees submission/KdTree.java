import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Node;
import edu.princeton.cs.algs4.SET;

public class KdTree{
    private int size = 0;
    private Node root = null; // very important.
    private static class Node{
        private Point2D p;
        private RectHV rect;
        private Node lb; // left bottom
        private Node rt; // right top
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
        return insertt(Node p2, Point2d p);

        

    }
    private void insert(Node p2, Point2D p){
        if(root == null){
            return new Node(p, null, null, null);
        }
        size++;
        int x = p2.compareTo(p);
        else if(x < 0){
            p = p.left;
        else if (x > 0){
            p = p.right;
        }
        else{
            return p.value;
        }
        return null;
        }

    }
    public boolean contains(Point2D p){
        if(!insert(Point2d p){
            return false;
        })
        else{
            return true;
        }


    }
    public void draw(){
        StdDraw(p);
        StdDraw(rect);


    }
    public Iterable<Point2d> range(RectHV rect){

    }
    public Point2D nearest(Point2D p){
        
    }
}