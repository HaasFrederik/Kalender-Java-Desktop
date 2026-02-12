package frontend.container.entry;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import backend.entries.Entry;
import frontend.container.LabelPanel;

public class EntryLabelPanel extends LabelPanel {

	JLabel entryLabel;
	
	public EntryLabelPanel(Entry entry) {
		super();
//		setup Layout
		setLayout(new FlowLayout());
//		if entry non-null
		if (entry != null) {
//			-> add label
			entryLabel = new JLabel(entry.toString());
			add(entryLabel);
		}
//		if entry null
		else {
//			add placeholder
			entryLabel = new JLabel("---");
			add(entryLabel);
		}
		
	}

}
