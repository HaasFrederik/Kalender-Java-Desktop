package frontend.container.calendar;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;

import backend.interactivity.Functionality;
import backend.interactivity.UserAction;
import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.container.MainPanel;
import frontend.container.calendar.CalendarFrame.View;
import frontend.components.MonthLabel;
import frontend.components.YearLabel;

public class CalendarMainPanel extends MainPanel {

	
	
	public CalendarMainPanel(View view, LocalDate referenceDate) {
		GridLayout layoutGrid;
		switch(view) {
		case days:
//			set Layout
			layoutGrid = new GridLayout(0, 7);
			layoutGrid.setVgap(5);
			layoutGrid.setHgap(5);
			setLayout(layoutGrid);
//			add labels denoting weekdays
			add(new JLabel("Mo"));
			add(new JLabel("Di"));
			add(new JLabel("Mi"));
			add(new JLabel("Do"));
			add(new JLabel("Fr"));
			add(new JLabel("Sa"));
			add(new JLabel("So"));
			
//			discern which 42 dates to display
//				Days Of current Month
			int daysOfMonth = referenceDate.lengthOfMonth();
//				Complete Mo-Su weeks at beginning and end of month
//					Days since last Su/to last Mo before 1st
			LocalDate firstOfMonth = LocalDate.of(referenceDate.getYear(), referenceDate.getMonthValue(), 1);
			int beforeFirstFillcount = firstOfMonth.getDayOfWeek().getValue() - 1; // range 0 - 6 (firstOfMonth: Mo - Su)
//					Days to next Su from last of month
			LocalDate lastOfMonth = LocalDate.of(referenceDate.getYear(), referenceDate.getMonthValue(), referenceDate.lengthOfMonth());
			int afterLastFillcount = 7 - lastOfMonth.getDayOfWeek().getValue(); // range 6 - 0 (lastOfMonth: Mo - Su)
//				if not 42 days yet append days of next month
			if (daysOfMonth + beforeFirstFillcount + afterLastFillcount < 42) {
				afterLastFillcount += 7;
			}
//			create and add 42 DayLables
//					add pre-month dates in from left
			for (int i = beforeFirstFillcount; i > 0; i--) {
				DayLabel dl = new DayLabel(firstOfMonth.minusDays(i), Look.grey);
				dl.doOn(UserAction.LeftPress, Functionality.DayLabelLookSetPressed);
				dl.doOn(UserAction.CursorEnter, Functionality.DayLabelLookSetHighlighted);
				dl.doOn(UserAction.CursorLeave, Functionality.DayLabelLookSetDefault);
				add(dl);
//				System.out.println((dl.getFont().getSize()));
//				System.out.println(dl.getFont().getFontName());
//				System.out.println(dl.getFont().getStyle());
//				System.out.println(Font.BOLD);
			}	
//					add month and post-month dates from right
			for (int i = 0; i < daysOfMonth; i++) {
				DayLabel dl = new DayLabel(firstOfMonth.plusDays(i), Look.black);
				dl.doOn(UserAction.LeftPress, Functionality.DayLabelLookSetPressed);
				dl.doOn(UserAction.CursorEnter, Functionality.DayLabelLookSetHighlighted);
				dl.doOn(UserAction.CursorLeave, Functionality.DayLabelLookSetDefault);
				add(dl);
			}
			for (int i = 1; i <= afterLastFillcount; i++) {
				DayLabel dl = new DayLabel(lastOfMonth.plusDays(i), Look.grey);
				dl.doOn(UserAction.LeftPress, Functionality.DayLabelLookSetPressed);
				dl.doOn(UserAction.CursorEnter, Functionality.DayLabelLookSetHighlighted);
				dl.doOn(UserAction.CursorLeave, Functionality.DayLabelLookSetDefault);
				add(dl);
			}
			break;
		case months:
//			set Layout
			layoutGrid = new GridLayout(0, 3);
			layoutGrid.setVgap(10);
			layoutGrid.setHgap(10);
			setLayout(layoutGrid);
//			add MonthLabels
			LocalDate month = LocalDate.of(referenceDate.getYear(), 1, 1);
			for (int i = 0; i < 12; i++) {
				MonthLabel monthLabel = new MonthLabel(month.plusMonths(i));
				monthLabel.doOn(UserAction.LeftPress, Functionality.CalendarViewGoDownToDays);
				monthLabel.doOn(UserAction.CursorEnter, Functionality.MonthLabelSetHighlighted);
				monthLabel.doOn(UserAction.CursorLeave, Functionality.MonthLabelSetDefault);
				add(monthLabel);
			}
			break;
		case years:
//			set Layout
			layoutGrid = new GridLayout(0, 3);
			layoutGrid.setVgap(10);
			layoutGrid.setHgap(10);
			setLayout(layoutGrid);
//			add YearLabels
			for (int i = -7; i <= 7; i++) {
				YearLabel yearLabel = new YearLabel(referenceDate.plusYears(i));
				yearLabel.doOn(UserAction.LeftPress, Functionality.CalendarViewGoDownToMonths);
				yearLabel.doOn(UserAction.CursorEnter, Functionality.YearLabelSetHighlighted);
				yearLabel.doOn(UserAction.CursorLeave, Functionality.YearLabelSetDefault);
				add(yearLabel);
			}
			break;
		}
	}
	
}
