package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.JOptionPane;

/**
 * Value Not Valid Exception
 *  
 * An exception that can occur when a user enters an invalid value (according to the rules of sudoku) value for a cell
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class ValueNotValidException extends Exception 
{
	/**
	 * Displays a generic error message
	 */
	public ValueNotValidException()
	{
		JOptionPane.showMessageDialog(null, "Value not valid", "Fatal error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Displays a customized error message
	 * 
	 * @param message Message text
	 */
	public ValueNotValidException(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Fatal error", JOptionPane.ERROR_MESSAGE);
	}
}
