package frontend.container.entry;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import backend.entries.Entry;
import frontend.container.SubFrame;

public class EntryFrame extends SubFrame {

	public Entry displayedEntry;
	public boolean entryDisplayed;
	
	public EntryFrame(Entry entry) {
		super();
		displayedEntry = entry;
		entryDisplayed = true;
//		create content
		labelPanel = new EntryLabelPanel(entry);
		buttonPanel = new EntryButtonPanel(entry);
		buttonPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel = new EntryMainPanel(entry);
//		add content
		add(labelPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	
}
