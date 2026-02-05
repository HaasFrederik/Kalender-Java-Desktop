package backend.entries;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Entry {

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
	public List<LocalDate> excludedDates;
}
