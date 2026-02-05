package frontend.container;

import java.awt.Container;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	public SubFrame dayFrame;
	public SubFrame entryFrame;
	public SubFrame calendarFrame;
	public Container mainFramePanel = this.getContentPane();
	
	public MainFrame() {
		super();
		
		dayFrame = new DayPanel();
		entryFrame = new EntryPanel();
		calendarFrame = new CalendarPanel();
		
	}
	
}
