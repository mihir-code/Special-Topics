/* Mihir Motukuri attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus.  */
// The challenging part on this assignment was learning how to use splitvert to properly represent the lines. But after thinking about it, I realized I could add a boolean into the insert method which would alternate.
// Another thing that was hard was realizing that you need to have a RectHV object for the Node to properly insert points.

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.Queue;

public class KdTree {
    private Node root; // very important.
    private int size;


    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb; // left bottom need both for vertical and horizontal
        private Node rt; // right top

        public Node(Point2D p, RectHV rect){
            this.p = p;
            this.rect = rect;
            lb = null; // not sure if not setting it would result in a problem. 
            rt = null; 
        }        
    }
    public KdTree(){
        size = 0;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size; 
    }
    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, p, true, 0, 0, 1, 1);
    }
    private Node insert(Node p2, Point2D p, boolean splitvert, double xmin, double ymin, double xmax, double ymax){
        if(p2 == null){
            size++;
            return new Node(point, new RectHV(xmin, ymin, xmax, ymax));
        }
        p2.rect = new RectHV(xmin, ymin, xmax, ymax);
        int compare = compare(p, p2.p, splitvert); // simply comparing two points won't work.
        if(compare < 0){
            if(splitvert){
                p2.lb = insert(p2.lb, p, false, xmin, ymin, p2.p.x(), ymax); 
            } else {
                p2.lb = insert(p2.lb, p, true, xmin, ymin, xmax, p2.p.y()); 
            }
        }
        else if (compare > 0){ 
            if(splitvert){
              p2.rt = insert(p2.rt, p, false, p2.p.x(),ymin, xmax, ymax);  
            }else{
              p2.rt = insert(p2.rt, p, true, xmin, p2.p.y(), xmax, ymax);
            }

        }
        return p2;
    }


    private int compare(Point2D p, Point2D p1, boolean splitvert){ // the range is from 0 to 1
        if(splitvert){
            if(p.x() < p1.x()){ // some variable inconsistency here, need to check
                return -1;
            }
            else if(p.x() > p1.x()){
                return 1;
            } 
            else if(p.y() > p1.y()){
                return 1;
            }
            else if(p.y() < p1.y()){
                return -1;
            }
        }
        else{
            if(p.y() < p1.y()){
                return -1;
            }
            else if(p.y() > p1.y()){
                return 1;
            }
            else if(p.x() < p1.x()){
                return -1;
            }
            else if(p.x() > p1.x()){
                return 1;
            }

        }
        return 0;
    }

    public boolean contains(Point2D p) {
        if(p == null){
            throw new IllegalArgumentException(); // nothing there
        }
        return contains(root, p, true) != null;
    }

    private Node contains(Node p2, Point2D p, boolean splitvert) {
        if (p2 == null) {
            return null;
        }
        int compare = compare(p, p2.p, splitvert);
        if (compare < 0) {
            return contains(p2.lb, p, !splitvert);
        } else if (compare > 0) {
            return contains(p2.rt, p, !splitvert);
        }
        return p2; // look over tis

    }

    public void draw() {
        StdDraw.clear();
        draw(root, true);
    }

    private void draw(Node p2, boolean splitvert) {
        if (p2 == null) {
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        p2.p.draw();
        if(splitvert){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            RectHV v = new RectHV(p2.p.x(),p2.rect.ymin(),p2.p.x(), p2.rect.ymax());
            v.draw();
            draw(p2.lb, false);
            draw(p2.rt, false);
        }
        else{
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            RectHV h = new RectHV(p2.rect.xmin(),p2.p.y(),p2.rect.xmax(), p2.p.y());
            h.draw();
            draw(p2.lb, true);
            draw(p2.rt, true);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> rangepoints = new Queue<>();
        range(root, rect, rangepoints);
        return rangepoints;
        

    }
    private void range(Node p2, RectHV rect,  Queue<Point2D> queue){
        if (p2 == null || !p2.rect.intersects(rect)) // put p2 first or else it would be redundent.
            return;
        if (rect.contains(p2.p)){
            queue.enqueue(p2.p);
        }
        range(p2.lb, rect, queue);
        range(p2.rt, rect, queue);

    }   
    public Point2D nearest(Point2D p) {
        if(isEmpty()){
            return null;
        }
        else{
            return nearest(root, p, null);
        }
        
    }
    private Point2D nearest(Node p2, Point2D p, Point2D near){
        if(p2 != null){
            if(near == null){
                near = p2.p; // first call so that makes sense
            }
        
        if (p.distanceSquaredTo(near) >= p2.rect.distanceSquaredTo(p)){
            if(p2.p.distanceSquaredTo(p) < near.distanceSquaredTo(p)){
                near = p2.p;
            } 
            if(p2.lb != null && p2.lb.rect.contains(p)){ 
                near = nearest(p2.lb, p, near);
                near = nearest(p2.rt, p, near);
            }
            else{ // if it calls nearest line 165 will check if it's null
                near = nearest(p2.rt, p, near);
                near = nearest(p2.lb, p, near);
            }
        }
    }
        return near;
    
    
    }
}