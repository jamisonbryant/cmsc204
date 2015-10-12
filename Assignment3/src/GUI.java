import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Application GUI class
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class GUI extends JFrame
{
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
        JTable table = new JTable(10, 6);
        table.getColumnModel().getColumn(0).setHeaderValue("Truck");
        table.getColumnModel().getColumn(1).setHeaderValue("Wal-Mart");
        table.getColumnModel().getColumn(2).setHeaderValue("Sam's Club");
        table.getColumnModel().getColumn(3).setHeaderValue("BJ's");
        table.getColumnModel().getColumn(4).setHeaderValue("Big Lots");
        table.getColumnModel().getColumn(5).setHeaderValue("Marlo");

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
        newButton.setActionCommand("new_simulation");
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
    }

    public class ActionHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            switch (e.getActionCommand())
            {
                case "load_furniture":
                    break;

                case "unload_furniture":
                    break;

                case "drive_truck":
                    break;

                case "new_simulation":
                    break;

                case "add_furniture":
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
