package frontend.container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SubFrame extends JPanel {

	public LabelPanel labelPanel;
	public MainPanel mainPanel;
	public ButtonPanel buttonPanel;
	
	public SubFrame() {
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
}
