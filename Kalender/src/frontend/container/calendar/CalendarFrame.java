package frontend.container.calendar;

import java.time.LocalDate;

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
	
	public CalendarFrame(LocalDate ld) {
		super();
		referenceDate = ld;
		
		
		
		
	}
	
}
