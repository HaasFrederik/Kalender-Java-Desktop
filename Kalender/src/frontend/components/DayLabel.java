package frontend.components;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class DayLabel extends MyLabel implements Interactible {

	public DayLabel(String text) {
		super(text);
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		// TODO Auto-generated method stub
		
	}

}
