import sun.invoke.empty.Empty;

import java.util.EmptyStackException;

/**
 * The truck data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Truck implements TruckInterface
{
    private MyStack stack;
    private Factory location;

    public Truck()
    {
        stack = new MyStack();
        location = null;
    }

    public Truck(Factory location)
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
        return null;
    }

    /**
     * Sets the truck's current location
     */
    public Factory getLocation()
    {
        return location;
    }

    public void setLocation(Factory location)
    {
        this.location = location;
    }
}
