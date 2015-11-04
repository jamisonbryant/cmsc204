package edu.montgomerycollege.cmsc204.ralexander;

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
        fail("Not yet implemented");
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
