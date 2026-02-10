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
		add(labelPanel);
		add(mainPanel);
		add(buttonPanel);
	}

	@Override
	public void update() {
//		Array contains children in Order label, main, button. If changes occur, used as buffer to remove and readd chlidren
		JPanel[] children = new JPanel[] {labelPanel, mainPanel, buttonPanel};
		this.removeAll();
		for (JPanel child : children) {
			this.add(child);
		}
		this.revalidate();
		this.repaint();
	}


	
}
