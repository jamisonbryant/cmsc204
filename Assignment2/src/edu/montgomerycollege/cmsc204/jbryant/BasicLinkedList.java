package edu.montgomerycollege.cmsc204.jbryant;

import java.util.*;

public class BasicLinkedList<T>
{
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int listSize;
    
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
            // Set first and last nodes
            firstNode = newNode;
            lastNode = newNode;
        } else {
            // Set new last node
            lastNode.nextNode = newNode;
            lastNode = newNode;
        }
        
        // Increment list size variable
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
        
        // Add node to front of list
        if (listSize == 0) {
            // Set first and last nodes
            firstNode = newNode;
            lastNode = newNode;
        } else {
            // Set new first node
            newNode.nextNode = firstNode;
            firstNode = newNode;
        }
        
        // Increment list size variable
        listSize++;
        
        // Return current node list
        return this;
    }
    
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
    
    public T retrieveLastElement()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            Node<T> currentNode = firstNode;
            Node<T> previousNode = null;

            while (currentNode != null) {
                if (currentNode.equals(lastNode)) {
                    lastNode = previousNode;
                    break;
                }

                previousNode = currentNode;
                currentNode = currentNode.nextNode;
            }

            // Decrement list size variable
            listSize--;

            // Return node data
            return currentNode.nodeData;
        } else {
            // Return null
            return null;
        }
    }
    
    public ArrayList<T> toArrayList()
    {
        ArrayList<T> newList = new ArrayList<T>();
        ListIterator iterator = iterator();
        Node currentNode = firstNode;
        
        // Import node list into array list        
        while (currentNode != null) {
            newList.add((T) currentNode.nodeData);
            currentNode = currentNode.nextNode;
        }
        
        return newList;
    }
    
    public ListIterator iterator()
    {
        // Return new list iterator
        return new ListIterator();
    }

    public void remove(T element, Comparator comparator)
    {
        Node<T> currentNode = firstNode;
        Node<T> previousNode = null;

        while (currentNode != null) {
            if (comparator.compare(currentNode.nodeData, element) == 0) {
                if (currentNode.equals(firstNode)) {
                    firstNode = firstNode.nextNode;
                } else if (currentNode.equals(lastNode)) {
                    lastNode = previousNode;
                } else {
                    previousNode.nextNode = currentNode.nextNode;
                }

                return;
            }

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
                throw new NoSuchElementException("next() called on iterator without subsequent member");
            }
        }

        public void remove()
        {
            throw new UnsupportedOperationException("remove() not implemented for ListIterator inner class");
        }
    }
    
    public class Node<T>
    {
        private T nodeData;
        private Node<T> nextNode;
        
        public Node()
        {
            nodeData = null;
            nextNode = null;
        }

        public Node(T data)
        {
            nodeData = data;
        }
    }
}
