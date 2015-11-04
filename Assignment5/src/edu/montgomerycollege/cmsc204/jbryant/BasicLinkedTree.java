package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayList;

/**
 * Basic Linked Tree class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) - CMSC 204 w/ R. Alexander M/W 1:00PM - 2:40PM
 */
public class BasicLinkedTree<T>
{
    /**
     * Number of nodes in tree
     */
    private int size;

    /**
     * ???
     */
    private String rootNode;

    /**
     * Creates a new tree
     */
    public BasicLinkedTree()
    {
        size = 0;
        rootNode = null;
    }

    /**
     * Adds a node to the tree
     *
     * @param node Node to add
     */
    public void add(T node)
    {
        // Increment tree size
        size++;
    }

    /**
     * Removes a node from the tree
     *
     * @param node Node to remove
     */
    public void remove(T node)
    {
        // Decrement tree size
        size--;
    }

    /**
     * Returns a copy of the tree as an ArrayList
     *
     * @return Tree as ArrayList
     */
    public ArrayList<String> toArrayList() { return null; }

    //<editor-fold desc="[Getters/Setters]">
    /**
     * Returns the number of nodes in the tree
     *
     * @return Number of nodes
     */
    public int getSize() { return size; }

    /**
     * Returns the root node of the tree
     *
     * @return Root node
     */
    public String getRootNode() { return rootNode; }
    //</editor-fold>
}
