package edu.montgomerycollege.cmsc204.jkartchner;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
public class DataManagerTestNoFileNeeded {
	private DataManagerInterface graphManager;	
	private static File participantsFile, friendsFile, filename;
	private static PrintWriter output;

	/**
	 * Get names of files for participants and friends
	 * @throws Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		createParticipantsFile();
		createFriendFile();	
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
		//STUDENT - Make sure you have a constructor like the following in your Friend class
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
		System.out.println(participants);
		assertTrue(participants.get(6).equals("Glenda Goodheart of Orem, UT"));
		assertTrue(participants.get(12).equals("Mark Miller of Star, WY"));
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
	
	public static void createParticipantsFile()
	{
		participantsFile = new File("participants.txt");
		try {
			output = new PrintWriter(participantsFile);
			
			output.println("Ann:Abbott:San Diego, CA");
			output.println("Maple:Myers:Cleveland, OH");
			output.println("Harold:Hanson:Pittsburg, OH");
			output.println("Bob:Brown:Independance, MI");
			output.println("Jane:Jones:Boise, ID");
			output.println("Wanda:Wallace:Billing, MO");
			output.println("Mark:Miller:Star, WY");
			output.println("Tom:Tuckett:New York, NY");
			output.println("Zeb:Zucker:Shreveport, AL");
			output.println("Catherine:Cob:Bloomington, IN");
			output.println("Derik:Dunning:New Orleans, LA");
			output.println("Eric:Epstein:Alexandria, VA");
			output.println("Fred:Flinstone:Galveston, TX");
			output.println("Glenda:Goodheart:Orem, UT");
			output.println("Ida:Ignome:Carson City, NV");
			output.println("Karen:Kirkland:Honolulu, HI");
			output.println("Larry:Lobster:Knox, ME");
			output.println("Nathaniel:Nestle:Hershey, PA");
			output.println("Olga:Oxford:Dover, DE");
			output.println("Patricia:Pagan:Fargo, ND");
			output.println("Quentin:Quimby:Souix Falls, SD");
			output.println("Rachel:Rigdon:Montpelier, VT");
			output.println("Samuel:Sisten:Cranston, RI");
			output.println("Ulysses:Ugg:Eugene, OR");
			output.println("Velma:Varington:Beaufort, SC");
			output.print("Yolonda:Yellow:Franklin, TN");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void createFriendFile()
	{
		friendsFile = new File("friends.txt");
		try {
			output = new PrintWriter(friendsFile);

			output.println("Ann:Abbott:3:Larry:Lobster:Eric:Epstein:Ida:Ignome");
			output.println("Maple:Myers:2:Jane:Jones:Olga:Oxford");
			output.println("Harold:Hanson:1:Rachel:Rigdon");
			output.println("Bob:Brown:4:Mark:Miller:Eric:Epstein:Samuel:Sisten:Glenda:Goodheart");
			output.println("Jane:Jones:2:Karen:Kirkland:Ulysses:Ugg");
			output.println("Wanda:Wallace:2:Patricia:Pagan:Derik:Dunning");
			output.println("Mark:Miller:3:Ann:Abbott:Bob:Brown:Nathaniel:Nestle");
			output.println("Tom:Tuckett:2:Fred:Flinstone:Maple:Myers");
			output.println("Zeb:Zucker:2:Samuel:Sisten:Yolonda:Yellow");
			output.println("Catherine:Cob:3:Wanda:Wallace:Tom:Tuckett:Zeb:Zucker");
			output.println("Derik:Dunning:2:Velma:Varington:Ida:Ignome");
			output.println("Eric:Epstein:1:Harold:Hanson");
			output.println("Fred:Flinstone:2:Derik:Dunning:Ann:Abbott");
			output.println("Glenda:Goodheart:3:Patricia:Pagan:Velma:Varington:Samuel:Sisten");
			output.println("Ida:Ignome:2:Karen:Kirkland:Harold:Hanson");
			output.println("Karen:Kirkland:2:Olga:Oxford:Yolonda:Yellow");
			output.println("Larry:Lobster:2:Maple:Myers:Catherine:Cob");
			output.println("Nathaniel:Nestle:2:Tom:Tuckett:Eric:Epstein");
			output.println("Olga:Oxford:2:Jane:Jones:Mark:Miller");
			output.println("Patricia:Pagan:3:Ann:Abbott:Rachel:Rigdon:Fred:Flinstone");
			output.println("Quentin:Quimby:2:Wanda:Wallace:Catherine:Cob");
			output.println("Rachel:Rigdon:2:Quentin:Quimby:Tom:Tuckett");
			output.println("Samuel:Sisten:2:Zeb:Zucker:Karen:Kirkland");
			output.println("Ulysses:Ugg:2:Bob:Brown:Glenda:Goodheart");
			output.println("Velma:Varington:2:Quentin:Quimby:Wanda:Wallace");
			output.print("Yolonda:Yellow:2:Ulysses:Ugg:Larry:Lobster");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

