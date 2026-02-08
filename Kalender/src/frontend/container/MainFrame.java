package frontend.container;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import backend.dating.Day;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.day.DayFrame;
import frontend.container.entry.EntryFrame;
import main.Main;

public class MainFrame extends JFrame {
	
	public SubFrame dayFrame;
	public SubFrame entryFrame;
	public SubFrame calendarFrame;
	public Container mainFramePanel = this.getContentPane();
	
	public MainFrame() {
		super();
		
//		setup Layout
		GridLayout layout = new GridLayout(0,3);
		mainFramePanel.setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		create contents
		calendarFrame = new CalendarFrame(Main.today, View.days);
//		check if today has entries else create new Day Object for today. Create DayFrame for today
		Day today = new Day(Main.today);
		if (Main.datesWithEntries.containsKey(Main.today)) {
			today = Main.datesWithEntries.get(Main.today);
		}
		dayFrame = new DayFrame(today);
		entryFrame = new EntryFrame();
		
//		add contents
		mainFramePanel.add(entryFrame);
		mainFramePanel.add(dayFrame);
		mainFramePanel.add(calendarFrame);
		
		
//		make presentatble
		validate();
		pack();
		
	}
	
}
