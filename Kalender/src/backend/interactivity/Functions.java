package backend.interactivity;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.time.LocalDate;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.components.MonthLabel;
import frontend.components.MyButton;
import frontend.components.MyLabel;
import frontend.components.YearLabel;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.calendar.CalendarMainPanel;
import main.Main;

public class Functions {
	
	private static void exchangeComponent(Component currComp, Component newComp) {
		Container parent = currComp.getParent();
		parent.remove(currComp);
		parent.add(newComp);
		parent.validate();
		parent.repaint();
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
		
		public static void GoToPrevMonth(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.minusMonths(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
			Main.mainFrame.calendarFrame = newCalendar;
			exchangeComponent(oldCalendar, newCalendar);
		}
		
		public static void GoToNextMonth(MyButton source) {
			LocalDate date = ((CalendarFrame) Main.mainFrame.calendarFrame).referenceDate.plusMonths(1);
			CalendarFrame oldCalendar = (CalendarFrame) Main.mainFrame.calendarFrame;
			CalendarFrame newCalendar = new CalendarFrame(date, View.days);
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
	
}
