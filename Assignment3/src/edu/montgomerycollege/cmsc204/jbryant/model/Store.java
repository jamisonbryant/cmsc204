package edu.montgomerycollege.cmsc204.jbryant.model;

import edu.montgomerycollege.cmsc204.jmyers.Location;
import edu.montgomerycollege.cmsc204.jmyers.StoreInterface;

/**
 * The store data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Store extends Location implements StoreInterface
{
    /**
     * Creates a new Store object
     *
     * @param name Store name
     */
    Store(String name)
    {
        super(name);
    }

    /**
     * Adds a piece of furniture to the store
     *
     * @param furniture Furniture to add
     */
    public void addFurniture(Furniture furniture)
    {

    }

    /**
     * Gets the furniture in a store
     *
     * @return Furniture in the store
     */
    public Furniture[] getFurnitures()
    {
        return new Furniture[0];
    }

    /**
     * Returns if the store is full
     *
     * @return True if the store is full, false otherwise
     */
    public boolean full()
    {
        return false;
    }
}
