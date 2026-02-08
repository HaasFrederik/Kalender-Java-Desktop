package backend.entries;

public enum EntryType {

	SimpleEntry("Eintrag"),
	Deadline("Deadline");
	
	public static final String SIMPLE_ENTRY = "Eintrag";
	public static final String DEADLINE = "Deadline";
	
	public final String type;
	private EntryType(String t) {
		type = t;
	}
}
