package edu.montgomerycollege.cmsc204.jbryant.model;

import edu.montgomerycollege.cmsc204.jmyers.FactoryInterface;
import edu.montgomerycollege.cmsc204.jmyers.Location;

/**
 * The factory data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Factory extends Location implements FactoryInterface
{
    Factory(String inName)
    {
        super(inName);
    }

    @Override
    public void addFurniture(Furniture frn)
    {

    }

    @Override
    public Furniture removeFurniture()
    {
        return null;
    }

    @Override
    public Furniture[] getFurnitures()
    {
        return new Furniture[0];
    }

    @Override
    public boolean empty()
    {
        return false;
    }
}
