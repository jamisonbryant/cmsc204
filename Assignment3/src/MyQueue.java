import java.util.LinkedList;

/**
 * My implementation of a queue
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class MyQueue<T> implements QueueInterface<T>
{
    private LinkedList<T> queue;

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
        // Fetch and delete last item
        T item = queue.getLast();
        queue.removeLast();

        // Return last item
        return item;
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
     * Adds an item to the queue
     *
     * @param item Item to add
     * @return True if successful, false otherwise
     */
    public boolean enqueue(T item)
    {
        // Add item to queue
        queue.add(item);

        // TODO: What conditions cause this method to fail?
        return true;
    }

    /**
     * Converts the queue to an array
     *
     * @return Queue as an array
     */
    public T[] toArray()
    {
        return null;
    }
}
