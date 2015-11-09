package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayList;
import java.util.Stack;

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
    protected int size;

    /**
     * Root node of the tree
     */
    protected TreeNode<T> rootNode;

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
    public BasicLinkedTree<T> add(T node)
    {
        // Create new node from given data
        TreeNode<T> newNode = new TreeNode<T>(node);

        // Increment tree size
        size++;

        // Check if root node is null
        if (rootNode == null) {
            // Set root node to new node
            rootNode = newNode;
        } else if(!rootNode.hasLeftChild()) {
            // Set root left child node to new node
            rootNode.setLeftChild(newNode);
        } else if (!rootNode.hasRightChild()) {
            // Set root right child node to new node
            rootNode.setRightChild(newNode);
        } else {
            // Add node to tree using Myers-Kartchner Tree Balancing algorithm
            String binaryString = Integer.toBinaryString(size);

            // Drop leading 1 from binary string
            binaryString = binaryString.substring(1);

            // Get dummy node for use during traversal
            TreeNode<T> dummyNode = rootNode;

            // Traverse tree according to new string
            for (char c : binaryString.substring(0, binaryString.length() - 1).toCharArray()) {
                // Check if character is 0 or 1
                if (c == '0') {
                    dummyNode = dummyNode.getLeftChild();
                } else if (c == '1') {
                    dummyNode = dummyNode.getRightChild();
                }
            }

            // Get last character of binary string
            char c = binaryString.charAt(binaryString.length() - 1);

            if (c == '0') {
                dummyNode.setLeftChild(newNode);
            } else if (c == '1') {
                dummyNode.setRightChild(newNode);
            }
        }

        return this;
    }

    /**
     * Removes a node from the tree
     *
     * @param node Node to remove
     */
    public BasicLinkedTree<T> remove(T node)
    {
        // Convert tree to node list
        ArrayList<TreeNode<T>> nodes = toNodeList();

        // Decrement tree size
        size--;

        // Search for node in list
        for (TreeNode<T> n : nodes) {
            if (n.getData().equals(node)) {
                // Check if node has children
                if (n.hasRightChild() && n.hasLeftChild()) {
                    // Remove node w/ left and right children
                    TreeNode<T> maximum = getMaximum(n.getLeftChild());

                    // Delete minimum node
                    T data = maximum.getData();
                    remove(data);

                    // Swap node data with minimum node data
                    n.setData(maximum.getData());
                } else if (n.hasRightChild() ^ n.hasLeftChild()) {
                    // Remove node w/ left OR right children but not both
                    TreeNode<T> parent = getParent(n);
                    TreeNode<T> successor = getSuccessor(n);

                    // Check if parent/successor found (they should be)
                    if (parent != null && successor != null) {
                        // Check if node has right child
                        if (parent.hasRightChild()) {
                            // Check if right child is equal to given node
                            if (parent.getRightChild().getData().equals(node)) {
                                // Update node
                                parent.setRightChild(successor);
                            }
                        }

                        // Check if node has left child
                        if (parent.hasLeftChild()) {
                            // Check if left child is equals to given node
                            if (parent.getLeftChild().getData().equals(node)) {
                                // Update node
                                parent.setLeftChild(successor);
                            }
                        }
                    }
                } else {
                    // Remove node w/ no children
                    TreeNode<T> parent = getParent(n);

                    // Check if parent found (it should be)
                    if (parent != null) {
                        // Check if node has right child
                        if (parent.hasRightChild()) {
                            // Check if right child is equal to given node
                            if (parent.getRightChild().getData().equals(node)) {
                                // Delete node
                                parent.setRightChild(null);
                            }
                        }

                        // Check if node has left child
                        if (parent.hasLeftChild()) {
                            // Check if left child is equals to given node
                            if (parent.getLeftChild().getData().equals(node)) {
                                // Delete node
                                parent.setLeftChild(null);
                            }
                        }
                    }
                }

            }
        }

        return this;
    }

    protected TreeNode<T> getMaximum(TreeNode<T> node)
    {
        if (node == null) return null;

        if (node.hasRightChild()) {
            return getMaximum(node.getRightChild());
        } else {
            return node;
        }
    }

    protected TreeNode<T> getParent(TreeNode<T> node)
    {
        if (node == null) return null;

        // Convert tree to node list
        ArrayList<TreeNode<T>> nodes = toNodeList();

        // Iterate over node list
        for (TreeNode<T> n : nodes) {
            // Check if node has right child
            if (n.hasRightChild()) {
                // Check if right child is equal to given node
                if (n.getRightChild().equals(node)) {
                    // Return node
                    return n;
                }
            }

            // Check if node has left child
            if (n.hasLeftChild()) {
                // Check if left child is equals to given node
                if (n.getLeftChild().equals(node)) {
                    // Return node
                    return n;
                }
            }
        }

        // Return null (given node not found)
        return null;
    }

    private TreeNode<T> getSuccessor(TreeNode<T> node)
    {
        if (node == null) return null;

        if (node.hasRightChild()) {
            return node.getRightChild();
        } else {
            return node.getLeftChild();
        }
    }

    /**
     * Returns a copy of the tree as an ArrayList
     *
     * @return Tree as ArrayList
     */
    public ArrayList<T> toArrayList()
    {
        ArrayList<T> list = new ArrayList<T>();
        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();

        // If root node null, return empty list
        if (rootNode == null) return list;

        // Add root to stack
        stack.push(rootNode);

        // Traverse stack
        while (!stack.empty()) {
            // Pop node and add to list
            TreeNode<T> node = stack.pop();
            list.add(node.getData());

            // Check if node has right child
            if (node.hasRightChild()) {
                // Push right child onto stack
                stack.push(node.getRightChild());
            }

            // Check if node has left child
            if (node.hasLeftChild()) {
                // Push left node onto stack
                stack.push(node.getLeftChild());
            }
        }

        // Return list
        return list;
    }

    /**
     * Returns a copy of the tree as an ArrayList
     *
     * @return Tree as ArrayList
     */
    public ArrayList<TreeNode<T>> toNodeList()
    {
        ArrayList<TreeNode<T>> list = new ArrayList<TreeNode<T>>();
        Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();

        // If root node null, return empty list
        if (rootNode == null) return list;

        // Add root to stack
        stack.push(rootNode);

        // Traverse stack
        while (!stack.empty()) {
            // Pop node and add to list
            TreeNode<T> node = stack.pop();
            list.add(node);

            // Check if node has right child
            if (node.hasRightChild()) {
                // Push right child onto stack
                stack.push(node.getRightChild());
            }

            // Check if node has left child
            if (node.hasLeftChild()) {
                // Push left node onto stack
                stack.push(node.getLeftChild());
            }
        }

        // Return list
        return list;
    }

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
