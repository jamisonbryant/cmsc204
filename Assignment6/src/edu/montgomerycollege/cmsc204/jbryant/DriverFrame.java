package edu.montgomerycollege.cmsc204.jbryant;

import javax.swing.*;

public class DriverFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	public DriverFrame() throws Exception
	{
		setTitle("Connections");     // set up the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DriverPanel newpanel = new DriverPanel();
		getContentPane().add(newpanel);
	}
	
  	public static void main(String[] args) throws Exception
  	{
  		JFrame frame = new DriverFrame();
  		frame.pack();
  		frame.setVisible(true);
  	}
 }
