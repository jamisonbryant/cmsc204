package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jbryant.error.KeyInUseException;
import edu.montgomerycollege.cmsc204.jmeyers.AddressBookInterface;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Address Book Utility Class
 * <p>
 * The data manager of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class AddressBookUtility implements AddressBookInterface
{
    private HashTable index;

    public AddressBookUtility()
    {
        index = new HashTable();
    }

    @Override
    public boolean contains(Object p)
    {
        // Return if index contains person
        return index.contains((Person) p);
    }

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

    @Override
    public boolean isValidKey(String key) throws InvalidKeyException
    {
        // Check if key is valid
        Pattern p = Pattern.compile("\\((\\d{3})\\)(\\d{3})-(\\d{4})");
        Matcher m = p.matcher(key);

        if (m.find()) {
            // Return true
            return true;
        } else {
            // Throw exception
            throw new InvalidKeyException();
        }
    }

    @Override
    public String reverseLookup(String key) throws InvalidKeyException
    {
        if (index.contains(key)) {
            Person p = (Person) index.getValue(key);
            return p.getLname() + ", " + p.getFname();
        }

        return null;
    }

    @Override
    public boolean readFile(File f) {
        // Read file line by line
        try (BufferedReader r = new BufferedReader(new FileReader(f))) {
            for (String line; (line = r.readLine()) != null;) {
                // Parse currently read line
                String[] deets = line.split(" ");
                Person p = new Person(deets[0], deets[1], deets[2], deets[3]);

                // Add person to index
                index.add(p);
            }
        } catch (IOException e) {
            // Print exception to console
            System.err.println(e.getMessage());

            // Display error message
            JOptionPane.showMessageDialog(null, "Sorry, the application has encountered an error and needs to close.",
                    "Fatal Error", JOptionPane.ERROR_MESSAGE);

            // Exit application
            System.exit(1);
        }

        return true;
    }

    @Override
    public void add(String f, String l, String p, String a) throws InvalidKeyException, KeyInUseException
    {
        // Create new Person object
        Person person = new Person(f, l, p, a);

        // Check if key is valid
        if (isValidKey(person.getKey())) {
            // Add person to index
            int result = index.add(person);

            // If unsuccessful, throw exception
            if (result == -1) {
                throw new KeyInUseException();
            }
        } else {
            throw new InvalidKeyException();
        }
    }

    @Override
    public boolean writeToFile(File f) {
        return false;
    }
}
