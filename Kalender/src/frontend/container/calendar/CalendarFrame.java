package frontend.container.calendar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.BoxLayout;
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
//	public CalendarFrame() {
//		super();
//		referenceDate = LocalDate.now();
//	}
	
	public CalendarFrame(LocalDate ld, View view) {
		super();
		referenceDate = ld;
		
//		create contents
		labelPanel = new CalendarLabelPanel();
		buttonPanel = new CalendarButtonPanel(view, referenceDate);
		mainPanel = new CalendarMainPanel(view, referenceDate);

//		pack contents
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
		topPanel.add(labelPanel);
		topPanel.add(buttonPanel);
		
//		add contents
		add(topPanel, BorderLayout.NORTH);
//		add(buttonPanel, BorderLayout.CENTER);
		add(mainPanel, BorderLayout.CENTER);

		
		
		
	}

	
}
