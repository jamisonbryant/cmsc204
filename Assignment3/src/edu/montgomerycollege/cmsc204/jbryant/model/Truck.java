package edu.montgomerycollege.cmsc204.jbryant.model;

import edu.montgomerycollege.cmsc204.jmyers.TruckInterface;

import java.util.EmptyStackException;

/**
 * The truck data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Truck implements TruckInterface
{
    @Override
    public void uploadFurniture(Furniture frn)
    {

    }

    @Override
    public Furniture offloadFurniture() throws EmptyStackException
    {
        return null;
    }

    @Override
    public Furniture[] toArray()
    {
        return new Furniture[0];
    }
}
