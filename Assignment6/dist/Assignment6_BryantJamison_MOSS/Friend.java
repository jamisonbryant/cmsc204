package edu.montgomerycollege.cmsc204.jbryant;

/**
 * Represents a vertex of the friends graph.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for R. Alexander's CMSC 204 (M/W 1PM-2:40PM)
 */
public class Friend implements Comparable<Friend>
{
    /**
     * First name of friend
     */
    private String fname;

    /**
     * Last name of friend
     */
    private String lname;

    /**
     * Hometown of friend
     */
    private String hometown;

    /**
     * Creates a new friend
     *
     * @param profile Profile of friend to create
     */
    public Friend(String profile)
    {
        String[] details = profile.split(" of ");
        String[] names = details[0].split(" ");
        fname = names[0];
        lname = names[1];
        hometown = details[1];
    }

    /**
     * Creates a new friend
     *
     * @param fname First name of friend to create
     * @param lname Last name of friend to create
     * @param hometown Hometown of friend to create
     */
    public Friend(String fname, String lname, String hometown)
    {
        this.fname = fname;
        this.lname = lname;
        this.hometown = hometown;
    }

    public String getFname() { return fname; }

    public String getLname() { return lname; }

    public String getHometown() { return hometown; }

    /**
     * Converts a friend object to a string (profile)
     *
     * @return Profile of friend
     */
    @Override
    public String toString() { return fname + " " + lname + " of " + hometown; }

    /**
     * Checks if this object equals another object
     *
     * @param object Friend object to check against
     * @return Result of check
     */
    @Override
    public boolean equals(Object object)
    {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Friend friend = (Friend) object;

        if (fname != null ? !fname.equals(friend.fname) : friend.fname != null) return false;
        if (lname != null ? !lname.equals(friend.lname) : friend.lname != null) return false;
        return !(hometown != null ? !hometown.equals(friend.hometown) : friend.hometown != null);
    }

    /**
     * Compares this object against another friend object
     *
     * @param friend Friend to compare against
     * @return Result of comparison
     */
    @Override
    public int compareTo(Friend friend)
    {
        return lname.compareTo(friend.getLname());
//        return fname.compareTo(f.getFname());
    }
}
