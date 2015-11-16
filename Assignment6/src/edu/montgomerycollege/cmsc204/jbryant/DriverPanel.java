package edu.montgomerycollege.cmsc204.jbryant;

import edu.montgomerycollege.cmsc204.jbryant.DataManager;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

class DriverPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private JLabel lblCode, lblEnglish;
	private JLabel fNameLbl, lNameLbl, cityLbl;
	private JTextField fNameTbx, lNameTbx, cityTbx;
	private JLabel newfNameLbl, newlNameLbl, newcityLbl;
	private JTextField newfNameTbx, newlNameTbx, newcityTbx;
	private JTextArea friendArea, friendOfFriendArea;
	private JScrollPane codeScroll, englishScroll;
	private	JButton newFileButton, convertButton, exitButton, friendBtn, friendOfFriendBtn, newButton;
	private DataManager friendGraph;
	private JComboBox addFriendComboBox, profileComboBox, friendOfFriendComboBox;
	private JScrollPane scroll, friendscroll;
	private String currentProfile;
	private ActionListener profile;
	private File filename, participantsFile, friendsFile;

	
	DriverPanel() throws Exception
	{
		friendGraph = new DataManager();
		getFileNames("Participants.txt");
		participantsFile = filename;
		getFileNames("Friends.txt");
		friendsFile = filename;
		friendGraph.populateParticipants(participantsFile);
		friendGraph.populateFriends(friendsFile);
		//friendGraph.getParticipantsFile();
		//friendGraph.getFriendsFile();
//		friendGraph.listAllParticipants();
		String[] emptyList = new String[2];
		emptyList[0] = "";
		addFriendComboBox = new JComboBox(emptyList);
		friendOfFriendComboBox = new JComboBox(emptyList);
		profileComboBox = new JComboBox(friendGraph.vectorOfParticipants());
		profile = new ProfileListener();
		profileComboBox.addActionListener(profile);
		
	    setPreferredSize (new Dimension(800, 600));
	    
	    // Labels
	    JPanel headerPanel = new JPanel();
	    headerPanel.setPreferredSize (new Dimension(750, 30));
	    lblCode = new JLabel("Connections");
	    lblCode.setFont(new Font(Font.SANS_SERIF, Font.BOLD,20));
	    headerPanel.add(lblCode);
	    
	    JPanel labelPanel = new JPanel();
	    labelPanel.setPreferredSize(new Dimension(75, 75));
//	    labelPanel.setLayout (new BoxLayout (labelPanel,BoxLayout.Y_AXIS));
//	    labelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    fNameLbl = new JLabel("First Name");
	    fNameLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    lNameLbl = new JLabel("Last Name");
	    lNameLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    cityLbl = new JLabel("Hometown");
	    cityLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    labelPanel.add(fNameLbl);
	    labelPanel.add(lNameLbl);
	    labelPanel.add(cityLbl);
	    
	    JPanel textPanel = new JPanel();
//	    textPanel.setLayout (new BoxLayout (textPanel, BoxLayout.Y_AXIS));
	    textPanel.setPreferredSize(new Dimension(200, 75));
	    fNameTbx = new JTextField(15);
	    fNameTbx.setEditable(false);
	    lNameTbx = new JTextField(15);
	    lNameTbx.setEditable(false);
	    cityTbx = new JTextField(15);
	    cityTbx.setEditable(false);
	    textPanel.add(fNameTbx);
	    textPanel.add(lNameTbx);
	    textPanel.add(cityTbx);
	    
	    JPanel infoPanel = new JPanel();
	    infoPanel.setPreferredSize(new Dimension(320, 90));
	    infoPanel.add(labelPanel);
	    infoPanel.add(textPanel);
	    
	    JPanel profilePanel = new JPanel();
//	    profilePanel.setLayout (new BoxLayout (profilePanel, BoxLayout.Y_AXIS));
	    profilePanel.setPreferredSize (new Dimension(350, 350));
	    profilePanel.setBorder (BorderFactory.createTitledBorder ("Choose a Profile"));
	    friendArea = new JTextArea(10,20);
	    friendArea.setBorder (BorderFactory.createTitledBorder ("Friends"));
	    scroll = new JScrollPane(friendArea);
	    profilePanel.add(scroll);
	    profilePanel.add(profileComboBox);
	    profilePanel.add(infoPanel);
	    profilePanel.add(friendArea);
	    
	    JPanel addFriendPanel = new JPanel();
	    addFriendPanel.setPreferredSize (new Dimension(350, 70));
	    addFriendPanel.setBorder (BorderFactory.createTitledBorder ("Add A Friend"));
	    friendBtn = new JButton("Add Friend");
	    friendBtn.addActionListener(new addAFriendListener());
	    addFriendPanel.add(addFriendComboBox);
	    addFriendPanel.add(friendBtn);

	    JPanel friendOfFriendPanel = new JPanel();
	    friendOfFriendPanel.setPreferredSize (new Dimension(350, 250));
	    friendOfFriendPanel.setBorder (BorderFactory.createTitledBorder ("Friends Of Friends"));
	    friendOfFriendArea = new JTextArea(10,20);
	    friendOfFriendBtn = new JButton("Show Friends of Friends");
	    friendOfFriendBtn.addActionListener(new FriendOfFriendListener());
//	    friendOfFriendPanel.add(friendOfFriendComboBox);
	    friendOfFriendPanel.add(friendOfFriendBtn);
	    friendOfFriendPanel.add(friendOfFriendArea);
	    friendscroll = new JScrollPane(friendOfFriendArea);
	    friendOfFriendPanel.add(friendscroll);
	    
	    JPanel friendPanel = new JPanel();
	    friendPanel.setPreferredSize (new Dimension(350, 350));
	    friendPanel.add(friendOfFriendPanel);
	    friendPanel.add(addFriendPanel);
	    
	    JPanel newlabelPanel = new JPanel();
	    newlabelPanel.setPreferredSize(new Dimension(75, 75));
//	    newlabelPanel.setLayout (new BoxLayout (labelPanel,BoxLayout.Y_AXIS));
//	    newlabelPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    newfNameLbl = new JLabel("First Name");
	    newfNameLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    newlNameLbl = new JLabel("Last Name");
	    newlNameLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    newcityLbl = new JLabel("Hometown");
	    newcityLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD,14));
	    newlabelPanel.add(newfNameLbl);
	    newlabelPanel.add(newlNameLbl);
	    newlabelPanel.add(newcityLbl);
	    
	    JPanel newtextPanel = new JPanel();
//	    textPanel.setLayout (new BoxLayout (textPanel, BoxLayout.Y_AXIS));
	    newtextPanel.setPreferredSize(new Dimension(200, 75));
	    newfNameTbx = new JTextField(15);
	    newlNameTbx = new JTextField(15);
	    newcityTbx = new JTextField(15);
	    newtextPanel.add(newfNameTbx);
	    newtextPanel.add(newlNameTbx);
	    newtextPanel.add(newcityTbx);
	    
	    JPanel newinfoPanel = new JPanel();
	    newinfoPanel.setPreferredSize(new Dimension(700, 120));
	    newinfoPanel.setBorder (BorderFactory.createTitledBorder ("Create New Profile"));
	    newinfoPanel.add(newlabelPanel);
	    newinfoPanel.add(newtextPanel);
	    newButton = new JButton("Add new Profile");
	    newButton.addActionListener(new newProfileListener());
	    newinfoPanel.add(newButton);
	    
	    // Button
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setPreferredSize (new Dimension(350, 50));	    
	    exitButton = new JButton("Exit");
	    exitButton.addActionListener(new exitButtonListener());
	    buttonPanel.add(exitButton);

	    add(headerPanel);
	    add(profilePanel);
	    add(friendPanel);
	    add(newinfoPanel);
	    add(buttonPanel);
	    
	}
	
	public void populateComboBox(JComboBox comboBox, Vector<String> vector)
	{
		 comboBox.removeAllItems();
		  for(String selement : vector)
		  {
			  comboBox.addItem(selement);
		  }
	}
	
	/**
	 * Gets the input file and output file names for the
	 * createConcordanceFile method
	 * @param in the name of the test file to be used
	 * @throws Exception
	 */
	public void getFileNames(String in) throws Exception 
	{
		JFileChooser chooser = new JFileChooser();
		int status;

		chooser.setDialogTitle("Select Input File - " + in);
		status = chooser.showOpenDialog(null);

		if(status == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				filename = chooser.getSelectedFile();
				// readFile();
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				JOptionPane.showMessageDialog(null, "There is a problem with this file", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}


	}
	
	//*****************************************************************
	//  Represents a listener for button push (action) events.
	//*****************************************************************
	 private class FriendOfFriendListener implements ActionListener
	 {
	     //--------------------------------------------------------------
	     //  Converts infix expression to postfix.
	     //--------------------------------------------------------------
		  public void actionPerformed(ActionEvent e)
		  {
			  String result = "";
			  ArrayList<String> friendOfFriendList = friendGraph.friendsOfFriends(currentProfile);
			  for(String element : friendOfFriendList)
				  result += element + "\n";
			  friendOfFriendArea.setText(result);
		  }
	 }
	 
		//*****************************************************************
		//  Represents a listener for button push (action) events.
		//*****************************************************************
		 private class ProfileListener implements ActionListener
		 {
		     //--------------------------------------------------------------
		     // 
		     //--------------------------------------------------------------
			  public void actionPerformed(ActionEvent e)
			  {		//choose a file to open
				  ArrayList<String> profile = new ArrayList<String>();
				  currentProfile = (String) profileComboBox.getSelectedItem();
				  Vector<String> friendVector = friendGraph.vectorOfParticipants();
				  String returnString = "";
				  profile = friendGraph.getProfile(currentProfile);
				  fNameTbx.setText(profile.get(0));
				  lNameTbx.setText(profile.get(1));
				  cityTbx.setText(profile.get(2));
				  profile = friendGraph.listFriends(currentProfile);
				  friendVector.remove(currentProfile);
				  for(String element : profile)
				  {
					  returnString += element + "\n";
					  friendVector.remove(element);  
				  }
				  populateComboBox(addFriendComboBox, friendVector);
				  /*addFriendComboBox.removeAllItems();
				  for(String selement : friendVector)
				  {
					  System.out.println(selement);
					  addFriendComboBox.addItem(selement);
				  }*/
				  friendArea.setText(returnString);
				  friendOfFriendArea.setText("");
			  }
		 }
		 
/*		 void getFriends(File inputFile) throws FileNotFoundException
		 {
			 MyGraph graph = new MyGraph();
			 String returnString="", nextLine;
			 String firstName, lastName, city;
			 Scanner scan = new Scanner(inputFile);
			 while(scan.hasNext())
			 {
				 nextLine = scan.nextLine();
				 Scanner lineScanner = new Scanner(nextLine);
				 lineScanner.useDelimiter(":");
				 firstName = lineScanner.next();
				 lastName = lineScanner.next();
				 city = lineScanner.next();
				 graph.addVertex(new Friend(firstName, lastName, city));
			 }
		 }*/

		 private class newProfileListener implements ActionListener
		 {
			 //--------------------------------------------------------------
			 // If exitButton, exit the program
			 //--------------------------------------------------------------
			 public void actionPerformed(ActionEvent e)
			 {
				 String firstN = newfNameTbx.getText();
				 String lastN = newlNameTbx.getText();
				 String homeT = newcityTbx.getText();
				 
				 friendGraph.addParticipant(firstN,lastN,homeT);
				 Vector<String> friends = friendGraph.vectorOfParticipants();
				 profileComboBox.removeActionListener(profile);
				 populateComboBox(profileComboBox, friends);
				 profileComboBox.addActionListener(profile);
				/* profileComboBox.removeAllItems();
				  for(String selement : friends)
				  {
					  profileComboBox.addItem(selement);
				  }*/
			 }
		 }   
	 // *********************************************************
	 //  Represents an action listener for the exitButton field.
	 //**********************************************************
	   private class exitButtonListener implements ActionListener
	   {
	      //--------------------------------------------------------------
	      // If exitButton, exit the program
	      //--------------------------------------------------------------
	      public void actionPerformed(ActionEvent e)
	      {
	           System.exit(0);
	      }
	   }   
	   
	   private class addAFriendListener implements ActionListener
	   {
	      //--------------------------------------------------------------
	      // If exitButton, exit the program
	      //--------------------------------------------------------------
	      public void actionPerformed(ActionEvent e)
	      {
	    	  ArrayList<String> profile = new ArrayList<String>();
	    	  String returnString="";
	    	  String newFriend = (String) addFriendComboBox.getSelectedItem();
	    	  Vector<String> friends = friendGraph.vectorOfParticipants();
	    //	  int newFriendIndex = addFriendComboBox.getSelectedIndex();
	    //	  addFriendComboBox.remove(newFriendIndex);
	    //	  addFriendComboBox.validate();
	    	  friendGraph.addFriend(currentProfile, newFriend);
	    	  profile = friendGraph.listFriends(currentProfile);
			  for(String element : profile)
			  {
				  returnString += element + "\n"; 
				  friends.remove(element);
			  }
			  friends.remove(currentProfile);
			  populateComboBox(addFriendComboBox, friends);
			  friendArea.setText(" ");
			  friendArea.setText(returnString);
	      }
	   }   
}
