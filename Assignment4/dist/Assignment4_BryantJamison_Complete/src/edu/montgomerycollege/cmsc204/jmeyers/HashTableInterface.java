package edu.montgomerycollege.cmsc204.jmeyers;

import java.util.ArrayList;

/** methods for a HashTable of Person objects.  
 * It is implemented as an array of LinkedLists
 * STUDENT - 
 *      1.  provide constructor that takes in an int for the number of Person objects to hold
 *      2.  To choose size of your Hash Table (number of buckets):
 *          The first 4K+3 number that is larger than (number of Person objects / 3)
 * 
 * @author Professor Myers
 *
 */
public interface HashTableInterface<T extends PersonInterface> {

	/**
	 * Adds an element to this set.  Adds to the appropriate LinkedList by taking the Person HashCode()
	 * and mod (%) by the table size.
	 * @param p a Person to add to the HashTable
	 * @return the number of People currently in HashTable
	 */
	public int add(T p);
	
	/**
	 * Tests to see if the Person is in the HashTable.
	 * @param p a Person Object
	 * @return true if Person is in the HashTable, false if not
	 */
	public boolean contains(T p);
	
	/**
	 * Returns the Person object that corresponds to the key
	 * @param key the key (phone number) for the Person object
	 * @return the Person object if the key is in the hash table, null if not
	 */
	public T getValue(String key);
	
	/**
	 * Tests to see if a Person is in the HashTable based on the key
	 * @param key a Person Object key (phone number)
	 * @return true if Person is in the HashTable, false if not
	 */
	public boolean contains(String key);
	
	/**
	 * Puts the People in the hashtable in a sorted ArrayList, sorted by key (phone number)
	 * Use Collections.sort with a Comparator that sorts by phone number
	 * @return ArrayList of People in hashtable in sorted order by key (phone number)
	 */
	public ArrayList<T> sort();
}
