package edu.montgomerycollege.cmsc204.jbryant;

import java.util.*;

public class BasicLinkedList<T>
{
    protected Node<T> firstNode;
    protected Node<T> lastNode;
    protected int listSize;

    public BasicLinkedList()
    {
        // Initialize nodes
        firstNode = null;
        lastNode = null;

        // Initialize node list size
        listSize = 0;
    }

    /**
     * Returns node list size
     *
     * @return List size
     */
    public int getSize()
    {
        return listSize;
    }

    /**
     * Adds node to end of node list
     *
     * @param data Node to add
     * @return Current node list
     */
    public BasicLinkedList<T> addToEnd(T data)
    {
        // Create new node
        Node<T> newNode = new Node<T>(data);

        // Add node to end of list
        if (listSize == 0) {
            // Set new first and last nodes
            firstNode = newNode;
            lastNode = newNode;
        } else {
            // Set new last node
            lastNode.nextNode = newNode;
            lastNode = newNode;
        }

        // Increment list size
        listSize++;

        // Return current node list
        return this;
    }

    /**
     * Adds node to front of node list
     *
     * @param data Node to add
     * @return Current node list
     */
    public BasicLinkedList<T> addToFront(T data)
    {
        // Create new node
        Node<T> newNode = new Node<T>(data);

        // Check if node list is empty
        if (listSize == 0) {
            // Set new first and last nodes
            firstNode = newNode;
            lastNode = newNode;
        } else {
            // Set new first node
            newNode.nextNode = firstNode;
            firstNode = newNode;
        }

        // Increment list size
        listSize++;

        // Return current node list
        return this;
    }

    /**
     * Returns the first node in node list
     *
     * @return First node
     */
    public T getFirst()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Return node data
            return firstNode.nodeData;
        } else {
            // Return null
            return null;
        }
    }

    /**
     * Removes and returns first node in node list
     *
     * @return First node
     */
    public T retrieveFirstElement()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Deep copy first node
            Node<T> currentNode = firstNode;

            // Update new first node
            firstNode = firstNode.nextNode;

            // Decrement list size variable
            listSize--;

            // Return node data
            return currentNode.nodeData;
        } else {
            // Return null
            return null;
        }
    }

    /**
     * Returns last node in node list
     *
     * @return Last node
     */
    public T getLast()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Return node data
            return lastNode.nodeData;
        } else {
            // Return null
            return null;
        }
    }

    /**
     * Removes and returns last node in node list
     *
     * @return Last node
     */
    public T retrieveLastElement()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Initialize node pointers
            Node<T> currentNode = firstNode;
            Node<T> previousNode = null;

            // Iterate through node list
            while (currentNode != null) {
                // Check if current node is last node
                if (currentNode.equals(lastNode)) {
                    // Set new last node to previous node
                    lastNode = previousNode;
                    break;
                }

                // Update node pointers
                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }

            // Decrement list size
            listSize--;

            // Return node data
            return currentNode.nodeData;
        } else {
            // Return null
            return null;
        }
    }

    /**
     * Returns node list converted to array list
     *
     * @return Array list
     */
    public ArrayList<T> toArrayList()
    {
        // Initialize array list
        ArrayList<T> newList = new ArrayList<T>();

        // Initialize list iterator
        ListIterator iterator = iterator();

        // Initialize node pointer
        Node currentNode = firstNode;

        // Iterate through node list
        while (currentNode != null) {
            // Add node to array list
            newList.add((T) currentNode.nodeData);

            // Update current node pointer
            currentNode = currentNode.nextNode;
        }

        // Return array list
        return newList;
    }

    /**
     * Creates and returns new list iterator
     *
     * @return New iterator
     */
    public ListIterator iterator()
    {
        // Return new list iterator
        return new ListIterator();
    }

    /**
     * Removes first occurrence of node from node list
     *
     * @param element    Data of node to remove
     * @param comparator Comparator for comparing given data/node data
     */
    public void remove(T element, Comparator comparator)
    {
        // Initialize node pointers
        Node<T> currentNode = firstNode;
        Node<T> previousNode = null;

        // Iterate through node list
        while (currentNode != null) {
            // Check if current node data matches query
            if (comparator.compare(currentNode.nodeData, element) == 0) {
                // Check if current node is first/last/middle node
                if (currentNode.equals(firstNode)) {
                    // Replace current first node
                    firstNode = firstNode.nextNode;
                } else if (currentNode.equals(lastNode)) {
                    // Replace current last node
                    lastNode = previousNode;
                } else {
                    // Replace current middle node
                    previousNode.nextNode = currentNode.nextNode;
                }

                // End routine
                return;
                // Note: Remove return to have algorithm delete all nodes with given node data instead of just first
            }

            // Update node pointers
            previousNode = currentNode;
            currentNode = currentNode.nextNode;
        }
    }

    public class ListIterator implements Iterator
    {
        private Node<T> currentNode;

        public ListIterator()
        {
            currentNode = firstNode;
        }

        @Override
        public boolean hasNext()
        {
            return currentNode != null;
        }

        @Override
        public T next()
        {
            if (hasNext()) {
                // Advance node pointer
                Node<T> requestedNode = currentNode;
                currentNode = currentNode.nextNode;

                // Return requested node data
                return requestedNode.nodeData;
            } else {
                // Throw exception
                throw new NoSuchElementException("next() called on iterator without subsequent member");
            }
        }

        public void remove()
        {
            // Throw exception
            throw new UnsupportedOperationException("remove() not implemented for ListIterator inner class");
        }
    }

    public class Node<T>
    {
        protected T nodeData;
        protected Node<T> nextNode;

        public Node()
        {
            nodeData = null;
            nextNode = null;
        }

        public Node(T data)
        {
            nodeData = data;
        }

        public Node(T data, Node<T> node)
        {
            nodeData = data;
            nextNode = node;
        }
    }
}
