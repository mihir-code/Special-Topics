import java.util.Iterator;
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
        if (oldfirst != null){ // to ensure that there are no pointer issues
            oldfirst.prev = first;
        }
        else{
            last = first;
        }
        size++;

    }
    public void addLast(Item item){
        if (item == null){
            throw new IllegalArgumentException();
        }        
        Node oldlast = last;
        last = new Node();
        last.prev = oldlast;
        last.item = item;
        if (oldlast != null){ // to ensure that there are no pointer issues
            oldlast.next = last;
        }
        else{
            first = last;
        }
        size++;

    }
    public Item removeFirst(){
        if (size == 0){
            throw new java.util.NoSuchElementException();
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
            throw new java.util.NoSuchElementException();
        }    
        Item item = last.item; // to ensure proper placing
        if (size > 1){
            last = last.prev;
            last.next = null;
        }
        else{
            last = null;
            first = null;
        }
        size--;
        return item;
    }
    public Iterator<Item> iterator(){
        return new Iteratorfirst();
    }
    private class Iteratorfirst implements Iterator<Item>{
        private Node current = first;
        
        public boolean hasNext(){
            return current != null;
        }
        public Item next(){
            if(!hasNext()){
            throw new java.util.NoSuchElementException();
            }
            else{
                Item item = current.item;
                current = current.next;
                return item;
            }
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
    public static void main(String[] args) {
        Deque<String> myDeque = new Deque<String>(); // mat gave his code for main and it tested properly
        try {
            myDeque.addFirst(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Testing exception for null add first. " + e.toString());

        }
        try {
            myDeque.addLast(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Testing exception for null add last. " + e.toString());

        }
        myDeque.addFirst("Matt");
        myDeque.addLast("Carrie");
        System.out.println(myDeque.toString());
        myDeque.addFirst("Danny");
        myDeque.addLast("Kaylee");
        myDeque.addLast("James");
        myDeque.addLast("Abby Jo");

        for (String name : myDeque) {
            System.out.println("EFL: " + name);
        }

        System.out.println(myDeque);
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque);
        System.out.println(myDeque.size());
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.removeFirst());
        System.out.println(myDeque.size());
        myDeque.addFirst("Danny");
        myDeque.addLast("Kaylee");
        myDeque.addLast("James");
        myDeque.addLast("Abby Jo");
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.removeLast());
        System.out.println(myDeque.size());

    }
}
