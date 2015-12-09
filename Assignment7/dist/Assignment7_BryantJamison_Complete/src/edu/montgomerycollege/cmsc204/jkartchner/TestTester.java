package edu.montgomerycollege.cmsc204.jkartchner;

import static org.junit.Assert.*;

import edu.montgomerycollege.cmsc204.jbryant.CSTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Professor Myers
 *
 */
public class TestTester {

	private CSTest test1, test2;
	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		String[] questions = new String[11];
		  questions[0] = "";
		  questions[1] = "Every node in a binary tree has ____ references.";
		  questions[2] = "In a perfectly balanced binary tree, the height of the left and right subtrees of the root are _____";
		  questions[3] = "A node in a binary tree is called a(n) ____ if it has no left and right children.";
		  questions[4] = "In a tree, the number of nodes in the longest branch, is called the ____ of the tree";
		  questions[5] = "Associated with each item in a data set is a special member that uniquely identifies it called a(n) ____.";
		  questions[6] = "In an ordered linked list the search algorithm is somewhat improved because the list is ____.";
		  questions[7] = "A queue is a(n) ____ data structure.";
		  questions[8] = "____ is the ability to create new data types from existing data types.";
		  questions[9] = "When the binding of methods takes place at execution time, it is ____.";
		  questions[10] = "Which operator is used to determine if an object is of a particular class type?";
		  String[] answers = new String[11];
		  answers[0] = "";
		  answers[1] = "2";
		  answers[2] = "equal";
		  answers[3] = "leaf";
		  answers[4] = "height";
		  answers[5] = "key";
		  answers[6] = "sorted";
		  answers[7] = 	"FIFO";
		  answers[8] = 	"inheritance";
		  answers[9] = "dynamic";
		  answers[10] = "instanceof";
		test1 = new CSTest(questions, answers);
		test2 = new CSTest(questions, answers);
		test1.next(); //first question
		test2.next(); //first question
	}

	/**
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link Test#check(String)}.
	 */
	@Test
	public void testCheck() {
		assertEquals("Correct", test1.check("2"));
		assertEquals("Incorrect",test1.check("3"));
	}

	/**
	 * Test method for {@link Test#next()}.
	 */
	@Test
	public void testNext() {
		assertEquals("In a perfectly balanced binary tree, the height of the left and right subtrees of the root are _____",test1.next());
		
	}

	/**
	 * Test method for {@link Test#getPercentCorrect()}.
	 */
	@Test
	public void testGetPercentCorrect() {
		test1.check("2"); // correct result
		test1.next(); // next question
		test1.check("equal"); // correct result
		assertEquals(100.0,test1.getPercentCorrect(),.001);
	}

}
