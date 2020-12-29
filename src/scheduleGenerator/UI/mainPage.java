package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

public class mainPage {
	JFrame f = new JFrame("Main Page");
	int colorPallet = 0;
	
	public mainPage(int _colorPallet) {
		colorPallet = _colorPallet;
	}
	
	public void closeFrame() {
		f.dispose();
	}
	
	public void startMainpage() {	
		JButton logoutButton = new JButton("Log out");
		logoutButton.setBounds(0, 0, 100, 50);
		logoutButton.setToolTipText("Return to login screen");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Logout pressed");
				loginPage temp = new loginPage(); 
				temp.startLogin();
				closeFrame();
			}
		});
		f.add(logoutButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Return to desktop");
		quitButton.setBounds(0,50,100,50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		f.add(quitButton);
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(false);
		if(colorPallet == 1) {
			f.getContentPane().setBackground(Color.GRAY);
		}else if(colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
		}
		f.setLayout(null);
		f.setVisible(true);
	}
}
