package edu.montgomerycollege.cmsc204.jbryant.data;

import edu.montgomerycollege.cmsc204.jmyers.StackInterface;

/**
 * My implementation of a stack
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class MyStack<T> implements StackInterface<T>
{
    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public T pop()
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean push(T e)
    {
        return false;
    }

    @Override
    public T[] toArray()
    {
        return new T[0];
    }
}
