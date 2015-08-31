package edu.montgomerycollege.cmsc204;

import javax.swing.JOptionPane;

public class ValueNotValidException extends RuntimeException 
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
