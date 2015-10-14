/** Interface for a generic Stack data structure
 * 
 * @author JMYERS
 *
 * @param <T> data type
 */
public interface StackInterface<T> {

	/**
	 * Determines if Stack is empty
	 * @return true if Stack is empty, false if not
	 */
	public boolean isEmpty();

	/**
	 * Deletes and returns the element at the top of the Stack
	 * @return the element at the top of the Stack
	 */
	public T pop();

	/**
	 * Number of elements in the Stack
	 * @return the number of elements in the Stack
	 */
	public int size();
	
	/**
	 * Adds an element to the top of the Stack
	 * @param e the element to add to the top of the Stack
	 * @return true if the add was successful, false if not
	 */
	public boolean push(T e);
	
	/**
	 * Returns the elements of the Stack in an array, [0] is top of Stack, [1] is next in Stack, etc.
	 * @return an array of the elements in the Stack
	 */
	public T[] toArray();

}
