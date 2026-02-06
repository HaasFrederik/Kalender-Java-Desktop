package frontend.container;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JLabel;

import frontend.components.DayLabel;
import frontend.components.DayLabel.Look;
import frontend.components.MonthLabel;
import frontend.components.YearLabel;
import frontend.container.CalendarFrame.View;

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
			for (int i = 1; i <= beforeFirstFillcount; i++) {
				add(new DayLabel(firstOfMonth.minusDays(i), Look.grey));
			}	
//					add month and post-month dates from right
			for (int i = 0; i < daysOfMonth; i++) {
				add(new DayLabel(firstOfMonth.plusDays(i), Look.black));
			}
			for (int i = 1; i <= afterLastFillcount; i++) {
				add(new DayLabel(lastOfMonth.plusDays(i), Look.grey));
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
			for (int i = 0; i< 12; i++) {
				add(new MonthLabel(month.plusMonths(i)));
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
				add(new YearLabel(referenceDate.plusYears(i)));
			}
			break;
		}
	}
	
}
