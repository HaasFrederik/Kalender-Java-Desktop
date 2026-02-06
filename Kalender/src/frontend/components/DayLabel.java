package frontend.components;

import java.time.LocalDate;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class DayLabel extends MyLabel implements Interactible {

	
	public enum Look {
		grey,
		black,
		highlighted;
	}
	
	public Look defaultLook;
	public LocalDate date;
	
	public DayLabel(LocalDate d, Look look) {
		super("" + d.getDayOfMonth());
		defaultLook = look;
	}
	
	public DayLabel(String text) {
		super(text);
		defaultLook = Look.black;
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		// TODO Auto-generated method stub
		
	}

}
