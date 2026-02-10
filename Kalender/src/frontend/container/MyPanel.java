package frontend.container;

import javax.swing.JPanel;

public abstract class MyPanel extends JPanel {

//	so I do not forget to setLayout
	public abstract void setupLayout();
//	to manage content changes
	public abstract void update();
	
}
