package frontend.container.entry;

import java.awt.GridLayout;

import javax.swing.border.EmptyBorder;

import backend.entries.Entry;
import backend.interactivity.Functionality;
import backend.interactivity.UserAction;
import frontend.components.MyButton;
import frontend.container.ButtonPanel;

public class EntryButtonPanel extends ButtonPanel {

	public MyButton editButton = new MyButton("Bearbeiten");
	public MyButton deleteButton = new MyButton("Löschen"); // -> der wird toll mit den Wiederholungseinträgen...
	public MyButton completionButton = new MyButton("Erledigt");
	public MyButton closeEntryButton = new MyButton("Schließen");
	
	
	public EntryButtonPanel(Entry entry) {
		super();
//		setup Layout
		GridLayout layout = new GridLayout(0,4);
		layout.setHgap(10);
		setBorder(new EmptyBorder(5,5,5,5));
		setLayout(layout);
//		add buttons
		add(editButton);
		add(deleteButton);
		add(completionButton);
		add(closeEntryButton);
//		if entry non-null
		if (entry != null) {
//			 -> add functionalities to buttons
			editButton.doOn(UserAction.LeftClick, Functionality.EditEntry);
			if (!entry.isCompleted) {
				completionButton.doOn(UserAction.LeftClick, Functionality.CompleteEntry);
			} else {
				completionButton.setText("Nicht erledigt");
				completionButton.doOn(UserAction.LeftClick, Functionality.IncompleteEntry);
			}
			
			closeEntryButton.doOn(UserAction.LeftClick, Functionality.CloseEntry);
		}
//		if entry null
		else {
//		-> disable buttons
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
			completionButton.setEnabled(false);
			closeEntryButton.setEnabled(false);
		}		


	}

	
	
}
