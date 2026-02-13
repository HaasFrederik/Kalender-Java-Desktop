package backend.serialization;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import backend.dating.Day;
import backend.entries.Entry;
import main.Main;

public class Serialize {

	public static void loadFromFile(Path path) throws IOException {
		List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
		for (String line : lines) {
			Entry newEntry = new Entry(line);
			Main.serializedEntries.put(newEntry, line);
			if (Main.datesWithEntries.keySet().contains(newEntry.date)) {
				Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
			} else {
				Day newDay = new Day(newEntry.date, newEntry);
				Main.datesWithEntries.put(newDay.date, newDay);
			}
			if (newEntry.isRepeatRoot) {
				Main.repeatRootEntries.add(newEntry);
			}
		}
	}
	
	public static void saveToFile(Path path) throws IOException {
		List<String> entryLines = new ArrayList<String>();
		for (String s : Main.serializedEntries.values()) {
			entryLines.add(s);
		}
//		for (Day day : Main.datesWithEntries.values()) {
//			for (Entry e : day.entryList) {
//				entryLines.add(e.serialize());
//			}
//		}
		Files.write(path, entryLines, StandardCharsets.UTF_8);
	}
	
}
