package frontend.components;

import java.time.LocalDate;
import java.time.Month;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MonthLabel extends MyLabel implements Interactible {

	public LocalDate monthDate;
	
	public MonthLabel(LocalDate d) {
		super(getGermanMonthAbbreviation(d.getMonth()));
	}
	
	public MonthLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		// TODO Auto-generated method stub

	}
	
	public static String getGermanMonthAbbreviation(Month month) {
		switch (month) {
		default:
			return "???";
		case Month.JANUARY:
			return "Jan";
		case Month.FEBRUARY:
			return "Feb";
		case Month.MARCH:
			return "Mär";
		case Month.APRIL:
			return "Apr";
		case Month.MAY:
			return "Mai";
		case Month.JUNE:
			return "Jun";
		case Month.JULY:
			return "Jul";
		case Month.AUGUST:
			return "Aug";
		case Month.SEPTEMBER:
			return "Sep";
		case Month.OCTOBER:
			return "Okt";
		case Month.NOVEMBER:
			return "Nov";
		case Month.DECEMBER:
			return "Dez";
		}
	}

}
