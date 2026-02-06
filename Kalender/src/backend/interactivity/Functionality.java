package backend.interactivity;

public enum Functionality {
	
//	******* CalendarButtons *******
	CalendarViewToYears(Functions.CalendarButtonFunctions.class, "CalendarViewToYears"),
	CalendarViewToMonths(Functions.CalendarButtonFunctions.class, "CalendarViewToMonths"),
	CalendarViewToDays(Functions.CalendarButtonFunctions.class, "CalendarViewToDays"),
	GoToPrevMonth(Functions.CalendarButtonFunctions.class, "GoToPrevMonth"), 
	GoToNextMonth(Functions.CalendarButtonFunctions.class, "GoToNextMonth"),
	GoToPrevYear(Functions.CalendarButtonFunctions.class, "GoToPrevYear"),
	GoToNextYear(Functions.CalendarButtonFunctions.class, "GoToNextYear"),
	GoToPrevYears(Functions.CalendarButtonFunctions.class, "GoToPrevYears"),
	GoToNextYears(Functions.CalendarButtonFunctions.class, "GoToNextYears"),
	
//	******* DayLabels *******
	DayLabelLookSetHighlighted(Functions.DayLabelFunctions.class, "DayLabelLookSetHighlighted"),
	DayLabelLookSetDefault(Functions.DayLabelFunctions.class, "DayLabelLookSetDefault"),
	DayLabelLookSetPressed(Functions.DayLabelFunctions.class, "DayLabelLookSetPressed"),
	ShowDayInDayFrame(Functions.DayLabelFunctions.class, "ShowDayInDayFrame"),
	
//	******* MonthLabels *******
	
	;
	
	public final Class<?> CLASS;
	public final String NAME;
	private Functionality(final Class<?> cls, final String name) {
		this.CLASS = cls;
		this.NAME = name;
	}
	
}
