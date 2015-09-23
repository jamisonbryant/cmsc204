import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BasicLinkedListTest {
	BasicLinkedList<String> linkedString;
	BasicLinkedList<Double> linkedDouble;
	StringComparator comparator;
	DoubleComparator comparatorD;

	@Before
	public void setUp() throws Exception {
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
	public void tearDown() throws Exception {
		linkedString = null;
		comparator = null;
	}

	@Test
	public void testGetSize() {
		assertEquals(2,linkedString.getSize());
		assertEquals(2,linkedDouble.getSize());
	}
	
	@Test
	public void testAddToEnd() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
	}

	@Test
	public void testAddToEndSTUDENT(){
		//test addToEnd for the linkedDouble
		fail("Not yet implemented");
	}
	
	@Test
	public void testAddToFront() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());
	}

	@Test
	public void testAddToFrontSTUDENT(){
		//test addToFront for the linkedDouble
		fail("Not yet implemented");
	}
	
	@Test
	public void testGetFirst() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
	}
	
	@Test
	public void testGetFirstSTUDENT(){
		//test getFirst for the linkedDouble
		fail("Not yet implemented");
	}

	@Test
	public void testGetLast() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
	}

	@Test
	public void testGetLastSTUDENT(){
		//test getLast for the linkedDouble
		fail("Not yet implemented");
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
	public void testToArraySTUDENT(){
		//test toArray for the linkedDouble
		fail("Not yet implemented");
	}
	
	@Test
	public void testIteratorSuccessful() {
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
	public void testIteratorSuccessfulSTUDENT(){
		//test the iterator for the linkedDouble
		//be throughal, use the preceeding test method as an example
		fail("Not yet implemented");
	}
	
	@Test
	public void testIteratorNoSuchElementException() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		Iterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		try{
			//no more elements in list
			iterator.next();
			assertTrue("Did not throw a NoSuchElementException",false);
		}
		catch (NoSuchElementException e)
		{
			assertTrue("Successfully threw a NoSuchElementException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the NoSuchElementException", false);
		}
		
	}
	
	@Test
	public void testIteratorNoSuchElementExceptionSTUDENT(){
		//test the iterator for the linkedDouble.  Exception raised
		//when next is called after last element.
		//be throughal, use the preceeding test method as an example
		fail("Not yet implemented");
	}
	
	@Test
	public void testIteratorUnsupportedOperationException() {
		linkedString.addToFront("Begin");
		linkedString.addToEnd("End");
		Iterator<String> iterator = linkedString.iterator();
		assertEquals(true, iterator.hasNext());
		assertEquals("Begin", iterator.next());
		assertEquals("Hello", iterator.next());
		assertEquals("World", iterator.next());
		assertEquals(true, iterator.hasNext());
		assertEquals("End", iterator.next());
		
		try{
			//remove is not supported for the iterator
			iterator.remove();
			assertTrue("Did not throw a UnsupportedOperationException",false);
		}
		catch (UnsupportedOperationException e)
		{
			assertTrue("Successfully threw a UnsupportedOperationException",true);
		}
		catch (Exception e)
		{
			assertTrue("Threw an exception other than the UnsupportedOperationException", false);
		}
		
	}

	@Test
	public void testIteratorUnsupportedOperationExceptionSTUDENT(){
		//test the remove method for the iterator for the linkedDouble
		//be throughal, use the preceeding test method as an example
		fail("Not yet implemented");
	}
	
	@Test
	public void testRemove() {
		// remove the first
		assertEquals("Hello", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		linkedString.remove("New", comparator);
		assertEquals("Hello", linkedString.getFirst());
		//remove from the end of the list
		linkedString.addToEnd("End");
		assertEquals("End", linkedString.getLast());
		linkedString.remove("End", comparator);
		assertEquals("World", linkedString.getLast());
		//remove from middle of list
		linkedString.addToFront("Begin");
		assertEquals("Begin", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		linkedString.remove("Hello", comparator);
		assertEquals("Begin", linkedString.getFirst());
		assertEquals("World", linkedString.getLast());
		
	}
	
	@Test
	public void testRemoveSTUDENT(){
		//test the remove method for the linkedDouble
		//be throughal, remove from the front of the list, the
		//end of the list and the middle of the list, 
		//use the preceeding test method as an example
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveFirstElement() {
		assertEquals("Hello", linkedString.getFirst());
		linkedString.addToFront("New");
		assertEquals("New", linkedString.getFirst());
		assertEquals("New", linkedString.retrieveFirstElement());
		assertEquals("Hello",linkedString.getFirst());
		assertEquals("Hello", linkedString.retrieveFirstElement());
		assertEquals("World",linkedString.getFirst());
		
	}
	
	@Test
	public void testRetrieveFirstElementSTUDENT(){
		//test retrieveLastElement for linkedDouble
		fail("Not yet implemented");
	}

	@Test
	public void testRetrieveLastElement() {
		assertEquals("World", linkedString.getLast());
		linkedString.addToEnd("New");
		assertEquals("New", linkedString.getLast());
		assertEquals("New", linkedString.retrieveLastElement());
		assertEquals("World",linkedString.getLast());
	}
	
	@Test
	public void testRetrieveLastElementSTUDENT(){
		//test retrieveLastElement for linkedDouble
		fail("Not yet implemented");
	}

	private class StringComparator implements Comparator<String>
	{

		@Override
		public int compare(String arg0, String arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
	
	private class DoubleComparator implements Comparator<Double>
	{

		@Override
		public int compare(Double arg0, Double arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
		
	}
}
