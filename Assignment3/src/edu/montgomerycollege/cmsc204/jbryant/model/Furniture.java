package edu.montgomerycollege.cmsc204.jbryant.model;

/**
 * The furniture data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Furniture
{
    private String name;
    public String material;
    public String color;

    public Furniture()
    {
        name = null;
        color = null;
        material = null;
    }

    public Furniture(String name, String color, String material)
    {
        this.name = name;
        this.color = color;
        this.material = material;
    }
}
