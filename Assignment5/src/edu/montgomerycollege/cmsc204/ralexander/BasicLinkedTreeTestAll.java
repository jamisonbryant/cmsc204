package edu.montgomerycollege.cmsc204.ralexander;

import edu.montgomerycollege.cmsc204.jbryant.BasicLinkedTree;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class BasicLinkedTreeTestAll {
    BasicLinkedTree<String> linkedTreeString;
    BasicLinkedTree<Double> linkedTreeDouble;

    @Before
    public void setUp() throws Exception {
        linkedTreeString = new BasicLinkedTree<String>();
        linkedTreeString.add("Hello");
        linkedTreeString.add("World");

        //STUDENT: use the following tree for STUDENT tests
        linkedTreeDouble = new BasicLinkedTree<Double>();
        linkedTreeDouble.add(3.14);
        linkedTreeDouble.add(10.11);
    }

    @After
    public void tearDown() throws Exception {
        linkedTreeString = null;
        linkedTreeDouble = null;
    }

    @Test
    public void testGetSize() {
        assertEquals(2, linkedTreeString.getSize());
    }

    /**
     * Test that nodes are added level by level from left to right
     */
    @Test
    public void testAdd() {
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
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(12.34);

        ArrayList<Double> list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(10.11, list.get(1), 0.00);
        assertEquals(12.34, list.get(2), 0.00);

        linkedTreeDouble.add(-133.0);
        linkedTreeDouble.add(0.01);

        list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(10.11, list.get(1), 0.00);
        assertEquals(-133.0, list.get(2), 0.00);
        assertEquals(0.01, list.get(3), 0.00);
        assertEquals(12.34, list.get(4), 0.00);
    }

    /**
     * Test to make sure that when a node is added, the root remains
     * the same
     */
    @Test
    public void testGetRootData() {
        assertEquals("Hello", linkedTreeString.getRootData());

        linkedTreeString.add("Apple");
        assertEquals("Hello", linkedTreeString.getRootData());
    }


    /**
     * STUDENT: test to make sure than when a node is added,
     * the root remains the same.  Use the linkedTreeDouble tree
     */
    @Test
    public void testGetRootSTUDENT() {
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);

        linkedTreeDouble.add(99.99);
        assertEquals(3.14, linkedTreeDouble.getRootData(), 0.00);
    }


    /**
     * Test to see that the nodes of the tree are in the
     * Arraylist in NLR (Preorder) traversal order
     */
    @Test
    public void testToArrayTree() {
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
    public void testToArraySTUDENT() {
        ArrayList<Double> list;

        linkedTreeDouble.add(4.56);
        linkedTreeDouble.add(92.9);
        linkedTreeDouble.add(1093.2);

        list = linkedTreeDouble.toArrayList();
        assertEquals(3.14, list.get(0), 0.00);
        assertEquals(10.11, list.get(1), 0.00);
        assertEquals(92.9, list.get(2), 0.00);
        assertEquals(1093.2, list.get(3), 0.00);
        assertEquals(4.56, list.get(4), 0.00);
    }

    /**
     * Test to remove node with 2 children
     */
    @Test
    public void testRemoveNodeWithTwoChildren() {
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
    public void testRemoveNodeWithTwoChildrenSTUDENT() {
        fail("Not yet implemented");
    }

    /**
     * Test to remove root of tree
     */
    @Test
    public void testRemoveRoot() {
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
    public void testRemoveRootSTUDENT() {
        fail("Not yet implemented");
    }

    /**
     * test to remove a node with node children (leaf)
     */
    @Test
    public void testRemoveLeaf() {
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
    public void testRemoveLeafSTUDENT() {
        fail("Not yet implemented");
    }

    /**
     * test to remove a node with 1 child
     */
    @Test
    public void testRemoveNodeWithOneChild() {
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
    public void testRemoveNodeWithOneChildSTUDENT() {
        fail("Not yet implemented");
    }
}
