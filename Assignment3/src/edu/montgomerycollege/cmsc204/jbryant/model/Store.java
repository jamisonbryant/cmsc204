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
    Store(String inName)
    {
        super(inName);
    }

    @Override
    public void addFurniture(Furniture frn)
    {

    }

    @Override
    public Furniture[] getFurnitures()
    {
        return new Furniture[0];
    }

    @Override
    public boolean full()
    {
        return false;
    }
}
