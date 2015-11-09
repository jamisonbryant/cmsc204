package edu.montgomerycollege.cmsc204.ralexander;

import edu.montgomerycollege.cmsc204.jbryant.BSTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BSTreeTestAdd {
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
        //for the student tests
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
    public void testAddString() {
        assertEquals("Hello", linkedTreeString.getRootData());

        try {
            linkedTreeString.add("End");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        ArrayList<String> list = linkedTreeString.toArrayList();
        assertEquals("End", list.get(0));
        assertEquals("Hello", list.get(1));
        assertEquals("World", list.get(2));

        try {
            linkedTreeString.add("Apple");
            linkedTreeString.add("Banana");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        list = linkedTreeString.toArrayList();
        assertEquals("Apple", list.get(0));
        assertEquals("Banana", list.get(1));
        assertEquals("End", list.get(2));
        assertEquals("Hello", list.get(3));
        assertEquals("World", list.get(4));
    }


    @Test
    public void testAddSTUDENT() {
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        try {
            linkedTreeDouble.add(29.39);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        ArrayList<Double> list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(10.11, list.get(1), 0.00);
        assertEquals(29.39, list.get(2), 0.00);

        try {
            linkedTreeDouble.add(5.4);
            linkedTreeDouble.add(6.7);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(5.4, list.get(1), 0.00);
        assertEquals(6.7, list.get(2), 0.00);
        assertEquals(10.11, list.get(3), 0.00);
        assertEquals(29.39, list.get(4), 0.00);
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
