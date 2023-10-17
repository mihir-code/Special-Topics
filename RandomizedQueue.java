import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;


public class RandomizedQueue<Item> implements Iterable<Item> {
    private int size = 0;
    private Item[] queue;

    public RandomizedQueue(){
        queue = (Item[]) new Object[2];

        
    }
    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;    
    }
    private void doublesize(int capacity){
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++){
            copy[i] = queue[i];
        }
        queue = copy;
    }
    public void enqueue(Item item){ // double it
        if (item == null){
            throw new IllegalArgumentException();
        }
        if (size == queue.length){
            doublesize(2*queue.length);
        }
        queue[size] = item;
        size = size + 1;
    }
    public Item dequeue(){ 
        if (size == 0){
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniformInt(size);
        Item item = queue[random];
        if (random != size - 1){
            queue[random] = queue[size - 1];
        }
        queue[size - 1] = null;
        size -=1;
        if (size == queue.length/4 && size > 0){
            doublesize(queue.length/2);
        }
        return item;
    }
    public Item sample(){
        if (size == 0){
            throw new java.util.NoSuchElementException();
        }
        int random = StdRandom.uniformInt(size);
        Item item = queue[random];
        return item;
    }
    public Iterator<Item> iterator(){
        return new Iteratortype();
    }
    private class Iteratortype implements Iterator<Item>{
        int counter;
        public Item next(){
            if (!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            else {
                Item item = queue[counter++];
                return item;
            }
            
        }
        public boolean hasNext(){
            return queue[counter] != null;
        }
        public void remove(){
            throw new UnsupportedOperationException();
        }



    }
    public static void main(String args[]){
        RandomizedQueue<String> random = new RandomizedQueue<>();
        while (!StdIn.isEmpty()){
            String read = StdIn.readString();
            if (read.equals("-")){
                System.out.print(random.dequeue());
            }
            else{
                random.enqueue(read);
            }
        }

    }

}
