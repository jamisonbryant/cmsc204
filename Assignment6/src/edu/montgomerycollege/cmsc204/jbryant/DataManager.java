package edu.montgomerycollege.cmsc204.jbryant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Jamison on 11/16/2015.
 */
public class DataManager implements edu.montgomerycollege.cmsc204.jkartchner.DataManagerInterface
{
    @Override
    public ArrayList<String> friendsOfFriends(String name)
    {
        return null;
    }

    @Override
    public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown)
    {
        return null;
    }

    @Override
    public ArrayList<String> listFriends(String name)
    {
        return null;
    }

    @Override
    public ArrayList<String> listFriends(String fname, String lname, String hometown)
    {
        return null;
    }

    @Override
    public ArrayList<String> listFriends(Friend f)
    {
        return null;
    }

    @Override
    public ArrayList<String> getProfile(String name)
    {
        return null;
    }

    @Override
    public Vector<String> vectorOfParticipants()
    {
        return null;
    }

    @Override
    public void addParticipant(String fName, String lName, String city)
    {

    }

    @Override
    public void addFriend(String profile, String newFriend)
    {

    }

    @Override
    public void addFriend(String profileFname, String profileLname, String profileHometown, String newFriendFname, String newFriendLName, String newFriendHometown)
    {

    }

    @Override
    public void populateParticipants(File participantsFile) throws FileNotFoundException
    {

    }

    @Override
    public void populateFriends(File friendsFile) throws FileNotFoundException
    {

    }
}
