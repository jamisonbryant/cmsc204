package edu.montgomerycollege.cmsc204.jmyers;

/**
 * The class that implements this interface uses a MyQueue<Furniture> to simulate Furniture being created in a factory
 * @author JMYERS
 *
 */
public interface FactoryInterface  {
		
	/**
	 * "Enqueue" a furniture onto the queue
	 * @param veh the furniture to be added to the queue
	 */
	public void addFurniture(Furniture frn);
	
	/**
	 * "Dequeue" a furniture from the queue
	 * @return the furniture deleted from the queue
	 */
	public Furniture removeFurniture();
	
	/**
	 * 
	 * Return an array of the furnitures in the queue
	 * @return
	 */
	public Furniture[] getFurnitures();

	/**
	 * Determines if the queue is empty
	 * @return true if the queue is empty, false if not empty
	 */
	public boolean empty();
}
