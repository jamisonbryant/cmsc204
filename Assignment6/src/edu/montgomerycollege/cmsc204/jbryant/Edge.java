package edu.montgomerycollege.cmsc204.jbryant;

/**
 * Edge Class
 */
public class Edge<T extends Comparable<T>, S extends Comparable<S>> implements Comparable<Edge<T, S>>
{
    private Friend friend1;
    private Friend friend2;

    public Edge(S friend1, S friend2)
    {
        this.friend1 = (Friend) friend1;
        this.friend2 = (Friend) friend2;
    }

    @Override
    public int compareTo(Edge<T, S> o)
    {
        return friend1.compareTo(friend2);
    }

    public Friend getFriend1() { return friend1; }

    public Friend getFriend2()
    {
        return friend2;
    }
}
