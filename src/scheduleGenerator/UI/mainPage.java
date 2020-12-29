package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class mainPage {
	JFrame f = new JFrame("Main Page");
	int colorPallet = 0;
	overseer currentUser;
	
	public mainPage(int _colorPallet, overseer _currentUser) {
		colorPallet = _colorPallet;
		currentUser = _currentUser;
	}
	
	public void closeFrame() {
		f.dispose();
	}
	
	public void startMainpage() {	
		JLabel nameLabel = new JLabel("Current user: " + currentUser.getUsername());
		nameLabel.setBounds(0, 150, 300, 50);
		f.add(nameLabel);
		
		JLabel greetingLabel = new JLabel("Welcome to the home page! Hover over a button to see what it does!");
		greetingLabel.setBounds(105, 0, 500, 50);
		f.add(greetingLabel);
		
		JLabel adminLabel = new JLabel("Is admin: " + currentUser.getAdminStatus());
		adminLabel.setBounds(0, 200, 150, 50);
		f.add(adminLabel);
		
		JButton logoutButton = new JButton("Log out");
		logoutButton.setBounds(0, 0, 100, 50);
		logoutButton.setToolTipText("Click me to return to the login screen");
		logoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPage temp = new loginPage(); 
				temp.startLogin();
				closeFrame();
			}
		});
		f.add(logoutButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setToolTipText("Click me to return to the desktop");
		quitButton.setBounds(0,50,100,50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeFrame();
			}
		});
		f.add(quitButton);
		
		//Settings change only for admins 
		JButton settingsButton = new JButton("Settings");
		settingsButton.setToolTipText("Click me to change the program settings! Only usable by an admin!");
		settingsButton.setBounds(0, 100, 100, 50);
		settingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				settingsPage temp = new settingsPage(colorPallet, currentUser);
				temp.startSettingsPage();
				closeFrame();
			}
		});
		f.add(settingsButton);
		if(!currentUser.getAdminStatus()) {
			settingsButton.setEnabled(false);
		}
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(false);
		if(colorPallet == 1) {
			f.getContentPane().setBackground(Color.GRAY);
		}else if(colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
			adminLabel.setForeground(Color.white);
			nameLabel.setForeground(Color.white);
			greetingLabel.setForeground(Color.white);
		}
		f.setLayout(null);
		f.setVisible(true);
	}
}
