package frontend.container;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public abstract class SubFrame extends JPanel{

	public LabelPanel labelPanel;
	public MainPanel mainPanel;
	public ButtonPanel buttonPanel;
	
	public SubFrame() {
//		setLayout
		BorderLayout layout = new BorderLayout();
		setLayout(layout);
	}


}
