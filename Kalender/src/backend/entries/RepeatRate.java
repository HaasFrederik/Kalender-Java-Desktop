package backend.entries;

public enum RepeatRate {
	
	Daily("daily"),
	Weekly("weekly"),
	Biweekly("biweekly"),
	Monthly("monthly"),
	Yearly("yearly");
	
	public static final String DAILY = "daily";
	public static final String WEEKLY = "weekly";
	public static final String BIWEEKLY = "biweekly";
	public static final String MONTHLY = "monthly";
	public static final String YEARLY = "yearly";
	
	public String type;
	private RepeatRate(String t) {
		type = t;
	}

}
