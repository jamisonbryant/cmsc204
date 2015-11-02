package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;

import edu.montgomerycollege.cmsc204.jbryant.BSTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BSTreeTest
{
    BSTree<String> linkedTreeString;
    BSTree<Double> linkedTreeDouble;
    StringComparator comparator;
    DoubleComparator comparatorD;

    @Before
    public void setUp() throws Exception
    {
        comparator = new StringComparator();
        linkedTreeString = new BSTree<String>(comparator);
        linkedTreeString.add("Hello");
        linkedTreeString.add("World");

        //STUDENT: You will use the linkedTreeDouble and the comparatorD
        // for your student tests
        comparatorD = new DoubleComparator();
        linkedTreeDouble = new BSTree<Double>(comparatorD);


    }

    @After
    public void tearDown() throws Exception
    {
        linkedTreeString = null;
        linkedTreeDouble = null;
        comparator = null;
        comparatorD = null;
    }

    @Test
    public void testGetSize()
    {
        assertEquals(2, linkedTreeString.getSize());
    }


    @Test
    public void testGetRootDataString()
    {
        assertEquals("Hello", linkedTreeString.getRootData());
        linkedTreeString.add("Apple");
        assertEquals("Hello", linkedTreeString.getRootData());
    }

    @Test
    public void testGetRootDataSTUDENT()
    {
        fail("Not yet implemented");
    }


    @Test
    public void testToArrayTree()
    {
        ArrayList<String> list;

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Next");

        list = linkedTreeString.toArrayList();
        assertEquals("Begin", list.get(0));
        assertEquals("End", list.get(1));
        assertEquals("Hello", list.get(2));
        assertEquals("Next", list.get(3));
        assertEquals("World", list.get(4));
    }


    @Test
    public void testToArrayTreeSTUDENT()
    {
        fail("Not yet implemented");
    }


    private class StringComparator implements Comparator<String>
    {

        @Override
        public int compare(String arg0, String arg1)
        {
            // TODO Auto-generated method stub
            return arg0.compareTo(arg1);
        }

    }

    private class DoubleComparator implements Comparator<Double>
    {

        @Override
        public int compare(Double arg0, Double arg1)
        {
            // TODO Auto-generated method stub
            return arg0.compareTo(arg1);
        }

    }

}
