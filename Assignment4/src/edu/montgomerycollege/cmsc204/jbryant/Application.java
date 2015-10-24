package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.*;
import java.awt.*;

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

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create add panel
        JPanel addPanel = new JPanel();
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Person"));

        // Create add panel elements
        JLabel fnameLabel = new JLabel("First Name");
        JLabel lnameLabel = new JLabel("Last Name");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel addressLabel = new JLabel("Address");

        JTextField fnameField = new JTextField("", 20);
        JTextField lnameField = new JTextField("", 20);
        JTextField phoneField = new JTextField("", 20);
        JTextField addressField = new JTextField("", 20);

        /*
         * Set up add panel layout
         *
         * Forces components in add panel to line up nicely
         *
         * @link http://docs.oracle.com/javase/8/docs/api/javax/swing/GroupLayout.html
         */
        GroupLayout layout = new GroupLayout(addPanel);
        addPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup horizontalGroup = layout.createSequentialGroup();
        horizontalGroup.addGroup(layout.createParallelGroup().addComponent(fnameLabel).addComponent(lnameLabel)
                .addComponent(phoneLabel).addComponent(addressLabel));
        horizontalGroup.addGroup(layout.createParallelGroup().addComponent(fnameField).addComponent(lnameField)
                .addComponent(phoneField).addComponent(addressField));
        layout.setHorizontalGroup(horizontalGroup);

        GroupLayout.SequentialGroup verticalGroup = layout.createSequentialGroup();
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(fnameLabel)
                .addComponent(fnameField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lnameLabel)
                .addComponent(lnameField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(phoneLabel)
                .addComponent(phoneField));
        verticalGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(addressLabel)
                .addComponent(addressField));
        layout.setVerticalGroup(verticalGroup);

        /*
         * I'm not sure why the components have to be added horizontally and THEN vertically...seems redundant.
         */

        // Create lookup panel
        JPanel lookupPanel = new JPanel();
        lookupPanel.setBorder(BorderFactory.createTitledBorder("Look Up Person"));

        // Add panels to main panel
        mainPanel.add(addPanel);
        mainPanel.add(lookupPanel);

        // Add main panel to window
        window.add(mainPanel);

        // Show main window
        window.setVisible(true);
    }
}
