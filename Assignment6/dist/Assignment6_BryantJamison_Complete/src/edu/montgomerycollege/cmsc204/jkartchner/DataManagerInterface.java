package edu.montgomerycollege.cmsc204.jkartchner;

import edu.montgomerycollege.cmsc204.jbryant.Friend;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Interface for the Data Manager of a FriendGraph which implements Graph
 * Contains an object of the FriendGraph
 * @author JWISNIEW
 *
 */
public interface DataManagerInterface {

	/**
	 * Returns the friends of the friends of a participant
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return An ArrayList of all the friends of friends in alphabetical 
	 * order by last name.  The ArrayList does not include the participant or the friends 
	 * of the participant.
	 * Example: [Karen Kirkland of Honolulu, HI, Maple Myers of Cleveland, OH,
	 * Nathaniel Nestle of Hershey, PA, . . ., Yolonda Yellow of Franklin, TN]
	 */
	public ArrayList<String> friendsOfFriends(String name);
	
	/**
	 * Returns the friends of the friends of a participant
	 * @param fname participants first name
	 * @param lname participants last name
	 * @param hometown participants home town
	 * @return An ArrayList of all the friends of friends in alphabetical 
	 * order by last name.  The ArrayList does not include the participant or the friends 
	 * of the participant.
	 * Example: [Karen Kirkland of Honolulu, HI, Maple Myers of Cleveland, OH,
	 * Nathaniel Nestle of Hershey, PA, . . ., Yolonda Yellow of Franklin, TN]
	 */
	public ArrayList<String> friendsOfFriends(String fname, String lname, String hometown);
	
	/**
	 * Returns the friends of a participant
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 */
	public ArrayList<String> listFriends(String name);
	
	/**
	 * Returns the friends of a participant
	 * @param fname participants first name
	 * @param lname participants last name
	 * @param hometown participants hometown
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 */
	public ArrayList<String> listFriends(String fname, String lname, String hometown);
	
	/**
	 * Returns the friends of a participant
	 * @param f a reference to a Friend object
	 * @return  An ArrayList of all the friends of a participant in alphabetical 
	 * order.  The ArrayList does not include the participant.
	 * Example: [Eric Epstein of Alexandria, VA, Fred Flinstone of Galveston, TX,
	 * Ida Ignome of Carson City, NV, . . ., Patricia Pagan of Fargo, ND]
	 * Used for testing
	 */
	public ArrayList<String> listFriends(Friend f);
	
	
	/**
	 * Returns the first name, last name and hometown in an ArrayList
	 * @param name String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @return An ArrayList of first name, last name and hometown
	 * Example: [Ann, Abbott, San Diego, CA]
	 */
	public ArrayList<String> getProfile(String name);
	
	
	/**
	 * Returns a vector of Participants for populating JComboBoxes
	 * in alphabetical order
	 * @return vector of Participants for populating JComboBoxes
	 * in alphabetical order
	 */
	public Vector<String> vectorOfParticipants();
	
	/**
	 * Adds a participant (vertex) to the Friend Graph
	 * @param fName first name, Example: "Ann"
	 * @param lName lastname, Example: "Abbott"
	 * @param city Hometown, HomeState, Example: "San Diego, CA"
	 */
	public void addParticipant(String fName, String lName, String city);
	
	/**
	 * Add a friend to a participant (edge)
	 * @param profile String of participant in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Ann Abbott of San Diego, CA"
	 * @param newFriend String of friend in the form 
	 * "<first name> <last name> of <hometown>"
	 * Example: "Larry Lobster of Knox, ME"
	 */
	public void addFriend(String profile, String newFriend);
	
	/**
	 * Add a friend to a participant (edge)
	 * @param profileFname participants first name 
	 * @param profileLname participants last name
	 * @param profileHometown participants hometown
	 * @param newFriendFname first name of new friend
	 * @param newFriendLname last name  of new friend
	 * @param newFriendHometown home town of new friend
	 * 
	 */
	public void addFriend(String profileFname, String profileLname, String profileHometown, String newFriendFname,
						  String newFriendLName, String newFriendHometown);
	
	/**
	 * Used to populate the participants (verticies) in the FriendGraph
	 * for testing purposes
	 * @param participantsFile File reference
	 * @throws FileNotFoundException
	 */
	public void populateParticipants(File participantsFile) throws FileNotFoundException;
	
	/**
	 * Used to populate the friends (edges) in the FriendGraph
	 * for testing purposes
	 * @param friendsFile File reference
	 * @throws FileNotFoundException
	 */
	public void populateFriends(File friendsFile) throws FileNotFoundException;

}
