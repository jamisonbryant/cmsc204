package edu.montgomerycollege.cmsc204.jbryant.data;

import edu.montgomerycollege.cmsc204.jmyers.QueueInterface;

/**
 * My implementation of a queue
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class MyQueue<T> implements QueueInterface<T>
{
    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public T dequeue()
    {
        return null;
    }

    @Override
    public int size()
    {
        return 0;
    }

    @Override
    public boolean enqueue(T e)
    {
        return false;
    }

    @Override
    public T[] toArray()
    {
        return new T[0];
    }
}
