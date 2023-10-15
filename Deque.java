import java.util.LinkedList;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    private Node last;

    private class Node{
        Item item;
        Node next;
        Node prev;
    }

    public Deque(){
        
    }
    public boolean isEmpty(){
        return size == 0;

    }
    public int size(){
        return size;


    }
    public void addFirst(Item item){
        Node oldfirst = first;
        first = new Node();
        first.next = oldfirst;
        first.item = item;

        

        

    }
    public void addLast(Item item){
        Node oldlast = last;
        last = new Node();
        last.prev = oldlast;
        last.item = item;



    }
    public Item removeFirst(){
        Item item = first.item;
        if (size > 1){
            first = first.next;
            first.prev = null;
        }
        else{
            last = null;
            first = null;
        }
        size -=1;
        return item;
        


    }
    public Item removeLast(){
        Item item = last.item;
        if (size > 1){
            last = last.prev;
            last.next = null;
        }
        else{
            last = null;
            first = null;
        }
        size -=1;
        return size;
       


    }
    public Iterator<Item> iterator(){

    }
    public static void main(String args[]){

    }


}
