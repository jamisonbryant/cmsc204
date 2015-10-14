import java.lang.reflect.Array;

/**
 * The factory data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Factory extends Location implements FactoryInterface
{
    private String name;
    private MyQueue<Furniture> queue;

    /**
     * Creates a new factory object
     */
    Factory(String name)
    {
        super(name);
        queue = new MyQueue<Furniture>();
    }

    /**
     * Adds a piece of furniture to the factory
     *
     * @param furniture Piece of furniture to add
     */
    public void addFurniture(Furniture furniture)
    {
        queue.enqueue(furniture);
    }

    /**
     * Remove a piece of furniture from the factory
     *
     * @return Piece of furniture that was removed
     */
    public Furniture removeFurniture()
    {
        Furniture f = queue.dequeue();
        return f;
    }

    /**
     * Returns an array of the furniture in the factory
     *
     * @return Furniture array
     */
    public Furniture[] getFurnitures()
    {
        Furniture[] array = (Furniture[]) Array.newInstance(Furniture.class, queue.size());

        for (int i = 0; i < queue.size(); i++) {
            Array.set(array, i, (Furniture) queue.getElementAt(i));
        }

        return array;
    }

    /**
     * Returns if the factory is empty
     *
     * @return True if factory is empty, false otherwise
     */
    public boolean empty()
    {
        return queue.isEmpty();
    }
}
