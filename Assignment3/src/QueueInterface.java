package edu.montgomerycollege.cmsc204.jmyers;

/** Interface for a Queue data structure
 * 
 * @author JMYERS
 *
 * @param <T> data type
 */
public interface QueueInterface<T> {

	/**
	 * Determines if Queue is empty
	 * @return true if Queue is empty, false if not
	 */
	public boolean isEmpty();

	/**
	 * Deletes and returns the element at the front of the Queue
	 * @return the element at the front of the Queue
	 */
	public T dequeue();

	/**
	 * Number of elements in the Queue
	 * @return the number of elements in the Queue
	 */
	public int size();
	
	/**
	 * Adds an element to the end of the Queue
	 * @param e the element to add to the end of the Queue
	 * @return true if the add was successful, false if not
	 */
	public boolean enqueue(T e);
	
	/**
	 * Returns the elements of the Queue in an array, [0] is front of Queue, [1] is next in Queue, etc.
	 * @return an array of the elements in the Queue
	 */
	public T[] toArray();

}
