package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.*;

/**
 * Application Class
 *
 * The entry point/launcher of the application.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu)
 */
public class Application
{
    /**
     * Data manager
     */
    private AddressBookUtility manager;

    /**
     * Instantiates application
     */
    public Application()
    {
        // Instantiate data manager
        manager = new AddressBookUtility();

        // Build GUI
        buildGUI();
    }

    /**
     * Builds application GUI
     */
    public void buildGUI()
    {
        // Create main window
        JFrame window = new JFrame("Address Book Utility");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(800, 650);
        window.setLocationRelativeTo(null);

        // Show main window
        window.setVisible(true);
    }
}
