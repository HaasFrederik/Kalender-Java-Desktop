package frontend.container.entry;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import backend.dating.Day;
import backend.entries.Entry;
import backend.entries.EntryType;
import backend.entries.RepeatRate;
import backend.interactivity.Functionality;
import backend.interactivity.UserAction;
import frontend.components.MyButton;
import frontend.components.MyCheckBox;
import frontend.container.MyFrame;
import frontend.container.day.DayFrame;
import main.Main;

public class EntryAuthor extends JPanel {

//	flag to differentiate between editor and creator function of the Author
	boolean isEditor;
	public MyFrame frame = new MyFrame();
	
//	Components to read out user-input
	public JTextField nameField = new JTextField();
	public JTextField dateField = new JTextField("TT.MM.JJJJ");
	public MyCheckBox simpleEntryCheckBox = new MyCheckBox("Eintrag");
	public MyCheckBox deadlineCheckBox = new MyCheckBox("Deadline");
	public MyCheckBox pointInTimeCheckBox = new MyCheckBox("Zeitpunkt");
	public JTextField pointInTimeField = new JTextField("HH:MM");
	public MyCheckBox timeRangeCheckBox = new MyCheckBox("Von - Bis");
	public JTextField timeRangeField = new JTextField("HH:MM - HH:MM");
	public JTextArea descriptionField = new JTextArea();
	public JScrollPane descriptionScroller = new JScrollPane(descriptionField);
	public MyCheckBox repeatCheckBox = new MyCheckBox("Wiederholt sich ");
	public JComboBox<String> repeatRateSelector = new JComboBox<String>(new String[] {"jeden Tag", "jede Woche", "alle zwei Wochen", "jeden Monat", "jedes Jahr"});
	public MyCheckBox lastRepeatDateCheckBox = new MyCheckBox("Wiederholt sich bis zum : ");
	public JTextField lastRepeatDateField = new JTextField("TT.MM.JJJJ");
	public MyCheckBox excludeDatesCheckBox = new MyCheckBox("Ausnahmen von der Wiederholung : ");
	public JTextField excludedDatesField = new JTextField("TT.MM.JJJJ,TT.MM.JJJJ,...");
	
	
//	Components for readability
	public JLabel nameFieldLabel = new JLabel("Name : ");
	public JLabel typeSelectorLabel = new JLabel("Art : ");
	public JLabel dateFieldLabel = new JLabel("Datum : ");
	public JLabel timeLabel = new JLabel("Uhrzeit : ");
	public JLabel descriptionLabel = new JLabel("Beschreibung : ");
	
//	Components with author extern interactions
	MyButton saveButton = new MyButton("Bestätigen");
	MyButton cancelButton = new MyButton("Abbrechen");
	
	
	public EntryAuthor(boolean isCreator) {
		super();
		isEditor = !isCreator;
		LayoutManager layout = new GridLayout(12,2);
		((GridLayout) layout).setVgap(5);
		((GridLayout) layout).setHgap(5);
		setLayout(layout);
		setAlignmentX(Component.LEFT_ALIGNMENT);
		setBorder(new EmptyBorder(10,10,10,10));
//		add Components
		addComponents();
		addInteractions();
//		contents dependent on the isEditor/isCreator flags
		primeComponents();
		
		
		frame.setContentPane(this);
		
		Main.mainFrame.setEnabled(false);

	}
	
//	*** convenience; mostly to keep constructor short-ish
	private void primeComponents() {
		for (Component c : listComponents()) {
			if (!(c instanceof JTextArea || c instanceof JTextField)) {
				c.setFocusable(false);
			}
		}
		if (!isEditor) {
			dateField.setText(((DayFrame)Main.mainFrame.dayFrame).displayedDay.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			simpleEntryCheckBox.setSelected(true);
			simpleEntryCheckBox.setEnabled(false);
			timeRangeCheckBox.setSelected(true);
			timeRangeCheckBox.setEnabled(false);

			repeatRateSelector.setEnabled(false);
			lastRepeatDateCheckBox.setEnabled(false);
			lastRepeatDateField.setEnabled(false);
			excludeDatesCheckBox.setEnabled(false);
			excludedDatesField.setEnabled(false);
		} else {
//			if isEditor -> gets data from DayFrame and EntryFrame
			Entry entry = ((EntryFrame)Main.mainFrame.entryFrame).displayedEntry;
			nameField.setText(entry.name);
			dateField.setText(entry.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			switch(entry.entryType) {
			case Deadline:
				deadlineCheckBox.checkBox.setSelected(true);
				break;
			case SimpleEntry:
				simpleEntryCheckBox.checkBox.setSelected(true);
				break;
			default:
				break;
			}
			if (entry.start.equals(entry.end)) {
				pointInTimeCheckBox.checkBox.setSelected(true);
				pointInTimeField.setText(entry.start.format(DateTimeFormatter.ofPattern("HH:mm")));
			} else {
				timeRangeCheckBox.checkBox.setSelected(true);
				timeRangeField.setText(entry.start.format(DateTimeFormatter.ofPattern("HH:mm")) + " - " + entry.end.format(DateTimeFormatter.ofPattern("HH:mm")));
			}
			descriptionField.setText(entry.description);
			if(entry.isRepeating) {
				repeatCheckBox.checkBox.setSelected(true);
				repeatRateSelector.setSelectedItem(entry.repeatRate.toGermanString());
				if (entry.stopsRepeat) {
					lastRepeatDateCheckBox.checkBox.setSelected(true);
					lastRepeatDateField.setText(entry.lastRepeatDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
				}
				if (!entry.excludedDates.isEmpty()) {
					excludeDatesCheckBox.checkBox.setSelected(true);
					String exclusionString = "";
					for (LocalDate ld : entry.excludedDates) {
						exclusionString += ld.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + ",";
					}
//					cut last comma
					exclusionString = exclusionString.substring(0, exclusionString.length()-1);
					excludedDatesField.setText(exclusionString);
				}
			}
		}
	}
	

	private List<Component> listComponents() {
		List<Component> components = new ArrayList<Component>();
		components.add(nameFieldLabel);
		components.add(nameField);
		components.add(dateFieldLabel);
		components.add(dateField);
		components.add(typeSelectorLabel);
		components.add(new JLabel());
		components.add(simpleEntryCheckBox);
		components.add(deadlineCheckBox);
		components.add(timeLabel);
		components.add(new JLabel());
		components.add(pointInTimeCheckBox);
		components.add(pointInTimeField);
		components.add(timeRangeCheckBox);
		components.add(timeRangeField);
		components.add(descriptionLabel);
		components.add(descriptionScroller);
		components.add(repeatCheckBox);
		components.add(repeatRateSelector);
		components.add(lastRepeatDateCheckBox);
		components.add(lastRepeatDateField);
		components.add(excludeDatesCheckBox);
		components.add(excludedDatesField);
		
		components.add(saveButton);
		components.add(cancelButton);
		return components;
	}
	
	private void addComponents() {
		for (Component c : listComponents()) {
			add(c);
		}
	}
	
	private void addInteractions() {
		simpleEntryCheckBox.doOn(UserAction.SelectCheckBox, Functionality.SimpleEntrySelected);
		deadlineCheckBox.doOn(UserAction.SelectCheckBox, Functionality.DeadlineSelected);
		pointInTimeCheckBox.doOn(UserAction.SelectCheckBox, Functionality.PointInTimeSelected);
		timeRangeCheckBox.doOn(UserAction.SelectCheckBox, Functionality.TimeRangeSelected);
		repeatCheckBox.doOn(UserAction.SelectCheckBox, Functionality.RepeatsSelected);
		repeatCheckBox.doOn(UserAction.DeselectCheckBox, Functionality.RepeatsDeselected);
		lastRepeatDateCheckBox.doOn(UserAction.SelectCheckBox, Functionality.LastRepeatSelected);
		lastRepeatDateCheckBox.doOn(UserAction.DeselectCheckBox, Functionality.LastRepeatDeselected);
		excludeDatesCheckBox.doOn(UserAction.SelectCheckBox, Functionality.ExcludeDatesSelected);
		excludeDatesCheckBox.doOn(UserAction.DeselectCheckBox, Functionality.ExcludeDatesDeselected);
		
		cancelButton.doOn(UserAction.LeftClick, Functionality.CancelAuthor);
		if (!isEditor) {
			saveButton.doOn(UserAction.LeftClick, Functionality.ConfirmAuthor);
		} else {
			saveButton.doOn(UserAction.LeftClick, Functionality.ConfirmEdit);
		}
		
		frame.doOn(UserAction.WindowClose, Functionality.CloseAuthor);
		
	}

	public List<String> readInput() {
//		returns either the list errorMessages
//		or a list containing a single String representation of an Entry flagged with the String "entry"
		List<String> errorMessages = new ArrayList<String>();
		List<String> entryList = new ArrayList<String>();
		entryList.add("entry");
		String name = nameField.getText();
//		name
		if (name == "") {
			errorMessages.add("Bitte einen Namen für den Eintrag angeben.");
		}
//		stuff for parsing
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//		date
		LocalDate date = null;
		try {
			date = LocalDate.parse(dateField.getText(), dateFormatter);
		} catch (DateTimeParseException e) {
			errorMessages.add("Bitte ein Datum im Format TT.MM.JJJJ angeben.");
		}
//		type
		EntryType type = null;
		if (simpleEntryCheckBox.isSelected()) {
			type = EntryType.SimpleEntry;
		} else if (deadlineCheckBox.isSelected()) {
			type = EntryType.Deadline;
		} else if (simpleEntryCheckBox.isSelected() && deadlineCheckBox.isSelected()) {
			errorMessages.add("Es kann nur ein Eintragstypen gewählt werden.");
		}
//		time
		LocalTime start = null;
		LocalTime end = null;
		if (pointInTimeCheckBox.isSelected()) {
			try {
				start = LocalTime.parse(pointInTimeField.getText(), timeFormatter);
				end = start;
			} catch (DateTimeParseException e) {
				errorMessages.add("Bitte eine Uhrzeit im Format HH:MM angeben.");
			}
		} else if (timeRangeCheckBox.isSelected()) {
			String time = timeRangeField.getText();
			time = time.replaceAll("\s", "");
			String[] times = time.split("-");
			try {
				start = LocalTime.parse(times[0], timeFormatter);
				end = LocalTime.parse(times[1], timeFormatter);
			} catch (DateTimeParseException e) {
				errorMessages.add("Bitte Zeitrahmen im Format HH:MM - HH:MM angeben.");
			}
		} else if (pointInTimeCheckBox.isSelected() && timeRangeCheckBox.isSelected()) {
			errorMessages.add("Es kann nur eine Art der Zeitangabe erfolgen.");
		}
//		description
		String description = descriptionField.getText();
//		repetition
		boolean repeats = false;
		RepeatRate rate = null;
		boolean stops = false;
		LocalDate lastRepeatDate = null;
		List<LocalDate> excludedDates = new ArrayList<LocalDate>();
		if (repeatCheckBox.isSelected()) {
			repeats = true;
			String rateString = (String) repeatRateSelector.getSelectedItem();
//			repetition rate
			switch (rateString) {
//			cases: "jeden Tag", "jede Woche", "alle zwei Wochen", "jeden Monat", "jedes Jahr"
			case "jeden Tag":
				rate = RepeatRate.Daily;
				break;
			case "jede Woche":
				rate = RepeatRate.Weekly;
				break;
			case "alle zwei Wochen":
				rate = RepeatRate.Biweekly;
				break;
			case "jeden Monat":
				rate = RepeatRate.Monthly;
				break;
			case "jedes Jahr":
				rate = RepeatRate.Yearly;
				break;
			}
//			repetition stop
			if (lastRepeatDateCheckBox.isSelected()) {
				stops = true;
				try {
					lastRepeatDate = LocalDate.parse(lastRepeatDateField.getText(), dateFormatter);
				} catch (DateTimeParseException e) {
					errorMessages.add("Bitte Datumsgrenze für die Wiederholung im Format TT.MM.JJJJ angeben, oder Option abwählen.");
				}
				
			}
//			repetition exclusions
			boolean parsingErrorFlag = false;
			if (excludeDatesCheckBox.isSelected()) {
				String exclusionString = excludedDatesField.getText();
				exclusionString = exclusionString.replaceAll("\s", "");
				String[] exclusions = exclusionString.split(",");
				for (String excl : exclusions) {
					try {
						excludedDates.add(LocalDate.parse(excl, dateFormatter));
					} catch (DateTimeParseException e) {
						parsingErrorFlag = true;
					}
				}
				if (parsingErrorFlag) {
					errorMessages.add("Bitte von Wiederholung ausgeschlossene Tage im Format TT.MM.JJJJ angeben und mit Kommata abtrennen.");
				}
			}
		}
//		no errors occurred parsing user-input
		if (errorMessages.size() > 0) {
//			TODO display error messages in Window
			errorMessages.add(0, "error");
			for (String s : errorMessages) {
				System.out.println(s);
			} return errorMessages;
		} else {
//			construct entry
			Entry entry;
			if (repeats) {
				/* arguments for repeatingEntry-constructor:
				 * LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete,
				 * boolean repeats, boolean isRepRoot, RepeatRate rate, boolean stops, LocalDate lastDate, List<LocalDate> repeatExclusions
				 */			
				entry = new Entry(date, start, end, type, name, description, repeats, true, rate, stops, lastRepeatDate, excludedDates);
			} else {
				/* Arguments for nonRepeatingEntry
				 * LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete
				 */
				entry = new Entry(date, start, end, type, name, description);				
			}
			String entryString = entry.serialize();
			entryList.add(entryString);
			return entryList;
		}
		
	}
	
	public void display() {
		frame.validate();
		frame.pack();
		frame.setVisible(true);
	}
	
	public void dispose() {
		frame.dispose();
	}
	
}
