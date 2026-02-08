package frontend.components;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.invoke.MethodHandle;

import javax.swing.JButton;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MyButton extends JButton implements Interactible {

	public MyButton(String text) {
		super(text);
		setFocusable(false);
	}
	
	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MyButton source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		switch(userAction) {
		case LeftClick:
			addActionListener(event -> {
				try {
					handle.invoke(source);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			break;
		case CursorEnter:
			break;
		case CursorLeave:
			break;
		case LeftDoubleClick:
			break;
		case LeftPress:
			break;
		default:
			break;
		}
		
		
	}
	
}
