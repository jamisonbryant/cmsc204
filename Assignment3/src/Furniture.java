/**
 * The furniture data object
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Furniture
{
    private String name;
    protected String material;
    protected String color;

    /**
     * Creates a new Furniture object
     */
    public Furniture()
    {
        name = null;
        color = null;
        material = null;
    }

    /**
     * Creates a new Furniture object with properties
     *
     * @param name Furniture name
     * @param color Furniture color
     * @param material Furniture material
     */
    public Furniture(String name, String color, String material)
    {
        this.name = name;
        this.color = color;
        this.material = material;
    }
}
