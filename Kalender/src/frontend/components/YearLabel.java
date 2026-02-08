package frontend.components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.time.LocalDate;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class YearLabel extends MyLabel {

	public LocalDate year;

	public YearLabel(LocalDate d) {
		super("" + d.getYear());
		year = d;
		setMinimumSize(new Dimension(35,25));
		setPreferredSize(new Dimension(35,25));
		setMaximumSize(new Dimension(35,25));
	}

	public YearLabel(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
}
