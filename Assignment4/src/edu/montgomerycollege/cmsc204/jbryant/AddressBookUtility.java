package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jbryant.error.KeyInUseException;
import edu.montgomerycollege.cmsc204.jmeyers.AddressBookInterface;

import javax.swing.*;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Address Book Utility Class
 * <p>
 * The data manager of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) - CMSC 204 M/W 1:00 PM - 2:30 PM (R. Alexander)
 */
public class AddressBookUtility implements AddressBookInterface
{
    /**
     * Data structure
     */
    private HashTable index;

    public AddressBookUtility()
    {
        index = new HashTable();
    }

    /**
     * Returns if the address book contains a given Person
     *
     * @param p Person object
     * @return True if address book contains Person, false otherwise
     */
    @Override
    public boolean contains(Object p)
    {
        // Return if index contains Person
        return index.contains((Person) p);
    }

    /**
     * Returns if the address book contains a given key
     *
     * @param key Key of Person object
     * @return True if address book contains key, false otherwise
     * @throws InvalidKeyException If given key is invalid
     */
    @Override
    public boolean contains(String key) throws InvalidKeyException
    {
        // Check if key is valid
        if (isValidKey(key)) {
            // Return if index contains key
            return index.contains(key);
        } else {
            // Throw exception
            throw new InvalidKeyException();
        }
    }

    /**
     * Returns if a given key is valid
     *
     * @param key Key to validate
     * @return True if key is valid, throws exception otherwise
     * @throws InvalidKeyException If given key is invalid
     */
    @Override
    public boolean isValidKey(String key) throws InvalidKeyException
    {
        // Check if key is valid
        Pattern p = Pattern.compile("\\(\\d{3}\\)\\d{3}-\\d{4}");
        Matcher m = p.matcher(key);

        if (m.find()) {
            // Return true
            return true;
        } else {
            // Throw exception
            throw new InvalidKeyException();
        }
    }

    /**
     * Searches for a given Person in the address book and (if found) returns the Person's name
     *
     * @param key Key for a Person object
     * @return Name (last, first) if found, null otherwise
     * @throws InvalidKeyException If the given key is invalid
     */
    @Override
    public String reverseLookup(String key) throws InvalidKeyException
    {
        if (isValidKey(key)) {
            if (index.contains(key)) {
                Person p = (Person) index.getValue(key);
                return p.getLname() + ", " + p.getFname();
            }
        } else {
            throw new InvalidKeyException();
        }

        return null;
    }

    /**
     * Reads a given file of Persons into the address book
     *
     * @param f File to read from
     * @return True if successful
     */
    @Override
    public boolean readFile(File f) {
        // Read file line by line
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            for (String line; (line = r.readLine()) != null;) {
                // Parse currently read line
                String[] deets = line.split(" ");
                Person p = new Person(deets[0], deets[1], deets[2], deets[3]);

                // Add Person to index
                index.add(p);
            }
        } catch (IOException e) {
            // Print exception to console
            System.err.println(e.getMessage());

            // Display error message
            JOptionPane.showMessageDialog(null, "The application has encountered an error and needs to close.",
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);

            // Exit application
            System.exit(1);
        }

        return true;
    }

    /**
     * Adds a new Person with given information to the address book
     *
     * @param f Person's first name
     * @param l Person's last name
     * @param p Person's phone number
     * @param a Person's address
     * @throws InvalidKeyException If the created Person's key is invalid
     * @throws KeyInUseException If the created Person's key is the same as an existing Person's key
     */
    @Override
    public void add(String f, String l, String p, String a) throws InvalidKeyException, KeyInUseException
    {
        // Create new Person object
        Person person = new Person(f, l, p, a);

        // Check if key is valid
        if (isValidKey(person.getKey())) {
            // Add Person to index
            int result = index.add(person);

            // If unsuccessful, throw exception
            if (result == -1) {
                throw new KeyInUseException();
            }
        } else {
            throw new InvalidKeyException();
        }
    }

    /**
     * Writes the Persons in the address book to a file
     *
     * @param f File to write to
     * @return True if successful
     */
    @Override
    public boolean writeToFile(File f)
    {
        try (PrintWriter pw = new PrintWriter(f.getAbsoluteFile(), "UTF-8")) {
            for (String s : index.sort()) {
                pw.write(s + "\n");
            }
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            // Print exception to console
            System.err.println(e.getMessage());

            // Display error message
            JOptionPane.showMessageDialog(null, "The application has encountered an error and needs to close.",
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);

            // Exit application
            System.exit(1);
        }

        return true;
    }
}
