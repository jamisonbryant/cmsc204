/**
 * The class that implements this class uses a data structure (your choice) to store the Furniture in the store
 * 
 * @author JMYERS
 *
 */
public interface StoreInterface {
	
	/**
	 * Adds a furniture to the store data structure
	 * @param frn the Furniture added to the data structure
	 */
	public void addFurniture(Furniture frn);
	
	/**
	 * Returns an array of furnitures that are currently at the store
	 * @return
	 */
	public Furniture[] getFurnitures();
	
	/**
	 * Determines if the data structure is full
	 * @return true if the data structure is full, false if it is not
	 */
	public boolean full();

}
