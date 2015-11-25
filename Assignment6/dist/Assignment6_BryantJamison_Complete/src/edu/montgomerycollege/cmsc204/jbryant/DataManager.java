package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.DataManagerInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Vector;

/**
 * Data Manager Class
 *
 * @author Jamison Bryant for R. Alexander's CMSC 204 M/W 1:00PM - 2:40PM
 */
public class DataManager implements DataManagerInterface
{
    private FriendGraph graph;

    public DataManager()
    {
        // Initialize graph
        graph = new FriendGraph();
    }

    @Override
    public ArrayList<String> friendsOfFriends(String profile)
    {
        ArrayList<String> allFriends = new ArrayList<String>();

        for (String s : listFriends(profile)) {
            ArrayList<String> friends = listFriends(s);

            for (String f : friends) {
                if (!allFriends.contains(f)) {
                    allFriends.add(f);
                }
            }
        }

        return allFriends;
    }

    @Override
    public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown)
    {
        Friend friend = new Friend(fname, lname, hometown);
        ArrayList<String> allFriends = new ArrayList<String>();

        for (String s : listFriends(friend.toString())) {
            ArrayList<String> friends = listFriends(s);

            for (String f : friends) {
                if (!allFriends.contains(f)) {
                    allFriends.add(f);
                }
            }
        }

        return allFriends;
    }

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
            } else {
                if (!a.contains(e.getFriend1().toString())) {
                    a.add(e.getFriend1().toString());
                }
            }

        }

        return a;
    }

    @Override
    public ArrayList<String> listFriends(String fname, String lname, String hometown)
    {
        Friend f = new Friend(fname, lname, hometown);
        TreeSet<Edge<Friend, Friend>> h = (TreeSet<Edge<Friend, Friend>>) graph.edgesOf(f);
        ArrayList<String> a = new ArrayList<String>();

        for (Edge<Friend, Friend> e : h) {
            a.add(e.getFriend2().toString());
        }

        return a;
    }

    @Override
    public ArrayList<String> listFriends(Friend f)
    {
        TreeSet<Edge<Friend, Friend>> h = (TreeSet<Edge<Friend, Friend>>) graph.edgesOf(f);
        ArrayList<String> a = new ArrayList<String>();

        for (Edge<Friend, Friend> e : h) {
            a.add(e.getFriend2().toString());
        }

        return a;
    }

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

    @Override
    public void addParticipant(String fname, String lname, String hometown)
    {
        graph.addVertex(new Friend(fname, lname, hometown));
    }

    @Override
    public void addFriend(String friend1, String friend2)
    {
        for (Friend f1 : graph.vertexSet()) {
            if (f1.toString().equals(friend1)) {
                graph.addEdge(f1, new Friend(friend2));
            }
        }
    }

    @Override
    public void addFriend(String fname1, String lname1, String hometown1, String fname2, String lname2, String hometown2)
    {
        Friend f1 = new Friend(fname1, lname1, hometown1);
        Friend f2 = new Friend(fname2, lname2, hometown2);
        graph.addEdge(f1, f2);
    }

    @Override
    public void populateParticipants(File participantsFile) throws FileNotFoundException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(participantsFile))) {
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

    @Override
    public void populateFriends(File friendsFile) throws FileNotFoundException
    {
        try (BufferedReader br = new BufferedReader(new FileReader(friendsFile))) {
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
}
