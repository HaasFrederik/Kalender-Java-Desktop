package frontend.components;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.time.LocalDate;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class YearLabel extends MyLabel implements Interactible {

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

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		YearLabel source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		switch (userAction) {
		case CursorEnter:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case CursorLeave:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case LeftPress:
			addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		default:
			break;
		}

	}
}
