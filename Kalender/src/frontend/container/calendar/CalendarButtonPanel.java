package frontend.container.calendar;

import java.time.LocalDate;

import frontend.components.MonthLabel;
import frontend.components.MyButton;
import frontend.container.ButtonPanel;
import frontend.container.calendar.CalendarFrame.View;
import backend.interactivity.Functionality;
import backend.interactivity.UserAction;

public class CalendarButtonPanel extends ButtonPanel {

	public LocalDate referenceDate;
	
	public CalendarButtonPanel(View view, LocalDate date) {
		super();
		referenceDate = date;
		MyButton yearButton;
		MyButton monthButton;
		MyButton prevButton = new MyButton("<");
		MyButton nextButton = new MyButton(">");
		switch(view) {
		case days:
//			create view specific buttons
			yearButton = new MyButton("" + referenceDate.getYear());
			monthButton = new MyButton(MonthLabel.getGermanMonthAbbreviation(referenceDate.getMonth()));
//			add functionality to buttons
			yearButton.doOn(UserAction.LeftClick, Functionality.CalendarViewToYears);
			monthButton.doOn(UserAction.LeftClick, Functionality.CalendarViewToMonths);
			prevButton.doOn(UserAction.LeftClick, Functionality.GoToPrevMonth);
			nextButton.doOn(UserAction.LeftClick, Functionality.GoToNextMonth);
//			add buttons
			add(yearButton);
			add(monthButton);
			add(prevButton);
			add(nextButton);
			break;
		case months:
//			create view specific button
			yearButton = new MyButton("" + referenceDate.getYear());
//			add functionality
			yearButton.doOn(UserAction.LeftClick, Functionality.CalendarViewToYears);
			prevButton.doOn(UserAction.LeftClick, Functionality.GoToPrevYear);
			nextButton.doOn(UserAction.LeftClick, Functionality.GoToNextYear);
//			add buttons
			add(yearButton);
			add(prevButton);
			add(nextButton);
			break;
		case years:
//			add functionality
			prevButton.doOn(UserAction.LeftClick, Functionality.GoToPrevYears);
			nextButton.doOn(UserAction.LeftClick, Functionality.GoToNextYears);
//			add buttons
			add(prevButton);
			add(nextButton);
			break;
		}
	}
	
}
