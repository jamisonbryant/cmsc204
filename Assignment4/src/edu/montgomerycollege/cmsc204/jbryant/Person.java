package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jmeyers.PersonInterface;

/**
 * Person Model
 *
 * The primary data element of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class Person implements PersonInterface
{
    private String fname;
    private String lname;
    private String phone;
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

    @Override
    public String getFname()
    {
        return fname;
    }

    @Override
    public String getLname()
    {
        return lname;
    }

    @Override
    public String getPhone()
    {
        return phone;
    }

    @Override
    public String getAddress()
    {
        return address;
    }

    @Override
    public boolean equals(PersonInterface p)
    {
        return false;
    }

    @Override
    public String getKey()
    {
        return null;
    }

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
            exchangeCode = Integer.parseInt(phone.substring(6, 9));
            extensionCode = Integer.parseInt(phone.substring(8));
        } else {
            System.err.println("Invalid phone number " + phone + ". Expecting format (XXX)XXX-XXXX");
        }

        // Calculate hash code
        return Math.abs(p1 * (areaCode + p2 * exchangeCode) + extensionCode);
    }
}
