package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.montgomerycollege.cmsc204.jbryant.BasicLinkedList;


public class BasicLinkedListTest
{
    BasicLinkedList<String> linkedString;
    BasicLinkedList<Double> linkedDouble;
    StringComparator comparator;
    DoubleComparator comparatorD;

    @Before
    public void setUp() throws Exception
    {
        linkedString = new BasicLinkedList<String>();
        linkedString.addToEnd("Hello");
        linkedString.addToEnd("World");
        comparator = new StringComparator();

        linkedDouble = new BasicLinkedList<Double>();
        linkedDouble.addToEnd(15.0);
        linkedDouble.addToEnd(100.0);
        comparatorD = new DoubleComparator();

    }

    @After
    public void tearDown() throws Exception
    {
        linkedString = null;
        comparator = null;
    }

    @Test
    public void testGetSize()
    {
        assertEquals(2, linkedString.getSize());
        assertEquals(2, linkedDouble.getSize());
    }

    @Test
    public void testAddToEnd()
    {
        assertEquals("World", linkedString.getLast());
        linkedString.addToEnd("End");
        assertEquals("End", linkedString.getLast());
    }

    @Test
    public void testAddToEndSTUDENT()
    {
        assertEquals((Double) 100.0, linkedDouble.getLast());
        linkedDouble.addToEnd(75.0);
        assertEquals((Double) 75.0, linkedDouble.getLast());
    }

    @Test
    public void testAddToFront()
    {
        assertEquals("Hello", linkedString.getFirst());
        linkedString.addToFront("Begin");
        assertEquals("Begin", linkedString.getFirst());
    }

    @Test
    public void testAddToFrontSTUDENT()
    {
        assertEquals((Double) 15.0, linkedDouble.getFirst());
        linkedDouble.addToFront(25.0);
        assertEquals((Double) 25.0, linkedDouble.getFirst());
    }

    @Test
    public void testGetFirst()
    {
        assertEquals("Hello", linkedString.getFirst());
        linkedString.addToFront("New");
        assertEquals("New", linkedString.getFirst());
    }

    @Test
    public void testGetFirstSTUDENT()
    {
        assertEquals((Double) 15.0, (Double) linkedDouble.getFirst());
    }

    @Test
    public void testGetLast()
    {
        assertEquals("World", linkedString.getLast());
        linkedString.addToEnd("New");
        assertEquals("New", linkedString.getLast());
    }

    @Test
    public void testGetLastSTUDENT()
    {
        assertEquals((Double) 100.0, linkedDouble.getLast());
        linkedDouble.addToEnd(75.0);
        assertEquals((Double) 75.0, linkedDouble.getLast());
    }

    @Test
    public void testToArrayList()
    {
        ArrayList<String> list;
        linkedString.addToFront("Begin");
        linkedString.addToEnd("End");
        list = linkedString.toArrayList();

        assertEquals("Begin", list.get(0));
        assertEquals("Hello", list.get(1));
        assertEquals("World", list.get(2));
        assertEquals("End", list.get(3));
    }

    @Test
    public void testToArraySTUDENT()
    {
        ArrayList<Double> doubleArrayList = linkedDouble.toArrayList();
        assertEquals((Double) 15.0, doubleArrayList.get(0));
        assertEquals((Double) 100.0, doubleArrayList.get(1));
    }

    @Test
    public void testIteratorSuccessful()
    {
        linkedString.addToFront("Begin");
        linkedString.addToEnd("End");
        Iterator<String> iterator = linkedString.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("End", iterator.next());
    }

    @Test
    public void testIteratorSuccessfulSTUDENT()
    {
        linkedDouble.addToFront(25.0);
        linkedDouble.addToEnd(75.0);
        Iterator<String> iterator = linkedDouble.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(25.0, iterator.next());
        assertEquals(15.0, iterator.next());
        assertEquals(100.0, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(75.0, iterator.next());
    }

    @Test
    public void testIteratorNloloSuchElementException()
    {
        linkedString.addToFront("Begin");
        linkedString.addToEnd("End");
        Iterator<String> iterator = linkedString.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("End", iterator.next());

        try {
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionSTUDENT()
    {
        linkedDouble.addToFront(25.0);
        linkedDouble.addToEnd(75.0);
        Iterator<String> iterator = linkedDouble.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(25.0, iterator.next());
        assertEquals(15.0, iterator.next());
        assertEquals(100.0, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(75.0, iterator.next());

        try {
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationException()
    {
        linkedString.addToFront("Begin");
        linkedString.addToEnd("End");
        Iterator<String> iterator = linkedString.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("End", iterator.next());

        try {
            // Remove is not supported for the iterator
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }

    }

    @Test
    public void testIteratorUnsupportedOperationExceptionSTUDENT()
    {
        linkedDouble.addToFront(25.0);
        linkedDouble.addToEnd(75.0);
        Iterator<Double> iterator = linkedDouble.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals((Double) 25.0, iterator.next());
        assertEquals((Double) 15.0, iterator.next());
        assertEquals((Double) 100.0, iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals((Double) 75.0, iterator.next());

        try {
            iterator.remove();
            assertTrue("Did not throw a UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw a UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testRemove()
    {
        // Remove the first
        assertEquals("Hello", linkedString.getFirst());
        assertEquals("World", linkedString.getLast());
        linkedString.addToFront("New");
        assertEquals("New", linkedString.getFirst());
        linkedString.remove("New", comparator);
        assertEquals("Hello", linkedString.getFirst());

        // Remove from the end of the list
        linkedString.addToEnd("End");
        assertEquals("End", linkedString.getLast());
        linkedString.remove("End", comparator);
        assertEquals("World", linkedString.getLast());

        // Remove from middle of list
        linkedString.addToFront("Begin");
        assertEquals("Begin", linkedString.getFirst());
        assertEquals("World", linkedString.getLast());
        linkedString.remove("Hello", comparator);
        assertEquals("Begin", linkedString.getFirst());
        assertEquals("World", linkedString.getLast());

    }

    @Test
    public void testRemoveSTUDENT()
    {
        // Remove the first
        assertEquals((Double) 15.0, linkedDouble.getFirst());
        assertEquals((Double) 100.0, linkedDouble.getLast());
        linkedDouble.addToFront(25.0);
        assertEquals((Double) 25.0, linkedDouble.getFirst());
        linkedDouble.remove((Double) 25.0, comparatorD);
        assertEquals((Double) 15.0, linkedDouble.getFirst());

        // Remove from the end of the list
        linkedDouble.addToEnd(75.0);
        assertEquals((Double) 75.0, linkedDouble.getLast());
        linkedDouble.remove(75.0, comparatorD);
        assertEquals((Double) 100.0, linkedDouble.getLast());

        // Remove from middle of list
        linkedDouble.addToFront(60.0);
        assertEquals((Double) 60.0, linkedDouble.getFirst());
        assertEquals((Double) 100.0, linkedDouble.getLast());
        linkedDouble.remove(15.0, comparatorD);
        assertEquals((Double) 60.0, linkedDouble.getFirst());
        assertEquals((Double) 100.0, linkedDouble.getLast());
    }

    @Test
    public void testRetrieveFirstElement()
    {
        assertEquals("Hello", linkedString.getFirst());
        linkedString.addToFront("New");
        assertEquals("New", linkedString.getFirst());
        assertEquals("New", linkedString.retrieveFirstElement());
        assertEquals("Hello", linkedString.getFirst());
        assertEquals("Hello", linkedString.retrieveFirstElement());
        assertEquals("World", linkedString.getFirst());

    }

    @Test
    public void testRetrieveFirstElementSTUDENT()
    {
        assertEquals((Double) 15.0, linkedDouble.getFirst());
        linkedDouble.addToFront(25.0);
        assertEquals((Double) 25.0, linkedDouble.getFirst());
        assertEquals((Double) 25.0, linkedDouble.retrieveFirstElement());
        assertEquals((Double) 15.0, linkedDouble.getFirst());
        assertEquals((Double) 15.0, linkedDouble.retrieveFirstElement());
        assertEquals((Double) 100.0, linkedDouble.getFirst());
    }

    @Test
    public void testRetrieveLastElement()
    {
        assertEquals("World", linkedString.getLast());
        linkedString.addToEnd("New");
        assertEquals("New", linkedString.getLast());
        assertEquals("New", linkedString.retrieveLastElement());
        assertEquals("World", linkedString.getLast());
    }

    @Test
    public void testRetrieveLastElementSTUDENT()
    {
        assertEquals((Double) 100.0, linkedDouble.getLast());
        linkedDouble.addToEnd(75.0);
        assertEquals((Double) 75.0, linkedDouble.getLast());
        assertEquals((Double) 75.0, linkedDouble.retrieveLastElement());
        assertEquals((Double) 100.0, linkedDouble.getLast());
    }

    private class StringComparator implements Comparator<String>
    {
        @Override
        public int compare(String arg0, String arg1)
        {
            return arg0.compareTo(arg1);
        }

    }

    private class DoubleComparator implements Comparator<Double>
    {
        @Override
        public int compare(Double arg0, Double arg1)
        {
            return arg0.compareTo(arg1);
        }

    }
}
