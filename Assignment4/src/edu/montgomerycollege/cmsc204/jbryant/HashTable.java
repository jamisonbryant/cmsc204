package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jmeyers.HashTableInterface;
import edu.montgomerycollege.cmsc204.jmeyers.PersonInterface;

import java.util.ArrayList;

/**
 * Hash Table Class
 *
 * The data structure of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class HashTable implements HashTableInterface
{
    @Override
    public int add(PersonInterface p)
    {
        return 0;
    }

    @Override
    public boolean contains(PersonInterface p)
    {
        return false;
    }

    @Override
    public PersonInterface getValue(String key)
    {
        return null;
    }

    @Override
    public boolean contains(String key)
    {
        return false;
    }

    @Override
    public ArrayList sort()
    {
        return null;
    }
}
