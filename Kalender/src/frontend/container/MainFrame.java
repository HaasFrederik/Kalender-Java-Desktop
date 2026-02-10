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
	
	public DayFrame dayFrame;
	public EntryFrame entryFrame;
	public CalendarFrame calendarFrame;
//	Array contains dayFrame, entryFrame, calendarFrame. gives order of addition to mainFrame. 
//	TODO Test: If dayFrame is written, should change content of subframes
//	Result: does NOT change!
	

	
	public MyPanel mainFramePanel;
	
	public MainFrame() {
		super();
//		setup contentPane
		mainFramePanel = new MyPanel() {

			@Override
			public void setupLayout() {
				GridLayout layout = new GridLayout(0,3);
				this.setLayout(layout);
			}

			@Override
			public void update() {
//				Array contains SubFrames in Order entry, day, calendar. If changes occur, use as buffer to write new subframes and recreate mainFrame
				SubFrame[] subframes = new SubFrame[] {entryFrame, dayFrame, calendarFrame};
				this.removeAll();
				for (SubFrame sf : subframes) {
					this.add(sf);
				}
				this.revalidate();
				this.repaint();
			}
			
		};
		setContentPane(mainFramePanel);
		
//		setup Layout
		mainFramePanel.setupLayout();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
//		create contents
		calendarFrame = new CalendarFrame(Main.today, View.days);
//		check if today has entries else create new Day Object for today. Create DayFrame for today
		Day today = new Day(Main.today);
		if (Main.datesWithEntries.containsKey(Main.today)) {
			today = Main.datesWithEntries.get(Main.today);
		}
		dayFrame = new DayFrame(today);
		entryFrame = new EntryFrame(null);
		
//		add contents
		mainFramePanel.add(entryFrame);
		mainFramePanel.add(dayFrame);
		mainFramePanel.add(calendarFrame);
		
		
//		make presentatble
		validate();
		pack();
		
	}
	
	public void update() {
		mainFramePanel.update();
	}
	
}
