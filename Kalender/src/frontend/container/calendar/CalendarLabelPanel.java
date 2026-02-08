package frontend.container.calendar;

import java.time.format.DateTimeFormatter;

import frontend.components.DayLabel;
import frontend.container.LabelPanel;
import main.Main;

public class CalendarLabelPanel extends LabelPanel {

	public CalendarLabelPanel() {
		DayLabel todayLabel = new DayLabel("Heute : " +  Main.today.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " 
								+ DayLabel.getGermanDayNames(Main.today.getDayOfWeek()));
		add(todayLabel);
	}
	
}
