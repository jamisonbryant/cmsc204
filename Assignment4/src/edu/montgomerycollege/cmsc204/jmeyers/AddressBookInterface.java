package edu.montgomerycollege.cmsc204.jmeyers;

import java.io.File;
import java.util.ArrayList;
/** 
 * Contains methods of the  LookUp Utility.
 * Data Manager
 * 
 * Data is stored in a hash table
 * 
 * Methods for add, contains, isValidKey, reverseLookup, readFile, writeToFile
 * 
 * @author Professor Myers
 *
 */

public interface AddressBookInterface <PersonInterface> {
	/**
	 * check if a Person is in the hashtable.  Uses getKey() to get
	 * the key (phone number) for the Person object
	 * @param p Person object
	 * @return true if Person is in the hashtable or false if not
	 */
	public boolean contains(PersonInterface p);

	/**
	 * check if a Person is in the hashtable by key (phone number)
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param key the key for a Person object
	 * @return true if key for Person is in the hashtable or false if not
	 * @throws InvalidKeyException if key is invalid
	 */
	public boolean contains(String key) throws InvalidKeyException;
	
	/**
	 * Checks that the String s is a valid key for a Person object
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param s String to be checked if is a valid key
	 * @return true if the String s is in the form (XXX)XXX-XXXX, where X is a digit, false if not
	 * @throws InvalidKeyException if key is invalid
	 */
	public boolean isValidKey(String s) throws InvalidKeyException;
	
	/**
	 * Return the Person object based on the key (phone number)
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param key the key for a Person object (phone number)
	 * @return the string "lastname, firstname" of the corresponding person object, null if key not in hashtable
	 * @throws InvalidKeyException if key is invalid
	 */
	public String reverseLookup(String key) throws InvalidKeyException;
	
	
	/**
	 * create hash table of contents in the file
	 * contents of file must be in this format:
	 * Firstname[space]lastname[space]phoneNumber[space]address\n
	 * phoneNumber must be in the form: (XXX)XXX-XXXX where X is a digit
	 * if the phoneNumber is not in the correct format - don't add to the hashtable
	 * if the phoneNumber has already been used - don't add to the hashtable
	 * @param f File
	 * @return true if File is found and Person objects added, returns false if file not found
	 */
	public boolean readFile(File f);

	
	/**
	 * add Person object to hashtable
	 * Valid key is in the form (XXX)XXX-XXXX where X is a digit
	 * @param fName first name
	 * @param lName last name
	 * @param pNumber phone number
	 * @param add address
	 * @throws InvalidKeyException if key is not in proper form
	 * @throws KeyInUseException if the key has already been used
	 */
	public void add(String fName, String lName, String pNumber, String add) throws InvalidKeyException, KeyInUseException;

	/**
	 * write words in hash table to a file
	 * @param f File
	 * @return true if File can be written to and Person objects in hash table are stored in a file, 
	 *      false if file cannot be written to.
	 */
	public boolean writeToFile(File f);

}
