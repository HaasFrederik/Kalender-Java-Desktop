package frontend.container;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandle;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

import backend.dating.Day;
import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;
import frontend.container.calendar.CalendarFrame;
import frontend.container.calendar.CalendarFrame.View;
import frontend.container.day.DayFrame;
import frontend.container.entry.EntryFrame;
import main.Main;

public class MainFrame extends JFrame implements Interactible {
	
	public DayFrame dayFrame;
	public EntryFrame entryFrame;
	public CalendarFrame calendarFrame;	

	
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
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		doOn(UserAction.WindowClose, Functionality.SaveAndExit);
		
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
	
	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MainFrame source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		switch (userAction) {
		default:
			System.out.println("Interaction " + userAction + " not defined for class MyFrame.");
			break;
		case WindowClose:
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		}
		
	}
	
}
