package edu.montgomerycollege.cmsc204.jbryant;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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
    }

    /**
     * Removes a node from the tree
     *
     * @param node Node to remove
     */
    public void remove(T node)
    {
        ArrayList<TreeNode> list = new ArrayList<TreeNode>();
        Stack<TreeNode> stack = new Stack<TreeNode>();

        // If root node null, return
        if (rootNode == null) return;

        // Add root to stack
        stack.push(rootNode);

        // Traverse stack
        while (!stack.empty()) {
            // Pop node and add to list
            TreeNode<T> n = stack.pop();

            // Check if node matches query
            if (n.getData().equals(node)) {
                // Check which (if any) child nodes node has
                if (n.hasLeftChild() && n.hasRightChild()) {
                    // Node has BOTH left child node AND right child node
                    System.out.println("Node: " + n.getData() + ", left/right");
                } else if (n.hasLeftChild() ^ n.hasRightChild()) {
                    // Node has EITHER left child node OR right child node, but not both
                    if (n.hasLeftChild()) {
                        System.out.println("Node: " + n.getData() + ", left");
                    } else {
                        System.out.println("Node: " + n.getData() + ", right");
                    }
                } else {
                    // Node has NEITHER left child node NOR right child node
                    TreeNode<T> parent = getParent(node);

                    if (parent != null) {
                        if (parent.getLeftChild().getData().equals(node)) {
                            parent.setLeftChild(null);
                        } else {
                            parent.setRightChild(null);
                        }
                    }
                }
            }

            // Check if node has right child
            if (n.hasRightChild()) {
                // Push right child onto stack
                stack.push(n.getRightChild());
            }

            // Check if node has left child
            if (n.hasLeftChild()) {
                // Push left node onto stack
                stack.push(n.getLeftChild());
            }
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
        Stack<TreeNode> stack = new Stack<TreeNode>();

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

        // Return preordered list
        return list;
    }

    private TreeNode<T> getParent(T node)
    {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(rootNode);

        // Traverse stack
        while (!stack.empty()) {
            // Pop node and add to list
            TreeNode<T> n = stack.pop();
            TreeNode<T> l = n.getLeftChild();
            TreeNode<T> r = n.getRightChild();

            // Check if node matches query
            if (l.getData().equals(node) || r.getData().equals(node)) {
                // Return parent
                return n;
            }

            // Check if node has right child
            if (n.hasRightChild()) {
                // Push right child onto stack
                stack.push(n.getRightChild());
            }

            // Check if node has left child
            if (n.hasLeftChild()) {
                // Push left node onto stack
                stack.push(n.getLeftChild());
            }
        }

        return null;
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
