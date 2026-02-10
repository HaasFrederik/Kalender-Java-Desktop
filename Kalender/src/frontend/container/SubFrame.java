package frontend.container;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class SubFrame extends MyPanel {

	public LabelPanel labelPanel;
	public MainPanel mainPanel;
	public ButtonPanel buttonPanel;
//	Array contains LabelPanel, MainPanel, ButtonPanel. Implemented in child-classes to give order of addition to subframe
//	public JPanel[] panels;
	
	public SubFrame() {
//		setLayout
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(layout);
	}
	@Override
	public void setupLayout() {
//	setLayout
	BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
	setLayout(layout);
	}

}
