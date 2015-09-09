package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.JOptionPane;

/**
 * Input Out-of-Range Exception
 *  
 * An exception that can occur when a user enters an out-of-range (not 1-9) value for a cell
 *
 * @author Jamison Bryant (jbryan46@montgomerycollege.edu) for CMSC 204 (R. Alexander) M/W 1PM-3PM
 */
public class InputOutOfRangeException extends Exception 
{
	/**
	 * Displays a generic error message
	 */
	public InputOutOfRangeException()
	{
		JOptionPane.showMessageDialog(null, "Input out of range", "Fatal error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Displays a customized error message
	 * 
	 * @param message Message text
	 */
	public InputOutOfRangeException(String message)
	{
		JOptionPane.showMessageDialog(null, message, "Fatal error", JOptionPane.ERROR_MESSAGE);
	}
}
