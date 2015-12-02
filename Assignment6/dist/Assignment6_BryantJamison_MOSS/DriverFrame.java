package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.*;

/**
 * Launches the application and initiates construction of the GUI.
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for R. Alexander's CMSC 204 (M/W 1PM-2:40PM)
 */
public class DriverFrame extends JFrame
{
    /**
     * Sets the properties of the window
     *
     * @throws Exception If anything goes wrong
     */
    public DriverFrame() throws Exception
    {
        setTitle("Assignment 6");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        DriverPanel panel = new DriverPanel();
        getContentPane().add(panel);
    }

    /**
     * Launches the application and creates the window
     *
     * @param args Command-line arguments
     * @throws Exception If anything goes wrong
     */
    public static void main(String[] args) throws Exception
    {
        JFrame frame = new DriverFrame();
        frame.pack();
        frame.setVisible(true);
    }
}
