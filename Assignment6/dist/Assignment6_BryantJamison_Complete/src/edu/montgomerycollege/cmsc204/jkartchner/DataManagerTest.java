package edu.montgomerycollege.cmsc204.jkartchner;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import edu.montgomerycollege.cmsc204.jbryant.DataManager;
import edu.montgomerycollege.cmsc204.jbryant.Friend;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * This is the test file for the GraphDataManager
 * Intended to be used with the Participants.txt and Friends.txt files
 * Contents of these files are listed at the end of this file
 * @author Professor Myers
 *
 */
public class DataManagerTest {
	private DataManagerInterface graphManager;	
	private static File participantsFile, friendsFile, filename;

	/**
	 * Get names of files for participants and friends
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		getFileNames("Participants.txt");
		participantsFile = filename;
		getFileNames("Friends.txt");
		friendsFile = filename;
	}
	/**
	 * Create an instance of GraphDataManager
	 * populate the contents of the participants and friends 
	 * file into the graph
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		graphManager = new DataManager();
		graphManager.populateParticipants(participantsFile);
		graphManager.populateFriends(friendsFile);
	}

	/**
	 * Set graphManager reference to null
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		graphManager = null;
	}

	/**
	 * Test the friendsOfFriends method
	 */
	@Test
	public void friendsOfFriends()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.friendsOfFriends("Bob Brown of Independance, MI");
		assertTrue(friends.get(0).equals("Ann Abbott of San Diego, CA"));
		assertTrue(friends.get(2).equals("Jane Jones of Boise, ID"));
		assertTrue(friends.get(4).equals("Nathaniel Nestle of Hershey, PA"));
		assertTrue(friends.get(6).equals("Patricia Pagan of Fargo, ND"));
		assertTrue(friends.get(8).equals("Yolonda Yellow of Franklin, TN"));
		assertTrue(friends.get(9).equals("Zeb Zucker of Shreveport, AL"));
	}
	
	@Test
	public void friendsOfFriendsFnameLnameHometown()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.friendsOfFriends("Bob", "Brown", "Independance, MI");
		assertTrue(friends.get(0).equals("Ann Abbott of San Diego, CA"));
		assertTrue(friends.get(2).equals("Jane Jones of Boise, ID"));
		assertTrue(friends.get(4).equals("Nathaniel Nestle of Hershey, PA"));
		assertTrue(friends.get(6).equals("Patricia Pagan of Fargo, ND"));
		assertTrue(friends.get(8).equals("Yolonda Yellow of Franklin, TN"));
		assertTrue(friends.get(9).equals("Zeb Zucker of Shreveport, AL"));
	}
	
	/**
	 * Test the listFriends(String) method
	 */
	@Test
	public void listFriendsString()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.listFriends("Derik Dunning of New Orleans, LA");
		assertTrue(friends.get(0).equals("Fred Flinstone of Galveston, TX"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Velma Varington of Beaufort, SC"));
		assertTrue(friends.get(3).equals("Wanda Wallace of Billing, MO"));
	}

	/**
	 * Test the listFriends(Friend) method
	 */
	@Test
	public void listFriendsFriend()
	{
		Friend larry = new Friend("Derik", "Dunning", "New Orleans, LA");
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.listFriends(larry);
		System.out.println(friends);
		assertTrue(friends.get(0).equals("Fred Flinstone of Galveston, TX"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Velma Varington of Beaufort, SC"));
		assertTrue(friends.get(3).equals("Wanda Wallace of Billing, MO"));
	}
	
	/**
	 * Test the listFriends(fname, lname, hometown) method
	 */
	@Test
	public void listFriendsFnameLnameHometown()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.listFriends("Derik", "Dunning", "New Orleans, LA");
		System.out.println(friends);
		assertTrue(friends.get(0).equals("Fred Flinstone of Galveston, TX"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Velma Varington of Beaufort, SC"));
		assertTrue(friends.get(3).equals("Wanda Wallace of Billing, MO"));
	}
	
	@Test
	public void getProfile()
	{
		ArrayList<String> profile = new ArrayList<String>();
		profile = graphManager.getProfile("Derik Dunning of New Orleans, LA");
		assertTrue(profile.get(0).equals("Derik"));
		assertTrue(profile.get(1).equals("Dunning"));
		assertTrue(profile.get(2).equals("New Orleans, LA"));
	}
	
	@Test
	public void vectorOfParticipants()
	{
		Vector<String> participants = new Vector<String>();
		participants = graphManager.vectorOfParticipants();
		assertTrue(participants.get(6).equals("Glenda Goodheart of Orem, UT"));
		assertTrue(participants.get(13).equals("Mark Miller of Star, WY"));
		assertTrue(participants.get(17).equals("Quentin Quimby of Souix Falls, SD"));
		assertTrue(participants.get(25).equals("Zeb Zucker of Shreveport, AL"));
		
	}
	
	@Test
	public void addParticipant()
	{
		Vector<String> participants = new Vector<String>();
		participants = graphManager.vectorOfParticipants();
		assertTrue(participants.get(6).equals("Glenda Goodheart of Orem, UT"));
		assertTrue(participants.get(12).equals("Mark Miller of Star, WY"));
		assertTrue(participants.get(17).equals("Quentin Quimby of Souix Falls, SD"));
		assertTrue(participants.get(25).equals("Zeb Zucker of Shreveport, AL"));
		graphManager.addParticipant("Felicia", "Fairheart", "College Park, MD");
		participants = new Vector<String>();
		participants = graphManager.vectorOfParticipants();
		assertTrue(participants.get(5).equals("Felicia Fairheart of College Park, MD"));
		assertTrue(participants.get(6).equals("Fred Flinstone of Galveston, TX"));
		assertTrue(participants.get(7).equals("Glenda Goodheart of Orem, UT"));
		assertTrue(participants.get(13).equals("Mark Miller of Star, WY"));
		assertTrue(participants.get(18).equals("Quentin Quimby of Souix Falls, SD"));
		assertTrue(participants.get(26).equals("Zeb Zucker of Shreveport, AL"));
	}
	
	
	@Test
	public void addFriend()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.listFriends("Harold Hanson of Pittsburg, OH");
		assertTrue(friends.get(0).equals("Eric Epstein of Alexandria, VA"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Rachel Rigdon of Montpelier, VT"));
		graphManager.addFriend("Harold Hanson of Pittsburg, OH", "Jane Jones of Boise, ID");
		friends = new ArrayList<String>();
		friends = graphManager.listFriends("Harold Hanson of Pittsburg, OH");
		assertTrue(friends.get(0).equals("Eric Epstein of Alexandria, VA"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Jane Jones of Boise, ID"));
		assertTrue(friends.get(3).equals("Rachel Rigdon of Montpelier, VT"));
	}
	
	@Test
	public void addFriendFnameLnameHometown()
	{
		ArrayList<String> friends = new ArrayList<String>();
		friends = graphManager.listFriends("Harold", "Hanson", "Pittsburg, OH");
		assertTrue(friends.get(0).equals("Eric Epstein of Alexandria, VA"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Rachel Rigdon of Montpelier, VT"));
		graphManager.addFriend("Harold", "Hanson", "Pittsburg, OH", "Jane", "Jones", "Boise, ID");
		friends = new ArrayList<String>();
		friends = graphManager.listFriends("Harold", "Hanson", "Pittsburg, OH");
		assertTrue(friends.get(0).equals("Eric Epstein of Alexandria, VA"));
		assertTrue(friends.get(1).equals("Ida Ignome of Carson City, NV"));
		assertTrue(friends.get(2).equals("Jane Jones of Boise, ID"));
		assertTrue(friends.get(3).equals("Rachel Rigdon of Montpelier, VT"));
	}
	
	/**
	 * Gets the input file and output file names for the
	 * createConcordanceFile method
	 * @param in the name of the test file to be used
	 * @throws Exception
	 */
	public static void getFileNames(String in) throws Exception 
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

}
/* Contents of the Participants.txt file:
Ann:Abbott:San Diego, CA
Maple:Myers:Cleveland, OH
Harold:Hanson:Pittsburg, OH
Bob:Brown:Independance, MI
Jane:Jones:Boise, ID
Wanda:Wallace:Billing, MO
Mark:Miller:Star, WY
Tom:Tuckett:New York, NY
Zeb:Zucker:Shreveport, AL
Catherine:Cob:Bloomington, IN
Derik:Dunning:New Orleans, LA
Eric:Epstein:Alexandria, VA
Fred:Flinstone:Galveston, TX
Glenda:Goodheart:Orem, UT
Ida:Ignome:Carson City, NV
Karen:Kirkland:Honolulu, HI
Larry:Lobster:Knox, ME
Nathaniel:Nestle:Hershey, PA
Olga:Oxford:Dover, DE
Patricia:Pagan:Fargo, ND
Quentin:Quimby:Souix Falls, SD
Rachel:Rigdon:Montpelier, VT
Samuel:Sisten:Cranston, RI
Ulysses:Ugg:Eugene, OR
Velma:Varington:Beaufort, SC
Yolonda:Yellow:Franklin, TN

Contents of the Friends.txt file:
Ann:Abbott:3:Larry:Lobster:Eric:Epstein:Ida:Ignome
Maple:Myers:2:Jane:Jones:Olga:Oxford
Harold:Hanson:1:Rachel:Rigdon
Bob:Brown:4:Mark:Miller:Eric:Epstein:Samuel:Sisten:Glenda:Goodheart
Jane:Jones:2:Karen:Kirk:Ulysses:Ugg
Wanda:Wallace:2:Patricia:Pagan:Derik:Dunning
Mark:Miller:3:Ann:Abbott:Bob:Brown:Nathaniel:Nestle
Tom:Tuckett:2:Fred:Flinstone:Maple:Myers
Zeb:Zucker:2:Samuel:Sisten:Yolonda:Yellow
Catherine:Cob:3:Wanda:Wallace:Tom:Tuckett:Zeb:Zucker
Derik:Dunning:2:Velma:Varington:Ida:Ignome
Eric:Epstein:1:Harold:Hanson
Fred:Flinstone:2:Derik:Dunning:Ann:Abbott
Glenda:Goodheart:3:Patricia:Pagan:Velma:Varington:Samuel:Sisten
Ida:Ignome:2:Karen:Kirkland:Harold:Hanson
Karen:Kirkland:2:Olga:Oxford:Yolonda:Yellow
Larry:Lobster:2:Maple:Myers:Catherine:Cob
Nathaniel:Nestle:2:Tom:Tuckett:Eric:Epstein
Olga:Oxford:2:Jane:Jones:Mark:Miller
Patricia:Pagan:3:Ann:Abbott:Rachel:Rigdon:Fred:Flinstone
Quentin:Quimby:2:Wanda:Wallace:Catherine:Cob
Rachel:Rigdon:2:Quentin:Quimby:Tom:Tuckett
Samuel:Sisten:2:Zeb:Zucker:Karen:Kirkland
Ulysses:Ugg:2:Bob:Brown:Glenda:Goodheart
Velma:Varington:2:Quentin:Quimby:Wanda:Wallace
Yolonda:Yellow:2:Ulysses:Ugg:Larry:Lobster
*/

