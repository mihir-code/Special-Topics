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
        range(v);
        range(w);
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
        range(w);
        range(v);

        length(v,w);
        return shortancestor[0];
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        verts(v);
        verts(w);
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
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        verts(v);
        verts(w);
        length(v,w);
        return shortancestor[0];

        

    }
    private void range(int r){
        int n = g.V();
        if(r < 0 || r >= n){
            throw new IllegalArgumentException();
        }
      
    }
    private void verts(Iterable<Integer> vert){
        if(vert == null){
            throw new IllegalArgumentException();
        }
        int n = g.V();
        for(int v: vert){
            if (v < 0 || v >=n){
                throw new IllegalArgumentException();
        }
        }
        
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