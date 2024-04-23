import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



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
            
        }
    }


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

public Iterable<String> certificateOfElimination(String Team){

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