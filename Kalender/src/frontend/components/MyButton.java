package frontend.components;

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
		switch(userAction) {
		case LeftClick:
			addActionListener(event -> {
				try {
					MethodHandle handle = resolveFunctionality(function, this);
					handle.invoke(this);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
