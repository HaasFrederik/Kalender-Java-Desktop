package frontend.container.day;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import backend.dating.Day;
import backend.entries.Entry;
import backend.entries.EntryType;
import frontend.components.MyLabel;
import frontend.container.MainPanel;

public class DayMainPanel extends MainPanel {

//	private class ScrollableEntryDisplay extends JScrollPane {
//		private ScrollableEntryDisplay(List<Entry> entries) {
//			super();
//			for (Entry e : entries) {
//				MyLabel entryLabel = new MyLabel()
//			}
//		}
//	}
	
	public DayMainPanel(Day displayedDay) {
//		setup Layout
		GridLayout layout = new GridLayout(3,1);
//		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
//		sort Entries into type-specific lists
		List<Entry> simpleEntries = new ArrayList<Entry>();
		List<Entry> deadlines = new ArrayList<Entry>();
		for (Entry e : displayedDay.entryList) {
			if (e.entryType == EntryType.SimpleEntry) {
				simpleEntries.add(e);
			} else if (e.entryType == EntryType.Deadline) {
				deadlines.add(e);
			}
		}
//		Panel for completed entries, regardless of type
		JPanel completedEntryPanel = new JPanel();
//		put simpleEntries into scrollable view
		JPanel simpleEntryPanel = new JPanel();
		simpleEntryPanel.setLayout(new BoxLayout(simpleEntryPanel, BoxLayout.Y_AXIS));
		for (Entry e : simpleEntries) {
			MyLabel entryLabel = new MyLabel(e.toString());
//			TODO add doOns 
			/*
			 * -> mark selected entry, unmark prev selected entry (scope:DayMainPanel)
			 * -> show selected Entry in EntryFrame
			*/
			if (e.isCompleted) completedEntryPanel.add(entryLabel);
			else simpleEntryPanel.add(entryLabel);
		}
		
		
//		put deadline into scrollable view
		JPanel deadlinePanel = new JPanel();
		deadlinePanel.setLayout(new BoxLayout(deadlinePanel, BoxLayout.Y_AXIS));
		for (Entry e : deadlines) {
			MyLabel entryLabel = new MyLabel(e.toString());
//			TODO add doOns
			/*
			 * -> mark selected entry, unmark prev selected entry (scope:DayMainPanel)
			 * -> show selected Entry in EntryFrame
			*/
			if (e.isCompleted) completedEntryPanel.add(entryLabel);
			else deadlinePanel.add(entryLabel);
		}
//		create contents
		JScrollPane simpleEntryScrollPanel = new JScrollPane(simpleEntryPanel);
		simpleEntryScrollPanel.setBorder(BorderFactory.createTitledBorder(simpleEntryScrollPanel.getBorder(),"Termine und Aufgaben", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		JScrollPane deadlineScrollPanel = new JScrollPane(deadlinePanel);
		deadlineScrollPanel.setBorder(BorderFactory.createTitledBorder(deadlineScrollPanel.getBorder(),"Deadlines", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
		JScrollPane completedEntryScrollPanel = new JScrollPane(completedEntryPanel);
		completedEntryScrollPanel.setBorder(BorderFactory.createTitledBorder(completedEntryScrollPanel.getBorder(),"Erledigt", TitledBorder.LEADING, TitledBorder.ABOVE_TOP));
//		add contents
		add(simpleEntryScrollPanel);
		add(deadlineScrollPanel);
		add(completedEntryScrollPanel);
	}
	
}
