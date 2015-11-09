package edu.montgomerycollege.cmsc204.ralexander;

import edu.montgomerycollege.cmsc204.jbryant.BSTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BSTreeTestRemove {
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

        //STUDENT: Use the linkedTreeDouble and the comparatorD
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
    public void testRemoveNodeWithTwoChildren() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");
        linkedTreeString.add("Zebra");

        //remove left of root
        linkedTreeString.remove("World");
        list = linkedTreeString.toArrayList();
        assertEquals("Begin", list.get(0));
        assertEquals("End", list.get(1));
        assertEquals("Hello", list.get(2));
        assertEquals("Middle", list.get(3));
        assertEquals("Zebra", list.get(4));
    }

    @Test
    public void testRemoveNodeWithTwoChildrenSTUDENT() {
        ArrayList<Double> list;
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(1.23);
        linkedTreeDouble.add(4.56);
        linkedTreeDouble.add(7.89);
        linkedTreeDouble.add(9.01);

        //remove left of root
        linkedTreeDouble.remove(3.14);
        list = linkedTreeDouble.toArrayList();
        assertEquals(1.23, list.get(0), 0.00);
        assertEquals(4.56, list.get(1), 0.00);
        assertEquals(7.89, list.get(2), 0.00);
        assertEquals(9.01, list.get(3), 0.00);
        assertEquals(10.11, list.get(4), 0.00);
    }

    @Test
    public void testRemoveRoot() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");
        linkedTreeString.add("Zebra");

        //remove root, replaces it with rightmost child of left subtree
        linkedTreeString.remove("Hello");
        assertEquals("End", linkedTreeString.getRootData());
        list = linkedTreeString.toArrayList();
        assertEquals("Begin", list.get(0));
        assertEquals("End", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("World", list.get(3));
        assertEquals("Zebra", list.get(4));

    }

    @Test
    public void testRemoveRootSTUDENT() {
        ArrayList<Double> list;
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(1.23);
        linkedTreeDouble.add(4.56);
        linkedTreeDouble.add(7.89);
        linkedTreeDouble.add(9.01);

        //remove root, replaces it with rightmost child of left subtree
        linkedTreeDouble.remove(3.14);
        assertEquals(1.23, linkedTreeDouble.getRootData(), 0.00);
        list = linkedTreeDouble.toArrayList();
        assertEquals(1.23, list.get(0), 0.00);
        assertEquals(4.56, list.get(1), 0.00);
        assertEquals(7.89, list.get(2), 0.00);
        assertEquals(9.01, list.get(3), 0.00);
        assertEquals(10.11, list.get(4), 0.00);
    }

    @Test
    public void testRemoveLeaf() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");
        linkedTreeString.add("Zebra");

        //remove leaf
        linkedTreeString.remove("Begin");
        list = linkedTreeString.toArrayList();
        assertEquals("End", list.get(0));
        assertEquals("Hello", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("World", list.get(3));
        assertEquals("Zebra", list.get(4));

    }

    @Test
    public void testRemoveLeafSTUDENT()
    {
        ArrayList<Double> list;
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(1.23);
        linkedTreeDouble.add(4.56);
        linkedTreeDouble.add(7.89);
        linkedTreeDouble.add(9.01);

        //remove leaf
        linkedTreeDouble.remove(1.23);
        list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(4.56, list.get(1), 0.00);
        assertEquals(7.89, list.get(2), 0.00);
        assertEquals(9.01, list.get(3), 0.00);
        assertEquals(10.11, list.get(4), 0.00);
    }

    @Test
    public void testRemoveNodeWithOneChild() {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");
        linkedTreeString.add("Zebra");

        //remove leaf
        linkedTreeString.remove("End");
        list = linkedTreeString.toArrayList();
        assertEquals("Begin", list.get(0));
        assertEquals("Hello", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("World", list.get(3));
        assertEquals("Zebra", list.get(4));

    }

    @Test
    public void testRemoveNodeWithOneChildSTUDENT() {
        ArrayList<Double> list;
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(1.23);
        linkedTreeDouble.add(4.56);
        linkedTreeDouble.add(7.89);
        linkedTreeDouble.add(9.01);

        //remove leaf
        linkedTreeDouble.remove(1.23);
        list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(4.56, list.get(1), 0.00);
        assertEquals(7.89, list.get(2), 0.00);
        assertEquals(9.01, list.get(3), 0.00);
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
