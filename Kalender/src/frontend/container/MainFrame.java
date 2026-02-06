package frontend.container;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

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
		FlowLayout layout = new FlowLayout();
		mainFramePanel.setLayout(layout);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		create contents
		dayFrame = new DayFrame();
		entryFrame = new EntryFrame();
		calendarFrame = new CalendarFrame(Main.today, View.days);
		
//		add contents
		mainFramePanel.add(entryFrame);
		mainFramePanel.add(dayFrame);
		mainFramePanel.add(calendarFrame);
		
//		make presentatble
		validate();
		pack();
		
	}
	
}
