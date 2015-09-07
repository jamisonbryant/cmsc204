package edu.montgomerycollege.cmsc204.jbryant;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import java.io.LineNumberReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.montgomerycollege.cmsc204.ralexander.SudokuBoardManagerInterface;
import jdk.internal.util.xml.impl.Input;

/**
 * Sudoku Board Manager
 *  
 * A simple Sudoku application
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class SudokuBoardManager implements SudokuBoardManagerInterface 
{
	/**
	 * 2D array of text fields for board fields
	 */
	private static JTextField[][] boardFields = new JTextField[9][9];
	
	/**
	 * 2D array of integers for board values
	 */
	private static int[][] boardValues = new int[9][9];
	
	/**
	 * Initializes program
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args)
	{
		new SudokuBoardManager();
	}

	/**
	 * Builds GUI and sets up action listeners
	 */
	public SudokuBoardManager()
	{
		/*
		 * Create main window
		 */
		JFrame mainWindow = new JFrame("Sudoku");
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		 * Construct board GUI
		 */		
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new BoxLayout(boardPanel, BoxLayout.Y_AXIS));	
		
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
				boardFields[i][j] = cellTextField;
				rowPanel.add(cellTextField);
			}
			
			// Add row to board
			boardPanel.add(rowPanel);
		}
		
		/*
		 * Construct control panel
		 */
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		
		// Create input panel
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
		inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Value"));
		
		// Create row input field
		JPanel rowInputField1 = new JPanel();
		JLabel rowInputLabel1 = new JLabel("Row:");
		JSpinner rowInputSpinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
		rowInputSpinner1.setPreferredSize(new Dimension(60, 25));
		rowInputField1.add(rowInputLabel1);
		rowInputField1.add(rowInputSpinner1);
		
		// Add row input field to input panel			
		inputPanel.add(rowInputField1);
		
		// Create column input field
		JPanel columnInputField1 = new JPanel();
		JLabel columnInputLabel1 = new JLabel("Column:");
		JSpinner columnInputSpinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
		columnInputSpinner1.setPreferredSize(new Dimension(60, 25));
		columnInputField1.add(columnInputLabel1);
		columnInputField1.add(columnInputSpinner1);
		
		// Add column input field to panel
		inputPanel.add(columnInputField1);		
		
		// Create value input field
		JPanel valueInputField1 = new JPanel();
		JLabel valueInputLabel1 = new JLabel("Value:");
		JTextField valueInputTextField1 = new JTextField();			
		valueInputTextField1.setPreferredSize(new Dimension(60, 25));
		valueInputField1.add(valueInputLabel1);
		valueInputField1.add(valueInputTextField1);
		
		// Add value input field to panel
		inputPanel.add(valueInputField1);
		
		// Create value button field
		JPanel valueButtonField1 = new JPanel();		
		JButton valueEnterButton = new JButton("Enter");
		valueEnterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = (int) rowInputSpinner1.getValue() - 1;
				int column = (int) columnInputSpinner1.getValue() - 1;
				int value = Integer.parseInt(valueInputTextField1.getText());
				
				try {
				    setValueAt(row + 1, column + 1, value);
				} catch (ValueNotValidException | InputOutOfRangeException ex) {
				    ex.printStackTrace();
				}
			}
		});
				
		valueButtonField1.add(valueEnterButton);
		
		// Add value button field to panel
		inputPanel.add(valueButtonField1);
		
		// Create display panel
		JPanel displayPanel = new JPanel();
		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
		displayPanel.setBorder(BorderFactory.createTitledBorder("Display Values"));
		
		// Create row input field
		JPanel rowInputField2 = new JPanel();
		JLabel rowInputLabel2 = new JLabel("Row:");
		JSpinner rowInputSpinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
		rowInputSpinner2.setPreferredSize(new Dimension(60, 25));
		rowInputField2.add(rowInputLabel2);
		rowInputField2.add(rowInputSpinner2);
		
		// Add row input field to input panel			
		displayPanel.add(rowInputField2);
		
		// Create column input field
		JPanel columnInputField2 = new JPanel();
		JLabel columnInputLabel2 = new JLabel("Column:");
		JSpinner columnInputSpinner2 = new JSpinner(new SpinnerNumberModel(1, 1, 9, 1));
		columnInputSpinner2.setPreferredSize(new Dimension(60, 25));
		columnInputField2.add(columnInputLabel2);
		columnInputField2.add(columnInputSpinner2);
		
		// Add column input field to panel
		displayPanel.add(columnInputField2);
		
		// Create values input field
		JPanel valueInputField2 = new JPanel();
		JLabel valueInputLabel2 = new JLabel("Value(s):");
		JTextField valueInputTextField2 = new JTextField();
		
		valueInputTextField2.setEnabled(false);
		valueInputTextField2.setPreferredSize(new Dimension(60, 25));
		valueInputField2.add(valueInputLabel2);
		valueInputField2.add(valueInputTextField2);
		
		// Add value input field to panel
		displayPanel.add(valueInputField2);
		
		// Create value button field
		JPanel valueButtonField2 = new JPanel();		
		JButton valueFetchButton = new JButton("Fetch");
		valueFetchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
				int row = (int) rowInputSpinner2.getValue() - 1;
				int column = (int) columnInputSpinner2.getValue() - 1;
				int[] values = displayPossibleValues(row + 1, column + 1);
				String text = "";
				
				for (int value : values) {
					text += Integer.toString(value) + ", ";
				}
				
				valueInputTextField2.setText(text.substring(0, (3 * values.length - 2)));
			}
		});
				
		valueButtonField2.add(valueFetchButton);	
		
		// Add value button field to panel
		displayPanel.add(valueButtonField2);
		
		// Add panels to control panel
		controlPanel.add(inputPanel);
		controlPanel.add(displayPanel);
		
		/*
		 * Add panels to main window
		 */
		mainWindow.add(boardPanel, BorderLayout.NORTH);
		mainWindow.add(controlPanel, BorderLayout.SOUTH);
		
		/*
		 * Create menu bar and menus
		 */
		JMenuBar menuBar = new JMenuBar();

		// Create Game menu
		JMenu gameMenu = new JMenu("Game");		
		gameMenu.setMnemonic(KeyEvent.VK_G);
		
		// Create New item
		JMenuItem newItem = new JMenuItem("New...", KeyEvent.VK_N);
		newItem.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		// Listen for New item click
		newItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Prompt user for game file
				final JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files (*.txt)", "txt");
				chooser.setFileFilter(filter);
				
				if (chooser.showOpenDialog(mainWindow) == JFileChooser.APPROVE_OPTION) {
					newGame(chooser.getSelectedFile());
					
					// Reset input fields
					rowInputSpinner1.setValue(1);
					columnInputSpinner1.setValue(1);
					valueInputTextField1.setText("");					
					rowInputSpinner2.setValue(1);
					columnInputSpinner2.setValue(1);
					valueInputTextField2.setText("");
				}
			}
		});
		
		// Add New item to Game menu
		gameMenu.add(newItem);
		
		// Create Exit item
		JMenuItem exitItem = new JMenuItem("Exit", KeyEvent.VK_W);
		exitItem.setAccelerator(KeyStroke.getKeyStroke('W', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		// Listen for Exit item click
		exitItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Add Exit item to Game menu
		gameMenu.add(exitItem);
		
		// Create Help menu 
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		
		// Create About item
		JMenuItem aboutItem = new JMenuItem("About", KeyEvent.VK_A);
		aboutItem.setAccelerator(KeyStroke.getKeyStroke('A', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		
		// Listen for About item click
		aboutItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Display about information
				JOptionPane.showMessageDialog(null, "Sudoku Board Manager v1.0.0\nBy Jamison Bryant for CMSC 204\n"
						+ "jbryan46@montgomerycollege.edu");
			}
		});
		
		// Add About item to Help menu
		helpMenu.add(aboutItem);		
		
		// Add menu bars to menu
		menuBar.add(gameMenu);
		menuBar.add(helpMenu);
		
		// Add menu to main window
		mainWindow.setJMenuBar(menuBar);
		
		/*
		 * Display main window
		 */
		mainWindow.pack();
		mainWindow.setVisible(true);
	}
	
	/**
	 * Checks if a given value is valid at a given row and column
	 * 
	 * @param row    Row to check
	 * @param column Column to check
	 * @param value  Value to check
	 * @return True if value valid at row and column, false otherwise
	 */
	public boolean isValueValidAt(int row, int column, int value) 
	{
		row = row - 1;
        column = column - 1;

		// Check cell validity
		if (boardValues[row][column] == value) {
			return true;
		}
		
		// Check row validity
		for (int i = 0; i < 9; i++) {
			if (boardValues[i][column] == value) {
				return false;
			}
		}
		
		// Check column validity
		for (int i = 0; i < 9; i++) {
			if (boardValues[row][i] == value) {
				return false;
			}
		}	
		
		// Check box validity
        int startX = row - (row % 3);
        int startY = column - (column % 3);

		for (int i = startX; i <= startX + 2; i++) {
		    for (int j = startY; j <= startY + 2; j++) {
		    /*
             * Note: This massive block of code is not commented out because it does not work; rather, because it is an
             * extremely useful debugging tool for visualizing which cells are being checked by the program.
             *
             * The logic below is a text-based alternative to updating the board itself, because the board cannot be
             * updated until the loop is complete; it resides in the same thread as the loop, making any visual updates
             * useless.
             */
//                System.out.println("Checking " + value + " for (" + row + ", " + column + ") at (" + i + ", " + j + ")");
//                System.out.println("-------------------------------------");
//                String line = "";
//
//                for (int a = 0; a < 9; a++) {
//                    System.out.print("|");
//
//                    for (int b = 0; b < 9; b++) {
//                        if (a == i && b == j) {
//                            line += " * |";
//                        } else {
//                            line += "   |";
//                        }
//                    }
//
//                    System.out.println(line);
//                    System.out.println("-------------------------------------");
//                    line = "";
//                }
//
//                System.out.println();
//
		        if (boardValues[i][j] == value) {
		            return false;
		        }
		    }
		}
		
		return true;
	}
	
	/**
	 * Sets the value of a cell at a given row and column to a given value
	 * 
	 * @param row    Row of cell to set
	 * @param column Column of cell to set+
	 * @param value  Value to set cell to
	 * @throws InputOutOfRangeException If given value is not 1-9
	 * @throws ValueNotValidException If given value is invalid according to game rules
	 */
	public void setValueAt(int row, int column, int value) throws InputOutOfRangeException, ValueNotValidException			
	{
		try {			
			if (value >= 1 && value <= 9) {
				if (isValueValidAt(row, column, value)) {
				    boardValues[row - 1][column - 1] = value;
					boardFields[row - 1][column - 1].setText(new Integer(value).toString());	
				} else {
					throw new ValueNotValidException();
				}
			} else {
				throw new InputOutOfRangeException();
			}
		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "Invalid number", "Error", 
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Returns the value of a cell at a given row and column
	 * 
	 * @param row    Row of cell to fetch
	 * @param column Column of cell to fetch
     * @throws InputOutOfRangeException If either row or column are out of range
	 * @return Value of cell
	 */
	public int getValueAt(int row, int column) throws InputOutOfRangeException
	{
        if ((row >= 1 && row <= 9) && (column >= 1 && column <= 9)) {
            return boardValues[row - 1][column - 1];
        } else {
            throw new InputOutOfRangeException();
        }
	}

	/**
	 * Returns an array of valid values for a cell at a given row and column
	 * 
	 * @param row    Row of cell to fetch
	 * @param column Column of cell to fetch
	 * @return Valid values for cell
	 */
	public int[] displayPossibleValues(int row, int column)
	{
		ArrayList<Integer> values = new ArrayList<Integer>();
		
		for (int i = 1; i < 10; i++) {
			if (isValueValidAt(row, column, i)) {
				values.add(i);
			}
		}
		
		int[] numbers = new int[values.size()];
		
		for (int i = 0; i < values.size(); i++) {
			numbers[i] = values.get(i);
		}
		
		return numbers;
	}

	/**
	 * Creates a new game by opening and importing a game file
	 * 
	 * @param gameFile Game file to import
	 */
	public void newGame(File gameFile) 
	{
		BufferedReader fileReader = null;
		LineNumberReader lineReader = null;
		String line = null;
		
		// Read file into board
		try {
			fileReader = new BufferedReader(new FileReader(gameFile));
			
			// Validate file content
			lineReader = new LineNumberReader(new FileReader(gameFile));
			lineReader.skip(Long.MAX_VALUE);
			
			if (lineReader.getLineNumber() + 1 < 9) {
				JOptionPane.showMessageDialog(null, "Invalid game file", "Fatal error", 
						JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
			// Apply file to board
			for (int i = 1; i < 10; i++) {
				if ((line = fileReader.readLine()) != null) {
					String[] values = line.split(",");
					
					// Validate line content
					if (values.length != 9) {
						JOptionPane.showMessageDialog(null, "Invalid game file", "Fatal error", 
								JOptionPane.ERROR_MESSAGE);
						System.exit(1);
					} 
				
					for (int j = 1; j < 10; j++) {
						if (!values[j - 1].equals("0")) {
							try {
								this.setValueAt(i, j, Integer.parseInt(values[j - 1]));
							} catch (NumberFormatException | InputOutOfRangeException | ValueNotValidException e) {
								e.printStackTrace();
							}
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
				// Close file reader
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (IOException ex) {}
			
			try {
				// Close line number reader
				if (lineReader != null) {
					lineReader.close();
				}
			} catch (IOException ex) {}
		}		
	}
	
	@Override
	public String toString()
	{
	    String string = "";
	    
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            string += Integer.toString(boardValues[i][j]) + ",";
	        }
	    }
	    
	    return string.substring(0, string.length() - 1);
	}
}
