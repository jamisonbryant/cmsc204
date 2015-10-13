import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Application GUI class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class GUI extends JFrame
{
    private FurnitureTrackerManager manager;
    private boolean initialized;

    /**
     * Creates GUI and sets up controls
     */
    public GUI()
    {
        // Create main window
        JFrame window = new JFrame("Furniture Tracker");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500, 300);
        window.setLocationRelativeTo(null);

        // Create action handler
        ActionHandler handler = new ActionHandler();

        // Create main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Create table
        JTable table = new JTable(10, 7);
        table.getColumnModel().getColumn(0).setHeaderValue("Truck");
        table.getColumnModel().getColumn(1).setHeaderValue("Factory");
        table.getColumnModel().getColumn(2).setHeaderValue("Wal-Mart");
        table.getColumnModel().getColumn(3).setHeaderValue("Sam's Club");
        table.getColumnModel().getColumn(4).setHeaderValue("BJ's");
        table.getColumnModel().getColumn(5).setHeaderValue("Big Lots");
        table.getColumnModel().getColumn(6).setHeaderValue("Marlo");

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
        panel.add(new JScrollPane(table));

        // Add buttons to panel
        panel.add(topButtonPanel);
        panel.add(bottomButtonPanel);

        // Add panel to window
        window.add(panel);

        // Show window
        window.setVisible(true);

        // Create manager
        manager = new FurnitureTrackerManager();
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
            // Create new manager
            manager.newFurnitureTracker(chooser.getSelectedFile());
            initialized = true;
        } else {
            System.out.println("Notice: File choosing cancelled by user");
        }
    }

    private void loadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (initialized) {

        } else {
            throw new FurnitureTrackerNotInitializedException();
        }
    }

    private void unloadFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (initialized) {

        } else {
            throw new FurnitureTrackerNotInitializedException();
        }
    }

    private void driveTruck() throws FurnitureTrackerNotInitializedException
    {
        if (initialized) {

        } else {
            throw new FurnitureTrackerNotInitializedException();
        }
    }

    private void addFurniture() throws FurnitureTrackerNotInitializedException
    {
        if (initialized) {

        } else {
            throw new FurnitureTrackerNotInitializedException();
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
}
