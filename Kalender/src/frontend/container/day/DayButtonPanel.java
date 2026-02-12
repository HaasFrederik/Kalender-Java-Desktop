package frontend.container.day;

import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import backend.dating.Day;
import backend.interactivity.Functionality;
import backend.interactivity.UserAction;
import frontend.components.MyButton;
import frontend.container.ButtonPanel;
import main.Main;

public class DayButtonPanel extends ButtonPanel {

//	public DayButtonPanel() {
//		MyButton newEntryButton = new MyButton("+ Neure Eintrag");
//		add(newEntryButton);
//		newEntryButton.setEnabled(false);
//	}
	
	public DayButtonPanel(Day displayedDay) {
		MyButton newEntryButton = new MyButton("+ Neuer Eintrag");
		newEntryButton.doOn(UserAction.LeftClick, Functionality.OpenEntryCreator);
//		newEntryButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		setLayout(new FlowLayout());
		add(newEntryButton);
	}
	
}
