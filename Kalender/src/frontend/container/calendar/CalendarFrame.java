package frontend.container.calendar;

import java.time.LocalDate;

import javax.swing.JPanel;

import frontend.container.MyPanel;
import frontend.container.SubFrame;

public class CalendarFrame extends SubFrame {

	public enum View {
		days,
		months,
		years;
	}
	
	public LocalDate referenceDate;
	
//	potentially only temp; depends where the startup-view date today will come from
	public CalendarFrame() {
		super();
		referenceDate = LocalDate.now();
	}
	
	public CalendarFrame(LocalDate ld, View view) {
		super();
		referenceDate = ld;

		
//		create contents
		labelPanel = new CalendarLabelPanel();
		buttonPanel = new CalendarButtonPanel(view, referenceDate);
		mainPanel = new CalendarMainPanel(view, referenceDate);
//		panels = new JPanel[] {labelPanel, buttonPanel, mainPanel};
//		add contents
		add(labelPanel);
		add(buttonPanel);
		add(mainPanel);
//		for (JPanel panel : panels) {
//			add(panel);
//		}
		
		
		
	}

	@Override
	public void update() {
//		Array contains children in Order label, button, main. If changes occur, used as buffer to remove and readd chlidren
		JPanel[] children = new JPanel[] {labelPanel, buttonPanel, mainPanel};
		this.removeAll();
		for (JPanel child : children) {
			this.add(child);
		}
		this.revalidate();
		this.repaint();
	}


	
}
