package edu.montgomerycollege.cmsc204.jbryant;

/**
 * Represents an edge between two vertices in the friends graph.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for R. Alexander's CMSC 204 (M/W 1PM-2:40PM)
 */
public class Edge<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Edge<T, S>>
{
    /**
     * First friend vertex
     */
    private Friend friend1;

    /**
     * Second friend vertex
     */
    private Friend friend2;

    /**
     * Creates a new edge between two friends
     *
     * @param friend1 First friend
     * @param friend2 Second friend
     */
    public Edge(S friend1, S friend2)
    {
        this.friend1 = (Friend) friend1;
        this.friend2 = (Friend) friend2;
    }

    /**
     * Compares two edges and returns a result
     *
     * @param edge Edge to compare
     * @return Result of comparison
     */
    @Override
    public int compareTo(Edge<T, S> edge)
    {
        return friend1.compareTo(friend2);
    }

    public Friend getFriend1() { return friend1; }

    public Friend getFriend2() { return friend2; }
}
