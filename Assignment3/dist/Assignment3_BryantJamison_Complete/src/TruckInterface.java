import java.util.EmptyStackException;

/**
 * This class uses a Stack<Furniture> to simulate putting furnitures on and off the truck
 * @author JMYERS
 *
 */
public interface TruckInterface  {

	/**
	 * "Push" a furniture onto the truck
	 * @param frn the furniture put onto the truck
	 */
	public void uploadFurniture(Furniture frn);

	/**
	 * "Pop" a furniture off the truck
	 * @return a reference to the furniture taken off the truck
	 * @throws EmptyStackException if there are no more furnitures on the truck
	 */
	public Furniture offloadFurniture() throws EmptyStackException;
	
	/**
	 * Returns an array of the furniture in the stack
	 * @return an array of the furniture in the stack
	 */
	public Furniture[] toArray();
	
}
