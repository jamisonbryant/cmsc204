import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Application GUI
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class GUI extends JFrame
{
    /**
     * GUI's instance of a furniture tracker manager
     */
    private FurnitureTrackerManager tracker;

    /**
     * Table that represents the furniture currently in various locations
     */
    private JTable furnitureTable;

    /**
     * The label that indicates the truck's current location
     *
     * Note: Were there more thorough support for Unicode on Windows, we could potentially do something fun here by
     * using the delivery truck emoji ("\uD83D\uDE9A") instead of just a "T". However, lacking the proper Unicode fonts
     * leads the emoji to be displayed as a blank box, thereby obscuring its meaning.
     */
    private static String truckLabel = " (T)";

    /**
     * Creates GUI and sets up controls
     */
    public GUI()
    {
        // Create tracker
        tracker = new FurnitureTrackerManager();

        // Create main window
        JFrame window = new JFrame("Furniture Tracker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 300);
        window.setLocationRelativeTo(null);

        // Create action handler
        ActionHandler handler = new ActionHandler();

        // Create main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create table
        furnitureTable = new JTable(10, 1 + tracker.getLocations().size());
        furnitureTable.setEnabled(false);
        furnitureTable.getColumnModel().getColumn(0).setHeaderValue("Truck");

        for (int i = 1; i < furnitureTable.getColumnCount(); i++) {
            furnitureTable.getColumnModel().getColumn(i).setHeaderValue(tracker.getLocations().get(i - 1).name);
        }

        // Create buttons
        JPanel topButtonPanel = new JPanel();
        JPanel bottomButtonPanel = new JPanel();

        JButton loadButton = new JButton("Load Furniture");
        loadButton.setActionCommand("load_furniture");
        loadButton.addActionListener(handler);

        JButton unloadButton = new JButton("Unload Furniture");
        unloadButton.setActionCommand("unload_furniture");
        unloadButton.addActionListener(handler);

        JButton driveButton = new JButton("Drive Truck");
        driveButton.setActionCommand("drive_truck");
        driveButton.addActionListener(handler);

        JButton newButton = new JButton("New");
        newButton.setActionCommand("create_simulation");
        newButton.addActionListener(handler);

        JButton addButton = new JButton("Add Furniture");
        addButton.setActionCommand("add_furniture");
        addButton.addActionListener(handler);

        JButton exitButton = new JButton("Exit");
        exitButton.setActionCommand("exit_application");
        exitButton.addActionListener(handler);

        topButtonPanel.add(loadButton);
        topButtonPanel.add(unloadButton);
        topButtonPanel.add(driveButton);
        bottomButtonPanel.add(newButton);
        bottomButtonPanel.add(addButton);
        bottomButtonPanel.add(exitButton);

        // Add table to panel
        panel.add(new JScrollPane(furnitureTable));

        // Add buttons to panel
        panel.add(topButtonPanel);
        panel.add(bottomButtonPanel);

        // Add panel to window
        window.add(panel);

        // Show window
        window.setVisible(true);
    }

    /**
     * Sets the location of the truck label
     *
     * @param column Column to set label on (0 = factory, 1 - n = stores)
     */
    private void setTruckLabel(int column)
    {
        int numLocations = tracker.getLocations().size();

        // Reset column headers
        for (int i = 0; i < numLocations; i++) {
            TableColumn c = furnitureTable.getColumnModel().getColumn(i + 1);
            c.setHeaderValue(tracker.getLocations().get(i).name);
        }

        // Set truck label
        TableColumn c = furnitureTable.getColumnModel().getColumn(column + 1);
        c.setHeaderValue(tracker.getLocations().get(column).name + truckLabel);
        furnitureTable.getTableHeader().repaint();
    }

    /**
     * Moves the truck label to the next location
     */
    private void moveTruckLabel()
    {
        setTruckLabel(tracker.getLocationIndex());
    }

    /**
     * Creates a new simulation
     */
    private void createSimulation()
    {
        // Show file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filter);
        int choice = chooser.showOpenDialog(GUI.this);

        if (choice == JFileChooser.APPROVE_OPTION) {
            // Initialize tracker
            tracker.newFurnitureTracker(chooser.getSelectedFile());

            // Add furniture to factory
            int r = 0;
            Furniture[] furnitures = tracker.getFurnitures(tracker.getFactory());

            for (Furniture f : furnitures) {
                furnitureTable.setValueAt(f.getName(), r++, 1);
            }

            // Set truck location
            setTruckLabel(0);
        }
    }

    /**
     * Loads a piece of furniture onto the truck
     *
     * @throws FurnitureTrackerNotInitializedException If the tracker hasn't been initialized when this method is called
     */
    private void loadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            int r = 0; int s = 0;

            try {
                // Update truck
                clearColumn(0);
                for (Furniture f : tracker.loadTruck()) {
                    furnitureTable.setValueAt(f.getName(), r++, 0);
                }
            } catch (TruckLoadException e) {
                GUI.displayWarning("You cannot load furniture onto a full truck", false);
            } catch (WrongLocationException e) {
                GUI.displayWarning("You cannot load furniture onto the truck at a store", false);
            }

            // Update factory column
            clearColumn(1);

            for (Furniture f : tracker.getFactory().getFurnitures()) {
                furnitureTable.setValueAt(f.getName(), s++, 1);
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    /**
     * Unloads a piece of furniture from the truck
     *
     * @throws FurnitureTrackerNotInitializedException If the tracker hasn't been initialized when this method is called
     */
    private void unloadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            int r = 0; int s = 0;
            Truck truck = tracker.getTruck();
            Store store = (Store) truck.getLocation();

            try {
                // Update truck
                clearColumn(0);
                for (Furniture f : tracker.unloadTruck(store)) {
                    furnitureTable.setValueAt(f.getName(), r++, 0);
                }
            } catch (TruckLoadException e) {
                GUI.displayWarning("You cannot unload furniture from an empty truck", false);
            } catch (WrongLocationException e) {
                GUI.displayWarning("You cannot unload furniture from the truck at the factory", false);
            }

            // Update store column
            int c = tracker.getLocationIndex() + 1;
            clearColumn(c);

            for (Furniture f : store.getFurnitures()) {
                furnitureTable.setValueAt(f.getName(), s++, c);
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    /**
     * Moves the truck to the next location
     *
     * @throws FurnitureTrackerNotInitializedException If the tracker hasn't been initialized when this method is called
     */
    private void driveTruck() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            tracker.dispatchTruck();
            moveTruckLabel();
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    /**
     * Adds a user-defined piece of furniture to the factory
     *
     * @throws FurnitureTrackerNotInitializedException If the tracker hasn't been initialized when this method is called
     */
    private void addFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            // Display furniture form
            JTextField nameField = new JTextField();
            JTextField colorField = new JTextField();
            JTextField materialField = new JTextField();
            Object[] labels = { "Name", nameField, "Color", colorField, "Material", materialField };
            int response = JOptionPane.showConfirmDialog(null, labels, "Add Furniture", JOptionPane.OK_CANCEL_OPTION);

            if (response == JOptionPane.OK_OPTION) {
                // Add furniture to factory
                tracker.getFactory().addFurniture(
                        new Furniture(nameField.getText(), colorField.getText(), materialField.getText()));

                // Update factory column
                int r = 0;
                clearColumn(1);

                for (Furniture f : tracker.getFactory().getFurnitures()) {
                    furnitureTable.setValueAt(f.getName(), r++, 1);
                }
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    /**
     * Handles events triggered by UI controls
     */
    public class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // Parse action command
            switch (e.getActionCommand())
            {
                case "create_simulation":
                    createSimulation();
                    break;

                case "load_furniture":
                    try {
                        loadFurniture();
                    } catch (FurnitureTrackerNotInitializedException e1) {
                        GUI.displayWarning("You must initialize the tracker before loading furniture", false);
                    }

                    break;

                case "unload_furniture":
                    try {
                        unloadFurniture();
                    } catch (FurnitureTrackerNotInitializedException e2) {
                        GUI.displayWarning("You must initialize the tracker before unloading furniture", false);
                    }

                    break;

                case "drive_truck":
                    try {
                        driveTruck();
                    } catch (FurnitureTrackerNotInitializedException e3) {
                        GUI.displayWarning("You must initialize the tracker before driving the truck", false);
                    }

                    break;

                case "add_furniture":
                    try {
                        addFurniture();
                    } catch (FurnitureTrackerNotInitializedException e4) {
                        GUI.displayWarning("You must initialize the tracker before adding furniture", false);
                    }

                    break;

                case "exit_application":
                    System.exit(0);
                    break;

                default:
                    System.err.println("Error: Command \"" + e.getActionCommand() + "\" not recognized");
            }
        }
    }

    /**
     * Displays an error message in a dialog box
     *
     * @param message Message to display
     * @param exit Exit application after message acknowledged
     */
    public static void displayError(String message, boolean exit)
    {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);

        if (exit) {
            System.exit(1);
        }
    }

    /**
     * Displays a warning message in a dialog box
     *
     * @param message Message to display
     * @param exit Exit application after message acknowledged
     */
    public static void displayWarning(String message, boolean exit)
    {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);

        if (exit) {
            System.exit(1);
        }
    }

    /**
     * Clears the contents of a column of the table
     *
     * @param column Index of column to clear
     */
    private void clearColumn(int column)
    {
        for (int i = 0; i < furnitureTable.getRowCount(); i++) {
            furnitureTable.setValueAt("", i, column);
        }
    }
}
