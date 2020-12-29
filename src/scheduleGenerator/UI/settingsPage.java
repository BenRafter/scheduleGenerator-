package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class settingsPage {
	JFrame f = new JFrame("Settings Page");
	Boolean changesMade = false;
	overseer currentUser;
	int colorPallet = 0;
	int colorPalletChange = 0;
	
	public void replaceLine(String replaceWith) {
		try {
			String filePath = "settings\\settingsFile";
			Scanner sc = new Scanner(new File(filePath));
			StringBuffer buffer = new StringBuffer(); 
			while(sc.hasNextLine()) {
				buffer.append(sc.nextLine()+System.lineSeparator());
			}
			String fileContents = buffer.toString();
			sc.close();
			String oldLine = "colorPallet:0";
			if(colorPallet==1) {
				oldLine = "colorPallet:1";
			}else if(colorPallet==2) {
				oldLine="colorPallet:2";
			}
			String newLine = replaceWith;
			fileContents = fileContents.replaceAll(oldLine, newLine);
			FileWriter writer = new FileWriter(filePath);
			writer.append(fileContents);
			writer.flush();
			writer.close();
		}catch(Exception e) {
			System.out.println("Problem reading file");
		}
	}
	
	public void closeFrame() {
		f.dispose();
	}
	
	public settingsPage(int _colorPallet, overseer _currentUser) {
		colorPallet = _colorPallet; 
		colorPalletChange = colorPallet;
		currentUser = _currentUser;
	}
	
	public void startSettingsPage() {
		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(0,0,100,50);
		exitButton.setToolTipText("Click me to return to the main page without saving any changes!");
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
				if(colorPalletChange != colorPallet) {
					changesMade = true;
				}
				JOptionPane.showMessageDialog(null, "Changes applied! The program will need to be closed and reopened to apply some changes!");
			}
		});
		f.add(applyButton);
		
		JLabel themeLabel = new JLabel("Theme");
		themeLabel.setBounds(0, 50, 100, 50);
		f.add(themeLabel);
		
		JRadioButton themeDefault = new JRadioButton("Default");
		themeDefault.setBounds(50, 50, 100, 50);
		themeDefault.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorPalletChange = 0;
				replaceLine("colorPallet:0");
			}
		});
		f.add(themeDefault);
		
		JRadioButton themeTwilight = new JRadioButton("Twilight");
		themeTwilight.setBounds(150, 50, 100, 50);
		themeTwilight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorPalletChange = 1;
				replaceLine("colorPallet:1");
			}
		});
		f.add(themeTwilight);
		
		JRadioButton themeMidnight = new JRadioButton("Midnight");
		themeMidnight.setBounds(250, 50, 100, 50);
		themeMidnight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colorPalletChange = 2;
				replaceLine("colorPallet:2");
			}
		});
		f.add(themeMidnight);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(themeDefault);
		group.add(themeTwilight);
		group.add(themeMidnight);
		if(colorPallet==0) {
			themeDefault.setSelected(true);
		}else if(colorPallet==1) {
			themeTwilight.setSelected(true);
		}else if(colorPallet==2) {
			themeMidnight.setSelected(true);
		}
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(400,400);
		f.setUndecorated(false);
		if(colorPallet == 1) {
			f.getContentPane().setBackground(Color.gray);
		}else if(colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
			themeLabel.setForeground(Color.white);
			themeDefault.setBackground(Color.black);
			themeDefault.setForeground(Color.white);
			themeTwilight.setBackground(Color.black);
			themeTwilight.setForeground(Color.white);
			themeMidnight.setBackground(Color.black);
			themeMidnight.setForeground(Color.white);
		}
		f.setLayout(null);
		f.setVisible(true);
	}
}
