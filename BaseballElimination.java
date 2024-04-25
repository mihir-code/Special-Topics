import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class BaseballElimination{
    private final int[] wins;
    private final int[] remaining;
    private final int[] losses;
    private final int[][] opponents;
    private final List <String> actteams;
    private List<String> cut;
    private final int numb;
    private final HashMap<String,Integer> mapping;
    


public BaseballElimination(String filename){
    if(filename == null){
        throw new IllegalArgumentException();
    }
    In input = new In(filename);
    this.numb = input.readInt();
    this.actteams = new ArrayList<>();
    this.mapping = new HashMap<>();
    this.wins = new int[numb];
    this.losses = new int[numb];
    this.remaining = new int[numb];
    this.opponents = new int [numb][numb];
    for (int n = 0; n < numb; n++){
        String team = input.readString();
        actteams.add(team);
        remaining[n] = input.readInt();
        wins[n] = input.readInt();
        losses[n] = input.readInt();
        mapping.put(team, n);
        for (int j = 0; j < numb; j++){
            opponents[n][j] = input.readInt();
        }

    }
    
}

public int numberOfTeams(){
    return numb;

}
public Iterable<String> teams(){ 
    return actteams;
}
// Don't know why but the autograder is saying that these methods aren't working? Will make a private helper for the null checker
public int wins(String team){ 
    checker(team);
    return wins[this.mapping.get(team)];
}

public int losses(String team){
    checker(team);
    return losses[this.mapping.get(team)];
}

public int remaining(String team){
    checker(team);
    return remaining[this.mapping.get(team)];
}


public int against(String team1, String team2){
    checker(team1);
    checker(team2);
    return opponents[this.mapping.get(team1)][this.mapping.get(team2)];

}

public boolean isEliminated(String team){
    checker(team);

   this.cut = new ArrayList<>();
   int maxnumofwins = this.wins(team) + this.remaining(team);
   for(String game : this.teams()){
    if(this.wins(game) > maxnumofwins){
        cut.add(game);
    }
   }
   if(!cut.isEmpty()){
    return true;
   }
   int verts = numb - 1;
   int overts = ((numb - 1) *(numb - 2)) / 2;
   int total = 2 + verts + overts;

   HashMap<Integer,String> corTeam = new HashMap<>();
   HashMap<Integer, List<Integer>> totalTeam = new HashMap<>();
   int s = 1;
   for(String game : this.teams()){
    if(!game.equals(team)){
        corTeam.put(s,game);
        s+=1;
    }
   }

   for(int i = 1; i <= verts - 1; i++){
    for(int j = i + 1; j <= verts; j++){
        List<Integer> tracker = new ArrayList<>();
        tracker.add(i);
        tracker.add(j);
        totalTeam.put(s,tracker);
        s+=1;
    }
   }

   FlowNetwork flow = new FlowNetwork(total);
   for(int z = verts + 1; z < total - 1; z++){
    int t = totalTeam.get(z).get(0);
    int t1 = totalTeam.get(z).get(1);
    int cap = this.against(corTeam.get(t), corTeam.get(t1));
    FlowEdge edge = new FlowEdge(0,z,cap);
    flow.addEdge(edge);
    FlowEdge edge1 = new FlowEdge(z, t, Integer.MAX_VALUE);
    FlowEdge edge2 = new FlowEdge(z, t1, Integer.MAX_VALUE);
    flow.addEdge(edge1);
    flow.addEdge(edge2);
   }

   int goal = total - 1;
   for (int z = 1; z <= verts; z++){
    int cap = maxnumofwins - this.wins(corTeam.get(z));
    FlowEdge edge3 = new FlowEdge(z, goal, cap);
    flow.addEdge(edge3);
   }

   FordFulkerson maxflow = new FordFulkerson(flow, 0, goal);
   boolean out = false;
   for(FlowEdge edge3 : flow.adj(0)){
    if(edge3.flow() != edge3.capacity()){
        out = true;
        break;
    }
   } 

   for (int z= 1; z < verts; z++){
    if(maxflow.inCut(z)){
        cut.add(corTeam.get(z));
    }
   }
   return out;


    
}


public Iterable<String> certificateOfElimination(String team){
    checker(team);

    if(this.isEliminated(team)){
        return cut;
    }
    return null;

}

private void checker(String team){
    if(team == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
}
}
