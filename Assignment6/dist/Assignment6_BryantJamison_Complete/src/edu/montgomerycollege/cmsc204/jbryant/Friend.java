package edu.montgomerycollege.cmsc204.jbryant;

/**
 * Friend Class
 *
 * @author Jamison Bryant for R. Alexander's CMSC 204 M/W 1:00PM - 2:40PM
 */
public class Friend implements Comparable<Friend>
{
    private String fname;
    private String lname;
    private String hometown;

    public Friend(String profile)
    {
        String[] details = profile.split(" of ");
        String[] names = details[0].split(" ");
        fname = names[0];
        lname = names[1];
        hometown = details[1];
    }

    public Friend(String fname, String lname, String hometown)
    {
        this.fname = fname;
        this.lname = lname;
        this.hometown = hometown;
    }

    public String getFname()
    {
        return fname;
    }

    public String getLname()
    {
        return lname;
    }

    public String getHometown()
    {
        return hometown;
    }

    @Override
    public String toString() { return fname + " " + lname + " of " + hometown; }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (fname != null ? !fname.equals(friend.fname) : friend.fname != null) return false;
        if (lname != null ? !lname.equals(friend.lname) : friend.lname != null) return false;
        return !(hometown != null ? !hometown.equals(friend.hometown) : friend.hometown != null);
    }

    @Override
    public int compareTo(Friend f) { return toString().compareTo(f.toString()); }
}
