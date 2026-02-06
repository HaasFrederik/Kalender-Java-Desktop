package frontend.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;
import java.time.LocalDate;

import javax.swing.SwingConstants;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class DayLabel extends MyLabel implements Interactible {

	
	public enum Look { //encompasses text-colour, background-colour and -opacity as well as font
		grey,
		black,
		highlighted,
		pressed;
	}
	
	public Look defaultLook;
	public Look currentLook;
	public LocalDate date;
	
	public DayLabel(LocalDate d, Look look) {
		super("" + d.getDayOfMonth());
		setHorizontalAlignment(SwingConstants.CENTER);
		setMinimumSize(new Dimension(25,25));
		setPreferredSize(new Dimension(25,25));
		setMaximumSize(new Dimension(25,25));
		defaultLook = look;
		currentLook = defaultLook;
		this.showCurrentLook();
	}
	
	public DayLabel(String text) {
		super(text);
		defaultLook = Look.black;
	}

	public void showCurrentLook() {
		switch(currentLook) {
		case black:
			setOpaque(false);
			setForeground(Color.BLACK);
			setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			break;
		case grey:
			setOpaque(false);
			setForeground(Color.GRAY);
			setFont(new Font(Font.DIALOG, Font.BOLD, 12));
			break;
		case pressed:
			setOpaque(true);
			setBackground(new Color(40,200,200));
			setForeground(new Color(200,40,40));
			setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
			break;
		case highlighted:
			setOpaque(true);
			setBackground(Color.DARK_GRAY);
			setForeground(Color.ORANGE);
			setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
			break;
		default:
			break;
		
		}
	}
	
	@Override
	public void doOn(UserAction userAction, Functionality function) {
		DayLabel source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		switch(userAction) {
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
			addMouseListener(new MouseAdapter () {
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
		case LeftClick:
			break;
		case LeftDoubleClick:
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
