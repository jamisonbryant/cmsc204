package edu.montgomerycollege.cmsc204.jbryant;

/**
 * Tree Node Class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) - CMSC 204 w/ R. Alexander M/W 1:00PM - 2:40PM
 */
public class TreeNode<T>
{
    /**
     * Node data
     */
    private T data;

    /**
     * Left child node
     */
    private TreeNode<T> leftChild;

    /**
     * Right child node
     */
    private TreeNode<T> rightChild;

    /**
     * Creates a new Tree Node
     */
    public TreeNode()
    {
        data = null;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Creates a copy of a Tree Node
     *
     * @param node Node to copy
     */
    public TreeNode(TreeNode<T> node)
    {
        data = node.getData();
        leftChild = node.getLeftChild();
        rightChild = node.getRightChild();
    }

    /**
     * Returns if the node has child nodes
     *
     * @return True if child nodes set, false otherwise
     */
    public boolean hasChildNodes()
    {
        return !(leftChild == null && rightChild == null);
    }

    //<editor-fold desc="[Getters/Setters]">
    /**
     * Returns the node's data
     *
     * @return Node data
     */
    public T getData() { return data; }

    /**
     * Sets the node's data
     *
     * @param data Node data to set
     */
    public void setData(T data) { this.data = data; }

    /**
     * Returns the node's left child node
     *
     * @return Left child node
     */
    public TreeNode<T> getLeftChild() { return leftChild; }

    /**
     * Sets the node's left child node
     *
     * @param leftChild Node to set
     */
    public void setLeftChild(TreeNode<T> leftChild) { this.leftChild = leftChild; }

    /**
     * Returns the node's right child node
     *
     * @return Right child node
     */
    public TreeNode<T> getRightChild() { return rightChild; }

    /**
     * Sets the node's right child node
     *
     * @param rightChild Node to set
     */
    public void setRightChild(TreeNode<T> rightChild) { this.rightChild = rightChild; }
    //</editor-fold>
}
