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
     * Root node of the tree
     */
    private TreeNode<T> rootNode;

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
        // Check if root node is null
        if (rootNode == null) {
            // Set root node to new node
            rootNode = new TreeNode<T>();
            rootNode.setData(node);
        }

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
        // Check if root node has child nodes
        if (rootNode != null && !rootNode.hasChildNodes()) {
            // Check if root node equals given node
            if (node.equals(rootNode)) {
                // Set root node to null
                rootNode = null;
            } else {
                // Throw exception
                throw new RuntimeException("Node being removed was not found in tree");
            }
        }

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
     * Sets the size of the tree
     *
     * @param size Size to set
     */
    public void setSize(int size) { this.size = size; }

    /**
     * Returns the root node of the tree
     *
     * @return Root node
     */
    public TreeNode<T> getRoot() { return rootNode; }

    /**
     * Sets the root node of the tree (creates a deep copy of the given node)
     *
     * @param rootNode Node to set
     */
    public void setRoot(TreeNode<T> rootNode) { this.rootNode = new TreeNode<T>(rootNode); }

    /**
     * Returns the data of the root node
     *
     * @return Root node data
     */
    public T getRootData() { return rootNode.getData(); }
    //</editor-fold>
}
