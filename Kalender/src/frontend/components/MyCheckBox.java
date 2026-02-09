package frontend.components;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.lang.invoke.MethodHandle;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;

import backend.interactivity.Functionality;
import backend.interactivity.Interactible;
import backend.interactivity.UserAction;

public class MyCheckBox extends JComponent implements Interactible {

	public JLabel textLabel;
	public JCheckBox checkBox;
	
	public MyCheckBox(String name) {
		super();
		checkBox = new JCheckBox();
		textLabel = new JLabel(name, JLabel.LEFT);
		setLayout(new BorderLayout());

		add(checkBox,BorderLayout.WEST);
		add(textLabel,BorderLayout.CENTER);
	}
	
	@Override
	public void doOn(UserAction userAction, Functionality function) {
		MyCheckBox source = this;
		MethodHandle handle;
		try {
			handle = resolveFunctionality(function, source);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		switch(userAction) {
		case SelectCheckBox:
			checkBox.addItemListener(event ->  {
				if (event.getStateChange() == ItemEvent.SELECTED) {
					try {
						handle.invoke(source);
					} catch (Throwable e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			break;
		case DeselectCheckBox:
			checkBox.addItemListener(event ->  {
				if (event.getStateChange() == ItemEvent.DESELECTED) {
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
	
	public void setSelected(boolean selected) {
		checkBox.setSelected(selected);
	}
	
	public void setEnabled(boolean enabled) {
		checkBox.setEnabled(enabled);
	}
	
	public boolean isSelected() {
		return checkBox.isSelected();
	}

}
