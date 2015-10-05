package edu.montgomerycollege.cmsc204.jbryant.model;

import edu.montgomerycollege.cmsc204.jmyers.TruckInterface;

import java.util.EmptyStackException;

/**
 * Created by Jamison on 10/5/2015.
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
