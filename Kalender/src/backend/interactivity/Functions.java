package backend.interactivity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import backend.dating.Day;
import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.components.MonthLabel;
import frontend.components.MyButton;
import frontend.components.MyLabel;
import frontend.components.YearLabel;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.calendar.CalendarMainPanel;
import frontend.container.day.DayFrame;
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
			Day day = new Day(selectedDate);
			if (Main.datesWithEntries.containsKey(selectedDate)) {
				day = Main.datesWithEntries.get(selectedDate);
			}
//			switch contents of oldDayFrame with newDayFrame
			DayFrame oldDayFrame = (DayFrame) Main.mainFrame.dayFrame;
			DayFrame newDayFrame = new DayFrame(day);
			exchangeComponents(oldDayFrame, newDayFrame);
			
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
//			TODO
			
		}
	}
	
}
