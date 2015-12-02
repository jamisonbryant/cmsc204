package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jkartchner.GraphInterface;

import java.util.*;

/**
 * Represents a graph of friends (vertices) and connections between friends (friendships).
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for R. Alexander's CMSC 204 (M/W 1PM-2:40PM)
 */
public class FriendGraph implements GraphInterface<Friend, Edge<Friend, Friend>>
{
    /**
     * List of friends (vertices) in graph
     */
    private ArrayList<Friend> friends;

    /**
     * List of friendships (friendships) in graph
     */
    private ArrayList<Edge<Friend, Friend>> friendships;

    /**
     * Creates a new friend graph
     */
    public FriendGraph()
    {
        friends = new ArrayList<Friend>();
        friendships = new ArrayList<Edge<Friend, Friend>>();
    }

    /**
     * Returns an edge between two friends
     *
     * @param friend1 First friend
     * @param friend2 Second friend
     * @return Edge between friends
     */
    @Override
    public Edge<Friend, Friend> getEdge(Friend friend1, Friend friend2)
    {
        for (Edge<Friend, Friend> e : friendships) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                return e;
            }
        }

        return null;
    }

    /**
     * Adds an edge to the friends graph
     *
     * @param friend1 First friend
     * @param friend2 Second friend
     * @return The added edge
     */
    @Override
    public Edge<Friend, Friend> addEdge(Friend friend1, Friend friend2)
    {
        // Create and return edge
        Edge<Friend, Friend> e = new Edge<Friend, Friend>(friend1, friend2);
        friendships.add(e);
        return e;
    }

    /**
     * Adds a vertex to the friends graph
     *
     * @param friend Friend to add
     * @return A boolean because...reasons?
     */
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

    /**
     * Checks if the friends graph contains an edge
     *
     * @param friend1 First friend
     * @param friend2 Second friend
     * @return True if the graph contains the edge, false otherwise
     */
    @Override
    public boolean containsEdge(Friend friend1, Friend friend2)
    {
        for (Edge<Friend, Friend> e : friendships) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the friends graph contains a vertex
     *
     * @param friend Friend to check for
     * @return True if the graph contains the vertex, false otherwise
     */
    @Override
    public boolean containsVertex(Friend friend)
    {
        return friends.contains(friend);
    }

    /**
     * Returns a set of edges in the friends graph
     *
     * @return Set of edges
     */
    @Override
    public Set<Edge<Friend, Friend>> edgeSet()
    {
        return new TreeSet<Edge<Friend, Friend>>(friendships);
    }

    /**
     * Returns a set of the edges of a given friend
     *
     * @param friend Friend to get edges for
     * @return Set of edges
     */
    @Override
    public Set<Edge<Friend, Friend>> edgesOf(Friend friend)
    {
        TreeSet<Edge<Friend, Friend>> h = new TreeSet<Edge<Friend, Friend>>();

        for (Edge<Friend, Friend> e : friendships) {
            Friend f1 = e.getFriend1();
            Friend f2 = e.getFriend2();

            if (f1.equals(friend) || f2.equals(friend)) {
                h.add(e);
            }
        }

        return h;
    }

    /**
     * Removes an edge from the friends graph
     *
     * @param friend1 First friend
     * @param friend2 Second friend
     * @return Removed edge
     */
    @Override
    public Edge<Friend, Friend> removeEdge(Friend friend1, Friend friend2)
    {
        // Update friendships list
        for (Edge<Friend, Friend> e : friendships) {
            if (e.getFriend1().equals(friend1) && e.getFriend2().equals(friend2)) {
                friendships.remove(e);
                return e;
            }
        }

        return null;
    }

    /**
     * Removes a friend from the friend graph
     *
     * @param friend Friend to remove
     * @return A boolean because...reasons?
     */
    @Override
    public boolean removeVertex(Friend friend)
    {
        friends.remove(friend);
        return true;
    }

    /**
     * Returns a set of the vertices in the friends graph
     *
     * @return Set of vertices
     */
    @Override
    public Set<Friend> vertexSet()
    {
        return new TreeSet<Friend>(friends);
    }

    /**
     * Returns the hometown for a given friend
     *
     * @param fname First name of friend
     * @param lname Last name of friend
     * @return Friend's hometown
     */
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
