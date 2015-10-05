public abstract class Location {
	String name = "";

	Location(String inName){
		name = inName;
	}
	
	public String toString() {
		return name;
	}

}
