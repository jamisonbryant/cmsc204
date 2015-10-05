package edu.montgomerycollege.cmsc204.jbryant.model;

import edu.montgomerycollege.cmsc204.jmyers.Location;
import edu.montgomerycollege.cmsc204.jmyers.StoreInterface;

/**
 * Created by Jamison on 10/5/2015.
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
