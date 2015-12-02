package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.DataManagerInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Defines methods for adding to and accessing to the friends graph.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for R. Alexander's CMSC 204 (M/W 1PM-2:40PM)
 */
public class DataManager implements DataManagerInterface
{
    /**
     * Graph object to manipulate
     */
    private FriendGraph graph;

    /**
     * Creates new Data Manager object
     */
    public DataManager()
    {
        // Initialize graph
        graph = new FriendGraph();
    }

    /**
     * Returns a list of friends of friends of a given friend
     *
     * @param profile The profile of the friend to find friends of friends of
     * @return List of friends of friends
     */
    @Override
    public ArrayList<String> friendsOfFriends(String profile)
    {
        String[] parts = profile.split(" of ");
        String[] names = parts[0].split(" ");

        return friendsOfFriends(names[0], names[1], parts[1]);
    }

    /**
     * Returns a list of friends of friends of a given friend
     *
     * @param fname First name of friend to find friends of friends of
     * @param lname Last name of friend to find friends of friends of
     * @param hometown Hometown of friend to find friends of friends of
     * @return List of friends of friends
     */
    @Override
    public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown)
    {
        Friend me = new Friend(fname, lname, hometown);
        ArrayList<String> myFriends = listFriends(me);
        ArrayList<String> myFriendsOfFriends = new ArrayList<String>();

        for (String myFriend : myFriends) {
            ArrayList<String> myFriendsFriends = listFriends(myFriend);

            for (String myFriendsFriend : myFriendsFriends) {
                if (!myFriendsFriend.equals(me.toString())) {
                    if (!myFriends.contains(myFriendsFriend) && !myFriendsOfFriends.contains(myFriendsFriend)) {
                        if (myFriendsOfFriends.isEmpty()) {
                            myFriendsOfFriends.add(myFriendsFriend);
                        } else {
                            for (int i = 0; i < myFriendsOfFriends.size(); i++) {
                                if (myFriendsOfFriends.get(i).compareTo(myFriendsFriend) > 0) {
                                    myFriendsOfFriends.add(i, myFriendsFriend);
                                    break;
                                }
                            }

                            if (!myFriendsOfFriends.contains(myFriendsFriend)) {
                                myFriendsOfFriends.add(myFriendsFriend);
                            }
                        }
                    }
                }
            }
        }

        return myFriendsOfFriends;
    }

    /**
     * Returns a list of friends of a given friend
     *
     * @param profile Profile of the friend to list friends of
     * @return List of friends
     */
    @Override
    public ArrayList<String> listFriends(String profile)
    {
        Friend friend = null;
        
        for (Friend f : graph.vertexSet()) {
            if (f.toString().equals(profile)) {
                friend = f;
                break;
            }
        }

        TreeSet<Edge<Friend, Friend>> h = (TreeSet<Edge<Friend, Friend>>) graph.edgesOf(friend);
        ArrayList<String> a = new ArrayList<String>();

        for (Edge<Friend, Friend> e : h) {
            if (e.getFriend1().equals(friend)) {
                if (!a.contains(e.getFriend2().toString())) {
                    a.add(e.getFriend2().toString());
                }
            } else if (e.getFriend2().equals(friend)) {
                if (!a.contains(e.getFriend1().toString())) {
                    a.add(e.getFriend1().toString());
                }
            }
        }

        return sortFriends(a);
    }

    /**
     * Returns a list of friends of a given friend
     *
     * @param fname First name of friend to list friends of
     * @param lname Last name of friend to list friends of
     * @param hometown Hometown of friend to list friends of
     * @return List of friends
     */
    @Override
    public ArrayList<String> listFriends(String fname, String lname, String hometown)
    {
        return listFriends(fname + " " + lname + " of " + hometown);
    }

    /**
     * Returns a list of friends of a given friend
     *
     * @param friend Friend to list friends of
     * @return List of friends
     */
    @Override
    public ArrayList<String> listFriends(Friend friend)
    {
        return listFriends(friend.toString());
    }

    /**
     * Decomposes and returns an array of a friend's profile data
     *
     * @param profile Profile string to decompose
     * @return Decomposed profile data
     */
    @Override
    public ArrayList<String> getProfile(String profile)
    {
        ArrayList<String> a = new ArrayList<String>();

        for (Friend f : graph.vertexSet()) {
            if (f.toString().equals(profile)) {
                a.add(f.getFname());
                a.add(f.getLname());
                a.add(f.getHometown());

                break;
            }
        }

        return a;
    }

    /**
     * Returns a vector of all participants (vertices) in the friends graph
     *
     * @return Vector of participants
     */
    @Override
    public Vector<String> vectorOfParticipants()
    {
        TreeSet<Friend> t = (TreeSet<Friend>) graph.vertexSet();
        Vector<String> v = new Vector<String>();

        for (Friend f : t) {
            v.add(f.toString());
        }

        return v;
    }

    /**
     * Adds a participant (vertex) to the friends graph
     *
     * @param fname First name of participant to add
     * @param lname Last name of participant to add
     * @param hometown hometown of participant to add
     */
    @Override
    public void addParticipant(String fname, String lname, String hometown)
    {
        graph.addVertex(new Friend(fname, lname, hometown));
    }

    /**
     * Adds a friendship (edge) to the friends graph
     *
     * @param friend1 Existing friend in graph
     * @param friend2 New friend of existing friend
     */
    @Override
    public void addFriend(String friend1, String friend2)
    {
        for (Friend f1 : graph.vertexSet()) {
            if (f1.toString().equals(friend1)) {
                graph.addEdge(f1, new Friend(friend2));
            }
        }
    }

    /**
     * Adds a friendship (edge) to the friends graph
     *
     * @param fname1 First name of existing friend
     * @param lname1 Last name of existing friend
     * @param hometown1 Hometown of existing friend
     * @param fname2 First name of new friend
     * @param lname2 Last name of new friend
     * @param hometown2 Hometown of new friend
     */
    @Override
    public void addFriend(String fname1, String lname1, String hometown1, String fname2, String lname2, String hometown2)
    {
        Friend f1 = new Friend(fname1, lname1, hometown1);
        Friend f2 = new Friend(fname2, lname2, hometown2);
        graph.addEdge(f1, f2);
    }

    /**
     * Reads a participants data file and populates the friends graph with its contents
     *
     * @param file File to read
     * @throws FileNotFoundException If the file does not exist
     */
    @Override
    public void populateParticipants(File file) throws FileNotFoundException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] details = line.split(":");

                if (details.length == 3) {
                    addParticipant(details[0], details[1], details[2]);
                } else {
                    System.err.println("Warning: Malformed participants file. Incorrect number of data elements.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads a friends file and populates the friends graph with its contents
     *
     * @param file File to read
     * @throws FileNotFoundException If the file does not exist
     */
    @Override
    public void populateFriends(File file) throws FileNotFoundException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] details = line.split(":");
                int friendCount = Integer.parseInt(details[2]);

                if (details.length == 3 + (friendCount * 2)) {
                    String fname = details[0];
                    String lname = details[1];
                    String hometown = graph.findHometown(fname, lname);

                    for (int i = 0; i < friendCount; i++) {
                        String friendFname = details[3 + (2 * i)];
                        String friendLname = details[4 + (2 * i)];
                        String friendHometown = graph.findHometown(friendFname, friendLname);

                        addFriend(fname, lname, hometown, friendFname, friendLname, friendHometown);
                    }
                } else {
                    System.err.println("Warning: Malformed friends file. Incorrect number of data elements.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sorts a list of friends using the <code>compareTo</code> method of the <code>Friend</code> class
     *
     * @param list List to sort
     * @return Sorted list
     */
    private ArrayList<String> sortFriends(ArrayList<String> list)
    {
        ArrayList<String> sortedFriends = new ArrayList<String>();
        sortedFriends.add(list.get(0));

        for (String friend : list) {
            for (int i = 0; i < sortedFriends.size(); i++) {
                if (sortedFriends.get(i).compareTo(friend) > 0) {
                    sortedFriends.add(i, friend);
                    break;
                }
            }

            if (!sortedFriends.contains(friend)) {
                sortedFriends.add(friend);
            }
        }

        return sortedFriends;
    }
}
