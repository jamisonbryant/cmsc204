package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jmeyers.PersonInterface;

/**
 * Person Model
 * <p>
 * The primary data element of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) - CMSC 204 M/W 1:00 PM - 2:30 PM (R. Alexander)
 */
public class Person implements PersonInterface
{
    /**
     * First name (single word)
     */
    private String fname;

    /**
     * Last name (single word)
     */
    private String lname;

    /**
     * Phone number (format: (XXX)XXX-XXXX where X is a number)
     */
    private String phone;

    /**
     * Address
     */
    private String address;

    /**
     * Creates a new Person
     *
     * @param f First name
     * @param l Last name
     * @param p Phone number
     * @param a Street address
     */
    public Person(String f, String l, String p, String a)
    {
        fname = f;
        lname = l;
        phone = p;
        address = a;
    }

    /**
     * Returns this Person's first name
     *
     * @return First name
     */
    @Override
    public String getFname()
    {
        return fname;
    }

    /**
     * Returns this Person's last name
     *
     * @return Last name
     */
    @Override
    public String getLname()
    {
        return lname;
    }

    /**
     * Returns this Person's phone number
     *
     * @return Phone number
     */
    @Override
    public String getPhone()
    {
        return phone;
    }

    /**
     * Returns this Person's address
     *
     * @return Address
     */
    @Override
    public String getAddress()
    {
        return address;
    }

    /**
     * Returns this Person's key
     *
     * @return Key
     */
    @Override
    public String getKey()
    {
        return phone;
    }

    /**
     * Returns if a given Person is identical to the current Person
     * @param p Person object
     * @return True if Persons are identical, false otherwise
     */
    @Override
    public boolean equals(PersonInterface p)
    {
        return (p.getFname().equals(fname) && p.getLname().equals(lname) && p.getPhone().equals(phone) &&
                p.getAddress().equals(address));
    }

    /**
     * Calculates and returns the hash code for this Person
     *
     * @return Hash code
     */
    public int hashCode()
    {
        int p1 = 23;
        int p2 = 31;
        int areaCode = 0;
        int exchangeCode = 0;
        int extensionCode = 0;

        // Parse phone number
        if (phone.length() == 13) {
            areaCode = Integer.parseInt(phone.substring(1, 4));
            exchangeCode = Integer.parseInt(phone.substring(5, 8));
            extensionCode = Integer.parseInt(phone.substring(9));
        } else {
            System.err.println("Invalid phone number " + phone + ". Expecting format (XXX)XXX-XXXX");
        }

        // Calculate hash code
        return Math.abs(p1 * (areaCode + p2 * exchangeCode) + extensionCode);
    }

    public String toString()
    {
        return fname + " " + lname + " " + phone + " " + address;
    }
}
