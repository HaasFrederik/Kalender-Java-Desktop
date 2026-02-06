package frontend.components;

import java.time.LocalDate;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class YearLabel extends MyLabel implements Interactible {

	public LocalDate year;
	
	public YearLabel(LocalDate d) {
		super("" + d.getYear());
	}
	
	public YearLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		// TODO Auto-generated method stub

	}

}
