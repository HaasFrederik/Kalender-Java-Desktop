package frontend.container.day;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import backend.dating.Day;
import frontend.components.DayLabel;
import frontend.components.MyLabel;
import frontend.container.LabelPanel;
import frontend.container.calendar.CalendarFrame;
import main.Main;

public class DayLabelPanel extends LabelPanel {
	
	public DayLabelPanel(Day displayedDay) {
		MyLabel selectedDayLabel = new MyLabel(displayedDay.date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " - " 
				+ DayLabel.getGermanDayNames(displayedDay.date.getDayOfWeek()));
		add(selectedDayLabel);
	}
	
}
