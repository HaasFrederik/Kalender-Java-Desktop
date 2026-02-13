package backend.dating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import backend.entries.Entry;
import main.Main;

public class Day {

	public List<Entry> entryList = new ArrayList<Entry>();
	public LocalDate date;

	public Day(LocalDate d) {
		date = d;
	}

	public Day(LocalDate d, Entry e) {
		date = d;
		entryList = new ArrayList<Entry>();
		entryList.add(e);
	}

	public void addRepeatingEntries() {
//		check all repeating Entry roots
		for (Entry repRoot : Main.repeatRootEntries) {
//			check: if repRootDate is before date; if yes continue
			if (repRoot.date.isBefore(date)) {
//				check: if date has been excluded; if not continue on
				if (!repRoot.excludedDates.contains(date)) {
//					Check: if repeat does not stop || (if repeat stops && if date is before or equal to stop date); if yes continue on
					if (!repRoot.stopsRepeat || (repRoot.stopsRepeat && (date.isBefore(repRoot.lastRepeatDate)) || (date.isEqual(repRoot.lastRepeatDate)))) {
//						check: if repeat hits date; if yes continue on
						if (repRoot.repeatRate.checkForRepeat(repRoot.date, date)) {
//							check: if repeat has already been added; if not create entry
							if (!repRoot.knownRepeats.containsKey(date)) {
								Entry rep = new Entry(date, repRoot);
//								check: if repeat is known to be completed; yes -> set isCompleted to true
								if (repRoot.completedDates.contains(date)) {
									rep.isCompleted = true;
								}
//								add entry to day
								entryList.add(rep);
							}
						}
					}
				}
			}
		}
	}

}
