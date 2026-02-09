package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.dating.Day;
import backend.entries.Entry;
import backend.serialization.Serialize;
import frontend.container.MainFrame;

public class Main {

	
	public static Map<LocalDate,Day> datesWithEntries = new HashMap<LocalDate,Day>();
	public static List<Entry> repeatRootEntries = new ArrayList<Entry>();
	
	public static Path saveFolderPath = Paths.get("data");
	public static Path saveFilePath = Paths.get("data/save.txt");
	
	public static MainFrame mainFrame;
	public static LocalDate today = LocalDate.now();
	
	public static void main(String[] args) throws IOException {
		if (Files.notExists(saveFolderPath)) {
			Files.createDirectories(saveFolderPath);
			
			System.out.println("hi");
//			TODO open "Calendar.exe"
		} else if (Files.exists(saveFilePath)){
			Serialize.loadFromFile(saveFolderPath);
//			TODO initialise Calendar with loaded data/open MainFrame
		}
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
}
