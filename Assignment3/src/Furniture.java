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
     * Creates a new furniture object
     */
    public Furniture()
    {
        name = null;
        color = null;
        material = null;
    }

    /**
     * Creates a new furniture object with properties
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

    /**
     * Returns a string representation of the furniture object
     *
     * @return String representation
     */
    public String toString()
    {
        String label = "";

        if (material != null) {
            label += material + " ";
        } else {
            label += "Unknown ";
            System.err.println("Warning: Furniture material not specified");
        }

        if (name != null) {
            label += name + " ";
        } else {
            label += "Unknown ";
            System.err.println("Warning: Furniture name not specified");
        }

        if (color != null) {
            label += "(" + color + ")";
        } else {
            label += "(Unknown)";
            System.err.println("Warning: Furniture color not specified");
        }

        return label;
    }
}
