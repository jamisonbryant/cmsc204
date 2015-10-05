import java.util.EmptyStackException;

/**
 * The truck data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Truck implements TruckInterface
{
    /**
     * Adds a piece of furniture to the truck
     *
     * @param furniture Piece of furniture to add
     */
    public void uploadFurniture(Furniture furniture)
    {

    }

    /**
     * Removes a piece of furniture from the truck
     *
     * @return The removed piece of furniture
     * @throws EmptyStackException If the furniture truck is empty
     */
    public Furniture offloadFurniture() throws EmptyStackException
    {
        return null;
    }

    /**
     * Returns an array of the furniture in the truck
     *
     * @return Furniture array
     */
    public Furniture[] toArray()
    {
        return new Furniture[0];
    }
}
