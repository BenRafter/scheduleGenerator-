package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import scheduleGenerator.helpers.MainWindow;
import scheduleGenerator.storage.overseer;

public class createNewTaskPage {
	
	public static void print(Object ret) {
		System.out.println(ret);
	}
	
	
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
		JLabel taskName, taskDescription, completionDate;
		JTextField nameField, descField;
		JButton chooseDate, createButton, quitButton;
		String dateToSet = "";
		
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
		
		chooseDate = new JButton("Date"); 
		chooseDate.setBounds(100,100,100,50);
		chooseDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainWindow temp = new MainWindow(); 
				temp.run(completionDate);
				print(" ");
			}
		});
		f.add(chooseDate);   
		
		createButton = new JButton("Create");
		createButton.setToolTipText("Click me to create task");
		createButton.setBounds(0, 200, 100, 50);
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String itemName = nameField.getText();
				print(itemName);
				String itemDescription = descField.getText();
				print(itemDescription);
				String itemDate = completionDate.getText();
				print(itemDate);
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
							print("User does not have existing database, creating one now");
							FileWriter writer = new FileWriter("data\\existingDataBases", true);
							writer.write(_currentUser.getUsername() + "\n");
							writer.close();
							String fileName = "data\\dataBases\\" + _currentUser.getUsername() + ".txt";
							System.out.println(fileName);
							File tempFile = new File(fileName);
							PrintWriter writer2 = new PrintWriter(new FileWriter(tempFile, true));
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
							LocalDateTime now = LocalDateTime.now(); 
							String toWrite = "Item Name: " + itemName + ", Item Description: " + itemDescription + ", Due Date: " + itemDate+ " Date Created: " + dtf.format(now) + "\n";
							writer2.write(toWrite); 
							tempFile.createNewFile();
							writer2.close();
						}else {
							print("User has existing database");
							String filePath = "data\\dataBases\\" + _currentUser.getUsername() + ".txt";
							//FileWriter writer = new FileWriter(filePath);
							PrintWriter writer = new PrintWriter(new FileWriter(filePath, true));
							DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
							LocalDateTime now = LocalDateTime.now();
							String toWrite = "Item Name: " + itemName + ", Item Description: " + itemDescription + ", Due Date: " + itemDate+ " Date Created: " + dtf.format(now) + "\n";
							writer.write(" \n");
							writer.write(toWrite);
							writer.close();
						}
						reader.close();
					}catch (Exception f) {
						print("Failed to read existingDataBases text file properly");
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
		}
	}
}
