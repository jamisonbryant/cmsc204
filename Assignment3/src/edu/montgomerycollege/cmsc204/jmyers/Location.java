package edu.montgomerycollege.cmsc204.jmyers;

public abstract class Location {
	String name = "";

	Location(String inName){
		name = inName;
	}
	
	public String toString() {
		return name;
	}

}
