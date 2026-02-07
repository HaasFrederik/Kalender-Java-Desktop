package frontend.components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.time.LocalDate;
import java.time.Month;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MonthLabel extends MyLabel implements Interactible {

	public LocalDate monthDate;
	
	public MonthLabel(LocalDate d) {
		super(getGermanMonthAbbreviation(d.getMonth()));
		monthDate = d;
		setMinimumSize(new Dimension(25,25));
		setPreferredSize(new Dimension(25,25));
		setMaximumSize(new Dimension(25,25));
	}
	
	public MonthLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MonthLabel source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		switch(userAction) {
		case CursorEnter:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case CursorLeave:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case LeftPress:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		default:
			break;
		}
		
		

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
