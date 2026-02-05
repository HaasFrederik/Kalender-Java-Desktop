package frontend.components;

import javax.swing.JButton;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MyButton extends JButton implements Interactible {

	public MyButton() {
		super();
	}
	
	@Override
	public void doOn(UserAction userAction, Functionality function) {
		
	}
	
}
