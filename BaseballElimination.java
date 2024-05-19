/**
 * @author Mihir Motukuri attests that this code is their original work and was written in compliance with the class Academic Integrity and Collaboration Policy found in the syllabus. 
 */
/* For the longest time, I was stuck at a 61 but thought about it and realized that the order for the arrays in the constructor matter. 
*  I noticed that everything was off by 1 in the Coursera Autograder so the order actually does matter. The other thing is that it makes sense as we are reading it. 
   Pretty upset at myself for not realizing that. 
 */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseballElimination{
    private final int numb; // number of teams
    private final int[] remaining;
    private final Map<String, Integer> actteam = new HashMap<>();
    private final int[] losses;
    private final int[] wins;
    private final Map<Integer, String> tea = new HashMap<>(); // Used this after seeing matt's code
    private final int[][] opponents;
    // private final List <String> actteams;
    private List<String> eliminated = new ArrayList<>(); 
    // private List<String> cut;
    // private final HashMap<String, Integer> mapping;


public BaseballElimination(String filename){
    if(filename == null){
        throw new IllegalArgumentException();
    }
    In input = new In(filename);
    numb = input.readInt();
    wins = new int[numb];
    remaining = new int[numb];
    losses = new int[numb];
    opponents = new int [numb][numb];
    // this.mapping = new HashMap<>();
    for (int n = 0; n < numb; n++){
        String t = input.readString();
        actteam.put(t,n);
        tea.put(n, t);
        wins[n] = input.readInt();
        losses[n] = input.readInt();
        remaining[n] = input.readInt();
        // mapping.put(team, n);
        for (int j = 0; j < numb; j++){
            opponents[n][j] = input.readInt();
        }

    }
    
}

private void checker(String team){
    if(!actteam.containsKey(team)){
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
    checker(team); // if team doesn't exist, throw error.

    eliminated = new ArrayList<>(); // initialize the new ArrayList to store the list of teams that were or are eliminated.
    int numofwins = wins(team) + remaining(team); // max number of wins
    boolean lost = false; // initialize a variable to check for elimination

    for (String game: teams()){
        if(numofwins < wins(game)){
            eliminated.add(game);
            lost = true;
        }
    } // if a team has more wins than "numofwins", they are added to the list.

    if(lost){
        return true;
    }

    int t = actteam.get(team);
    int vert = ((numb - 1) * (numb -2)) /2; // believe that this is the calculated amount
    int curvert = 0;
    int s = curvert++;
    int a = curvert++;
    Map<Integer, Integer> totalverts = new HashMap<>(); // maps the verts in the flow network
    for(String name : actteam.keySet()){
        int cur = actteam.get(name);
        if(cur == t){
            continue;
        }
     totalverts.put(cur, curvert++);  // assigns each team verts in the flow network
    }

    FlowNetwork flownet = new FlowNetwork(vert + numb + 1);
    for (int n = 0; n < numb; n++){
        if(t == n){
            continue;
        }
        for (int i = n + 1; i <numb; i++){
            if(t == i){
                continue;
            }

            flownet.addEdge(new FlowEdge(s,curvert, opponents[n][i])); // adds verts between each matchup
            flownet.addEdge(new FlowEdge(curvert,totalverts.get(n),Integer.MAX_VALUE)); // changed this after talking to matt
            flownet.addEdge(new FlowEdge(curvert,totalverts.get(i),Integer.MAX_VALUE)); // capacity as infinity, 
            curvert++;
        }
    }
    for(int n = 0; n < numb; n++){
        if(t == n){
            continue;
        }
        flownet.addEdge(new FlowEdge(totalverts.get(n), a, Math.max(0,wins[t] + remaining[t] - wins[n]))); // this is the maximum possible wins a team can get
    }
    FordFulkerson f = new FordFulkerson(flownet, s, a);
    for(FlowEdge edge: flownet.adj(s)){
        if(edge.flow() != edge.capacity()){ // If the max flow equals the total cap, team isn't eliminated
            lost = true;
        break;
        }
    }
    if(lost){
        for(int n = 0; n < numb; n++){
            if(t == n){
                continue;
            }
            if(f.inCut(totalverts.get(n))){
                eliminated.add(tea.get(n));
            }
        }
        return true; // we check if the corvertex is part of mincut and if it is, it's part of the eliminated.
        
    }
return false; // if not eliminated.


    
}


public Iterable<String> certificateOfElimination(String team){
    checker(team);

    if(isEliminated(team)){
        return eliminated;
    }
    return null;

}

}
