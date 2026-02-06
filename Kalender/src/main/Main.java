package main;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.dating.Day;
import backend.serialization.Serialize;

public class Main {

	
	public static Map<LocalDate,Day> datesWithEntries = new HashMap<LocalDate,Day>();
	
	public static Path saveFolderPath = Paths.get("data");
	public static Path saveFilePath = Paths.get("data/save.txt");
	
	public static void main(String[] args) throws IOException {
		if (Files.notExists(saveFolderPath)) {
			Files.createDirectories(saveFolderPath);
//			TODO open "Calendar.exe"
		} else {
			Serialize.loadFromFile(saveFolderPath);
//			TODO initialise Calendar with loaded data/open MainFrame
		}
		
	}
	
}
