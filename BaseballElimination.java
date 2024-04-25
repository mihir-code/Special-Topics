import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseballElimination{
    private final int[] wins;
    private final int[] remaining;
    private final int[] losses;
    private final int[][] opponents;
    // private final List <String> actteams;
    private List<String> cut;
    private final int numb;
    private final HashMap<String, Integer> mapping;
    private final Map<String, Integer> actteam = new HashMap<>();
    private final Map<String, Integer> team = new HashMap<>();
    private List<String> eliminated = new ArrayList<>(); 


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
        actteams.put(team,n);
        team.put(n, team);
        remaining[n] = input.readInt();
        wins[n] = input.readInt();
        losses[n] = input.readInt();
        mapping.put(team, n);
        for (int j = 0; j < numb; j++){
            opponents[n][j] = input.readInt();
        }

    }
    
}

private void checker(String team){
    if(team == null){
        throw new IllegalArgumentException();
    }
    if(!this.mapping.containsKey(team)){
        throw new IllegalArgumentException();
    }
}
public int numberOfTeams(){
    return numb;

}
public Iterable<String> teams(){ 
    return actteam.keySet();
}
// Don't know why but the autograder is saying that these methods aren't working? Will make a private helper for the null checker
public int wins(String team){ 
    checker(team);
    return wins[actteam.get(team)];
}

public int losses(String team){
    checker(team);
    return losses[actteam.get(team)];
}

public int remaining(String team){
    checker(team);
    return remaining[actteam.get(team)];
}


public int against(String team1, String team2){
    checker(team1);
    checker(team2);
    return opponents[actteam.get(team1)][actteam.get(team2)];

}

public boolean isEliminated(String team){
    checker(team);

    eliminated = new ArrayList<>();
    int numofwins = wins(team) + remaining(team);
    boolean lost = false;

    for (String game: teams()){
        if(numofwins < wins(game)){
            eliminated.add(game);
            lost = true;
        }
    }

    if(lost){
        return true;
    }

    int t = actteam.get(team);
    int vert = ((numb - 1) * (numb -2)) /2;
    int curvert = 0;
    int s = curvet++;
    int a = curvet++;
    Map<Integer, Integer> totalverts = new HashMap<>();
    for(String name : actteam.keySet()){
        int cur = actteam.get(name);
        if(cur == t){
            continue;
        }
     totalverts.put(cur, curvert++);   
    }

    FlowNetwork flow = new FlowNetwork(vert + numb + 1);
    for (int n = 0;n < numb; n++ ){
        if(t == n){
            continue;
        }
        for (int i =0; i <numb; i++){
            if(t == i){
                continue;
            }

            flow.addEdge(new FlowEdge(s,curvert, opponents[n][i]));
            flow.addEdge(new FlowEdge(s,totalverts.get(n),Double.POSITIVE_INFINITY));
            flow.addEdge(new FlowEdge(s,totalverts.get(i),Double.POSITIVE_INFINITY));
            curvert++;
        }
    }
    


    
}


public Iterable<String> certificateOfElimination(String team){
    checker(team);

    if(this.isEliminated(team)){
        return cut;
    }
    return null;

}

}
