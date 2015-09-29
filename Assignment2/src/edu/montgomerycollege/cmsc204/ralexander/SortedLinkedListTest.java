package edu.montgomerycollege.cmsc204.ralexander;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.montgomerycollege.cmsc204.jbryant.SortedLinkedList;

public class SortedLinkedListTest
{
    SortedLinkedList<String> sortedLinkedString;
    StringComparator comparator;
    DoubleComparator comparatorD;
    GregorianCalendarComparator comparatorG;
    GregorianCalendar g1, g2, g3, g4, g5;
    SortedLinkedList<Double> sortedLinkedDouble;
    SortedLinkedList<GregorianCalendar> sortedLinkedDate;

    @Before
    public void setUp() throws Exception
    {
        comparator = new StringComparator();
        sortedLinkedString = new SortedLinkedList<String>(comparator);
        comparatorD = new DoubleComparator();
        sortedLinkedDouble = new SortedLinkedList<Double>(comparatorD);

        g1 = new GregorianCalendar(2015, 0, 1); // January 1, 2015
        g2 = new GregorianCalendar(2015, 3, 15); // April 15, 2015
        g3 = new GregorianCalendar(2015, 5, 6); //June 6, 2015
        g4 = new GregorianCalendar(2015, 7, 13); // August 13, 2015
        g5 = new GregorianCalendar(2015, 8, 23); // September 23, 2015

        comparatorG = new GregorianCalendarComparator();
        sortedLinkedDate = new SortedLinkedList<GregorianCalendar>(comparatorG);
    }

    @After
    public void tearDown() throws Exception
    {
        comparator = null;
        sortedLinkedString = null;
    }

    @Test
    public void testAddToEnd()
    {
        try {
            sortedLinkedString.addToEnd("Hello");
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testAddToFront()
    {
        try {
            sortedLinkedString.addToFront("Hello");
            assertTrue("Did not throw an UnsupportedOperationException", false);
        } catch (UnsupportedOperationException e) {
            assertTrue("Successfully threw an UnsupportedOperationException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the UnsupportedOperationException", false);
        }
    }

    @Test
    public void testIteratorSuccessfulString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        sortedLinkedString.add("Begin");
        sortedLinkedString.add("Zebra");
        Iterator<String> iterator = sortedLinkedString.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        sortedLinkedDouble.add(new Double(2));
        sortedLinkedDouble.add(new Double(10));
        Iterator<Double> iterator = sortedLinkedDouble.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(new Double(2), iterator.next());
        assertEquals(new Double(5), iterator.next());
        assertEquals(new Double(8), iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorSuccessfulCalendar()
    {
        sortedLinkedDate.add(g2);
        sortedLinkedDate.add(g3);
        sortedLinkedDate.add(g1);
        sortedLinkedDate.add(g4);
        Iterator<GregorianCalendar> iterator = sortedLinkedDate.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(g1, iterator.next());
        assertEquals(g2, iterator.next());
        assertEquals(g3, iterator.next());
        assertEquals(true, iterator.hasNext());
    }

    @Test
    public void testIteratorNoSuchElementExceptionString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        sortedLinkedString.add("Begin");
        sortedLinkedString.add("Zebra");
        Iterator<String> iterator = sortedLinkedString.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals("Begin", iterator.next());
        assertEquals("Hello", iterator.next());
        assertEquals("World", iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals("Zebra", iterator.next());

        try {
            // Nno more elements in list
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorNoSuchElementExceptionDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        sortedLinkedDouble.add(new Double(2));
        sortedLinkedDouble.add(new Double(10));
        Iterator<Double> iterator = sortedLinkedDouble.iterator();

        assertEquals(true, iterator.hasNext());
        assertEquals(new Double(2), iterator.next());
        assertEquals(new Double(5), iterator.next());
        assertEquals(new Double(8), iterator.next());
        assertEquals(true, iterator.hasNext());
        assertEquals(new Double(10), iterator.next());

        try {
            // No more elements in list
            iterator.next();
            assertTrue("Did not throw a NoSuchElementException", false);
        } catch (NoSuchElementException e) {
            assertTrue("Successfully threw a NoSuchElementException", true);
        } catch (Exception e) {
            assertTrue("Threw an exception other than the NoSuchElementException", false);
        }
    }

    @Test
    public void testIteratorUnsupportedOperationExceptionString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        sortedLinkedString.add("Begin");
        sortedLinkedString.add("Zebra");
        Iterator<String> iterator = sortedLinkedString.iterator();

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
    public void testIteratorUnsupportedOperationExceptionDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        sortedLinkedDouble.add(new Double(2));
        sortedLinkedDouble.add(new Double(10));
        Iterator<String> iterator = sortedLinkedString.iterator();

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
    public void testAddString()
    {
        sortedLinkedString.add("Banana");
        sortedLinkedString.add("Elephant");
        sortedLinkedString.add("Apple");
        assertEquals("Apple", sortedLinkedString.getFirst());
        assertEquals("Elephant", sortedLinkedString.getLast());

        sortedLinkedString.add("Cat");
        sortedLinkedString.add("Dog");
        assertEquals("Apple", sortedLinkedString.getFirst());
        assertEquals("Elephant", sortedLinkedString.getLast());

        // Delete Elephant from linked list
        assertEquals("Elephant", sortedLinkedString.retrieveLastElement());
        assertEquals("Dog", sortedLinkedString.getLast());
    }

    @Test
    public void testAddDouble()
    {
        sortedLinkedDouble.add(new Double(2));
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(1));
        assertEquals(new Double(1), sortedLinkedDouble.getFirst());
        assertEquals(new Double(5), sortedLinkedDouble.getLast());

        sortedLinkedDouble.add(new Double(3));
        sortedLinkedDouble.add(new Double(4));
        assertEquals(new Double(1), sortedLinkedDouble.getFirst());
        assertEquals(new Double(5), sortedLinkedDouble.getLast());

        // Delete 5 from linked list
        assertEquals(new Double(5), sortedLinkedDouble.retrieveLastElement());
        assertEquals(new Double(4), sortedLinkedDouble.getLast());
    }

    @Test
    public void testAddDate()
    {
        sortedLinkedDate.add(g2);
        sortedLinkedDate.add(g5);
        sortedLinkedDate.add(g1);
        assertEquals(g1, sortedLinkedDate.getFirst());
        assertEquals(g5, sortedLinkedDate.getLast());

        sortedLinkedDate.add(g3);
        sortedLinkedDate.add(g4);
        assertEquals(g1, sortedLinkedDate.getFirst());
        assertEquals(g5, sortedLinkedDate.getLast());

        // Delete 5 from linked list
        assertEquals(g5, sortedLinkedDate.retrieveLastElement());
        assertEquals(g4, sortedLinkedDate.getLast());
    }

    @Test
    public void testRemoveFirstString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        assertEquals("Hello", sortedLinkedString.getFirst());
        assertEquals("World", sortedLinkedString.getLast());

        sortedLinkedString.add("Begin");
        assertEquals("Begin", sortedLinkedString.getFirst());

        // Remove the first
        sortedLinkedString.remove("Begin", comparator);
        assertEquals("Hello", sortedLinkedString.getFirst());
    }

    @Test
    public void testRemoveFirstDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        assertEquals(new Double(5), sortedLinkedDouble.getFirst());
        assertEquals(new Double(8), sortedLinkedDouble.getLast());

        sortedLinkedDouble.add(new Double(2));
        assertEquals(new Double(2), sortedLinkedDouble.getFirst());

        // Remove the first
        sortedLinkedDouble.remove(new Double(2), comparatorD);
        assertEquals(new Double(5), sortedLinkedDouble.getFirst());
    }

    @Test
    public void testRemoveFirstDate()
    {
        sortedLinkedDate.add(g2);
        sortedLinkedDate.add(g4);
        assertEquals(g2, sortedLinkedDate.getFirst());
        assertEquals(g4, sortedLinkedDate.getLast());

        sortedLinkedDate.add(g1);
        assertEquals(g1, sortedLinkedDate.getFirst());

        // Remove the first
        sortedLinkedDate.remove(g1, comparatorG);
        assertEquals(g2, sortedLinkedDate.getFirst());
    }

    @Test
    public void testRemoveEndString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        assertEquals("Hello", sortedLinkedString.getFirst());
        assertEquals("World", sortedLinkedString.getLast());

        sortedLinkedString.add("Zebra");
        assertEquals("Zebra", sortedLinkedString.getLast());

        // Remove from the end of the list
        sortedLinkedString.remove("Zebra", comparator);
        assertEquals("World", sortedLinkedString.getLast());
    }

    @Test
    public void testRemoveEndDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        assertEquals(new Double(5), sortedLinkedDouble.getFirst());
        assertEquals(new Double(8), sortedLinkedDouble.getLast());

        sortedLinkedDouble.add(new Double(10));
        assertEquals(new Double(10), sortedLinkedDouble.getLast());

        // Remove from the end of the list
        sortedLinkedDouble.remove(new Double(10), comparatorD);
        assertEquals(new Double(8), sortedLinkedDouble.getLast());
    }

    @Test
    public void testRemoveMiddleString()
    {
        sortedLinkedString.add("Hello");
        sortedLinkedString.add("World");
        assertEquals("Hello", sortedLinkedString.getFirst());
        assertEquals("World", sortedLinkedString.getLast());

        sortedLinkedString.add("Begin");
        assertEquals("Begin", sortedLinkedString.getFirst());
        assertEquals("World", sortedLinkedString.getLast());

        // Remove from middle of list
        sortedLinkedString.remove("Hello", comparator);
        assertEquals("Begin", sortedLinkedString.getFirst());
        assertEquals("World", sortedLinkedString.getLast());
    }

    @Test
    public void testRemoveMiddleDouble()
    {
        sortedLinkedDouble.add(new Double(5));
        sortedLinkedDouble.add(new Double(8));
        assertEquals(new Double(5), sortedLinkedDouble.getFirst());
        assertEquals(new Double(8), sortedLinkedDouble.getLast());

        sortedLinkedDouble.add(new Double(2));
        assertEquals(new Double(2), sortedLinkedDouble.getFirst());

        // Remove from middle of list
        sortedLinkedDouble.remove(new Double(5), comparatorD);
        assertEquals(new Double(2), sortedLinkedDouble.getFirst());
        assertEquals(new Double(8), sortedLinkedDouble.getLast());
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

    private class GregorianCalendarComparator implements Comparator<GregorianCalendar>
    {
        @Override
        public int compare(GregorianCalendar arg0, GregorianCalendar arg1)
        {
            return arg0.compareTo(arg1);
        }

    }
}
