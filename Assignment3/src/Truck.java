import sun.invoke.empty.Empty;

import java.lang.reflect.Array;
import java.util.EmptyStackException;

/**
 * The truck data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Truck implements TruckInterface
{
    /**
     * Stack that serves as the backbone of the truck stack
     */
    private MyStack stack;

    /**
     * Current location of the truck
     */
    private Location location;

    /**
     * Creates a new Truck object
     */
    public Truck()
    {
        stack = new MyStack();
        location = null;
    }

    /**
     * Creates a new Truck object with an initial Location
     *
     * @param location Initial location
     */
    public Truck(Location location)
    {
        stack = new MyStack();
        this.location = location;
    }

    /**
     * Adds a piece of furniture to the truck
     *
     * @param furniture Piece of furniture to add
     */
    public void uploadFurniture(Furniture furniture)
    {
        stack.push(furniture);
    }

    /**
     * Removes a piece of furniture from the truck
     *
     * @return The removed piece of furniture
     * @throws EmptyStackException If the furniture truck is empty
     */
    public Furniture offloadFurniture() throws EmptyStackException
    {
        if (!stack.isEmpty()) {
            return (Furniture) stack.pop();
        } else {
            GUI.displayWarning("The truck is empty and cannot be unloaded", false);
            throw new EmptyStackException();
        }
    }

    /**
     * Returns an array of the furniture in the truck
     *
     * @return Furniture array
     */
    public Furniture[] toArray()
    {
        Furniture[] array = (Furniture[]) Array.newInstance(Furniture.class, stack.size());

        for (int i = 0; i < stack.size(); i++) {
            Array.set(array, i, (Furniture) stack.getElementAt(i));
        }

        return array;
    }

    /**
     * Sets the truck's current location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * Sets the truck's location
     *
     * @param location Location to set
     */
    public void setLocation(Location location)
    {
        this.location = location;
    }
}
