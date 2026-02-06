package backend.dating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import backend.entries.Entry;

public class Day {

	public List<Entry> entryList;
	public LocalDate date;
	
	public Day(LocalDate d) {
		date = d;
	}
	
	public Day(LocalDate d, Entry e) {
		date = d;
		entryList = new ArrayList<Entry>();
		entryList.add(e);
	}
	
//	public Day(LocalDate d, List<Entry> entries) {
//		date = d;
//		entryList = entries;
//	}
	
}
