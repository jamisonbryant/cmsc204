/**
 * The factory data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Factory extends Location implements FactoryInterface
{
    /**
     * Creates a new factory object
     *
     * @param name Factory name
     */
    Factory(String name)
    {
        super(name);
    }

    /**
     * Adds a piece of furniture to the factory
     *
     * @param furniture Piece of furniture to add
     */
    public void addFurniture(Furniture furniture) {}

    /**
     * Remove a piece of furniture from the factory
     *
     * @return Piece of furniture that was removed
     */
    public Furniture removeFurniture()
    {
        return null;
    }

    /**
     * Returns an array of the furniture in the factory
     *
     * @return Furniture array
     */
    public Furniture[] getFurnitures()
    {
        return new Furniture[0];
    }

    /**
     * Returns if the factory is empty
     *
     * @return True if factory is empty, false otherwise
     */
    public boolean empty()
    {
        return false;
    }
}
