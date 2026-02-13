package frontend.container;


import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import backend.interactivity.Functionality;
import backend.interactivity.Functions;
import backend.interactivity.UserAction;
import frontend.components.MyButton;
import frontend.container.entry.EntryAuthor;

public class EntryEditDialog extends JPanel {

	public EntryAuthor author;
	public MyDialog dialog;
	
	public EntryEditDialog(int dialogFlag, EntryAuthor author) {
		super();
		this.author = author;
//		modality so application waits for Dialog input
		dialog = new MyDialog();
		dialog.setModal(true);
		dialog.setContentPane(this);
//		JPanel rootPane = new JPanel();
//		setContentPane(rootPane);
		JTextArea optionOneText = new JTextArea();
		JTextArea optionTwoText = new JTextArea();
		JTextArea cancelText = new JTextArea("Abbrechen und zurück zum Editor.");
		optionOneText.setEditable(false);
		optionOneText.setFocusable(false);
		optionTwoText.setEditable(false);
		optionTwoText.setFocusable(false);
		cancelText.setEditable(false);
		cancelText.setFocusable(false);
		MyButton optionOneButton = new MyButton("Option 1");
		MyButton optionTwoButton = new MyButton("Option 2");
		MyButton cancelButton = new MyButton("Abbrechen");
		cancelButton.doOn(UserAction.LeftClick, Functionality.CancelEdit);
		optionOneButton.doOn(UserAction.LeftClick, Functionality.SelectOptionOne);
		optionTwoButton.doOn(UserAction.LeftClick, Functionality.SelectOptionTwo);
		dialog.doOn(UserAction.WindowClose, Functionality.CloseDialog);
		switch(dialogFlag) {
		case 0:
			optionOneText.setText("Alle Einträge ersetzen.");
			optionOneButton.setText("Ersetzen");
			optionTwoText.setText("Als neuen Eintrag hinzufügen.\nAlter Eintrag bleibt unverändert.");
			optionTwoButton.setText("Hinzufügen");			
			break;
		case 1:
			optionOneText.setText("Alle Einträge ab gewähltem Datum ersetzen.");
			optionOneButton.setText("Ersetzen");
			optionTwoText.setText("Als neuen Eintrag hinzufügen.\nAlter Eintrag bleibt unverändert.");
			optionTwoButton.setText("Hinzufügen");			
			break;
		case 2:
			optionOneText.setText("Alle Wiederholungen löschen.");
			optionOneButton.setText("Löschen");
			optionTwoText.setText("Nur diesen Eintrag ändern.");
			optionTwoButton.setText("Ändern");			
			break;
		case 3:
			optionOneText.setText("Zukünftige Wiederholungen löschen.");
			optionOneButton.setText("Löschen");
			optionTwoText.setText("Nur diesen Eintrag ändern.");
			optionTwoButton.setText("Ändern");			
			break;
		}
		GridLayout layout = new GridLayout(0,3);
		setLayout(layout);
		add(optionOneText);
		add(optionTwoText);
		add(cancelText);
		add(optionOneButton);
		add(optionTwoButton);
		add(cancelButton);
		
		dialog.validate();
		dialog.pack();
	}
	
	public void setEditFlag(int flag) {
		author.frame.setEnabled(true);
		author.frame.setVisible(true);
		Functions.EntryAuthorFunctions.editFlag = flag;
		dialog.setVisible(false);
		dialog.dispose();
	}
	
	
	
}
