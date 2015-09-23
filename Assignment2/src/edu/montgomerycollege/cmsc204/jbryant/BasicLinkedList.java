package edu.montgomerycollege.cmsc204.jbryant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class BasicLinkedList<T>
{
    private LinkedList<Node> nodeList;
    private Node<T> firstNode;
    private Node<T> lastNode;
    private int listSize;
    private ListIterator iterator;
    
    public BasicLinkedList() 
    {
        // Initialize nodes
        firstNode = null;
        lastNode = null;
        
        // Initialize node list size
        listSize = 0;
        
        // Initialize iterator
        iterator = new ListIterator();
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
        nodeList.add(newNode);
        
        // Increment list size variable
        listSize++;
        
        // Update last node variable
        lastNode = newNode;
        
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
        nodeList.add(0, newNode);
        
        // Increment list size variable
        listSize++;
        
        // Update first node variable
        firstNode = newNode;
        
        // Return current node list
        return this;
    }
    
    public T getFirst()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Get first node in list
            Node<T> firstNode = nodeList.getFirst();
            
            // Return node data
            return (T) firstNode.nodeData;
        } else {
            // Return null
            return null;
        }       
    }
    
    public T retrieveFirstElement()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Get first node in list
            Node<T> firstNode = nodeList.getFirst();
            
            // Remove node from list
            nodeList.removeFirst();
            
            // Decrement list size variable
            listSize--;
            
            // Return node data
            return (T) firstNode.nodeData;                      
        } else {
            // Return null
            return null;
        }        
    }
    
    public T getLast()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Get last node in list
            Node<T> lastNode = nodeList.getLast();
            
            // Return node data
            return (T) lastNode.nodeData;
        } else {
            // Return null
            return null;
        }       
    }
    
    public T retrieveLastElement()
    {
        // Check if node list is not empty
        if (listSize > 0) {
            // Get last node in list
            Node<T> lastNode = nodeList.getLast();
            
            // Remove node from list
            nodeList.removeLast();
            
            // Decrement list size variable
            listSize--;
            
            // Return node data
            return (T) lastNode.nodeData;                      
        } else {
            // Return null
            return null;
        }        
    }
    
    public ArrayList<T> toArrayList()
    {
        // Initialize node array list
        ArrayList<T> newList = new ArrayList<T>();
        
        // Import node list into array list
        for (Node<T> currentNode : nodeList) {
            newList.add(currentNode.nodeData);
        }
        
        // Return array list
        return newList;
    }
    
    public ListIterator iterator()
    {
        // Return node list iterator
        return iterator;
    }
    
    public class ListIterator implements Iterator
    {
        @Override
        public boolean hasNext()
        {
            // TODO: Auto-generated method stub
            return false;
        }

        @Override
        public Object next()
        {
            // TODO: Auto-generated method stub
            return null;
        }      
    }
    
    public class Node<T>
    {
        private T nodeData;
        private Node<T> nextNode;
        
        public Node()
        {
            // Initialize node data
            nodeData = null;
            
            // Initialize next node
            nextNode = null;
        }
        
        // TODO: Specify next node as parameter
        public Node(T data)
        {
            // Initialize node data
            nodeData = data;
        }
    }
}
