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
    private int[] victories;
    private int[] gamesremaning;
    private int[] loss;
    private int[][] opponents;
    private int numbofteams;
    private ArrayList<String> actteams;
    private List<String> cut;
    private final int numb;
    private final HashMap<String,Integer> mapping;
    


public BaseballElimination(String filename){
    In input = new In(filename);
    for (int i = 0; i < numbofteams; i++){
        actteams.add(input.readString());
        gamesremaning[i] = input.readInt();
        victories[i] = input.readInt();
        loss[i] = input.readInt();
        for (int opp = 0; i < numbofteams; i++){
            opponents[i][j] = input.readInt();
        }

    }
    
}

public int numberofTeams(){
    return numbofteams;

}

public Iterable<String> teams(){
    return actteams;
}

public int wins(String team){
    int t = getTeam(team);
    return victories[t];
}

public int losses(String team){
    int t = getTeam(team);
    return loss[t];
}

public int remaining(String team){
    int t = getTeam(team);
    return gamesremaning[t];
}

public int against(String team1, String team2){
    int t1 = getTeam(team1);
    int t2 = getTeam(team2);
    return opponents[t1][t2];
}

public boolean isEliminated(String team){
    if (team == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
    this.cut = new ArrayList<>();
    if(!cut.isEmpty()){
        return true;
    }
    int verts = numb - 1;
    int games = ((n-1) *(n-2)) /2;
    int total = 2 + verts + games;
    HashMap<Integer,String> corteam = new HashMap<>();
    HashMap<Integer,List<Integer>> gameperteam = new HashMap<>();
    int start = 1;
    for(String game: this.teams()){
        if(!game.equals(team)){
            corteam(i,t);
            start+=1;
        }
    }
    for (int i = 1; 1 <= verts; i++){
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

        int cap = this.against(findTeam.get(vert),findTeam.get(vert1));
        FlowEdge edge = new FlowEdge(0,i,cap);
        flow.addEdge(edge);
        FlowEdge edge1 = new FlowEdge(i,vert,Integer.MAX_VALUE);
        flow.addEdge(edge1);
        FlowEdge edge2 = new FlowEdge(i,vert1,Integer.MAX_VALUE);
        flow.addEdge(edge2);
    }
    int aim = total - 1;
    for(int i = 1; i <= verts; i++){
        int cap = maximumwins - this.wins(corteam.get(i));
        FlowEdge connect = new FlowEdge(i,target,cap);
        flow.addEdge(connect);
    }
    FordFulkerson maxflow = new FordFulkerson(flow,0,target);
    boolean lost = true;
    for (FlowEdge edge : flow.adj(0)){
        if(edge.flow() == edge.capacity){
            elim = false;
            break;
        }
    }
    for (int i = 1; i <=verts;i++){
        if(maxflow.inCut(i)){
            cut.add(findTeam.get(i));
        }
    }
    return elim;
    int maximumwins = this.wins(team) + this.remaining(team);
    for (String game : this.teams()){
        if(this.wins(game) > maximumwins){
            cut.add(game);
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
}

public Iterable<String> certificateOfElimination(String team){
    if (team == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
    if(this.isEliminated(team)){
        return cut;
    }
    return null;

}

private getTeam(String team){
    if (team == null){
        throw new IllegalArgumentException();
        for (int t = 0; t < numbofteams; t++){
            if (t.get(teams).equals(team)){
                return t;
            }
            else{
                throw new IllegalArgumentException();
            }
        }
    }
}
}