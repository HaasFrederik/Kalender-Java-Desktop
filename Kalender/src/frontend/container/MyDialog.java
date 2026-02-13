package frontend.container;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.invoke.MethodHandle;

import javax.swing.JDialog;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MyDialog extends JDialog implements Interactible {

	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MyDialog source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		switch (userAction) {
		default:
			System.out.println("Interaction " + userAction + " not defined for class MyFrame.");
			break;
		case WindowClose:
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent event) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		}

	}

}
