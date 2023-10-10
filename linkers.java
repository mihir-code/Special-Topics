import java.util.LinkedList;

public class linkers {
    
    public static void main(String [] args){
    LinkedList<String> linkers = new LinkedList<String>();

    linkers.add("hi");
    linkers.add("bye");
    linkers.remove();
    // this removes the first one, not the last one, usually you would expect it to remove the last one

    System.out.println(linkers);

    }
}
