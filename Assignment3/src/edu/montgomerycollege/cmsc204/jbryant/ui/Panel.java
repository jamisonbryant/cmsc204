package edu.montgomerycollege.cmsc204.jbryant.ui;

import edu.montgomerycollege.cmsc204.jbryant.data.FurnitureTrackerManager;

/**
 * The application GUI
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class Panel
{
    private FurnitureTrackerManager manager;

    public Panel(FurnitureTrackerManager manager)
    {
        this.manager = manager;
    }

    public Panel()
    {
        this.manager = null;
    }
}
