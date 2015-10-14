import java.lang.reflect.Array;

/**
 * The store data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Store extends Location implements StoreInterface
{
    /**
     * List that serves as the backbone of the store queue
     */
    private MyQueue<Furniture> queue;

    /**
     * Creates a new Store object
     *
     * @param name Store name
     */
    Store(String name)
    {
        super(name);
        queue = new MyQueue<Furniture>();
    }

    /**
     * Adds a piece of furniture to the store
     *
     * @param furniture Furniture to add
     */
    public void addFurniture(Furniture furniture)
    {
        queue.enqueue(furniture);
    }

    /**
     * Gets the furniture in a store
     *
     * @return Furniture in the store
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
     * Returns if the store is full
     *
     * @return True if the store is full, false otherwise
     */
    public boolean full()
    {
        // TODO: What conditions cause the store to be full?
        return false;
    }

    /**
     * Returns store name
     *
     * @return Store name
     */
    public String getName()
    {
        return name;
    }
}
