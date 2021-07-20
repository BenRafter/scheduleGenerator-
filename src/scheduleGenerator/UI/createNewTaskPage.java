package scheduleGenerator.UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
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
	
	public Boolean isLegalDate (String x) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(false);
		return sdf.parse(x, new ParsePosition(0)) != null;
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
		JLabel taskName, taskDescription, completionDate, completionTime;
		JTextField nameField, descField, timeField;
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
				System.out.println(temp.run(completionDate)); 
				System.out.println(dateToSet); 
			}
		});
		f.add(chooseDate);   
		/**dateField = new JTextField("YYYY-MM-DD");
		dateField.setBounds(100, 100, 100, 50);
		dateField.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				dateField.setText("");
			}
		});
		f.add(dateField);**/
		
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
				Boolean goodItem = true;//If this is false won't allow the creation of a new item
				System.out.println("Made the thing");
				String itemName = nameField.getText();
				print(itemName);
				String itemDescription = descField.getText();
				print(itemDescription);
				//String itemDate = dateField.getText();
				//goodItem = isLegalDate(itemDate);
				// print(itemDate);
				String itemTime = timeField.getText();
				print(itemTime);
				if(goodItem == true) {
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
						
							String fileName = "data/dataBases/" + _currentUser.getUsername() + ".txt";
							System.out.println(fileName);
							File tempFile = new File(fileName);
							FileWriter writer2 = new FileWriter(tempFile, true);
							//String toWrite = itemName + ", " + itemDescription + ", " + itemDate + ", " + itemTime + "\n";
							//writer2.write(toWrite);
							writer2.write("test");
							tempFile.createNewFile();
							writer2.close();
						}else {
							print("User has existing database");
							String filePath = "data\\dataBases\\" + _currentUser.getUsername() + ".txt";
							FileWriter writer = new FileWriter(filePath);
							writer.write("Text \n");
							writer.close();
						}
						reader.close();
					}catch (Exception f) {
						print("Failed to read existingDataBases text file properly");
					}
				}else {
					print("Item doesn't work");
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
