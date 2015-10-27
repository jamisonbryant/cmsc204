package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jmeyers.HashTableInterface;
import edu.montgomerycollege.cmsc204.jmeyers.PersonInterface;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hash Table Class
 *
 * The data structure of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class HashTable implements HashTableInterface
{
    HashMap<Integer, LinkedList<Person>> persons = new HashMap<Integer, LinkedList<Person>>();

    @Override
    public int add(PersonInterface p)
    {
        // Check if hash map contains hash code
        int hashCode = p.hashCode();
        if (persons.containsKey(hashCode)) {
//            // Add person to existing bucket in hash map
//            LinkedList<Person> l = persons.get(hashCode);
//            l.add((Person) p);

            // Return negative number to indicate failure
            return -1;
        } else {
            // Add person to new bucket in hash map
            LinkedList<Person> l = new LinkedList<Person>();
            l.add((Person) p);
            persons.put(hashCode, l);
        }

        return 0;
    }

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

    @Override
    public boolean contains(String key)
    {
        // Iterate over hash map
        Iterator i = persons.entrySet().iterator();
        while (i.hasNext()) {
            Map.Entry pair = (Map.Entry) i.next();
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

    @Override
    public ArrayList sort()
    {
        return null;
    }

    private boolean isValidKey(String key) throws InvalidKeyException
    {
        // Check if key is valid
        Pattern p = Pattern.compile("\\((\\d{3})\\)(\\d{3})-(\\d{4})");
        Matcher m = p.matcher(key);
        return m.find();
    }
}
