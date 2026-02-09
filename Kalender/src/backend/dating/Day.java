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
//		System.out.println("Check for repeats in Day");
//		check all repeating Entry roots
		for (Entry repRoot : Main.repeatRootEntries) {
//			check if date has been excluded; if not continue on
			if (!repRoot.excludedDates.contains(date)) {
//				System.out.println("Date  is not excluded");
				if (repRoot.stopsRepeat) {
//					System.out.println("repeat stops");
//					last repeat date is date -> add entry
					if (repRoot.lastRepeatDate.isEqual(date)) {
//						check if repeat has already been added;if not add and return
						if (!repRoot.knownRepeats.containsKey(date)) {
//							System.out.println("Date is equal to last date and unknown");
							Entry rep = new Entry(date, repRoot);
							entryList.add(rep);
							return;
						}
					}
//					check if repeat hits date
					if (repRoot.repeatRate.checkForRepeat(repRoot.date, date)) {
//						System.out.println("Date is hit by repeat");
//						check if repeat has already been added;if not add and return
						if (!repRoot.knownRepeats.containsKey(date)) {
//							System.out.println("Date is unknown");
							Entry rep = new Entry(date, repRoot);
							entryList.add(rep);
							return;
						}
					}
					
				} else {
//					System.out.println("repeat does not stop");
//					check if repeat hits date
					if (repRoot.repeatRate.checkForRepeat(repRoot.date, date)) {
//						System.out.println("Date is hit by repeat");
//						check if repeat has already been added;if not add and return
						if (!repRoot.knownRepeats.containsKey(date)) {
//							System.out.println("Date is unknown");
							Entry rep = new Entry(date, repRoot);
							entryList.add(rep);
							return;
						}
					}
				}
			}
		}
	}
	
	
	
}
