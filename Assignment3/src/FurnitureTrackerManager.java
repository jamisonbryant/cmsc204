import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Application data class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class FurnitureTrackerManager
{
    private boolean initialized;
    private Factory factory;
    private Truck truck;
    private Store store1;
    private Store store2;
    private Store store3;
    private Store store4;
    private LinkedList<Location> locations;

    public FurnitureTrackerManager()
    {
        // Initialize stores
        store1 = new Store("Wal-Mart");
        store2 = new Store("Sam's Club");
        store3 = new Store("BJ's");
        store4 = new Store("Big Lots");

        // Initialize locations
        factory = new Factory("Factory");
        locations = new LinkedList<Location>(Arrays.asList(factory, store1, store2, store3, store4));

        // Initialize truck
        truck = new Truck(factory);
    }

    /**
     * Creates a new furniture tracker
     *
     * @param file Furniture data file
     */
    public void newFurnitureTracker(File file)
    {
        // Read furniture file
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line;
            String[] contents;

            while ((line = br.readLine()) != null) {
                // Parse line contents into array
                contents = line.split(",");

                try {
                    // Create furniture object
                    Furniture f = new Furniture(contents[0], contents[1], contents[2]);

                    // Add furniture to factory
                    factory.addFurniture(f);
                } catch(ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error: Exception occurred while parsing file");
                    e.printStackTrace();
                    GUI.displayError("An error occurred while parsing the file", true);
                }
            }
        } catch (IOException e) {
            System.err.println("Error: Exception occurred while reading file");
            e.printStackTrace();
            GUI.displayError("An error occurred while reading the file", true);
        }

        // Set tracker as initialized
        initialized = true;
    }

    /**
     * Loads a piece of furniture onto the truck from the store
     *
     * @throws WrongLocationException If the truck is not at the factory when this method is called
     * @throws TruckLoadException If the truck is full when this method is called
     * @return Furniture on the truck after loading
     */
    public Furniture[] loadTruck() throws WrongLocationException, TruckLoadException
    {
        // Check if truck is at factory
        if (truck.getLocation().equals(factory)) {
            // Check if truck is not full
            if (truck.toArray().length < 10) {
                // Add furniture to truck
                truck.uploadFurniture(factory.removeFurniture());
                return truck.toArray();
            } else {
                throw new TruckLoadException("Error: Cannot load furniture onto full truck");
            }
        } else {
            throw new WrongLocationException("Error: Cannot load truck at non-factory location");
        }
    }

    /**
     * Unloads a piece of furniture from the truck into a store
     *
     * @param store The store to unload the truck into
     * @throws WrongLocationException If the truck is not at a store when this method is called
     * @throws TruckLoadException If the truck is empty when this method is called
     * @return Furniture on the track after unloading
     */
    public Furniture[] unloadTruck(Store store) throws WrongLocationException, TruckLoadException
    {
        // Check if truck is not at factory
        if (!truck.getLocation().equals(factory)) {
            // Check if truck is not empty
            if (truck.toArray().length != 0) {
                // Remove furniture from truck
                Furniture f = truck.offloadFurniture();

                // Add furniture to store
                store.addFurniture(f);

                // Return new truck contents
                return truck.toArray();
            } else {
                throw new TruckLoadException("Error: Cannot unload furniture from empty truck");
            }
        } else {
            throw new WrongLocationException("Error: Cannot unload furniture at non-store location");
        }
    }

    /**
     * Moves the truck to the next location
     *
     * @throws FurnitureTrackerNotInitializedException If furniture tracker is not initialized
     */
    public void dispatchTruck() throws FurnitureTrackerNotInitializedException
    {
        if (initialized) {
            if (getLocationIndex() + 1 < locations.size()) {
                truck.setLocation(locations.get(getLocationIndex() + 1));
            } else {
                truck.setLocation(locations.get(0));
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Action attempted before tracker initialized");
        }
    }

    //<editor-fold desc="[Getters/Setters] ...">
    /**
     * Returns the manager's truck object
     *
     * @return Truck object
     */
    public Truck getTruck()
    {
        return truck;
    }

    /**
     * Returns the manager's factory object
     *
     * @return Factory object
     */
    public Factory getFactory() { return factory; }

    /**
     * Returns the first of the manager's store objects
     *
     * @return 1st store object
     */
    public Store getStore1()
    {
        return store1;
    }

    /**
     * Returns the second of the manager's store objects
     *
     * @return 2nd store object
     */
    public Store getStore2()
    {
        return store2;
    }

    /**
     * Returns the third of the manager's store objects
     *
     * @return 3rd store object
     */
    public Store getStore3()
    {
        return store3;
    }

    /**
     * Returns the fourth of the manager's store objects
     *
     * @return 4th store object
     */
    public Store getStore4()
    {
        return store4;
    }

    /**
     * Returns tracker's truck's location
     *
     * @return Truck location
     */
    public Location getTruckLocation() { return truck.getLocation(); }

    /**
     * Returns an array of the furniture in a factory
     *
     * @param factory Factory to get furniture of
     * @return Furniture in factory
     */
    public Furniture[] getFurnitures(Factory factory) { return factory.getFurnitures(); }

    /**
     * Returns tracker's initialization state
     *
     * @return True if tracker initialized, false otherwise
     */
    public boolean isInitialized() { return initialized; }

    /**
     * Returns tracker's location list
     *
     * @return Location list
     */
    public LinkedList<Location> getLocations()
    {
        return locations;
    }
    //</editor-fold>

    //<editor-fold desc="[Convenience Methods] ...">
    /**
     * Returns the index of the truck's current location in the location list
     *
     * @return Location index
     */
    public int getLocationIndex()
    {
        return locations.indexOf(getTruckLocation());
    }
    //</editor-fold>

    //<editor-fold desc="[Not Yet Implemented] ...">
    /**
     * Adds a piece of furniture to the factory
     *
     * @param furniture Piece of furniture to add
     */
    public void addFurnitureToFactory(Furniture furniture)
    {
        factory.addFurniture(furniture);
    }
    //</editor-fold>
}
