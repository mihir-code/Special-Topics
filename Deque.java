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
        oldfirst.item = item;
        oldfirst.next = first;

        

        

    }
    public void addLast(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        


    }
    public Item removeFirst(){
        Item item = first.item;
        first = first.next;
        return item;

        


    }
    public Item removeLast(){
        // if deque is empty throw a java.util.NoSuchElementException  


    }
    public Iterator<Item> iterator(){

    }
    public static void main(String args[]){

    }


}
