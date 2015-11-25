package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.GraphInterface;

import java.util.*;

/**
 * Friend Graph Class
 */
public class FriendGraph implements GraphInterface<Friend, Edge<Friend, Friend>>
{
    private ArrayList<Friend> friends;
    private ArrayList<Edge<Friend, Friend>> edges;

    public FriendGraph()
    {
        friends = new ArrayList<Friend>();
        edges = new ArrayList<Edge<Friend, Friend>>();
    }

    @Override
    public Edge<Friend, Friend> getEdge(Friend friend1, Friend friend2)
    {
        for (Edge<Friend, Friend> e : edges) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                return e;
            }
        }

        return null;
    }

    @Override
    public Edge<Friend, Friend> addEdge(Friend friend1, Friend friend2)
    {
        // Create and return edge
        Edge<Friend, Friend> e = new Edge<Friend, Friend>(friend1, friend2);
        edges.add(e);
        return e;
    }

    @Override
    public boolean addVertex(Friend friend)
    {
        if (friends.size() == 0) {
            friends.add(friend);
        } else {
            for (int i = 0; i < friends.size(); i++) {
                if (friends.get(i).compareTo(friend) > 0) {
                    friends.add(i, friend);
                    return true;
                }
            }

            friends.add(friend);
        }

        return true;
    }

    @Override
    public boolean containsEdge(Friend friend1, Friend friend2)
    {
        for (Edge<Friend, Friend> e : edges) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsVertex(Friend friend)
    {
        return friends.contains(friend);
    }

    @Override
    public Set<Edge<Friend, Friend>> edgeSet()
    {
        return new TreeSet<Edge<Friend, Friend>>(edges);
    }

    @Override
    public Set<Edge<Friend, Friend>> edgesOf(Friend friend)
    {
        TreeSet<Edge<Friend, Friend>> h = new TreeSet<Edge<Friend, Friend>>();

        for (Edge<Friend, Friend> e : edges) {
            Friend f1 = e.getFriend1();
            Friend f2 = e.getFriend2();

            if (f1.equals(friend) || f2.equals(friend)) {
                h.add(e);
            }
        }

        return h;
    }

    @Override
    public Edge<Friend, Friend> removeEdge(Friend friend1, Friend friend2)
    {
        // Update edges list
        for (Edge<Friend, Friend> e : edges) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                edges.remove(e);
                return e;
            }
        }

        return null;
    }

    @Override
    public boolean removeVertex(Friend friend)
    {
        friends.remove(friend);
        return true;
    }

    @Override
    public Set<Friend> vertexSet()
    {
        return new TreeSet<Friend>(friends);
    }

    public String findHometown(String fname, String lname)
    {
        for (Friend f : friends) {
            if (f.getFname().equals(fname) && f.getLname().equals(lname)) {
                return f.getHometown();
            }
        }

        return null;
    }
}
