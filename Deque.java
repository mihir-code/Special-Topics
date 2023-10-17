import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;

public class Deque<Item> implements Iterable<Item> {
    private int size = 0;
    private Node first;
    private Node last;

    private class Node{
        private Item item;
        private Node next;
        private Node prev;
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
        if (item == null){
            throw new IllegalArgumentException();
        }
        Node oldfirst = first;
        first = new Node();
        first.next = oldfirst;
        first.item = item;

    }
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }        
        Node oldlast = last;
        last = new Node();
        last.prev = oldlast;
        last.item = item;

    }
    public Item removeFirst(){
        if (size == 0){
            throw new IllegalArgumentException();
        }
        Item item = first.item;
        if (size > 1){
            first = first.next;
            first.prev = null;
        }
        else{
            last = null;
            first = null;
        }
        size--;
        return item;
        


    }
    public Item removeLast(){
        if (size == 0){
            throw new IllegalArgumentException();
        }    
        Item item = last.item;
        if (size > 1){
            last = last.prev;
            last.next = null;
        }
        else{
            last = null;
            first = null;
        }
        size --;
        return item;
       


    }
    public Iterator<Item> iterator(){
        return new Iteratorfirst();


    }
    private class Iteratorfirst implements Iterator<Item>{
        private Node current= first;
        
        public boolean hasNext(){
            return current.next !=last;
        }
        public Item next(){
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            else{
                current = current.next;
                return current.item;
            }
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String args[]){
        Deque<String> deck = new Deque<>();
        while (!StdIn.isEmpty()){
            String read = StdIn.readString();
            if (read.equals("-")){
                System.out.print(deck.removeLast());
            }
            else{
                deck.addFirst(read);
            }
        }

    }


}
