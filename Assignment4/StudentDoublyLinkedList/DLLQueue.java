
/**
 * Write a description of class DLLQueue here.
 *
 * The thing about this class is that it didn't take that much thought.  All
 * I really did was extract the code for the queue from the Palindrome class
 * and do a simple rewrite to include the arbitrary object aspect and the 
 * iterator.
 * 
 */
import java.util.*;
public class DLLQueue<T>
{
    private LinkedList<T> queue;
    private Iterator<T> iter;

    /**
     * Constructor for objects of class DLLQueue
     */
    public DLLQueue()
    {
        queue = new LinkedList<T>();
        iter = queue.iterator();
    }
    boolean enqueue(T node)
    {
        this.queue.addLast(node);
        return true;
    }
    T dequeue()
    {
        return this.queue.remove();
    }
    T peek()
    {
        return this.queue.peek();
    }
    void showAll()
    {
        iter = this.queue.iterator();
        while(this.iter.hasNext()) System.out.println(this.iter.next().toString());
    }

    
}
