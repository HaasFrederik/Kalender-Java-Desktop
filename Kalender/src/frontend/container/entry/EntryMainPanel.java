package frontend.container.entry;

import java.time.format.DateTimeFormatter;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import backend.entries.Entry;
import frontend.container.MainPanel;

public class EntryMainPanel extends MainPanel {

//	contents
	public JTextArea descriptionText = new JTextArea();
	public JScrollPane descriptionDisplay = new JScrollPane(descriptionText);
	public JLabel repeatLabel = new JLabel(" ");;
	public JLabel completedLabel = new JLabel(" ");
	
	public EntryMainPanel(Entry entry) {
		super();
//		setup Layout
		BoxLayout layout  = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
//		prime textarea
		descriptionText.setEditable(false);
		descriptionText.setFocusable(false);
//		add contents
		add(descriptionDisplay);
		add(repeatLabel);
		add(completedLabel);
//		if entry non-null
		if (entry != null) {
//			-> set contents
//			description
			descriptionText.setText(entry.description);
//			repetition
			if (entry.isRepeating) {
				repeatLabel.setText("Wiederholt sich " + entry.repeatRate.toGermanString() + "." 
								+ "Nächstes mal am " + entry.nextRepeatDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
			} else {
				repeatLabel.setText("Eintrag wiederholt sich nicht.");
			}
//			completion
			if (entry.isCompleted) {
				completedLabel.setText("Erledigt");
			} else {
				completedLabel.setText("Zu erledigen");
			}
		} 
//		else entry is null -> contents stay empty
	}
}
