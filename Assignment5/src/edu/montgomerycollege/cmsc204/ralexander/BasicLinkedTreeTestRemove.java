package edu.montgomerycollege.cmsc204.ralexander;

import edu.montgomerycollege.cmsc204.jbryant.BasicLinkedTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BasicLinkedTreeTestRemove {
    BasicLinkedTree<String> linkedTreeString;
    BasicLinkedTree<Double> linkedTreeDouble;
    StringComparator comparator;
    DoubleComparator comparatorD;

    @Before
    public void setUp() throws Exception {
        linkedTreeString = new BasicLinkedTree<String>();
        linkedTreeString.add("Hello");
        linkedTreeString.add("World");
        comparator = new StringComparator();

        linkedTreeDouble = new BasicLinkedTree<Double>();
        linkedTreeDouble.add(15.0);
        linkedTreeDouble.add(100.0);
        comparatorD = new DoubleComparator();

    }

    @After
    public void tearDown() throws Exception {
        linkedTreeString = null;
        linkedTreeDouble = null;
        comparator = null;
    }


    @Test
    public void testRemoveNodeWithTwoChildren() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());
        try {
            linkedTreeString.add("End");
            linkedTreeString.add("Begin");
            linkedTreeString.add("Middle");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        //remove left of root
        linkedTreeString.remove("World");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("Begin", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("End", list.get(3));
    }

    @Test
    public void testRemoveRoot() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());
        try {
            linkedTreeString.add("End");
            linkedTreeString.add("Begin");
            linkedTreeString.add("Middle");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        //remove root, replaces it with rightmost child of left subtree
        linkedTreeString.remove("Hello");
        list = linkedTreeString.toArrayList();
        assertEquals("Middle", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Begin", list.get(2));
        assertEquals("End", list.get(3));

    }

    @Test
    public void testRemoveLeaf() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());
        try {
            linkedTreeString.add("End");
            linkedTreeString.add("Begin");
            linkedTreeString.add("Middle");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        //remove leaf
        linkedTreeString.remove("Begin");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("End", list.get(3));

    }

    @Test
    public void testRemoveNodeWithOneChild() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());
        try {
            linkedTreeString.add("End");
            linkedTreeString.add("Begin");
            linkedTreeString.add("Middle");
            linkedTreeString.add("Very End");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have thrown an exception");
        }

        //remove leaf
        linkedTreeString.remove("End");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Begin", list.get(2));
        assertEquals("Middle", list.get(3));
        assertEquals("Very End", list.get(4));

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
