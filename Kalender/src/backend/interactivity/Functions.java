package backend.interactivity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

import backend.dating.Day;
import backend.entries.Entry;
import backend.entries.EntryType;
import backend.entries.RepeatRate;
import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.components.MonthLabel;
import frontend.components.MyButton;
import frontend.components.MyCheckBox;
import frontend.components.MyLabel;
import frontend.components.YearLabel;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.calendar.CalendarMainPanel;
import frontend.container.day.DayFrame;
import frontend.container.entry.EntryAuthor;
import main.Main;

public class Functions {
	
	
//	Only viable for Containers containing a single component, else order of components is changed
	private static void exchangeComponent(Component currComp, Component newComp) {
		Container parent = currComp.getParent();
		parent.remove(currComp);
		parent.add(newComp);
		parent.validate();
		parent.repaint();
	}
	
//	Hollows a container out and replaces its contents
	private static void exchangeComponents(Container currCont, Container newCont) {
		Component[] newComps = newCont.getComponents();
		currCont.removeAll();
		for (Component c : newComps) {
			currCont.add(c);
		}
		currCont.validate();
		currCont.repaint();
	}
	
	public class CalendarButtonFunctions {
		public static void CalendarViewGoUpToYears(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate;
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void CalendarViewGoUpToMonths(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate;
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
//		TODO change condition from look=pressed to isSelected in DayFrame
		public static void GoToPrevMonth(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.minusMonths(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
//			keep pressed DayLabels pressed, if viewable from previous month
			LocalDate pressedDate = null;
			Component[] allOldChildren = oldCalendar.mainPanel.getComponents();
			Component[] dlOldChildren = Arrays.copyOfRange(allOldChildren, 7, 48);
			for (int i = 0; i < dlOldChildren.length; i++) {
				if (((DayLabel)dlOldChildren[i]).currentLook == Look.pressed) {
					pressedDate = ((DayLabel)dlOldChildren[i]).date;
				}
			}
			if (pressedDate != null) {
				Component[] allNewChildren = newCalendar.mainPanel.getComponents();
				Component[] dlNewChildren = Arrays.copyOfRange(allNewChildren, 7, 48);
				for (int i = 0; i < dlNewChildren.length; i++) {
					if (pressedDate.isEqual(((DayLabel)dlNewChildren[i]).date)) {
						((DayLabel)dlNewChildren[i]).currentLook = Look.pressed;
						((DayLabel)dlNewChildren[i]).showCurrentLook();
					}
				}
			}
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
//		TODO change condition from look=pressed to isSelected in DayFrame
		public static void GoToNextMonth(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.plusMonths(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
//			keep pressed DayLabels pressed, if viewable from next month
			LocalDate pressedDate = null;
			Component[] allOldChildren = oldCalendar.mainPanel.getComponents();
			Component[] dlOldChildren = Arrays.copyOfRange(allOldChildren, 7, 48);
			for (int i = 0; i < dlOldChildren.length; i++) {
				if (((DayLabel)dlOldChildren[i]).currentLook == Look.pressed) {
					pressedDate = ((DayLabel)dlOldChildren[i]).date;
				}
			}
			if (pressedDate != null) {
				Component[] allNewChildren = newCalendar.mainPanel.getComponents();
				Component[] dlNewChildren = Arrays.copyOfRange(allNewChildren, 7, 48);
				for (int i = 0; i < dlNewChildren.length; i++) {
					if (pressedDate.isEqual(((DayLabel)dlNewChildren[i]).date)) {
						((DayLabel)dlNewChildren[i]).currentLook = Look.pressed;
						((DayLabel)dlNewChildren[i]).showCurrentLook();
					}
				}
			}
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void GoToPrevYear(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.minusYears(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void GoToNextYear(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.plusYears(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void GoToPrevYears(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.minusYears(15);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void GoToNextYears(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.plusYears(15);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
	}
	
	public class DayLabelFunctions {
		public static void DayLabelLookSetHighlighted(DayLabel dl) {
			if (dl.currentLook == dl.defaultLook) {
				dl.currentLook = Look.highlighted;
				dl.showCurrentLook();
			}
				
		}
		
		public static void DayLabelLookSetDefault(DayLabel dl) {
			if (!(dl.currentLook == DayLabel.Look.pressed)) {
				dl.currentLook = dl.defaultLook;
				dl.showCurrentLook();
			}
				
		}
		
		public static void DayLabelLookSetPressed(DayLabel dl) {
			CalendarMainPanel parent = (CalendarMainPanel) dl.getParent();
			Component[] allChildren = parent.getComponents();
			Component[] dlChildren = Arrays.copyOfRange(allChildren, 7, 48);
			for (int i = 0; i < dlChildren.length; i++) {
				DayLabel child = (DayLabel) dlChildren[i];
				if (child.currentLook == DayLabel.Look.pressed) {
					child.currentLook = child.defaultLook;
					child.showCurrentLook();
				}
			}
			dl.currentLook = Look.pressed;
			dl.showCurrentLook();
		}
		
		public static void ShowDayInDayFrame(DayLabel dl) {
//			check if day associated with selected date has entries or is empty, if empty create new Day Object for the date
			LocalDate selectedDate = dl.date;
			Day day; 
			if (Main.datesWithEntries.containsKey(selectedDate)) {
				day = Main.datesWithEntries.get(selectedDate);
			} else {
				day = new Day(selectedDate);
			}
//			check if repeating entries hit the day and add them as a repeatingEntry (not repRoot)
			day.addRepeatingEntries();
			if (day.entryList.size() > 0) {
				Main.datesWithEntries.put(selectedDate, day);
			}
			for (Entry e: day.entryList) {
				System.out.println(e.toString());
			}
//			switch contents of oldDayFrame with newDayFrame
			DayFrame oldDayFrame = (DayFrame) Main.mainFrame.dayFrame;
			DayFrame newDayFrame = new DayFrame(day);
			exchangeComponents(oldDayFrame, newDayFrame);
			((DayFrame) Main.mainFrame.dayFrame).displayedDay = day;
		}
		
	}
	
	public class MonthLabelFunctions {
		
		public static void CalendarViewGoDownToDays(MonthLabel ml) {
			LocalDate date = ml.monthDate;
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void MonthLabelSetHighlighted(MonthLabel ml) {
			ml.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			ml.setBackground(Color.DARK_GRAY);
			ml.setForeground(Color.ORANGE);
			ml.setOpaque(true);
		}
		
		public static void MonthLabelSetDefault(MonthLabel ml) {
			ml.setBorder(null);
			ml.setOpaque(false);
			ml.setForeground(Color.BLACK);
		}
	}
	
	public class YearLabelFunctions {
		public static void CalendarViewGoDownToMonths(YearLabel yl) {
			LocalDate date = yl.year;
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void YearLabelSetHighlighted(YearLabel yl) {
			yl.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			yl.setBackground(Color.DARK_GRAY);
			yl.setForeground(Color.ORANGE);
			yl.setOpaque(true);
		}
		
		public static void YearLabelSetDefault(YearLabel yl) {
			yl.setBorder(null);
			yl.setOpaque(false);
			yl.setForeground(Color.BLACK);
		}
	}
	
	public class DayButtonFunctions {
		
		public static void OpenEntryCreator(MyButton source) {
//			TODO make it so mainFrame is not accessible while entryAuthor is open
			
			EntryAuthor author = new EntryAuthor(true);
			author.display();
		}
	}
	
	public class EntryAuthorFunctions {
		
		public static void SimpleEntrySelected(MyCheckBox source) {
//			grey-out source; remove grey out from timeRangeCheckBox and -field
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.timeRangeCheckBox.setEnabled(true);
			parent.deadlineCheckBox.setEnabled(true);
			parent.deadlineCheckBox.setSelected(false);
			source.setEnabled(false);
		}
		
		public static void DeadlineSelected(MyCheckBox source) {
//			grey-out source, timeRangeCheckBox and -field; deselect simpleEntry and timeRange; select pointInTime
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.pointInTimeCheckBox.setSelected(true);
			parent.timeRangeCheckBox.setSelected(false);
			parent.timeRangeCheckBox.setEnabled(false);
			parent.timeRangeField.setEnabled(false);
			parent.simpleEntryCheckBox.setEnabled(true);
			parent.simpleEntryCheckBox.setSelected(false);
			source.setEnabled(false);
		}
		
		public static void TimeRangeSelected(MyCheckBox source) {
//			grey out source, pointInTimeField; enable timeRangeField; deselect pointInTimeCheckBox
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.pointInTimeCheckBox.setSelected(false);
			parent.pointInTimeCheckBox.setEnabled(true);
			parent.pointInTimeField.setEnabled(false);
			parent.timeRangeField.setEnabled(true);
			source.setEnabled(false);
		}
		
		public static void PointInTimeSelected(MyCheckBox source) {
//			grey out source, timeRangeField; deselect timeRangeCheckBox; enable pointInTimeField, timeRangeCheckBox
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.timeRangeCheckBox.setSelected(false);
			parent.timeRangeCheckBox.setEnabled(true);
			parent.timeRangeField.setEnabled(false);
			parent.pointInTimeField.setEnabled(true);
			source.setEnabled(false);
		}
		
		public static void RepeatsSelected(MyCheckBox source) {
//			enable repeat checkboxes
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.repeatRateSelector.setEnabled(true);
			parent.lastRepeatDateCheckBox.setEnabled(true);
			parent.excludeDatesCheckBox.setEnabled(true);
		}
		
		public static void RepeatsDeselected(MyCheckBox source) {
//			disable repeat checkboxes
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.repeatRateSelector.setEnabled(false);
			parent.lastRepeatDateCheckBox.setEnabled(false);
			parent.excludeDatesCheckBox.setEnabled(false);
		}
		
		public static void LastRepeatSelected(MyCheckBox source) {
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.lastRepeatDateField.setEnabled(true);
		}
		
		public static void LastRepeatDeselected(MyCheckBox source) {
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.lastRepeatDateField.setEnabled(false);
		}
		
		public static void ExcludeDatesSelected(MyCheckBox source) {
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.excludedDatesField.setEnabled(true);
		}
		
		public static void ExcludeDatesDeselected(MyCheckBox source) {
			EntryAuthor parent = (EntryAuthor) source.getParent();
			parent.excludedDatesField.setEnabled(false);
		}
		
		public static void CancelAuthor(MyButton source) {
			((EntryAuthor) source.getParent()).dispose();
		}
		
		public static void ConfirmAuthor(MyButton source) {
//			TODO readout inputs and create new entry
			List<String> errorMessages = new ArrayList<String>();
			EntryAuthor parent = (EntryAuthor) source.getParent();
			String name = parent.nameField.getText();
//			name
			if (name == "") {
				errorMessages.add("Bitte einen Namen für den Eintrag angeben.");
			}
//			stuff for parsing
			DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
//			date
			LocalDate date = null;
			try {
				date = LocalDate.parse(parent.dateField.getText(), dateFormatter);
			} catch (DateTimeParseException e) {
				errorMessages.add("Bitte ein Datum im Format TT.MM.JJJJ angeben.");
			}
//			type
			EntryType type = null;
			if (parent.simpleEntryCheckBox.isSelected()) {
				type = EntryType.SimpleEntry;
			} else if (parent.deadlineCheckBox.isSelected()) {
				type = EntryType.Deadline;
			} else if (parent.simpleEntryCheckBox.isSelected() && parent.deadlineCheckBox.isSelected()) {
				errorMessages.add("Es kann nur ein Eintragstypen gewählt werden.");
			}
//			time
			LocalTime start = null;
			LocalTime end = null;
			if (parent.pointInTimeCheckBox.isSelected()) {
				try {
					start = LocalTime.parse(parent.pointInTimeField.getText(), timeFormatter);
					end = start;
				} catch (DateTimeParseException e) {
					errorMessages.add("Bitte eine Uhrzeit im Format HH:MM angeben.");
				}
			} else if (parent.timeRangeCheckBox.isSelected()) {
				String time = parent.timeRangeField.getText();
				time = time.replaceAll("\s", "");
				String[] times = time.split("-");
				try {
					start = LocalTime.parse(times[0], timeFormatter);
					end = LocalTime.parse(times[1], timeFormatter);
				} catch (DateTimeParseException e) {
					errorMessages.add("Bitte Zeitrahmen im Format HH:MM - HH:MM angeben.");
				}
			} else if (parent.pointInTimeCheckBox.isSelected() && parent.timeRangeCheckBox.isSelected()) {
				errorMessages.add("Es kann nur eine Art der Zeitangabe erfolgen.");
			}
//			description
			String description = parent.descriptionField.getText();
//			repetition
			boolean repeats = false;
			RepeatRate rate = null;
			boolean stops = false;
			LocalDate lastRepeatDate = null;
			List<LocalDate> excludedDates = new ArrayList<LocalDate>();
			if (parent.repeatCheckBox.isSelected()) {
				repeats = true;
				String rateString = (String) parent.repeatRateSelector.getSelectedItem();
//				repetition rate
				switch (rateString) {
//				cases: "jeden Tag", "jede Woche", "alle zwei Wochen", "jeden Monat", "jedes Jahr"
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
//				repetition stop
				
				
				if (parent.lastRepeatDateCheckBox.isSelected()) {
					stops = true;
					try {
						lastRepeatDate = LocalDate.parse(parent.lastRepeatDateField.getText(), dateFormatter);
					} catch (DateTimeParseException e) {
						errorMessages.add("Bitte Datumsgrenze für die Wiederholung im Format TT.MM.JJJJ angeben, oder Option abwählen.");
					}
					
				}
//				repetition exclusions
				
				boolean parsingErrorFlag = false;
				if (parent.excludeDatesCheckBox.isSelected()) {
					String exclusionString = parent.excludedDatesField.getText();
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
//			no errors occurred parsing user-input
			if (errorMessages.size() > 0) {
//				TODO display error messages in Window
				for (String s : errorMessages) {
					System.out.println(s);
				} return;
			} else {
//				construct entry
				Entry entry;
				Day day;
				if (repeats) {
					/* arguments for repeatingEntry-constructor:
					 * LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete,
					 * boolean repeats, boolean isRepRoot, RepeatRate rate, boolean stops, LocalDate lastDate, List<LocalDate> repeatExclusions
					 */			
					entry = new Entry(date, start, end, type, name, description, repeats, true, rate, stops, lastRepeatDate, excludedDates);
					Main.repeatRootEntries.add(entry);
				} else {
					/* Arguments for nonRepeatingEntry
					 * LocalDate date, LocalTime start, LocalTime end, EntryType type, String name, String descr, boolean complete
					 */
					entry = new Entry(date, start, end, type, name, description);				
				}
//				check if day already has entries : yes -> add entry to day ; no -> create new day, add entry and add day to map
				if (Main.datesWithEntries.containsKey(date)) {
					day = Main.datesWithEntries.get(date);
					day.entryList.add(entry);
				} else {
					day = new Day(date, entry);
					Main.datesWithEntries.put(date, day);
				}
//				TODO update dayFrame only if displayedDay coincides with date of new Entry
//				switch contents of oldDayFrame with newDayFrame
				if (((DayFrame) Main.mainFrame.dayFrame).displayedDay.date.isEqual(date)) {
					DayFrame oldDayFrame = (DayFrame) Main.mainFrame.dayFrame;
					DayFrame newDayFrame = new DayFrame(day);
					exchangeComponents(oldDayFrame, newDayFrame);
				}
			}
			
			parent.dispose();
		}
		
		public static void ConfirmEdit(MyButton source) {
//			TODO edit fields of entry and handle repetition-stuff (Yay...)
		}
		
	}
	
}
