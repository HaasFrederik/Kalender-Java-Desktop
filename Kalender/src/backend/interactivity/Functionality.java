package backend.interactivity;

public enum Functionality {
	
//	******* ClosingWindows *******
	SaveAndExit(Functions.MyFrameFunctions.class, "SaveAndExit"),
	
//	******* CalendarButtons *******
	CalendarViewGoUpToYears(Functions.CalendarButtonFunctions.class, "CalendarViewGoUpToYears"),
	CalendarViewGoUpToMonths(Functions.CalendarButtonFunctions.class, "CalendarViewGoUpToMonths"),
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
	CalendarViewGoDownToDays(Functions.MonthLabelFunctions.class, "CalendarViewGoDownToDays"),
	MonthLabelSetHighlighted(Functions.MonthLabelFunctions.class, "MonthLabelSetHighlighted"),
	MonthLabelSetDefault(Functions.MonthLabelFunctions.class, "MonthLabelSetDefault"),
	
//	******* YearLabels *******
	CalendarViewGoDownToMonths(Functions.YearLabelFunctions.class, "CalendarViewGoDownToMonths"),
	YearLabelSetHighlighted(Functions.YearLabelFunctions.class, "YearLabelSetHighlighted"),
	YearLabelSetDefault(Functions.YearLabelFunctions.class, "YearLabelSetDefault"),
	
	
//	******* DayButtons *******
	OpenEntryCreator(Functions.DayButtonFunctions.class, "OpenEntryCreator"),
	
//	******* EntryLabels *******
	ShowEntryInEntryFrame(Functions.EntryLabelFunctions.class, "ShowEntryInEntryFrame"),
	
	
//	******* EntryAuthorComponents *******
	SimpleEntrySelected(Functions.EntryAuthorFunctions.class, "SimpleEntrySelected"),
	DeadlineSelected(Functions.EntryAuthorFunctions.class, "DeadlineSelected"),
	TimeRangeSelected(Functions.EntryAuthorFunctions.class, "TimeRangeSelected"),
	PointInTimeSelected(Functions.EntryAuthorFunctions.class, "PointInTimeSelected"),
	RepeatsSelected(Functions.EntryAuthorFunctions.class,  "RepeatsSelected"),
	RepeatsDeselected(Functions.EntryAuthorFunctions.class, "RepeatsDeselected"),
	LastRepeatSelected(Functions.EntryAuthorFunctions.class, "LastRepeatSelected"),
	LastRepeatDeselected(Functions.EntryAuthorFunctions.class, "LastRepeatDeselected"),
	ExcludeDatesSelected(Functions.EntryAuthorFunctions.class, "ExcludeDatesSelected"),
	ExcludeDatesDeselected(Functions.EntryAuthorFunctions.class, "ExcludeDatesDeselected"),
	CancelAuthor(Functions.EntryAuthorFunctions.class, "CancelAuthor"),
	ConfirmAuthor(Functions.EntryAuthorFunctions.class, "ConfirmAuthor"),
	ConfirmEdit(Functions.EntryAuthorFunctions.class, "ConfirmEdit");
	;
	
	public final Class<?> CLASS;
	public final String NAME;
	private Functionality(final Class<?> cls, final String name) {
		this.CLASS = cls;
		this.NAME = name;
	}
	
}
