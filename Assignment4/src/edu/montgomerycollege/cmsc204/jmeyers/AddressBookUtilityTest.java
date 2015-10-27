package edu.montgomerycollege.cmsc204.jmeyers;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.montgomerycollege.cmsc204.jbryant.AddressBookUtility;
import edu.montgomerycollege.cmsc204.jbryant.Person;
import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jbryant.error.KeyInUseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AddressBookUtilityTest {
    private AddressBookUtility myAddressBook;
	private AddressBookUtility addressBook;
	PersonInterface p1, p2, p3, p4, p5, p6;
    PersonInterface me, mySister, myMom, myDad;
	PrintWriter output;
	File newFile, outFile;

	@Before
	public void setUp() throws Exception {
		addressBook = new AddressBookUtility();
		p1 = new Person("John", "Smith", "(208)322-8822", "ID");
		p2 = new Person("Mary", "Arnold", "(240)944-3347", "VA");
		p3 = new Person("Eric", "Wiz", "(701)445-2333", "NY");
		p4 = new Person("Robert", "Kaznick", "(615)387-3935", "TN");
		p5 = new Person("Betty", "Hanson", "(714)273-3813", "CA");
		p6 = new Person("Steve", "Monty", "(801)553-8273", "UT");
		
		addressBook.add("John", "Smith", "(208)322-8822", "ID");
		addressBook.add("Mary", "Arnold", "(240)944-3347", "VA");
		addressBook.add("Eric", "Wiz", "(701)445-2333", "NY");
		addressBook.add("Robert", "Kaznick", "(615)387-3935", "TN");
		addressBook.add("Betty", "Hanson", "(714)273-3813", "CA");
		
		//STUDENT - create an additional address book object and add
		// Person objects that will be different from those above
		// use these in the STUDENT test below
		myAddressBook = new AddressBookUtility();
        me = new Person("Jamison", "Bryant", "(123)456-7890", "MD");
        mySister = new Person("Jenna", "Bryant", "(321)456-7890", "MD");
        myMom = new Person("Gin", "Bryant", "(123)654-7890", "MD");
        myDad = new Person("Ross", "Bryant", "(321)654-7890", "MD");

        myAddressBook.add(me.getFname(), me.getLname(), me.getPhone(), me.getAddress());
        myAddressBook.add(mySister.getFname(), mySister.getLname(), mySister.getPhone(), mySister.getAddress());
        myAddressBook.add(myMom.getFname(), myMom.getLname(), myMom.getPhone(), myMom.getAddress());
        myAddressBook.add(myDad.getFname(), myDad.getLname(), myDad.getPhone(), myDad.getAddress());
	}

	@After
	public void tearDown() throws Exception {
		addressBook = null;
		p1 = p2 = p3 = p4 = p5 = p6 = null;

		//STUDENT - teardown your address book objects and Person objects
        myAddressBook = null;
        me = mySister = myMom = myDad = null;
	}

	@Test
	public void testContainsPersonInterface() {
		assertEquals(true, addressBook.contains(p1));
		assertEquals(true, addressBook.contains(p2));
		assertEquals(true, addressBook.contains(p3));
		assertEquals(true, addressBook.contains(p4));
		assertEquals(true, addressBook.contains(p5));
		assertEquals(false, addressBook.contains(p6));
	}

	@Test
	public void testContainsString() {
		try {
			assertEquals(true, addressBook.contains("(208)322-8822"));
			assertEquals(true, addressBook.contains("(240)944-3347"));
			assertEquals(true, addressBook.contains("(701)445-2333"));
			assertEquals(true, addressBook.contains("(615)387-3935"));
			assertEquals(true, addressBook.contains("(714)273-3813"));
			assertEquals(false, addressBook.contains("(801)553-8273"));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			fail("This should not have raised an InvalidKeyException");
		}
		try {
			addressBook.contains("801553-8273");
			fail("This should have raised an InvalidKeyException ");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// correct
		}
	}
	
	@Test
	public void testContainsStringSTUDENT() {
        try {
            assertEquals(true, myAddressBook.contains("(123)456-7890"));
            assertEquals(true, myAddressBook.contains("(321)456-7890"));
            assertEquals(true, myAddressBook.contains("(123)654-7890"));
            assertEquals(true, myAddressBook.contains("(321)654-7890"));
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            fail("This should not have raised an InvalidKeyException");
        }
        try {
            myAddressBook.contains("311-293-2920");
            fail("This should have raised an InvalidKeyException ");
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            // correct
        }
	}

	@Test
	public void testReverseLookup() {
		try {
			assertEquals("Smith, John", addressBook.reverseLookup("(208)322-8822"));
			assertEquals("Arnold, Mary", addressBook.reverseLookup("(240)944-3347"));
			assertEquals("Wiz, Eric", addressBook.reverseLookup("(701)445-2333"));
			assertEquals("Kaznick, Robert", addressBook.reverseLookup("(615)387-3935"));
			assertEquals("Hanson, Betty", addressBook.reverseLookup("(714)273-3813"));
			assertEquals(null, addressBook.reverseLookup("(801)553-8273"));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			fail("Should not have raised an InvalidKeyException");
		}
		try {
			assertEquals("Arnold, Mary", addressBook.reverseLookup("240)944-3347"));
			fail("Should have raised an InvalidKeyException");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// correct
		}
	}

	@Test
	public void testReadFile() {
		newFile = new File("hashTableTest");
		try {
			output = new PrintWriter(newFile);
			output.println("John Smith (208)322-8822 ID");
			output.println("Mary Arnold (240)944-3347 VA");
			output.println("Eric Wiz (701)445-2333 NY");
			output.println("Robert Kaznick (615)387-3935 TN");
			output.println("Betty Hanson (714)273-3813 CA");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addressBook = null;
		addressBook = new AddressBookUtility();
		addressBook.readFile(newFile.getAbsoluteFile());
		assertEquals(true, addressBook.contains(p1));
		assertEquals(true, addressBook.contains(p2));
		assertEquals(true, addressBook.contains(p3));
		assertEquals(true, addressBook.contains(p4));
		assertEquals(true, addressBook.contains(p5));
		assertEquals(false, addressBook.contains(p6));
	}

	@Test
	public void testAdd() {
			try {
				assertEquals(true, addressBook.contains("(208)322-8822"));
				assertEquals(true, addressBook.contains("(240)944-3347"));
				assertEquals(true, addressBook.contains("(701)445-2333"));
				assertEquals(true, addressBook.contains("(615)387-3935"));
				assertEquals(true, addressBook.contains("(714)273-3813"));
				assertEquals(false, addressBook.contains("(801)553-8273"));
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				fail("Should have not raised an InvalidKeyException");
			}
			try {
				addressBook.add("Steve", "Monty", "(801)553-8273", "UT");
				assertEquals(true, addressBook.contains("(208)322-8822"));
				assertEquals(true, addressBook.contains("(240)944-3347"));
				assertEquals(true, addressBook.contains("(701)445-2333"));
				assertEquals(true, addressBook.contains("(615)387-3935"));
				assertEquals(true, addressBook.contains("(714)273-3813"));
				assertEquals(true, addressBook.contains("(801)553-8273"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("Should not have raised an exception");
			} 
			
			try {
				addressBook.add("Steve", "Monty", "801553-8273", "UT");
				fail("Should have raised an InvalidKeyException");
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				// correct
			} catch (KeyInUseException e) {
				// TODO Auto-generated catch block
				fail("Should have raised an InvalidKeyException");
			}
			
			try {
				addressBook.add("Steve", "Monty", "(714)273-3813", "UT");
				fail("Should have raised an KeyInUseException");
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				fail("Should have raised an KeyInUseException");
			} catch (KeyInUseException e) {
				// TODO Auto-generated catch block
				// correct
			}
	}

	@Test
	public void testAddSTUDENT() {
        try {
            assertEquals(true, myAddressBook.contains("(123)456-7890"));
            assertEquals(true, myAddressBook.contains("(321)456-7890"));
            assertEquals(true, myAddressBook.contains("(123)654-7890"));
            assertEquals(true, myAddressBook.contains("(321)654-7890"));
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            fail("Should have not raised an InvalidKeyException");
        }

        try {
            myAddressBook.add("Burnie", "Burns", "(929)199-0193", "TX");
            assertEquals(true, myAddressBook.contains("(929)199-0193"));
            assertEquals(true, myAddressBook.contains("(123)456-7890"));
            assertEquals(true, myAddressBook.contains("(321)456-7890"));
            assertEquals(true, myAddressBook.contains("(123)654-7890"));
            assertEquals(true, myAddressBook.contains("(321)654-7890"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            fail("Should not have raised an exception");
        }

        try {
            myAddressBook.add("Burnie", "Burns", "(9291990193", "TX");
            fail("Should have raised an InvalidKeyException");
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            // correct
        } catch (KeyInUseException e) {
            // TODO Auto-generated catch block
            fail("Should have raised an InvalidKeyException");
        }

        try {
            myAddressBook.add("Gavin", "Free", "(929)199-0193", "TX");
            fail("Should have raised an KeyInUseException");
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            fail("Should have raised an KeyInUseException");
        } catch (KeyInUseException e) {
            // TODO Auto-generated catch block
            // correct
        }
	}
	
	@Test
	public void testIsValidKey() {
		try {
			assertEquals(true, addressBook.isValidKey("(208)322-8822"));
			assertEquals(true, addressBook.isValidKey("(240)944-3347"));
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			fail("Should have not raised an InvalidKeyException");
		}
		
		try {
			assertEquals(true, addressBook.isValidKey("701)445-2333"));
			fail("Should have raised an InvalidKeyException");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// correct
		}
		try {
			assertEquals(true, addressBook.contains("(615)3873935"));
			fail("Should have raised an InvalidKeyException");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// correct
		}
		try {
			assertEquals(true, addressBook.contains("7142733813"));
			fail("Should have raised an InvalidKeyException");
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			// correct
		}
	}
	
	@Test
	public void testIsValidKeySTUDENT() {
        try {
            assertEquals(true, myAddressBook.isValidKey("(123)456-7890"));
            assertEquals(true, myAddressBook.isValidKey("(987)654-3210"));
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            fail("Should have not raised an InvalidKeyException");
        }
	}
	
	@Test
	public void testWriteToFile() {
		// The following assures that the read To File works correctly
		newFile = new File("hashTableTest");
		try {
			output = new PrintWriter(newFile);
			output.println("John Smith (208)322-8822 ID");
			output.println("Mary Arnold (240)944-3347 VA");
			output.println("Eric Wiz (701)445-2333 NY");
			output.println("Robert Kaznick (615)387-3935 TN");
			output.println("Betty Hanson (714)273-3813 CA");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addressBook = null;
		addressBook = new AddressBookUtility();
		addressBook.readFile(newFile.getAbsoluteFile());
		if(addressBook.contains(p1) && addressBook.contains(p2) && addressBook.contains(p3) && addressBook.contains(p4) && addressBook.contains(p5))
		{
			// we know that the readFile is working correctly
			// now test the writeToFile
			outFile = new File("hashTableWriteTest");
			addressBook.writeToFile(outFile);
			addressBook = null;
			addressBook = new AddressBookUtility();
			addressBook.readFile(outFile.getAbsoluteFile());
			
			assertEquals(true, addressBook.contains(p1));
			assertEquals(true, addressBook.contains(p2));
			assertEquals(true, addressBook.contains(p3));
			assertEquals(true, addressBook.contains(p4));
			assertEquals(true, addressBook.contains(p5));
			assertEquals(false, addressBook.contains(p6));
			
		}
		
	}

}
