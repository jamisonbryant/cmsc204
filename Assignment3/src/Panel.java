/**
 * The application GUI
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Panel
{
    private FurnitureTrackerManager manager;

    /**
     * Creates a new Panel object
     */
    public Panel()
    {
        this.manager = null;
    }

    /**
     * Creates a new Panel object with properties
     *
     * @param manager Furniture manager
     */
    public Panel(FurnitureTrackerManager manager)
    {
        this.manager = manager;
    }
}
