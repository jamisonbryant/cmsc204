package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jmeyers.HashTableInterface;
import edu.montgomerycollege.cmsc204.jmeyers.PersonInterface;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hash Table Class
 * <p>
 * The data structure of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class HashTable implements HashTableInterface
{
    HashMap<Integer, LinkedList<Person>> persons = new HashMap<Integer, LinkedList<Person>>();

    /**
     * Adds a given Person to the data structure
     *
     * @param p Person to add
     * @return 0 if successful, -1 otherwise
     */
    @Override
    public int add(PersonInterface p)
    {
        // Check if hash map contains hash code
        int hashCode = p.hashCode();
        if (persons.containsKey(hashCode)) {
//            // Add Person to existing bucket in hash map
//            LinkedList<Person> l = persons.get(hashCode);
//            l.add((Person) p);

            // Return negative number to indicate failure
            return -1;
        } else {
            // Add Person to new bucket in hash map
            LinkedList<Person> l = new LinkedList<Person>();
            l.add((Person) p);
            persons.put(hashCode, l);
        }

        return 0;
    }

    /**
     * Returns if the data structure contains a given Person
     *
     * @param pi Person to search for
     * @return True if Person found, false otherwise
     */
    @Override
    public boolean contains(PersonInterface pi)
    {
        // Iterate over hash map
        for (Object o : persons.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            LinkedList<Person> l = (LinkedList<Person>) pair.getValue();

            // Iterate over current bucket
            for (Person p : l) {
                if (p.equals(pi)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns the Person associated with a given key
     *
     * @param key Key of Person to fetch
     * @return Person object if found, null otherwise
     */
    @Override
    public PersonInterface getValue(String key)
    {
        // Iterate over hash map
        for (Object o : persons.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            LinkedList<Person> l = (LinkedList<Person>) pair.getValue();

            // Iterate over current bucket
            for (Person p : l) {
                if (p.getKey().equals(key)) {
                    return p;
                }
            }
        }

        return null;
    }

    /**
     * Returns if the data structure contains a given key
     *
     * @param key The key of a Person object
     * @return True if key found, false otherwise
     */
    @Override
    public boolean contains(String key)
    {
        // Iterate over hash map
        for (Object o : persons.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            LinkedList<Person> l = (LinkedList<Person>) pair.getValue();

            // Iterate over current bucket
            for (Person p : l) {
                if (p.getKey().equals(key)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns a sorted ArrayList of the Persons in the data structure
     *
     * @return Sorted ArrayList of Persons
     */
    @Override
    public ArrayList<String> sort()
    {
        // Get dump of hash table
        ArrayList<String> a = new ArrayList<String>();

        for (Object o : persons.entrySet()) {
            Map.Entry pair = (Map.Entry) o;
            LinkedList<Person> l = (LinkedList<Person>) pair.getValue();

            // Iterate over current bucket
            for (Person p : l) {
                a.add(p.toString());
            }
        }

        // Sort dump
        Collections.sort(a);

        return a;
    }
}
