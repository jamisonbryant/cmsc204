package edu.montgomerycollege.cmsc204.jbryant;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Sorted Linked List
 *
 * Description goes here.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class SortedLinkedList<T> extends BasicLinkedList<T>
{
    private Comparator<T> comparator;

    public SortedLinkedList(Comparator<T> c)
    {
        // Initialize comparator
        comparator = c;
    }

    /**
     * Adds a node to the appropriate position in the node list
     *
     * @param data Data of node to add
     * @return Current node list
     */
    public SortedLinkedList<T> add(T data)
    {
        // Initialize test node
        Node<T> testNode = new Node<T>(data);

        // Check if list is not empty
        if (listSize > 0) {
            // Initialize node pointers
            Node<T> currentNode = firstNode;
            Node<T> previousNode = null;

            // Initialize comparison flag
            int comparison;

            // Iterate through node list
            while (currentNode != null) {
                // Compare nodes and capture result
                comparison = comparator.compare(testNode.nodeData, currentNode.nodeData);

                // Check if nodes are identical
                if (comparison == 0) {
                    // Append test node
                    testNode.nextNode = currentNode.nextNode;
                    currentNode.nextNode = testNode;

                    // Exit routine
                    break;
                } else if (comparison < 0) {
                    // Check if current node is first node
                    if (currentNode.equals(firstNode)) {
                        // Prepend test node
                        testNode.nextNode = currentNode;
                        firstNode = testNode;
                    } else {
                        // Prepend test node
                        testNode.nextNode = currentNode;
                        previousNode.nextNode = testNode;
                    }

                    // Exit routine
                    break;
                } else if (comparison > 0) {
                    if (currentNode.equals(lastNode)) {
                        // Append test node
                        lastNode.nextNode = testNode;
                        lastNode = testNode;

                        // Exit routine
                        break;
                    } else {
                        // Update node pointers
                        previousNode = currentNode;
                        currentNode = currentNode.nextNode;
                    }
                }
            }
        } else {
            // Add new first/last nodes
            firstNode = testNode;
            lastNode = testNode;
        }

        // Increment list size
        listSize++;

        // Return current node list
        return this;
    }

    /**
     * Throws an UnsupportedOperationException (appending is an invalid operation for sorted lists)
     *
     * @param data Node to add
     * @return void
     */
    public BasicLinkedList<T> addToEnd(T data)
    {
        // Throw exception
        throw new UnsupportedOperationException("addToEnd() is unsupported for a sorted list");
    }

    /**
     * Throws an UnsupportedOperationException (prepending is an invalid operation for sorted lists)
     *
     * @param data Node to add
     * @return void
     */
    public BasicLinkedList<T> addToFront(T data)
    {
        // Throw exception
        throw new UnsupportedOperationException("addToFront() is unsupported for a sorted list");
    }

    /**
     * Creates and returns new list iterator
     *
     * @return New iterator
     */
    public ListIterator iterator()
    {
        // Call parent method
        return super.iterator();
    }
}
