package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jbryant.error.KeyInUseException;
import edu.montgomerycollege.cmsc204.jmeyers.AddressBookInterface;

import java.io.File;

/**
 * Address Book Utility Class
 *
 * The data manager of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class AddressBookUtility implements AddressBookInterface
{
    private HashTable index;

    @Override
    public boolean contains(Object p) {
        return false;
    }

    @Override
    public boolean contains(String key) throws InvalidKeyException {
        return false;
    }

    @Override
    public boolean isValidKey(String s) throws InvalidKeyException {
        return false;
    }

    @Override
    public String reverseLookup(String key) throws InvalidKeyException {
        return null;
    }

    @Override
    public boolean readFile(File f) {
        return false;
    }

    @Override
    public void add(String fName, String lName, String pNumber, String add) throws InvalidKeyException, KeyInUseException {

    }

    @Override
    public boolean writeToFile(File f) {
        return false;
    }
}
