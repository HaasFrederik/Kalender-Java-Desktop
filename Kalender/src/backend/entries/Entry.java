package backend.entries;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Entry {

//	fields determining behaviour on (de-)serialisation
	public LocalDate date;
	public LocalTime start;
	public LocalTime end;
	public EntryType entryType;
	public String name;
	public String description;
	public boolean isCompleted;
	
	public boolean isRepeating;
	public boolean isRepeatRoot;
	public LocalDate repeatRootDate;
	public RepeatRate repeatRate;
	public boolean stopsRepeat;
	public LocalDate lastRepeatDate;
	public List<LocalDate> excludedDates; //only written, when repeated entries are deleted
	
	
	private class Serialize {
		private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		private static final String listSeparator = "<>";
		private static final String sublistSeparator = "><";
	}
	
//	constructor for non-repeating Entry-Objects
	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete) {
		this.date = date;
		this.start = start;
		this.end = end;
		entryType = type;
		this.name = name;
		description = descr;
		isCompleted = complete;
		isRepeating = false;
		isRepeatRoot = false;
		repeatRootDate = null;
		repeatRate = null;
		stopsRepeat = false;
		lastRepeatDate = null;
		excludedDates = null;
	}
	
//	constructor for repeat-root Entry-Objects
	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete, 
			boolean repeats, boolean isRepRoot, RepeatRate rate, boolean stops, LocalDate lastDate) {
		this.date = date;
		this.start = start;
		this.end = end;
		entryType = type;
		this.name = name;
		description = descr;
		isCompleted = complete;
		
		isRepeating = repeats;
		isRepeatRoot = isRepRoot;
		repeatRootDate = date;
		repeatRate = rate;
		stopsRepeat = stops;
		lastRepeatDate = lastDate;
	}
	
//	constructor for repeated Entry-Objects
	public Entry(LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete, 
			boolean repeats, boolean isRepRoot, LocalDate repRoot) {
		this.date = date;
		this.start = start;
		this.end = end;
		entryType = type;
		this.name = name;
		description = descr;
		isCompleted = complete;
		
		isRepeating = repeats;
		isRepeatRoot = isRepRoot;
		repeatRootDate = repRoot;
	}
	
//	recreates Entry from output of Entry.toString()
	public Entry(String serializedEntry) {
		String[] fields = serializedEntry.split(Serialize.listSeparator);
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
			lastRepeatDate = LocalDate.parse(fields[10], Serialize.dateFormatter);
			if (fields[11].length() > 0) {
				String[] exclusions = fields[11].split(Serialize.sublistSeparator);
				excludedDates = new ArrayList<LocalDate>();
				for (String excl : exclusions) {
					excludedDates.add(LocalDate.parse(excl,Serialize.dateFormatter));
				}
			} else excludedDates = new ArrayList<LocalDate>();
		} else {
			isRepeating = false;
			repeatRootDate = null;
			repeatRate = null;
			stopsRepeat = false;
			lastRepeatDate = null;
			excludedDates = null;
		}
		
		
	}
	
	public String serialize() {
		String dateString = date.format(Serialize.dateFormatter);
		String timeStartString = start.format(Serialize.timeFormatter);
		String timeEndString = end.format(Serialize.timeFormatter);
		String typeString = entryType.type;
		String completeString;
		if (isCompleted) completeString = "true";
		else completeString = "false";
		
		String repRootString;
		String repRateString = "";
		String stopsRepString = "";
		String lastRepDateString = "";
		String excludedDatesString = "";
		
		
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
			}
			
		}
		else repRootString = "false";
		
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
			+ lastRepDateString +Serialize.listSeparator
			+ excludedDatesString;
	}
	
	
	public String toString() {
//		type + name + time + 
		String timeString = this.start.format(Serialize.dateFormatter);
		if (!this.start.equals(this.end)) {
			timeString = this.start.format(Serialize.dateFormatter) + " - " + this.end.format(Serialize.dateFormatter);
		}
		return this.entryType.type + " " + this.name + " " + timeString;
	}
	
}
