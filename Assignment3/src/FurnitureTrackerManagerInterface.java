package edu.montgomerycollege.cmsc204.jmyers;

import java.io.File;

/**
 * Manages a truck picking up new furniture from a factory and delivering them to four stores.
 * Allows the user to initialize a new tracker by reading furniture info from a file, to load furniture onto 
 * a truck, to dispatch the truck to each store in turn, to unload furniture at each store, 
 * and then to return to the factory.  It also allows the user to add new furniture directly to the
 * factory by entering name, color, and size.
 * The furniture loaded on the truck, in the assembly line at the factory, and at each store's showroom
 * are listed by name. 
 * The class that implements this interface will use a stack to represent the truck (first in, last out),
 * and a queue to represent the factory (first in, first out).  The data structure of each store is 
 * unspecified, i.e., left to the implementing class.
 * This class depends on a furniture class, a Truck class, a Factory class, and a store class.
 * Factory and store both extend the Location class,
 * @author Professor Alexander
 *
 */
public interface  FurnitureTrackerManagerInterface {
	
	/**
	 * This method reads furniture from a file, instantiates them as furniture objects, adds them to the 
	 * factory queue, and sets the truck location to the factory.
	 * @param inFile a File representation of the ASCII file to be read in.  Format of the file is 
	 * 			name, color, and size separated by commas (no spaces), one line per furniture
	 */
	public void newFurnitureTracker(File inFile);
	
	/**
	 * This method gets the set of furniture objects at the specified Location and returns them as an 
	 * array of furniture objects.
	 * @param loc a Location object, which is either a Factory or a Store object, the location 
	 * 			from which to list furnitures
	 * @return an array of furniture objects, the furniture at the location
	 */
	public Furniture[] getFurnitures(Location loc);
	
	/** 
	 * This method retrieves the current location of the truck.  It is either at the Factory, or at one
	 * of the Store.  (Note that transit time is ignored)
	 * @return a Location object representing the Factory or one of the Stores.
	 */
	public Location getTruckLocation();
	
	/**
	 * This method sends the truck to the next location.  It rotates from factory, to store1, to store2,
	 * to store3, store4 and back to factory.
	 * @throws FurnitureTrackerNotInitializedException if the application has not been initialized, since 
	 * the truck does not have a location.
	 */
	public void dispatchTruck() throws FurnitureTrackerNotInitializedException;
	
	/**
	 * This method simulates loading Furniture onto the truck as they come off the factory's assembly line.
	 * The truck cargo is represented with a stack data structure, so the first Furniture loaded will be the last one
	 * that can be offloaded.
	 * @return an array of Furniture objects representing the Furniture currently on the truck.
	 * @throws WrongLocationException if truck is not at the factory
	 * @throws TruckLoadException if the truck cannot be loaded because it is full, or because the factory is empty.
	 */
	public Furniture[] loadTruck() throws WrongLocationException, TruckLoadException;
	
	/**
	 * This method simulates off-loading the truck at a store.  Since the truck's load is represented
	 * as a stack data structure, the last Furniture loaded will be the next one off-loaded.
	 * @param truckLoc the location of the truck, one of the stores or (erroneously) the factory.
	 * @return an array of Furniture objects remaining on the truck after off-loading one Furniture.
	 * @throws WrongLocationException if the truck is currently at the factory
	 * @throws TruckLoadException if the truck is empty or the Store is full
	 */
	public Furniture[] unloadTruck(Location truckLoc) throws WrongLocationException, TruckLoadException;
	
	/**
	 * This method causes one Furniture object to be added to the factory queue, i.e., at the end of the list.
	 * @param frn a Furniture object
	 */
	public void addFurnitureToFactory(Furniture frn);
	
	/**
	 * This method allows the factory instance to be viewed outside the furniture tracker manager
	 * @return Factory object instantiated by the FurnitureTrackerManager object
	 */
	public Factory getFactory();
	/**
	 * This method allows the store1 instance to be viewed outside the furniture tracker manager
	 * @return Store object store 1 instantiated by the FurnitureTrackerManager object
	 */
	public Store getStore1();
	public Store getStore2();
	public Store getStore3();
	public Store getStore4();
	/**
	 * This method allows the truck instance to be viewed outside the furniture tracker manager
	 * @return Truck object instantiated by the FurnitureTrackerManager object
	 */
	public Truck getTruck();
}
