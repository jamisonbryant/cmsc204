package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        window.setSize(750, 650);
        window.setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create add panel
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setBorder(BorderFactory.createTitledBorder("Add Person"));

        // Create form panel
        JPanel formPanel = new JPanel();
        JLabel fnameLabel = new JLabel("First Name");
        JLabel lnameLabel = new JLabel("Last Name");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel addressLabel = new JLabel("Address");

        JTextField fnameField = new JTextField("", 20); fnameField.setMaximumSize(new Dimension(300, 20));
        JTextField lnameField = new JTextField("", 20); lnameField.setMaximumSize(new Dimension(300, 20));
        JTextField phoneField = new JTextField("", 20);
        phoneField.setMaximumSize(new Dimension(300, 20));
        JTextField addressField = new JTextField("", 20);
        addressField.setMaximumSize(new Dimension(300, 20));

        /*
         * Force components in add panel to line up nicely.
         *
         * Note: I'm not sure why the components have to be added both horizontally and vertically, but they do.
         *
         * @link http://docs.oracle.com/javase/8/docs/api/javax/swing/GroupLayout.html
         */
        GroupLayout layout = new GroupLayout(formPanel);
        formPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Add elements to form panel
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

        // Create button panel 1
        JPanel buttonPanel1 = new JPanel();
        JButton addButton = new JButton("Add Person");
        addButton.setActionCommand("add_person");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });

        // Add button to button panel
        buttonPanel1.add(addButton);

        // Add panels to add panel
        addPanel.add(formPanel);
        addPanel.add(buttonPanel1);

        // Create lookup panel
        JPanel lookupPanel = new JPanel();
        lookupPanel.setBorder(BorderFactory.createTitledBorder("Look Up Person"));

        // Create button panel 3
        JPanel buttonPanel3 = new JPanel();

        JButton readButton = new JButton("Read Text File");
        readButton.setActionCommand("read_file");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });

        JButton writeButton = new JButton("Write Text File");
        writeButton.setActionCommand("write_file");
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(e.getActionCommand());
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to button panel
        buttonPanel3.add(writeButton);
        buttonPanel3.add(readButton);
        buttonPanel3.add(exitButton);

        // Add panels to main panel
        mainPanel.add(addPanel);
        mainPanel.add(lookupPanel);
        mainPanel.add(buttonPanel3);

        // Add main panel to window
        window.add(mainPanel);

        // Show main window
        window.setVisible(true);
    }
}
