package frontend.container;

import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JFrame;

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
		
		
//		create contents
		dayFrame = new DayPanel();
		entryFrame = new EntryPanel();
		calendarFrame = new CalendarPanel();
		
//		add contents
		mainFramePanel.add(entryFrame);
		mainFramePanel.add(dayFrame);
		mainFramePanel.add(calendarFrame);
		
//		make presentatble
		validate();
		pack();
		
	}
	
}
