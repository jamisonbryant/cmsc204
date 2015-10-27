package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.error.InvalidKeyException;
import edu.montgomerycollege.cmsc204.jbryant.error.KeyInUseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Application Class
 * <p>
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
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(new Dimension(600, 500));
        window.setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create add panel
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
        addPanel.setBorder(BorderFactory.createTitledBorder(" Add Person "));

        // Create form panel
        JPanel formPanel1 = new JPanel();
        JLabel fnameLabel = new JLabel("First Name");
        JLabel lnameLabel = new JLabel("Last Name");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel addressLabel = new JLabel("Address");

        JTextField fnameField = new JTextField("", 20);
        fnameField.setMaximumSize(new Dimension(250, 20));
        JTextField lnameField = new JTextField("", 20);
        lnameField.setMaximumSize(new Dimension(250, 20));
        JTextField phoneField = new JTextField("", 20);
        phoneField.setMaximumSize(new Dimension(250, 20));
        JTextField addressField = new JTextField("", 20);
        addressField.setMaximumSize(new Dimension(250, 20));

        /*
         * Force components in add panel to line up nicely.
         *
         * Note: I'm not sure why the components have to be added both horizontally and vertically, but they do.
         *
         * @link http://docs.oracle.com/javase/8/docs/api/javax/swing/GroupLayout.html
         */
        GroupLayout layout = new GroupLayout(formPanel1);
        formPanel1.setLayout(layout);
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
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String fname = fnameField.getText();
                    String lname = lnameField.getText();
                    String phone = phoneField.getText();
                    String address = addressField.getText();

                    if (!fname.isEmpty() && !lname.isEmpty() && !phone.isEmpty() && !address.isEmpty()) {
                        manager.add(fname, lname, phone, address);
                    } else {
                        JOptionPane.showMessageDialog(null, "One or more required fields are empty", "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } catch (InvalidKeyException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid phone number format", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } catch (KeyInUseException ex) {
                    JOptionPane.showMessageDialog(null, "Person is already in address book", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add button to button panel
        buttonPanel1.add(addButton);

        // Add panels to add panel
        addPanel.add(formPanel1);
        addPanel.add(buttonPanel1);

        // Create lookup panel
        JPanel lookupPanel = new JPanel();
        lookupPanel.setLayout(new BoxLayout(lookupPanel, BoxLayout.Y_AXIS));
        lookupPanel.setBorder(BorderFactory.createTitledBorder(" Reverse Lookup "));

        // Create form panel
        JPanel formPanel2 = new JPanel();
        formPanel2.setLayout(new BoxLayout(formPanel2, BoxLayout.X_AXIS));

        JTextArea lookupArea = new JTextArea(8, 24);
        JScrollPane lookupPane = new JScrollPane(lookupArea);
        lookupArea.setBorder(BorderFactory.createTitledBorder("Phone number(s)"));

        JTextArea resultArea = new JTextArea();
        resultArea.setBorder(BorderFactory.createTitledBorder("Names (Last, First)"));
        resultArea.setEditable(false);

        // Add textreas to form panel
        formPanel2.add(lookupPane);
        formPanel2.add(resultArea);

        // Create button panel 2
        JPanel buttonPanel2 = new JPanel();
        JButton lookupButton = new JButton("Reverse Lookup");
        lookupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String results = "";
                String name;

                for (String line : lookupArea.getText().split("\\n")) {
                    try {
                        if ((name = manager.reverseLookup(line)) != null) {
                            results += name + "\n";
                        } else {
                            results += "Person not found";
                        }
                    } catch (InvalidKeyException ex) {
                        JOptionPane.showMessageDialog(null, "One or more phone numbers were invalid", "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }

                resultArea.setText(results);
            }
        });

        // Add button to button panel
        buttonPanel2.add(lookupButton);

        // Add panels to lookup panel
        lookupPanel.add(formPanel2);
        lookupPanel.add(buttonPanel2);

        // Create button panel 3
        JPanel buttonPanel3 = new JPanel();

        JButton readButton = new JButton("Read File");
        readButton.setActionCommand("read_file");
        readButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser f = new JFileChooser();
                int result = f.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = f.getSelectedFile();
                    manager.readFile(file);
                } else {
                    System.out.println("Notice: Read file action cancelled by user");
                }
            }
        });

        JButton writeButton = new JButton("Write File");
        writeButton.setActionCommand("write_file");
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser f = new JFileChooser();
                int result = f.showOpenDialog(null);

                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = f.getSelectedFile();
                    manager.writeToFile(file);
                } else {
                    System.out.println("Notice: Write file action cancelled by user");
                }
            }
        });

        JButton exitButton = new JButton("Exit App");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        // Add buttons to button panel
        buttonPanel3.add(readButton);
        buttonPanel3.add(writeButton);
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
