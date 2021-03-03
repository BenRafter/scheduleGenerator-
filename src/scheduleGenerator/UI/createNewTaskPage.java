package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class createNewTaskPage {
	JFrame f = new JFrame("Create new task");
	int _colorPallet;
	overseer _currentUser;
	
	public void closeFrame() {
		f.dispose();
	}
	
	public createNewTaskPage(int colorPallet, overseer currentUser){
		_colorPallet = colorPallet;
		_currentUser = currentUser;
	}
	
	public void startCreateNewTaskPage() {
		JLabel taskName, taskDescription, completionDate, completionTime;
		JTextField nameField, descField, dateField, timeField;
		JButton createButton, quitButton;
		
		taskName = new JLabel("Task Name");
		taskName.setBounds(0,0,100,50);
		f.add(taskName);
		
		nameField = new JTextField("Name");
		nameField.setBounds(100, 0 , 100, 50);
		nameField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				nameField.setText("");
			}
		});
		f.add(nameField);
		
		taskDescription = new JLabel("Task description");
		taskDescription.setBounds(0,50,100,50);
		f.add(taskDescription);
		
		descField = new JTextField("Description");
		descField.setBounds(100, 50, 100, 50);
		descField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				descField.setText("");
			}
		});
		f.add(descField);
		
		completionDate = new JLabel("Date due");
		completionDate.setBounds(0, 100, 100, 50);
		f.add(completionDate);
		
		dateField = new JTextField("YYYY-MM-DD");
		dateField.setBounds(100, 100, 100, 50);
		dateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateField.setText("");
			}
		});
		f.add(dateField);
		
		completionTime = new JLabel("Time due");
		completionTime.setBounds(0, 150, 100, 50);
		f.add(completionTime);
		
		timeField = new JTextField("HH:MM 24hr format");
		timeField.setBounds(100,150,130,50);
		timeField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				timeField.setText("");
			}
		});
		f.add(timeField);
		
		createButton = new JButton("Create");
		createButton.setToolTipText("Click me to create task");
		createButton.setBounds(0, 200, 100, 50);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Made the thing");
				try {
					Scanner reader = new Scanner(new File("data\\existingDataBases"));
					String curLine = "";
					Boolean foundUser = false;
					while(reader.hasNext()) {
						curLine = reader.next().toString();
						if(curLine.equals(_currentUser.getUsername())) {
							foundUser = true;
						}
					}
					if(foundUser == false) {
						System.out.println("User does not have existing database, creating one now");
						FileWriter writer = new FileWriter("data\\existingDataBases", true);
						writer.write(_currentUser.getUsername() + "\n");
						writer.close();
						
						String fileName = "data/dataBases/" + _currentUser.getUsername() + ".txt";
						System.out.println(fileName);
						File tempFile = new File(fileName);
						tempFile.createNewFile();
					}else {
						System.out.println("User has existing database");
					}
					reader.close();
				}catch (Exception f) {
					System.out.println("Failed to read existingDataBases text file properly");
				}
			}
		});
		f.add(createButton);
		
		quitButton = new JButton("Quit");
		quitButton.setToolTipText("Click me to quit to the previous page without creating a task");
		quitButton.setBounds(100, 200, 100, 50);
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPage temp = new mainPage(_colorPallet, _currentUser);
				temp.startMainpage();
				closeFrame();
			}
		});
		f.add(quitButton);
		
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setSize(400,500);
		f.setLayout(null);
		f.setVisible(true);
		if(_colorPallet == 1) {
			f.getContentPane().setBackground(Color.gray);
		}else if(_colorPallet == 2) {
			f.getContentPane().setBackground(Color.black);
			taskName.setForeground(Color.white);
			taskDescription.setForeground(Color.white);
			completionDate.setForeground(Color.white);
			completionTime.setForeground(Color.white);
		}
	}
}
