package edu.montgomerycollege.cmsc204.jmeyers;

/** Guarantees that Person objects have definitions for hashCode, equals, getKey, getters
 *  STUDENT - provide static methods hashKey and isValidKay as described below in class that
 *  implements this interface.
 * 
 * @author Professor Myers
 *
 */
public interface PersonInterface extends Keyable {
	
	/**
	 * Return first name of person
	 * @return first name of person
	 */
	public String getFname();
	
	/**
	 * Return last name of person
	 * @return last name of person
	 */
	public String getLname();
	
	/**
	 * Return phone number of person
	 * @return phone number of person
	 */
	public String getPhone();
	
	/**
	 * Return address of person
	 * @return address of person
	 */
	public String getAddress();
	/**
	 * compute the hash code of Person
	 * key is phone number, phone number is a string in the form: <br/>
	 *      (XXX)XXX-XXXX
	 * hashcode = (p1 * (areaCode + p2 * exchangeCode) + extensionCode)
	 * p1 is the prime 23 and p2 is the prime 31
	 * In the phone number (555)123-4567
	 * areaCode = 555
	 * exchangeCode = 123
	 * extensionCode = 4567
	 * @return hash code of phone number
	 */
	public int hashCode();

	/**
	 * compute the hash code of a phone number
	 * phone number, phone number is a string in the form: <br/>
	 *      (XXX)XXX-XXXX
	 * hashcode = (p1 * (areaCode + p2 * exchangeCode) + extensionCode)
	 * p1 is the prime 23 and p2 is the prime 31
	 * In the phone number (555)123-4567
	 * areaCode = 555
	 * exchangeCode = 123
	 * extensionCode = 4567
	 * @param key the phone number
	 * @return hash code of phone number
	 */
	//STUDENT create this as a static method
	//public static int hashKey(String key);
	
	/**
	 * Checks if this String is a valid key for a Person object
	 * A valid key is in the form (XXX)XXX-XXXX, where X is a numeric
	 * @param s String being checked for validity
	 * @return true if s is in form (XXX)XXX-XXXX, where X is a numeric
	 */
	//STUDENT create this as a static method
	//public static boolean isValidKey(String s);
	
	/**
	 * Compares the phone numbers of each of the Person objects
	 * phone number is a string in the form: (XXX)XXX-XXXX
	 * @param p Person object
	 * @return true if phone numbers are the same or else false
	 */
	
	public boolean equals(PersonInterface p);
	
	/**
	 * Returns the key for the Person object which is in the form:
	 *      (XXX)XXX-XXXX
	 * @return the phone number in the form (XXX)XXX-XXXX
	 */
	public String getKey();
	

}
