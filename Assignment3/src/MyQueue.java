import java.lang.reflect.Array;
import java.util.LinkedList;

/**
 * My implementation of a queue
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class MyQueue<T> implements QueueInterface<T>
{
    private LinkedList<T> queue;

    public MyQueue()
    {
        queue = new LinkedList<T>();
    }

    /**
     * Returns if queue is empty
     *
     * @return True if queue is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (queue.size() == 0);
    }

    /**
     * Deletes the last item in the queue
     *
     * @return Deleted item
     */
    public T dequeue()
    {
        // Return last item
        return queue.poll();
    }

    /**
     * Adds an item to the queue
     *
     * @param item Item to add
     * @return True if successful, false otherwise
     */
    public boolean enqueue(T item)
    {
        return queue.offer(item);
    }

    /**
     * Returns the element at an index of the queue
     *
     * @param index Index of element to get
     * @return Element at index
     */
    public T getElementAt(int index)
    {
        return queue.get(index);
    }


    /**
     * Returns the size of the queue
     *
     * @return Size of the queue
     */
    public int size()
    {
        return queue.size();
    }

    /**
     * Converts the queue to an array
     *
     * @return Queue as an array
     */
    public T[] toArray()
    {
        return (T[]) queue.toArray();
    }
}
