package edu.montgomerycollege.cmsc204.ralexander;

import edu.montgomerycollege.cmsc204.jbryant.BSTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BSTreeTest {
    BSTree<String> linkedTreeString;
    BSTree<Double> linkedTreeDouble;
    StringComparator comparator;
    DoubleComparator comparatorD;

    @Before
    public void setUp() throws Exception {
        comparator = new StringComparator();
        linkedTreeString = new BSTree<String>(comparator);
        linkedTreeString.add("Hello");
        linkedTreeString.add("World");

        //STUDENT: You will use the linkedTreeDouble and the comparatorD
        // for your student tests
        comparatorD = new DoubleComparator();
        linkedTreeDouble = new BSTree<Double>(comparatorD);
        linkedTreeDouble.add(3.14);
        linkedTreeDouble.add(10.11);
    }

    @After
    public void tearDown() throws Exception {
        linkedTreeString = null;
        linkedTreeDouble = null;
        comparator = null;
        comparatorD = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedTreeString.getSize());
    }


    @Test
    public void testGetRootDataString() {
        assertEquals("Hello", linkedTreeString.getRootData());
        linkedTreeString.add("Apple");
        assertEquals("Hello", linkedTreeString.getRootData());
    }

    @Test
    public void testGetRootDataSTUDENT() {
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);
        linkedTreeDouble.add(1.23);
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);
    }


    @Test
    public void testToArrayTree() {
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
    public void testToArrayTreeSTUDENT() {
        ArrayList<Double> list;

        linkedTreeDouble.add(103.29);
        linkedTreeDouble.add(93.29);
        linkedTreeDouble.add(9.9);

        list = linkedTreeDouble.toArrayList();
        assertEquals(93.29, list.get(0), 0.00);
        assertEquals(103.29, list.get(1), 0.00);
        assertEquals(3.14, list.get(2), 0.00);
        assertEquals(9.9, list.get(3), 0.00);
        assertEquals(10.11, list.get(4), 0.00);
    }


    private class StringComparator implements Comparator<String> {
        @Override
        public int compare(String arg0, String arg1) {
            // TODO Auto-generated method stub
            return arg0.compareTo(arg1);
        }
    }

    private class DoubleComparator implements Comparator<Double> {
        @Override
        public int compare(Double arg0, Double arg1) {
            // TODO Auto-generated method stub
            return arg0.compareTo(arg1);
        }
    }
}
