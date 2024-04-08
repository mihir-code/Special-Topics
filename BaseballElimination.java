import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;

import java.util.ArrayList;
import java.util.HashMap;



public class BaseballElimination{
    private int[] victories;
    private int[] gamesremaning;
    private int[] losses;
    private int[][] opponents;
    private int numbofteams;
    private ArrayList<String> actteams;


public BaseballElimination(String filename){
    In input = new In(filename);
    for (int i = 0; i < numbofteams; i++){
        actteams.add(input.readString());
        gamesremaning[i]= input.readInt();
        victories[i]= input.readInt();
        losses[i]= input.readInt();
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
    return losses[t];
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