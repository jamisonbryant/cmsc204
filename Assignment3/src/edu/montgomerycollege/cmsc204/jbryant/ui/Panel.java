package edu.montgomerycollege.cmsc204.jbryant.ui;

import edu.montgomerycollege.cmsc204.jbryant.data.FurnitureTrackerManager;

/**
 * Created by Jamison on 10/5/2015.
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
