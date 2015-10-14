import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Application GUI class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class GUI extends JFrame
{
    private FurnitureTrackerManager tracker;
    private JTable furnitureTable;
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
            // Create new tracker
            tracker.newFurnitureTracker(chooser.getSelectedFile());

            // Add furniture to factory
            int r = 0;
            Furniture[] furnitures = tracker.getFurnitures(tracker.getFactory());

            for (Furniture f : furnitures) {
                furnitureTable.setValueAt(f.getName(), r++, 1);
            }

            // Set truck location
            setTruckLabel(0);
        } else {
            System.out.println("Notice: File choosing cancelled by user");
        }
    }

    private void loadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            // Load furniture onto truck
            Truck truck = tracker.getTruck();
            truck.uploadFurniture(tracker.getFactory().removeFurniture());

            // Update truck column
            int r1 = 0;
            clearColumn(0);

            for (Furniture f : truck.toArray()) {
                furnitureTable.setValueAt(f.getName(), r1++, 0);
            }

            // Update factory column
            int r2 = 0;
            clearColumn(1);

            for (Furniture f : tracker.getFactory().getFurnitures()) {
                furnitureTable.setValueAt(f.getName(), r2++, 1);
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    private void unloadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            int r;

            // Remove furniture from truck
            r = 0;
            Truck truck = tracker.getTruck();
            Furniture f = truck.offloadFurniture();

            // Add furniture to store
            ((Store) truck.getLocation()).addFurniture(f);

            // Update truck column
            clearColumn(0);

            for (Furniture f2 : truck.toArray()) {
                furnitureTable.setValueAt(f.getName(), r++, 0);
            }

            // Update store column
            r = 0;
            int c = tracker.getLocationIndex();
            clearColumn(c);

            for (Furniture f2 : ((Store) truck.getLocation()).getFurnitures()) {
                furnitureTable.setValueAt(f.getName(), r++, c);
            }
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    private void driveTruck() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {
            tracker.dispatchTruck();
            moveTruckLabel();
        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    private void addFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (tracker.isInitialized()) {

        } else {
            throw new FurnitureTrackerNotInitializedException("Error: Action attempted before tracker initialized");
        }
    }

    public class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
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

    //<editor-fold desc="[Convenience Methods] ...">
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
    //</editor-fold>
}
