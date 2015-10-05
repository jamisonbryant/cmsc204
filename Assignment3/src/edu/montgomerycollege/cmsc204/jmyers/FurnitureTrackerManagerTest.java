package edu.montgomerycollege.cmsc204.jmyers;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.montgomerycollege.cmsc204.jbryant.data.FurnitureTrackerManager;
import edu.montgomerycollege.cmsc204.jbryant.error.FurnitureTrackerNotInitializedException;
import edu.montgomerycollege.cmsc204.jbryant.error.TruckLoadException;
import edu.montgomerycollege.cmsc204.jbryant.error.WrongLocationException;
import edu.montgomerycollege.cmsc204.jbryant.model.Factory;
import edu.montgomerycollege.cmsc204.jbryant.model.Furniture;
import edu.montgomerycollege.cmsc204.jbryant.model.Store;
import edu.montgomerycollege.cmsc204.jbryant.model.Truck;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FurnitureTrackerManagerTest
{
    private FurnitureTrackerManager frnTrackerManager;
    private File inFile;
    private PrintWriter output;
    private Truck truck;
    private Factory factory;
    private Store store1, store2, store3, store4;

    @Before
    public void setUp() throws Exception
    {
        inFile = new File("frnTrackerTest");

        try {
            output = new PrintWriter(inFile);
            output.println("Bookcase,Mahagony,Wood");
            output.println("End Table,Maple,Wood");
            output.println("Futon,Black,Fabric");
            output.println("Chair,Cherry,Wood");
            output.println("Sofa,Brown,Leather");
            output.println("Lamp,White,Cristal");
            output.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        frnTrackerManager = new FurnitureTrackerManager();
        frnTrackerManager.newFurnitureTracker(inFile.getAbsoluteFile());
        truck = frnTrackerManager.getTruck();
        factory = frnTrackerManager.getFactory();
        store1 = frnTrackerManager.getStore1();
    }

    @After
    public void tearDown() throws Exception
    {
        frnTrackerManager = null;
        truck = null;
        factory = null;
        store1 = null;
    }

    @Test
    public void testGetFurnitures()
    {
        Furniture[] newFrns = frnTrackerManager.getFurnitures(factory);
        assertEquals(newFrns[1].toString().compareTo("End Table"), 0);
        assertEquals(newFrns[4].toString().compareTo("Sofa"), 0);
    }

    @Test
    public void testGetTruckLocation()
    {
        assertTrue(frnTrackerManager.getTruckLocation().equals(factory));
    }

    @Test
    public void testDispatchTruck()
    {
        try {
            frnTrackerManager.dispatchTruck();
            assertTrue(frnTrackerManager.getTruckLocation().equals(store1));
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.dispatchTruck();
            assertTrue(frnTrackerManager.getTruckLocation().equals(store1));
        } catch (FurnitureTrackerNotInitializedException e) {
            fail("should not have thrown a FurnitureTrackerNotInitialized Exception " + e);
        }
    }

    @Test
    public void testLoadTruck()
    {
        Furniture[] truckLoad = null;
        try {
            truckLoad = frnTrackerManager.loadTruck();
            truckLoad = frnTrackerManager.loadTruck();
            truckLoad = frnTrackerManager.loadTruck();
            truckLoad = frnTrackerManager.loadTruck();
            truckLoad = frnTrackerManager.loadTruck();
            assertEquals(truckLoad[0].color.compareTo("Mahagony"), 0);
            assertEquals(truckLoad[3].material.compareTo("Wood"), 0);
        } catch (WrongLocationException e) {
            fail("should not have thrown a WrongLocationException Exception " + e);
        } catch (TruckLoadException e) {
            fail("should not have thrown a TruckLoadException Exception " + e);
        }
    }

    @Test
    public void testLoadTruckSTUDENT()
    {
        fail("not implemented yet");
    }

    @Test
    public void testUnloadTruck()
    {
        Furniture[] frnList = null;
        try {
            frnTrackerManager.loadTruck();
            frnTrackerManager.loadTruck();
            frnTrackerManager.loadTruck();
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.unloadTruck(store1);
            frnList = frnTrackerManager.unloadTruck(store1);
            assertEquals(frnList[0].color.compareTo("Mahagony"), 0);
            frnList = store1.getFurnitures();
            assertEquals(frnList[1].color.compareTo("Maple"), 0);
        } catch (WrongLocationException e) {
            fail("should not have thrown a WrongLocationException Exception " + e);
            e.printStackTrace();
        } catch (TruckLoadException e) {
            fail("should not have thrown a TruckLoadException Exception " + e);
        } catch (FurnitureTrackerNotInitializedException e) {
            fail("should not have thrown a FurnitureTrackerNotInitializedException Exception " + e);
        }
    }

    @Test
    public void testUnloadTruckSTUDENT()
    {
        fail("not implemented yet");
    }

    @Test
    public void testWrongLocationException()
    {
        try {
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.loadTruck();
            fail("should have thrown a WrongLocationException Exception ");
        } catch (WrongLocationException e) {
            System.out.println("correctly threw " + e);
        } catch (FurnitureTrackerNotInitializedException e) {
            fail("should not have thrown a FurnitureTrackerNotInitializedException Exception " + e);
        } catch (TruckLoadException e) {
            fail("should not have thrown a TruckLoadException Exception " + e);
        }
    }

    @Test
    public void testTruckLoadException()
    {
        try {
            frnTrackerManager.dispatchTruck();
            frnTrackerManager.unloadTruck(store1);
            fail("should have thrown a TruckLoadException Exception ");
        } catch (WrongLocationException e) {
            fail("should not have thrown a WrongLocationException Exception " + e);
        } catch (FurnitureTrackerNotInitializedException e) {
            fail("should not have thrown a FurnitureTrackerNotInitializedException Exception " + e);
        } catch (TruckLoadException e) {
            System.out.println("correctly threw " + e);
        }
    }

    @Test
    public void testAddVehicleToFactory()
    {
        String name = "Bed";
        String color = "Cherry";
        String material = "Wood";
        Furniture newFrn = new Furniture(name, color, material);
        frnTrackerManager.addFurnitureToFactory(newFrn);
        Furniture[] frnList = factory.getFurnitures();
        assertEquals(frnList[6].color.compareTo("Cherry"), 0);
    }

    @Test
    public void testAddVehicleToFactorySTUDENT()
    {
        fail("not implemented yet");
    }

}
