package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

/**
 * Binary Search Tree class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) - CMSC 204 w/ R. Alexander M/W 1:00PM - 2:40PM
 */
public class BSTree<T> extends BasicLinkedTree<T>
{
    /**
     * Comparator for comparing nodes
     */
    private Comparator<T> comparator;

    /**
     * Creates a new BSTree
     *
     * @param comparator Comparator for BST to use when inserting
     */
    public BSTree(Comparator<T> comparator)
    {
        this.comparator = comparator;
    }

    /**
     * Adds a node to the BST
     *
     * @param node Node to add
     * @return BST with the node added
     */
    public BSTree<T> add(T node)
    {
        if (rootNode == null) {
            rootNode = new TreeNode<T>(node);
        } else {
            rootNode = insert(rootNode, node);
        }

        size++;

        return this;
    }

    /**
     * Returns a copy of the tree as an ArrayList
     *
     * @return Tree as ArrayList
     */
    public ArrayList<T> toArrayList()
    {
        ArrayList<T> list = new ArrayList<T>();

        // If root node null, return empty list
        if (rootNode == null) return list;

        // Return list
        return traverse(rootNode, list);
    }

    /**
     * Inserts a node into a given BST
     *
     * @param tree Tree to insert node into
     * @param data Node to insert into tree
     * @return BST with node inserted
     */
    private TreeNode<T> insert(TreeNode<T> tree, T data)
    {
        // Compare data values
        if (comparator.compare(data, tree.getData()) < 0) {
            // Check if tree has left child
            if (tree.hasLeftChild()) {
                // Call insertion algorithm on left child
                insert(tree.getLeftChild(), data);
            } else {
                // Insert node into tree as left child
                tree.setLeftChild(new TreeNode<T>(data));
            }
        } else {
            // Check if tree has right child
            if (tree.hasRightChild()) {
                // Call insertion algorithm on right child
                insert(tree.getRightChild(), data);
            } else {
                // Insert node into tree as right child
                tree.setRightChild(new TreeNode<T>(data));
            }
        }

        return tree;
    }

    /**
     * Places BST nodes in a list according to inorder algorithm
     *
     * @param node BST to traverse
     * @param list List to place nodes in
     * @return List with ordered nodes
     */
    private ArrayList<T> traverse(TreeNode<T> node, ArrayList<T> list) {
        // Check if given node is null
        if (node == null) return null;

        // Traverse node left subtree
        traverse(node.getLeftChild(), list);

        list.add(node.getData());

        // Traverse node right subtree
        traverse(node.getRightChild(), list);

        return list;
    }
}
