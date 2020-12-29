package scheduleGenerator.UI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class mainPage {
	public void startMainpage() {
		JFrame f = new JFrame("Main Page");
		
		JButton logoutButton = new JButton("Log out");
		
		
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(true);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
