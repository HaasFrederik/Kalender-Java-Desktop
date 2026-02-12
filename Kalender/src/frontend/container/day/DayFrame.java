package frontend.container.day;


import java.awt.BorderLayout;

import javax.swing.JPanel;

import backend.dating.Day;
import frontend.container.SubFrame;

public class DayFrame extends SubFrame {

	public Day displayedDay = null;
	
	public DayFrame(Day date) {
		super();
		displayedDay = date;

		buttonPanel = new DayButtonPanel(displayedDay);
		labelPanel = new DayLabelPanel(displayedDay);
		mainPanel = new DayMainPanel(displayedDay);
		add(labelPanel, BorderLayout.NORTH);
		add(mainPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}


	
}
