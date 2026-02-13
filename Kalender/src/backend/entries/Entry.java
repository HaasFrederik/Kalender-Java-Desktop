package backend.entries;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entry {

//	fields determining behaviour on (de-)serialisation
	public LocalDate date;
	public LocalTime start;
	public LocalTime end;
	public EntryType entryType;
	public String name;
	public String description;
	public boolean isCompleted;
	
	
	public boolean isRepeatRoot;
	public LocalDate repeatRootDate;
	public RepeatRate repeatRate;
	public boolean stopsRepeat;
	public LocalDate lastRepeatDate;
	public List<LocalDate> excludedDates = new ArrayList<LocalDate>(); 
	public List<LocalDate> completedDates = new ArrayList<LocalDate>();
	
//	fields not affecting  serialisation
	public Entry repeatRoot;
	public boolean isRepeating;
	public Map<LocalDate, Entry> knownRepeats = new HashMap<LocalDate, Entry>();
//	public List<Entry> knownRepeats = new ArrayList<Entry>();
	
	
	private class Serialize {
		private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		private static final String listSeparator = "<>";
		private static final String sublistSeparator = "><";
	}
	
//	constructor for non-repeating Entry-Objects
	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr) {
		this.date = date;
		this.start = start;
		this.end = end;
		entryType = type;
		this.name = name;
		description = descr;
		isCompleted = false;
		isRepeating = false;
		isRepeatRoot = false;
		repeatRootDate = null;
		repeatRoot = null;
		repeatRate = null;
		stopsRepeat = false;
		lastRepeatDate = null;
		excludedDates = new ArrayList<LocalDate>();
		knownRepeats = new HashMap<LocalDate, Entry>();
		repeatRoot = null;
	}
	
//	constructor for repeat-root Entry-Objects
	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr,
			boolean repeats, boolean isRepRoot, RepeatRate rate, boolean stops, LocalDate lastDate, List<LocalDate> repeatExclusions) {
		this.date = date;
		this.start = start;
		this.end = end;
		entryType = type;
		this.name = name;
		description = descr;
		isCompleted = false;
		
		isRepeating = repeats;
		isRepeatRoot = isRepRoot;
		repeatRootDate = date;
		repeatRoot = this;
		repeatRate = rate;
		stopsRepeat = stops;
		lastRepeatDate = lastDate;
		excludedDates = repeatExclusions;
		knownRepeats = new HashMap<LocalDate, Entry>();
		repeatRoot = this;
	}
	
//	constructor for repeated Entry-Objects
	public Entry(LocalDate date, Entry repRoot) {
		this.date = date;
		start = repRoot.start;
		end = repRoot.end;
		entryType = repRoot.entryType;
		name = repRoot.name;
		description = repRoot.description;
		isCompleted = false;
		isRepeating = true;
		isRepeatRoot = false;
		repeatRate = repRoot.repeatRate;
		repeatRoot = repRoot;
		repeatRootDate = repRoot.date;	
		excludedDates = repRoot.excludedDates;
		if (!repRoot.knownRepeats.containsKey(date)) {
			repRoot.knownRepeats.put(date, this);
		}
		knownRepeats = repRoot.knownRepeats;
		completedDates = repRoot.completedDates;
		stopsRepeat = repRoot.stopsRepeat;
		lastRepeatDate = repRoot.lastRepeatDate;
	}
	
//	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr,
//			boolean repeats, boolean isRepRoot, LocalDate repRoot) {
//		this.date = date;
//		this.start = start;
//		this.end = end;
//		entryType = type;
//		this.name = name;
//		description = descr;
//		isCompleted = false;
//		
//		isRepeating = repeats;
//		isRepeatRoot = isRepRoot;
//		repeatRootDate = repRoot;
//	}
	
//	recreates Entry from output of Entry.toString()
	public Entry(String serializedEntry) {
		String[] fields = serializedEntry.split(Serialize.listSeparator);
		for (int i = 0; i < fields.length; i++) {
			fields[i] = fields[i].trim();
		}
		date = LocalDate.parse(fields[0], Serialize.dateFormatter);
		start = LocalTime.parse(fields[1], Serialize.timeFormatter);
		end = LocalTime.parse(fields[2], Serialize.timeFormatter);
		name = fields[3];
		description = fields[4];
		switch(fields[5]) {
		case EntryType.SIMPLE_ENTRY:
			entryType = EntryType.SimpleEntry;
			break;
		case EntryType.DEADLINE:
			entryType = EntryType.Deadline;
			break;			
		}
		isCompleted = Boolean.parseBoolean(fields[6]);
		
		
		isRepeatRoot = Boolean.parseBoolean(fields[7]);
		if (isRepeatRoot) {
			isRepeating = true;
			repeatRootDate = date;
			switch(fields[8]) {
			case RepeatRate.DAILY:
				repeatRate = RepeatRate.Daily;
				break;
			case RepeatRate.WEEKLY:
				repeatRate = RepeatRate.Weekly;
				break;
			case RepeatRate.BIWEEKLY:
				repeatRate = RepeatRate.Biweekly;
				break;
			case RepeatRate.MONTHLY:
				repeatRate = RepeatRate.Monthly;
				break;
			case RepeatRate.YEARLY:
				repeatRate = RepeatRate.Yearly;
				break;
			}
			stopsRepeat = Boolean.parseBoolean(fields[9]);
			if (fields[10].length() > 0) lastRepeatDate = LocalDate.parse(fields[10], Serialize.dateFormatter);
			if (fields[11].length() > 0) {
				String[] exclusions = fields[11].split(Serialize.sublistSeparator);
				excludedDates = new ArrayList<LocalDate>();
				for (String excl : exclusions) {
					excludedDates.add(LocalDate.parse(excl,Serialize.dateFormatter));
				}
			} else excludedDates = new ArrayList<LocalDate>();
			if (fields[12].length() > 0) {
				String[] completions = fields[12].split(Serialize.sublistSeparator);
				completedDates = new ArrayList<LocalDate>();
				for (String compl : completions) {
					completedDates.add(LocalDate.parse(compl, Serialize.dateFormatter));
				}
			} else completedDates = new ArrayList<LocalDate>();
		} else {
			isRepeating = false;
			repeatRootDate = null;
			repeatRate = null;
			stopsRepeat = false;
			lastRepeatDate = null;
			excludedDates = new ArrayList<LocalDate>();
			completedDates = new ArrayList<LocalDate>();
		}
		
		
	}
	
	public String serialize() {
//		returns serialisation of entry; if isRepeating==true && isRepRoot==false returns null or empty String
		String dateString = date.format(Serialize.dateFormatter);
		String timeStartString = start.format(Serialize.timeFormatter);
		String timeEndString = end.format(Serialize.timeFormatter);
		String typeString = entryType.type;
		String completeString;
		if (isCompleted) completeString = "true";
		else completeString = "false";
		
//		whitespace necessary for split-operator during deserialisation
		String repRootString;
		String repRateString = " ";
		String stopsRepString = " ";
		String lastRepDateString = " ";
		String excludedDatesString = "";
		String completedDatesString = "";
		
		
		if (isRepeatRoot) {
			repRootString = "true";
			repRateString = repeatRate.type;
			if (stopsRepeat) {
				stopsRepString = "true";
				lastRepDateString = lastRepeatDate.format(Serialize.dateFormatter);
			}
			else stopsRepString = "false";
			if (!excludedDates.isEmpty()) {
				int lastIndex = excludedDates.size()-1;
				for (LocalDate excluded : excludedDates) {
					excludedDatesString += excluded.format(Serialize.dateFormatter);
					// -> add sublistSeparator only if not last element
					if (excludedDates.indexOf(excluded) < lastIndex) {
						excludedDatesString += Serialize.sublistSeparator;
					}
				}
			} else excludedDatesString = " ";
			if (!completedDates.isEmpty()) {
				int lastIndex = completedDates.size()-1;
				for (LocalDate completed : completedDates) {
					completedDatesString += completed.format(Serialize.dateFormatter);
					// -> add sublistSeparator only if not last element
					if (completedDates.indexOf(completed) < lastIndex) {
						completedDatesString += Serialize.sublistSeparator;
					}
				}
			} else completedDatesString = " ";
			
		}
//		is not repRoot, but is repeating (generated entry)
		else if (isRepeating) {
			return "";
		} else {
//			is not repeating
			repRootString = "false";
			excludedDatesString = " ";
			completedDatesString = " ";
		}
		
		return  dateString + Serialize.listSeparator 
			+ timeStartString + Serialize.listSeparator
			+ timeEndString + Serialize.listSeparator
			+ name + Serialize.listSeparator
			+ description +Serialize.listSeparator
			+ typeString + Serialize.listSeparator
			+ completeString + Serialize.listSeparator
			+ repRootString + Serialize.listSeparator
			+ repRateString + Serialize.listSeparator
			+ stopsRepString + Serialize.listSeparator
			+ lastRepDateString + Serialize.listSeparator
			+ excludedDatesString + Serialize.listSeparator
			+ completedDatesString;
	}
	
	
	public String toString() {
//		type + name + time + 
		String timeString = this.start.format(Serialize.timeFormatter);
		if (!this.start.equals(this.end)) {
			timeString = this.start.format(Serialize.timeFormatter) + " - " + this.end.format(Serialize.timeFormatter);
		}
		return this.entryType.type + " " + this.name + " " + timeString;
	}
	
//	if there is no next date returns date of entry
	public LocalDate nextRepeatDate() {
		LocalDate candidate = date;
		if (isRepeating) {
			switch(repeatRate) {
			case Biweekly:
				candidate = candidate.plusWeeks(2);
				while (excludedDates.contains(candidate) && (lastRepeatDate == null || candidate.isBefore(lastRepeatDate))) {
					candidate = candidate.plusWeeks(2);
				}
				if (lastRepeatDate != null && candidate.isAfter(lastRepeatDate)) {
					return date;
				}
				return candidate;
			case Daily:
				candidate = candidate.plusDays(1);				
				while (excludedDates.contains(candidate) && (lastRepeatDate == null || candidate.isBefore(lastRepeatDate))) {
					candidate = candidate.plusDays(1);
				}
				if (lastRepeatDate != null && candidate.isAfter(lastRepeatDate)) {
					return date;
				}
				return candidate;
			case Monthly:
				candidate = candidate.plusMonths(1);
				while (excludedDates.contains(candidate) && (lastRepeatDate == null || candidate.isBefore(lastRepeatDate))) {
					candidate = candidate.plusMonths(1);
				}
				if (lastRepeatDate != null && candidate.isAfter(lastRepeatDate)) {
					return date;
				}
				return candidate;
			case Weekly:
				candidate = candidate.plusWeeks(1);
				while (excludedDates.contains(candidate) && (lastRepeatDate == null || candidate.isBefore(lastRepeatDate))) {
					candidate = candidate.plusWeeks(1);
				}
				if (lastRepeatDate != null && candidate.isAfter(lastRepeatDate)) {
					return date;
				}
				return candidate;
			case Yearly:
				candidate = candidate.plusYears(1);
				while (excludedDates.contains(candidate) && (lastRepeatDate == null || candidate.isBefore(lastRepeatDate))) {
					candidate = candidate.plusYears(1);
				}
				if (lastRepeatDate != null && candidate.isAfter(lastRepeatDate)) {
					return date;
				}
				return candidate;
			default:
				return date;
			}
		} else {
			return date;
		}
	}
	
}
