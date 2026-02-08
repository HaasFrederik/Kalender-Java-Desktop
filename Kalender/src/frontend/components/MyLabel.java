package frontend.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.invoke.MethodHandle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;



public class MyLabel extends JLabel implements Interactible{

	public MyLabel() {
		super();
	}
	
	public MyLabel(String text) {
		super(text);
		setHorizontalAlignment(SwingConstants.CENTER);
//		TODO layout stuff
	}

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MyLabel source = this;
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
