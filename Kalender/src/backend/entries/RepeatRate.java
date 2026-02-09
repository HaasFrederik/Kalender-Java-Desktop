package backend.entries;

import java.time.LocalDate;

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
	
	public String toGermanString() {
		switch (type) {
		case DAILY:
			return "jeden Tag";
		case WEEKLY:
			return "jede Woche";
		case BIWEEKLY:
			return "alle zwei Wochen";
		case MONTHLY:
			return "jeden Monat";
		case YEARLY:
			return "jedes Jahr";
			default:
				return "???";
		}
	}
	
//	returns true if repeat hits checkdate coming from rootDate
	public boolean checkForRepeat(LocalDate rootDate, LocalDate checkDate) {
		LocalDate iterDate = rootDate;
		switch(this) {
		case Daily:
			return true;
		case Weekly:
//			if rootDate == checkDate entry is already in rootDay
			do {
				iterDate = iterDate.plusWeeks(1);
				if (iterDate.isEqual(checkDate)) return true;
			} while(iterDate.isBefore(checkDate));
			return false;
		case Biweekly:
//			if rootDate == checkDate entry is already in rootDay
			do {
				iterDate = iterDate.plusWeeks(2);
				if (iterDate.isEqual(checkDate)) return true;
			} while(iterDate.isBefore(checkDate));
			return false;
		case Monthly:
//			if rootDate == checkDate entry is already in rootDay
			do {
				iterDate = iterDate.plusMonths(1);
				if (iterDate.isEqual(checkDate)) return true;
			} while(iterDate.isBefore(checkDate));
			return false;
		case Yearly:
//			if rootDate == checkDate entry is already in rootDay
			do {
				iterDate = iterDate.plusYears(1);
				if (iterDate.isEqual(checkDate)) return true;
			} while(iterDate.isBefore(checkDate));
			return false;
		default:
			return false;
		}
	}
	
	public String type;
	private RepeatRate(String t) {
		type = t;
	}

}
