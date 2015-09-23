package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class BasicLinkedList<T>
{
    /**
     * Linked list that stores elements of basic linked list
     */
    private LinkedList<T> list;
    
    /**
     * Iterator of basic linked list
     */
    private Iterator<T> iterator;
    
    /**
     * Index of current element of basic linked list
     */
    private int index;
    
    public BasicLinkedList()
    {
        // Initialize list
        list = null;
        
        // Initialize iterator
        iterator = new BasicIterator();
        
        // Initialize index
        index = 0;
    }
    
    /**
     * Adds an element to the end of the basic linked list
     */    
    public BasicLinkedList<T> addToEnd(T data)
    {
        // Add element to end of list
        list.add(data);        
        
        // Return object reference
        return this;
        
    }
    
    /**
     * Adds an element to the beginning of the basic linked list
     * 
     * @param data Element to add
     * @return Reference to the list
     */
    public BasicLinkedList<T> addToFront(T data)
    {
        // Add element to beginning of list
        list.add(0, data);
        
        // Return object reference
        return this;
    }
    
    /**
     * Returns the first element of the basic linked list
     * 
     * @return First element
     */
    public T getFirst()
    {
        return list.getFirst();
    }
    
    /**
     * Returns the last element of the basic linked list
     * 
     * @return Last element
     */
    public T getLast()
    {
        return list.getLast();
    }
    
    /**
     * Returns the size of the basic linked list
     * 
     * @return List size
     */
    public int getSize()
    {
        return list.size();
    }
    
    /**
     * Returns the iterator object of the basic linked list
     * 
     * @return
     */
    public Iterator<T> iterator()
    {
        return iterator;
    }
    
    /**
     * Removes and returns the first element of the basic linked list
     * 
     * @return First element before removal
     */
    public T retrieveFirstElement()
    {
        // Get and remove first element
        T element = list.getFirst();
        list.remove(0);
        
        // Return first element
        return element;
    }
    
    /**
     * Removes and returns the last element of the basic linked list
     * 
     * @return Last element before removal
     */
    public T retrieveLastElement()
    {
        // Get and remove last element
        T element = list.getLast();
        list.remove(0);
        
        // Return last element
        return element;
    }
    
    /**
     * Converts the basic linked list to an ArrayList
     * 
     * @return Converted basic linked list
     */
    public ArrayList<T> toArrayList()
    {
        return new ArrayList<T>(list);
    }
    
    class BasicIterator implements Iterator<T> 
    {

        @Override
        public boolean hasNext()
        {
            return index == list.size() - 1;
        }

        @Override
        public T next()
        {            
            return list.get(++index);
        }
        
    }
}
