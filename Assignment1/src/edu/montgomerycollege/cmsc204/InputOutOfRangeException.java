package edu.montgomerycollege.cmsc204;

import javax.swing.JOptionPane;

public class InputOutOfRangeException extends RuntimeException 
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
