package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class viewTasksPage {
	JFrame f = new JFrame("View Tasks");
	int colorPallet = 0;
	overseer currentUser; 
	
	public void print(Object toPrint) {
		System.out.println(toPrint); 
	}
	
	public viewTasksPage(int _colorPallet, overseer _currentUser) {
		this.colorPallet = _colorPallet; 
		this.currentUser = _currentUser; 
	}
	
	public void closeFrame() {
		f.dispose();
	}
	
	public void startViewTasksPage() {
		int count = 0;
		try {
			String filePath = "data\\databases\\"+ currentUser.getUsername()+".txt";
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line = "";
			int nextPos = 50;
			
			while((line = in.readLine())!=null) {
				count++;
			}
			in.close();
			String[] textFromFile = new String[count];
			BufferedReader in2 = new BufferedReader(new FileReader(filePath));
			String line2 = "";
			int countTemp = count-1;
			while((line2 = in2.readLine()) != null) {
				textFromFile[countTemp] = line2;
				countTemp--;
			}
			JTextField[] taskArray = new JTextField[count];
			for(int i = count - 1; i > -1; i--) {
				print("text field created");
				taskArray[i] = new JTextField(textFromFile[i]);
				taskArray[i].setBounds(0, nextPos, 1000, 50);
				nextPos += 50;
				f.add(taskArray[i]);
			}
			int count2 = count;
			JButton submitButton = new JButton("Submit Changes");
			submitButton.setBounds(0,0, 200, 50);
			submitButton.setToolTipText("Click me to submit your changes and return to the main page");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						PrintWriter writer = new PrintWriter(filePath);
						writer.print("");
						for(int i = count2-1; i > -1; i--) {
							writer.write(taskArray[i].getText());
							writer.append("\n");
						}
						writer.close();
						JOptionPane.showMessageDialog(null,  "Change Applied");
						mainPage temp = new mainPage(colorPallet, currentUser);
						temp.startMainpage();
						closeFrame();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			f.add(submitButton);
			
			JButton quitButton = new JButton("Back");
			quitButton.setBounds(200, 0, 200, 50);
			quitButton.setToolTipText("Click me to go back to the main page. If you didn't hit submit your changes won't be saved!");
			quitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainPage temp = new mainPage(colorPallet, currentUser);
					temp.startMainpage();
					closeFrame();
				}
			});
			f.add(quitButton);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(f, "Failed to open data for user");
			e.printStackTrace();
		}
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setUndecorated(false);
		if(colorPallet == 1) {
			f.getContentPane().setBackground(Color.GRAY);
		}else if(colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
			//change label colors to Color.white
		}
		f.setLayout(null);
		f.setVisible(true);
	}
}
