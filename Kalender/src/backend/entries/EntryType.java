package backend.entries;

public enum EntryType {

	SimpleEntry("entry"),
	Deadline("deadline");
	
	public static final String SIMPLE_ENTRY = "entry";
	public static final String DEADLINE = "deadline";
	
	public final String type;
	private EntryType(String t) {
		type = t;
	}
}
