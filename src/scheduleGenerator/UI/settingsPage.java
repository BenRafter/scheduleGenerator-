package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class settingsPage {
	JFrame f = new JFrame("Settings Page");
	Boolean changesMade = false;
	overseer currentUser;
	int colorPallet = 0;
	
	public void closeFrame() {
		f.dispose();
	}
	
	public settingsPage(int _colorPallet, overseer _currentUser) {
		colorPallet = _colorPallet; 
		currentUser = _currentUser;
	}
	
	public void startSettingsPage() {
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(0,0,100,50);
		exitButton.setToolTipText("Click me to return to the main page!");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPage temp = new mainPage(colorPallet, currentUser);
				temp.startMainpage();
				closeFrame();
			}
		});
		f.add(exitButton);
	
		JButton applyButton = new JButton("Apply");
		applyButton.setBounds(100,0,100,50);
		applyButton.setToolTipText("Click me to apply settings changes!");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Apply hit");
			}
		});
		f.add(applyButton);
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(400,400);
		f.setUndecorated(false);
		if(colorPallet == 1) {
			f.getContentPane().setBackground(Color.gray);
		}else if(colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
		}
		f.setLayout(null);
		f.setVisible(true);
	}
}
