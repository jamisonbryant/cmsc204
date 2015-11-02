package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.montgomerycollege.cmsc204.jbryant.BSTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BSTreeTestRemove
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

        //STUDENT: Use the linkedTreeDouble and the comparatorD
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
    public void testRemoveNodeWithTwoChildren()
    {
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
    public void testRemoveNodeWithTwoChildrenSTUDENT()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveRoot()
    {
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
    public void testRemoveRootSTUDENT()
    {
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveLeaf()
    {
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
        fail("Not yet implemented");
    }

    @Test
    public void testRemoveNodeWithOneChild()
    {
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
    public void testRemoveNodeWithOneChildSTUDENT()
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
