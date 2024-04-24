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
    int maximumwins = this.wins(team) + this.remaining(team);

    for (String game : this.teams()){
        if(this.wins(game) > maximumwins){
            cut.add(game);
        }
    }
    if(!cut.isEmpty()){
        return true;
    }

    int verts = numb - 1;
    int games = ((numb - 1) * (numb - 2)) / 2;
    int total = 2 + verts + games;


    HashMap<Integer,String> corteam = new HashMap<>();
    HashMap<Integer,List<Integer>> gameperteam = new HashMap<>();
    int source = 1;
    for(String game: this.teams()){
        if(!game.equals(team)){
            corteam.put(source,game);
            source=+1; // This looks cooler than the other way
        }
    }
    for (int i = 1; i <= verts - 1; i++){
        for(int j = i + 1; j <= verts; j++){
            List<Integer> tracker = new ArrayList<>();
            tracker.add(i);
            tracker.add(j);
            gameperteam.put(source,tracker);
            source=+1;
        }
    }

    FlowNetwork flow = new FlowNetwork(total);
    for(int i = verts + 1; i < total - 1; i++){
        int vert = gameperteam.get(i).get(0);
        int vert1 = gameperteam.get(i).get(1);

        int cap = this.against(corteam.get(vert), corteam.get(vert1));
        FlowEdge edge = new FlowEdge(0, i, cap);
        flow.addEdge(edge);
        FlowEdge edge1 = new FlowEdge(i,vert,Double.POSITIVE_INFINITY);
        FlowEdge edge2 = new FlowEdge(i,vert1,Double.POSITIVE_INFINITY);
        flow.addEdge(edge1);
        flow.addEdge(edge2);
    }

    int aim = total - 1;
    for(int i = 1; i <= verts; i++){
        int cap = maximumwins - this.wins(corteam.get(i));
        FlowEdge connect = new FlowEdge(i,aim,cap);
        flow.addEdge(connect);
    }

    FordFulkerson maxflow = new FordFulkerson(flow,0,aim);
    boolean lost = false;
    for (FlowEdge edge : flow.adj(0)){
        if(edge.flow() != edge.capacity()){
            lost = true;
            break;
        }
    }

    for (int i = 1; i <=verts;i++){
        if(maxflow.inCut(i)){
            cut.add(corteam.get(i));
        }
    }
    return lost;
    
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
