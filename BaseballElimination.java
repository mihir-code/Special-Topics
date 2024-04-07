import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;

import java.util.ArrayList;




public class BaseballElimination{
    private int numbofteams;
    private ArrayList<String> teamnames;


public BaseballElimination(String filename){
    In in = new In(filename);
    amountofteams
    numbofteams = in.readInt();

}

public int numberofTeams(){
    return numbofteams;

}

public Iterable<String> teams(){
    return teamnames;
}

public int wins(String team){

}

public int losses(String team){

}

public int remaining(String team){

}

public int against(String team1, String team2){

}

public boolean isEliminated(String team){

}

public Iterable<String> certificateOfElimination(String Team){

}
}