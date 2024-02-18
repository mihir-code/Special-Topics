/**
 * @author Mihir Motukuri attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 */
// Maksim helped me with the edge case for the iterable length and ancestor. 
// His way of checking if the path returned -1 is much simpler and I quickly implemented it. 
// After implementing it, my 18/20 turned into a 20/20.
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class SAP{
        private final Digraph g;
        private final int[] shortancestor;  
  

    public SAP(Digraph G){
        g = new Digraph(G);
        if (g == null){
            throw new IllegalArgumentException();
        }
        shortancestor = new int[1];
        shortancestor[0] = -1;       
    }

    public int length(int v, int w){
        if ((v < 0|| v > g.V() || (w < 0 || w > g.V()))){
            throw new IllegalArgumentException();
        }
        int length = Integer.MAX_VALUE; // need a big number
        int ancestor = 0;
        int vw = 0;
        BreadthFirstDirectedPaths pathv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths pathw = new BreadthFirstDirectedPaths(g, w);
        for (int i = 0; i < g.V(); i++){
            if (pathv.hasPathTo(i) && pathv.distTo(i) < length && pathw.hasPathTo(i) && pathw.distTo(i) < length){
                vw = pathv.distTo(i) + pathw.distTo(i);
                if (vw < length){
                    length = vw;
                    ancestor = i;
                }
            }
        }
        if (length == Integer.MAX_VALUE){
            shortancestor[0] = -1;
            return -1;
        }
        shortancestor[0] = ancestor;
        return length;

        
    }

    public int ancestor(int v, int w){
        if ((v < 0|| v > g.V() || (w < 0 || w > g.V()))){
            throw new IllegalArgumentException();
        }

        length(v,w);
        return shortancestor[0];
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        int checkv = verts(v);
        int checkw = verts(w);
        if (checkv == -1 || checkw == -1){
            return -1;
        }
        int length = Integer.MAX_VALUE; // need a big number
        int ancestor = 0;
        int vw = 0;
        BreadthFirstDirectedPaths pathv = new BreadthFirstDirectedPaths(g, v);
        BreadthFirstDirectedPaths pathw = new BreadthFirstDirectedPaths(g, w);
        for (int i = 0; i < g.V(); i++){
            if (pathv.hasPathTo(i) && pathw.hasPathTo(i)){
                vw = pathv.distTo(i) + pathw.distTo(i);
                if (vw < length){
                    length = vw;
                    ancestor = i;
                }
            }
        }
        if (length == Integer.MAX_VALUE){
            shortancestor[0] = -1;
            return -1;
        }
        shortancestor[0] = ancestor;
        return length;
        

    }
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        
        length(v,w);
        return shortancestor[0];

        

    }
    private int verts(Iterable<Integer> vert){
        int c = 0;
        for(Integer v: vert){
            c++;
            if (v == null){
                throw new IllegalArgumentException();
        }
        }
        if (c == 0){
            return -1;
        }
        return 0;
        
    }
    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        while (!StdIn.isEmpty()) {
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }
    }
}