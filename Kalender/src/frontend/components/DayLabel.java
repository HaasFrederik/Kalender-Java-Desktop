package frontend.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import javax.swing.BorderFactory;
import javax.swing.SwingConstants;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class DayLabel extends MyLabel{

	
	public enum Look { //encompasses text-colour, background-colour and -opacity as well as font
		grey,
		black,
		highlighted,
		today,
		pressed;
	}
	
	public Look defaultLook;
	public Look currentLook;
	public LocalDate date;
	
	public DayLabel(LocalDate d, Look look) {
		super("" + d.getDayOfMonth());
		date = d;
		setMinimumSize(new Dimension(25,25));
		setPreferredSize(new Dimension(25,25));
		setMaximumSize(new Dimension(25,25));
		defaultLook = look;
		currentLook = defaultLook;
		this.showCurrentLook();
	}
	
	public DayLabel(String text) {
		super(text);
		defaultLook = Look.black;
	}

	public void showCurrentLook() {
		switch(currentLook) {
		case black:
			setOpaque(false);
			setForeground(Color.BLACK);
			setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			setBorder(null);
			break;
		case grey:
			setOpaque(false);
			setForeground(Color.GRAY);
			setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			setBorder(null);
			break;
		case pressed:
			setOpaque(true);
			setBackground(new Color(20,100,100));
			setForeground(new Color(255,255,255));
//			setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			break;
		case highlighted:
			setOpaque(true);
			setBackground(Color.DARK_GRAY);
			setForeground(Color.ORANGE);
//			setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			break;
		case today:
			setOpaque(false);
			setForeground(Color.BLUE);
			setBorder(BorderFactory.createEtchedBorder(new Color(50,50,150), new Color(0,0,50)));
		default:
			break;
		
		}
	}
	
	public static String getGermanDayNames(DayOfWeek day) {
		switch (day) {
		default:
			return "???";
		case DayOfWeek.MONDAY:
			return "Montag";
		case DayOfWeek.TUESDAY:
			return "Dienstag";
		case DayOfWeek.WEDNESDAY:
			return "Mittwoch";
		case DayOfWeek.THURSDAY:
			return "Donnerstag";
		case DayOfWeek.FRIDAY:
			return "Freitag";
		case DayOfWeek.SATURDAY:
			return "Samstag";
		case DayOfWeek.SUNDAY:
			return "Sonntag";
		}
	}

}
