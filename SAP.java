import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Hashmap;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs.StdIn;
import edu.princeton.cs.algs.StdOut;


public class SAP{
        private final Digraph G;        

    public SAP(Digraph G){
        this.G = G;
        
      
    }
    
    public int length(int v, int w){
        if (w = null){
            throw new IllegalArgumentException();
        }
        range(v,w);

        int length = Integer.MAX_VALUE; // need a big number
        int ancestor = 0;
        BreadthFirstDirectedPaths pathv = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths pathw = new BreadthFirstDirectedPaths(G, w);
        for (int i = 0; i < g.V(); i++){
            if (pathv.HasPathTo(i) && pathw.HasPathTo(i)){
                int vw = pathv.distTo(i) + pathw.distTo(i);
                if (vw < length){
                    length = vw;
                    ancestor = i;
                }
            }
        }
        if (length == Integer.MAX_VALUE){
            shortancestor = -1;
            return -1;
        }
        shortancestor = ancestor;
        return distance;
    }

    public int ancestor(int v, int w){
        if (w = null){
            throw new IllegalArgumentException();
        }
        range(v,w);
        length(v,w);
        return shortancestor;
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        range(v,w);
        length(v, w);


    }
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        if(v == null || w == null){
            throw new IllegalArgumentException();
        }
        range(v,w);
        length(v,w);
        return shortancestor;

        

    }
    private void range(int r){
        int w = G.r();
        if(r < 0 || o >= w){
            throw new IllegalArgumentException();
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