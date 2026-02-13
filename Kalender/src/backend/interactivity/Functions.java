package backend.interactivity;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;

import backend.dating.Day;
import backend.entries.Entry;
import backend.entries.EntryType;
import backend.entries.RepeatRate;
import backend.serialization.Serialize;
import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.components.EntryLabel;
import frontend.components.MonthLabel;
import frontend.components.MyButton;
import frontend.components.MyCheckBox;
import frontend.components.YearLabel;
import frontend.container.EntryEditDialog;
import frontend.container.MainFrame;
import frontend.container.MyDialog;
import frontend.container.MyFrame;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.calendar.CalendarMainPanel;
import frontend.container.day.DayFrame;
import frontend.container.entry.EntryAuthor;
import frontend.container.entry.EntryFrame;
import main.Main;

public class Functions {

	public class MyFrameFunctions {

		public static void SaveAndExit(MainFrame source) {
//			TODO serialize and save to file
			System.out.println("save exit");
			if (Files.notExists(Main.saveFolderPath)) {
				try {
					Files.createDirectories(Main.saveFolderPath);
				} catch (IOException e) {
					System.out.println("Error on making save-file.");
					e.printStackTrace();
					return;
				}
			}
			try {
				Serialize.saveToFile(Main.saveFilePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Error writing save-file");
				e.printStackTrace();
				return;
			}
			source.dispose();
			System.exit(0);
		}
	}

	public class CalendarButtonFunctions {
		public static void CalendarViewGoUpToYears(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate;
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void CalendarViewGoUpToMonths(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate;
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToPrevMonth(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.minusMonths(1);
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
//			keep DayLabel of selected day pressed
			LocalDate pressedDate = Main.mainFrame.dayFrame.displayedDay.date;
			Component[] allNewChildren = newCalendar.mainPanel.getComponents();
			Component[] dlNewChildren = Arrays.copyOfRange(allNewChildren, 7, 48);
			for (int i = 0; i < dlNewChildren.length; i++) {
				if (pressedDate.isEqual(((DayLabel) dlNewChildren[i]).date)) {
					((DayLabel) dlNewChildren[i]).currentLook = Look.pressed;
					((DayLabel) dlNewChildren[i]).showCurrentLook();
				}
			}

			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToNextMonth(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.plusMonths(1);
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
//			keep DayLabel of selected day pressed
			LocalDate pressedDate = Main.mainFrame.dayFrame.displayedDay.date;
			Component[] allNewChildren = newCalendar.mainPanel.getComponents();
			Component[] dlNewChildren = Arrays.copyOfRange(allNewChildren, 7, 48);
			for (int i = 0; i < dlNewChildren.length; i++) {
				if (pressedDate.isEqual(((DayLabel) dlNewChildren[i]).date)) {
					((DayLabel) dlNewChildren[i]).currentLook = Look.pressed;
					((DayLabel) dlNewChildren[i]).showCurrentLook();
				}
			}
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToPrevYear(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.minusYears(1);
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToNextYear(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.plusYears(1);
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToPrevYears(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.minusYears(15);
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
		}

		public static void GoToNextYears(MyButton source) {
			LocalDate date = Main.mainFrame.calendarFrame.referenceDate.plusYears(15);
			CalendarFrame newCalendar = new CalendarFrame(date, View.years);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
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
//			checks if repeating entries hit the day and adds them as a repeatingEntry if necessary
			day.addRepeatingEntries();
			if (day.entryList.size() > 0) {
				Main.datesWithEntries.put(selectedDate, day);
			}
			Main.mainFrame.dayFrame = new DayFrame(day);
			Main.mainFrame.entryFrame = new EntryFrame(null);
			Main.mainFrame.update();
		}

	}

	public class MonthLabelFunctions {

		public static void CalendarViewGoDownToDays(MonthLabel ml) {
			LocalDate date = ml.monthDate;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
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
			CalendarFrame newCalendar = new CalendarFrame(date, View.months);
			Main.mainFrame.calendarFrame = newCalendar;
			Main.mainFrame.update();
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
			EntryAuthor author = new EntryAuthor(true);
			author.display();
		}
	}

	public class EntryLabelFunctions {

		public static void ShowEntryInEntryFrame(EntryLabel source) {
			Main.mainFrame.entryFrame = new EntryFrame(source.entry);
			Main.mainFrame.update();
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
			Main.mainFrame.setEnabled(true);
			Main.mainFrame.setVisible(true);

		}

		public static void CloseAuthor(MyFrame source) {
			source.dispose();
			Main.mainFrame.setEnabled(true);
			Main.mainFrame.setVisible(true);
		}

		public static void ConfirmAuthor(MyButton source) {
//			readout inputs and create new entry
			EntryAuthor parent = (EntryAuthor) source.getParent();
			List<String> output = parent.readInput();
			if (output.get(0) == "error") {
//				TODO do error handling
				return;
			} else if (output.get(0) == "entry") {
				Entry entry = new Entry(output.get(1));
				LocalDate date = entry.date;
				Day day;
				if (entry.isRepeatRoot) {
					Main.repeatRootEntries.add(entry);
				}
//				check if day already has entries : yes -> add entry to day ; no -> create new day, add entry and add day to map
				if (Main.datesWithEntries.containsKey(date)) {
					day = Main.datesWithEntries.get(date);
					day.entryList.add(entry);
				} else {
					day = new Day(date, entry);
					Main.datesWithEntries.put(date, day);
				}
//				update dayFrame only if displayedDay coincides with date of new Entry
				if (Main.mainFrame.dayFrame.displayedDay.date.isEqual(date)) {
					Main.mainFrame.dayFrame = new DayFrame(day);
					Main.mainFrame.update();
				}
				Main.serializedEntries.put(entry, entry.serialize());
			}

			parent.dispose();
			Main.mainFrame.setEnabled(true);
			Main.mainFrame.setVisible(true);
		}

		public static int editFlag = 0;
		public static int dialogFlag = 0;
		
		public static void ConfirmEdit(MyButton source) {
			EntryAuthor parent = (EntryAuthor) source.getParent();
			Entry oldEntry = Main.mainFrame.entryFrame.displayedEntry;
			Entry newEntry = null;
			String newSerializedEntry = null;
			List<String> output = parent.readInput();
			if (output.get(0) == "error") {
//				TODO do error handling
				return;
			} else if (output.get(0) == "entry") {
				newEntry = new Entry(output.get(1));
				if (output.get(1).equals(Main.serializedEntries.get(oldEntry))) {
					System.out.println("No Edit");
					Main.mainFrame.setEnabled(true);
					Main.mainFrame.setVisible(true);
					parent.dispose();
					return;
				}
				newSerializedEntry = output.get(1);
				if (newEntry.isRepeatRoot) {
					if (oldEntry.isRepeating) {
						if (oldEntry.isRepeatRoot) {
//							oldEntry is root of repetition and so is newEntry
							/*
							 * Three-way decision cancel edit 
							 * -> return to editor 
							 * -> 0 
							 * complete replacement of oldEntry with newEntry
							 * -> 1
							 * plain add newEntry as new Entry; oldEntry stays same
							 * -> -1
							 */
							dialogFlag = 0;
							MyDialog dialog = new EntryEditDialog(dialogFlag, parent).dialog;
							dialog.setVisible(true);
							switch (editFlag) {
							case 0:
//								System.out.println("case 0");
//								parent.dispose();
//								Main.mainFrame.setEnabled(true);
//								Main.mainFrame.setVisible(true);
								return;
							case 1:
//								remove all known repetitions of oldEntry
								for (LocalDate date : oldEntry.knownRepeats.keySet()) {
									Main.datesWithEntries.get(date).entryList.remove(oldEntry.knownRepeats.get(date));
								}
//								remove oldEntry
								Main.repeatRootEntries.remove(oldEntry);
								Main.datesWithEntries.get(oldEntry.date).entryList.remove(oldEntry);
								Main.serializedEntries.remove(oldEntry);
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								Main.repeatRootEntries.add(newEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							case -1:
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								Main.repeatRootEntries.add(newEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							}
						} else {
//							oldEntry isRepeating but is not repRoot and newEntry is repRoot
							/*
							 * Three-way decision cancel edit 
							 * -> return to editor 
							 * 		-> 0 
							 * cut off after newEntry.date 
							 * 		-> replace known reps of oldEntry.repRoot !before newEntry.date; oldEntry.repRoot only till newEntry.date 
							 * 		-> 1 
							 * plain add newEntry
							 * 		-> -1
							 */
//							int flag = 0;
							dialogFlag = 1;
							MyDialog dialog = new EntryEditDialog(dialogFlag, parent).dialog;
							dialog.setVisible(true);
							switch (editFlag) {
							case 0:
//								System.out.println("case 0");
//								parent.dispose();
//								Main.mainFrame.setEnabled(true);
//								Main.mainFrame.setVisible(true);
								return;
							case 1:
//								remove all known repetitions of oldEntry !before newEntry.date
								for (LocalDate date : oldEntry.repeatRoot.knownRepeats.keySet()) {
									if (!date.isBefore(newEntry.date)) {
//										remove all known repeats at or after newEntry.date
										Main.datesWithEntries.get(date).entryList.remove(oldEntry.repeatRoot.knownRepeats.get(date));
									}
								}
//								edit oldEntry.repRoot
								Main.serializedEntries.remove(oldEntry.repeatRoot);
								oldEntry.repeatRoot.stopsRepeat = true;
								oldEntry.repeatRoot.lastRepeatDate = newEntry.date.minusDays(1);
								Main.serializedEntries.put(oldEntry.repeatRoot, oldEntry.repeatRoot.serialize());
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								Main.repeatRootEntries.add(newEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							case -1:
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								Main.repeatRootEntries.add(newEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							}
						}
					} else {
//						new Entry isRepRoot and oldEntry !isRepeating
						/*
						 * remove oldEntry and add newEntry
						 */
						Main.serializedEntries.remove(oldEntry);
						Main.datesWithEntries.get(oldEntry.date).entryList.remove(oldEntry);
						Main.serializedEntries.put(newEntry, newSerializedEntry);
						Main.repeatRootEntries.add(newEntry);
						if (Main.datesWithEntries.containsKey(newEntry.date)) {
							Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
						} else {
							Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date,newEntry));
						}
					}
				} else {
//					new Entry is not repeating
					if (oldEntry.isRepeating) {
						if (oldEntry.isRepeatRoot) {
//							old Entry is root of repetition and new Entry is not repeating
							/*
							 * Three-way decision: cancel edit 
							 * -> return to editor 
							 * 		-> 0 
							 * delete every known repetition; remove oldEntry; add newEntry
							 * 		-> 1 
							 * make new repRoot-Entry for repetition of oldEntry; remove oldEntry completely; add newRepRoot and newEntry
							 * exclude date of new Entry from repetition of old Entry and make new non-rep Entry 
							 * 		-> -1
							 */
//							int flag = 0;
							dialogFlag = 2;
							MyDialog dialog = new EntryEditDialog(dialogFlag, parent).dialog;
							dialog.setVisible(true);
							switch (editFlag) {
							case 0:
								System.out.println("case 0");
								parent.dispose();
								Main.mainFrame.setEnabled(true);
								Main.mainFrame.setVisible(true);
								return;
							case 1:
								for (LocalDate date : oldEntry.knownRepeats.keySet()) {
//									remove all known repeats
									Main.datesWithEntries.get(date).entryList.remove(oldEntry.knownRepeats.get(date));
								}
//								remove oldEntry
								Main.serializedEntries.remove(oldEntry);
								Main.datesWithEntries.get(oldEntry.date).entryList.remove(oldEntry);
								Main.repeatRootEntries.remove(oldEntry);
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;

							case -1:
//								remove oldEntry completely
								for (LocalDate date : oldEntry.knownRepeats.keySet()) {
//									remove all known repeats
									Main.datesWithEntries.get(date).entryList.remove(oldEntry.knownRepeats.get(date));
								}
								Main.serializedEntries.remove(oldEntry);
								Main.repeatRootEntries.remove(oldEntry);
								Main.datesWithEntries.get(oldEntry.date).entryList.remove(oldEntry);
//								check for next repDate
								LocalDate newRootDate = oldEntry.nextRepeatDate();
								if (!newRootDate.isEqual(oldEntry.date)) {
//									nextRepeatDate returns date of root if there is no next date
//									make and add newRepRoot
									Entry newRepRoot = new Entry(newRootDate, oldEntry);
									newRepRoot.isRepeatRoot = true;
									Main.serializedEntries.put(newRepRoot, newRepRoot.serialize());
									Main.repeatRootEntries.add(newRepRoot);
									if (Main.datesWithEntries.containsKey(newRepRoot.date)) {
										Main.datesWithEntries.get(newRepRoot.date).entryList.add(newRepRoot);
									} else {
										Main.datesWithEntries.put(newRootDate, new Day(newRootDate, newRepRoot));
									}
								}
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							}

						} else {
//							old Entry is not root of repetition and new Entry is not repeating
							/*
							 * Three-way decision: cancel edit 
							 * -> return to editor 
							 * 		-> 0 
							 * cut off everything after newEntry.date; make repetition of oldEntry.repRoot only up to newEntry.date.minusDays(1) 
							 * 		-> delete known repetitions after newEntry.date 
							 * 		-> 1
							 * exclude date of new Entry from repetition of oldEntry.repRoot and make new non-rep Entry 
							 * 		-> -1
							 */
//							int flag = 0;
							dialogFlag = 3;
							MyDialog dialog = new EntryEditDialog(dialogFlag, parent).dialog;
							dialog.setVisible(true);
							switch (editFlag) {
							case 0:
								System.out.println("case 0");
								parent.dispose();
								Main.mainFrame.setEnabled(true);
								Main.mainFrame.setVisible(true);
								return;
							case 1:
								for (LocalDate date : oldEntry.repeatRoot.knownRepeats.keySet()) {
									if (!date.isBefore(newEntry.date)) {
//										remove all known repeats at or after newEntry.date
										Main.datesWithEntries.get(date).entryList.remove(oldEntry.repeatRoot.knownRepeats.get(date));
									}
								}
//								edit oldEntry.repRoot and update serialisation
								Main.serializedEntries.remove(oldEntry.repeatRoot);
								oldEntry.repeatRoot.stopsRepeat = true;
								oldEntry.repeatRoot.lastRepeatDate = newEntry.date.minusDays(1);
								Main.serializedEntries.put(oldEntry.repeatRoot, oldEntry.repeatRoot.serialize());
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							case -1:
//								remove known repeat of oldEntry on newEntry.date
								if (oldEntry.repeatRoot.knownRepeats.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList
											.remove(oldEntry.repeatRoot.knownRepeats.get(newEntry.date));
								}
//								edit old Entry and update serialisation
								Main.serializedEntries.remove(oldEntry.repeatRoot);
								oldEntry.repeatRoot.excludedDates.add(newEntry.date);
								oldEntry.repeatRoot.knownRepeats.remove(newEntry.date);
								Main.serializedEntries.put(oldEntry.repeatRoot, oldEntry.repeatRoot.serialize());
//								add new Entry
								Main.serializedEntries.put(newEntry, newSerializedEntry);
								if (Main.datesWithEntries.containsKey(newEntry.date)) {
									Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
								} else {
									Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
								}
								break;
							}
						}
					} else {
//						both not repeating -> remove old put new
						Main.serializedEntries.remove(oldEntry);
						Main.serializedEntries.put(newEntry, newSerializedEntry);
						Main.datesWithEntries.get(oldEntry.date).entryList.remove(oldEntry);
						if (Main.datesWithEntries.containsKey(newEntry.date)) {
							Main.datesWithEntries.get(newEntry.date).entryList.add(newEntry);
						} else {
							Main.datesWithEntries.put(newEntry.date, new Day(newEntry.date, newEntry));
						}
					}
				}

				parent.dispose();
				Main.mainFrame.setEnabled(true);
				Main.mainFrame.setVisible(true);
				Main.mainFrame.entryFrame = new EntryFrame(newEntry);
				Main.mainFrame.dayFrame = new DayFrame(Main.datesWithEntries.get(newEntry.date));
				Main.mainFrame.update();
			}

		}

	}
	
	public class EntryEditDialogFunctions {
		
		public static void CancelEdit(MyButton source) {
			EntryEditDialog parent = (EntryEditDialog) source.getParent();
			parent.setEditFlag(0);
		}
		
		public static void SelectOptionOne(MyButton source) {
			EntryEditDialog parent = (EntryEditDialog) source.getParent();
			parent.setEditFlag(1);
		}
		
		public static void SelectOptionTwo(MyButton source) {
			EntryEditDialog parent = (EntryEditDialog) source.getParent();
			parent.setEditFlag(-1);
		}
		
		public static void CloseDialog(MyDialog source) {
			((EntryEditDialog)source.getContentPane()).setEditFlag(0);
		}
	}

	public class EntryButtonFunctions {

		public static void EditEntry(MyButton source) {
			EntryAuthor author = new EntryAuthor(false);
			author.display();
		}

		public static void DeleteEntry(MyButton source) {
			Entry entry = Main.mainFrame.entryFrame.displayedEntry;
			if (entry.isRepeating) {
				if (entry.isRepeatRoot) {
//					remove self and all repetitions
					Main.repeatRootEntries.remove(entry);
					Main.serializedEntries.remove(entry);
					Main.datesWithEntries.get(entry.date).entryList.remove(entry);
					for (LocalDate knownDate : entry.knownRepeats.keySet()) {
						Main.datesWithEntries.get(knownDate).entryList.remove(entry.knownRepeats.get(knownDate));
					}
				} else {
//					remove this entry and add to exclusionlist of repRoot
					Main.datesWithEntries.get(entry.date).entryList.remove(entry);
					entry.repeatRoot.excludedDates.add(entry.date);
				}
			} else {
//				entry is not repeating
//				remove entry
				Main.datesWithEntries.get(entry.date).entryList.remove(entry);
				Main.serializedEntries.remove(entry);
			}
//			update mainFrame
			Main.mainFrame.entryFrame = new EntryFrame(null);
			Main.mainFrame.dayFrame = new DayFrame(Main.mainFrame.dayFrame.displayedDay);
			Main.mainFrame.update();
		}

		public static void CompleteEntry(MyButton source) {
			Entry entry = Main.mainFrame.entryFrame.displayedEntry;
//			backend
//			check if generated repeating entry; if yes -> add to completed list of repRoot and update repRoot in Main.fields
			if (entry.isRepeating && !entry.isRepeatRoot) {
				entry.isCompleted = true;
				Main.serializedEntries.remove(entry.repeatRoot);
				entry.repeatRoot.completedDates.add(entry.date);
				Main.serializedEntries.put(entry.repeatRoot, entry.repeatRoot.serialize());
			}
//			if not generated entry -> set isCompleted and update in Main.fields
			else {
				Main.serializedEntries.remove(entry);
				entry.isCompleted = true;
				Main.serializedEntries.put(entry, entry.serialize());
			}

//			frontend
			Main.mainFrame.entryFrame = new EntryFrame(entry);
			Main.mainFrame.dayFrame = new DayFrame(Main.mainFrame.dayFrame.displayedDay);
			Main.mainFrame.update();
		}

		public static void IncompleteEntry(MyButton source) {
			Entry entry = Main.mainFrame.entryFrame.displayedEntry;
//			backend
//			check if generated repeating entry; if yes -> remove from completedList of repRoot and update repRoot in MainFields
			if (entry.isRepeating && !entry.isRepeatRoot) {
				entry.isCompleted = false;
				Main.serializedEntries.remove(entry.repeatRoot);
				entry.repeatRoot.completedDates.remove(entry.date);
				Main.serializedEntries.put(entry.repeatRoot, entry.repeatRoot.serialize());
			}
//			if not generated entry -> set isCompleted and update Main.fields
			else {
				Main.serializedEntries.remove(entry);
				entry.isCompleted = false;
				Main.serializedEntries.put(entry, entry.serialize());
			}

//			frontend
			Main.mainFrame.entryFrame = new EntryFrame(entry);
			Main.mainFrame.dayFrame = new DayFrame(Main.mainFrame.dayFrame.displayedDay);
			Main.mainFrame.update();
		}

		public static void CloseEntry(MyButton source) {
			EntryFrame emptyEntryFrame = new EntryFrame(null);
			Main.mainFrame.entryFrame = emptyEntryFrame;
			Main.mainFrame.update();
		}
	}

}
