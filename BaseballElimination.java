import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
// import java.util.Maps;


public class BaseballElimination{
    private final int[] wins;
    private final int[] remaning;
    private final int[] losses;
    private final int[][] opponents;
   // private int numbofteams;
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
    this.wins = new int[numb];
    this.losses = new int[numb];
    this.remaning = new int[numb];
    this.mapping = new HashMap<>();
    this.opponents = new int [numb][numb];
    for (int i = 0; i < numb; i++){
        String teams = input.readString();
        actteams.add(teams);
        remaning[i] = input.readInt();
        wins[i] = input.readInt();
        losses[i] = input.readInt();
        mapping.put(teams, i);
        for (int opp = 0; opp < numb; opp++){
            opponents[i][opp] = input.readInt();
        }

    }
    
}

public int numberOfTeams(){
    return numb;

}

public Iterable<String> teams(){
    return actteams;
}

public int wins(String team){
    if (team == null || !this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
    return wins[this.mapping.get(team)];
}

public int losses(String team){
    if (team == null || !this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
    return losses[this.mapping.get(team)];
}

public int remaining(String team){
    if (team == null || !this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
    return remaning[this.mapping.get(team)];
}


public int against(String team1, String team2){
    if (team1 == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team1)){
        throw new IllegalArgumentException();
    }
    if (team2 == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team2)){
        throw new IllegalArgumentException();
    }
    return opponents[this.mapping.get(team1)][this.mapping.get(team2)];

}

public boolean isEliminated(String team){
    if (team == null || !this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }

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
    int games = ((numb-1) *(numb-2)) /2;
    int total = 2 + verts + games;


    HashMap<Integer,String> corteam = new HashMap<>();
    HashMap<Integer,List<Integer>> gameperteam = new HashMap<>();
    int start = 1;
    for(String game: this.teams()){
        if(!game.equals(team)){
            corteam.put(start,game);
            start+=1;
        }
    }
    for (int i = 1; i <= verts - 1; i++){
        for(int j = i + 1; j <= verts; j++){
            List<Integer> tracker = new ArrayList<>();
            tracker.add(i);
            tracker.add(j);
            gameperteam.put(start,tracker);
            start += 1;
        }
    }

    FlowNetwork flow = new FlowNetwork(total);
    for(int i = verts + 1; i < total - 1; i++){
        int vert = gameperteam.get(i).get(0);
        int vert1 = gameperteam.get(i).get(1);

        int cap = this.against(corteam.get(vert),corteam.get(vert1));
        FlowEdge edge = new FlowEdge(0,i,cap);
        flow.addEdge(edge);
        FlowEdge edge1 = new FlowEdge(i,vert,Integer.MAX_VALUE);
        FlowEdge edge2 = new FlowEdge(i,vert1,Integer.MAX_VALUE);
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
    if (team == null || !this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }

    if(this.isEliminated(team)){
        return cut;
    }
    return null;

}

}

/*ArrayList<String> result = new ArrayList<>();
    int t = getTeam(team);
    int verts = ((numbofteams -2) *(numbofteams *2)+ numbofteams -2) / 2;
    int winratio = victories[t] + gamesremaning[t];
    
    // easy check
    for (int v = 0; v < numbofteams; v++){
        if (winratio - victories[v] < 0){ // mathematically impossible
             return result.add(actteams.get(v));
                
        }
    }
    */
