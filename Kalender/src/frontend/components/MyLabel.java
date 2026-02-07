package frontend.components;

import javax.swing.JLabel;
import javax.swing.SwingConstants;



public class MyLabel extends JLabel {

	public MyLabel() {
		super();
	}
	
	public MyLabel(String text) {
		super(text);
		setHorizontalAlignment(SwingConstants.CENTER);
//		TODO layout stuff
	}
	
}
