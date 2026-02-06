package frontend.container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {

	public ButtonPanel() {
		super();
//		setup layout
		BoxLayout layout = new BoxLayout(this, BoxLayout.X_AXIS);
		setLayout(layout);
	}
	
}
