import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SudokuBoardManager implements SudokuBoardManagerInterface 
{
	private static JTextField[][] boardCells = new JTextField[9][9];
	
	/**
	 * Initializes program and builds GUI
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args)
	{
		// Create main window
		JFrame mainWindow = new JFrame("Sudoku Board Manager");
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//mainWindow.setSize(700, 700);
		
		// Create board panel
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));
		
		// Create control panel
		JPanel controlPanel = new JPanel();
		
		// Add cells to board
		for (int i = 0; i < 9; i++) {
			// Create row panel
			JPanel rowPanel = new JPanel();
			rowPanel.setLayout(new BoxLayout(rowPanel, BoxLayout.X_AXIS));
			
			// Add cells to row
			for (int j = 0; j < 9; j++) {
				// Create cell
				JTextField cellTextField = new JTextField("     ");				
				cellTextField.setDisabledTextColor(new Color(119, 119, 119));
				cellTextField.setBorder(BorderFactory.createLineBorder(new Color(170, 170, 170)));
				cellTextField.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
				cellTextField.setHorizontalAlignment(JTextField.CENTER);
				cellTextField.setEnabled(false);
				
				// Set cell background color				
				if (i <= 2 || i >= 6) {
					if (j <= 2 || j >= 6) {
						cellTextField.setBackground(new Color(204, 204, 204));						
					}
				} else {
					if (j > 2 && j < 6) {
						cellTextField.setBackground(new Color(204, 204, 204));
					}
				}

				// Add cell to row
				boardCells[i][j] = cellTextField;
				rowPanel.add(cellTextField);
			}
			
			// Add row to board
			boardPanel.add(rowPanel, BorderLayout.AFTER_LAST_LINE);
		}
		
		// Add panels to main window
		mainWindow.add(boardPanel, BorderLayout.NORTH);
		mainWindow.add(controlPanel, BorderLayout.SOUTH);
		
		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// Create menus
		JMenu gameMenu = new JMenu("Game");		
		gameMenu.setMnemonic(KeyEvent.VK_G);
		JMenuItem newItem = new JMenuItem("New...", KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Prompt user to open game file
				final JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
				chooser.setFileFilter(filter);
				BufferedReader reader = null;
				String line = null;
				
				if (chooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
					// Read file into board
					try {
						File file = chooser.getSelectedFile();
						reader = new BufferedReader(new FileReader(file));
						
						for (int i = 0; i < 9; i++) {
							if ((line = reader.readLine()) != null) {
								String[] values = line.split(",");
								
								for (int j = 0; j < 9; j++) {
									if (!values[j].equals("0")) {
									    boardCells[i][j].setText(values[j]);	
									}									
							    }
							}																										
						}
					} catch (FileNotFoundException ex) {
						// Display error message and exit
						JOptionPane.showMessageDialog(null, "File not found", "Fatal error", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
						System.exit(1);
					} catch (IOException ex) {
						// Display error message and exit
						JOptionPane.showMessageDialog(null, "I/O error", "Fatal error", JOptionPane.ERROR_MESSAGE);
						ex.printStackTrace();
						System.exit(1);
					} finally {
						try {
							// Close reader resource
							if (reader != null) {
								reader.close();
							}
						} catch (IOException ex) {}
					}
				}
			}
		});
		
		gameMenu.add(newItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display about information
				JOptionPane.showMessageDialog(null, "Sudoku Board Manager v1.0.0\nBy Jamison Bryant for CMSC 204\n"
						+ "jbryan46@montgomerycollege.edu");
			}
		});
		
		helpMenu.add(aboutItem);		
		
		// Add menus to menu bar
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		
		// Add menu to main window
		mainWindow.setJMenuBar(menuBar);
		
		// Display main window
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

	/**
	 * 
	 */
	public void setValueAt(int row, int column, int value)			
	{
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 */
	public int getValueAt(int row, int column) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * 
	 */
	public int[] displayPossibleValues(int row, int column)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public void newGame(File gameFile) 
	{
		// TODO Auto-generated method stub		
	}
}
