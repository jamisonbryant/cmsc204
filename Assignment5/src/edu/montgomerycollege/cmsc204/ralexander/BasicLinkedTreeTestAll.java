package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.*;

import java.util.ArrayList;

import edu.montgomerycollege.cmsc204.jbryant.BasicLinkedTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BasicLinkedTreeTestAll
{
    BasicLinkedTree<String> linkedTreeString;
    BasicLinkedTree<Double> linkedTreeDouble;

    @Before
    public void setUp() throws Exception
    {
        linkedTreeString = new BasicLinkedTree<String>();
        linkedTreeString.add("Hello");
        linkedTreeString.add("World");

        //STUDENT: use the following tree for STUDENT tests
        linkedTreeDouble = new BasicLinkedTree<Double>();
    }

    @After
    public void tearDown() throws Exception
    {
        linkedTreeString = null;
        linkedTreeDouble = null;
    }

    @Test
    public void testGetSize()
    {
        assertEquals(2, linkedTreeString.getSize());
    }

    /**
     * Test that nodes are added level by level from left to right
     */
    @Test
    public void testAdd()
    {
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");

        ArrayList<String> list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("End", list.get(2));


        linkedTreeString.add("Apple");
        linkedTreeString.add("Banana");

        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Apple", list.get(2));
        assertEquals("Banana", list.get(3));
        assertEquals("End", list.get(4));

    }


    /**
     * STUDENT: test that nodes are added level by level from left to right
     * Use the test above as an example.  Use the linkedTreeDouble
     */
    @Test
    public void testAddSTUDENT()
    {
        fail("Not yet implemented");
    }

    /**
     * Test to make sure that when a node is added, the root remains
     * the same
     */
    @Test
    public void testGetRootData()
    {
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("Apple");
        assertEquals("Hello", linkedTreeString.getRootData());
    }


    /**
     * STUDENT: test to make sure than when a node is added,
     * the root remains the same.  Use the linkedTreeDouble tree
     */
    @Test
    public void testGetRootSTUDENT()
    {
        fail("Not yet implemented");
    }


    /**
     * Test to see that the nodes of the tree are in the
     * Arraylist in NLR (Preorder) traversal order
     */
    @Test
    public void testToArrayTree()
    {
        ArrayList<String> list;

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Next");

        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Begin", list.get(2));
        assertEquals("Next", list.get(3));
        assertEquals("End", list.get(4));
    }

    /**
     * STUDENT: Test that the nodes are in the Arraylist in NLR
     * (Preorder) traversal order.  Use the linkedTreeDouble tree
     */
    @Test
    public void testToArraySTUDENT()
    {
        fail("Not yet implemented");
    }


    /**
     * Test to remove node with 2 children
     */
    @Test
    public void testRemoveNodeWithTwoChildren()
    {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");

        //remove left of root
        linkedTreeString.remove("World");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("Begin", list.get(1));
        assertEquals("Middle", list.get(2));
        assertEquals("End", list.get(3));
    }

    /**
     * STUDENT: test remove node with 2 children.
     * Use the linkedTreeDouble tree
     */
    @Test
    public void testRemoveNodeWithTwoChildrenSTUDENT()
    {
        fail("Not yet implemented");
    }

    /**
     * Test to remove root of tree
     */
    @Test
    public void testRemoveRoot()
    {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");

        //remove root, replaces it with rightmost child of left subtree
        linkedTreeString.remove("Hello");
        list = linkedTreeString.toArrayList();
        assertEquals("Middle", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Begin", list.get(2));
        assertEquals("End", list.get(3));

    }

    /**
     * STUDENT: test remove root
     * Use the linkedTreeDouble tree
     */
    @Test
    public void testRemoveRootSTUDENT()
    {
        fail("Not yet implemented");
    }

    /**
     * test to remove a node with node children (leaf)
     */
    @Test
    public void testRemoveLeaf()
    {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");

        //remove leaf
        linkedTreeString.remove("Begin");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("End", list.get(2));
    }

    /**
     * STUDENT: test remove a node with no children (leaf)
     * Use the linkedTreeDouble tree
     */
    @Test
    public void testRemoveLeafSTUDENT()
    {
        fail("Not yet implemented");
    }

    /**
     * test to remove a node with 1 child
     */
    @Test
    public void testRemoveNodeWithOneChild()
    {
        ArrayList<String> list;
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("End");
        linkedTreeString.add("Begin");
        linkedTreeString.add("Middle");
        linkedTreeString.add("Very End");

        //remove leaf
        linkedTreeString.remove("End");
        list = linkedTreeString.toArrayList();
        assertEquals("Hello", list.get(0));
        assertEquals("World", list.get(1));
        assertEquals("Begin", list.get(2));
        assertEquals("Middle", list.get(3));
        assertEquals("Very End", list.get(4));

    }

    /**
     * STUDENT: test remove node with 1 child
     * Use the linkedTreeDouble tree
     */
    @Test
    public void testRemoveNodeWithOneChildSTUDENT()
    {
        fail("Not yet implemented");
    }
}
