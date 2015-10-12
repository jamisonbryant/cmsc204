import javax.swing.*;

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
        JButton loadButton = new JButton("Load Furniture");
        JButton unloadButton = new JButton("Unload Furniture");
        JButton driveButton = new JButton("Drive Truck");
        topButtonPanel.add(loadButton);
        topButtonPanel.add(unloadButton);
        topButtonPanel.add(driveButton);

        JPanel bottomButtonPanel = new JPanel();
        JButton newButton = new JButton("New");
        JButton addButton = new JButton("Add Furniture");
        JButton exitButton = new JButton("Exit");
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
}
