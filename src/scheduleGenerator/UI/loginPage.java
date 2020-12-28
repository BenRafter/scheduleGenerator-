//This page is where the user will log in to the schedule maker. Can be set to ignore. 
package scheduleGenerator.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import scheduleGenerator.storage.overseer;

public class loginPage {
	static JFrame f = new JFrame("Login");
	static Boolean skipLogin = false;
	static Map<String, String> userList = new HashMap<String, String>(); 
	overseer currentUser;
	
	public static void closeFrame() {
		f.dispose();
	}
	
	public static void startLogin() {
		try {
			Scanner input = new Scanner( new File("settings\\settingsFile"));
			String skip = input.nextLine().toString(); 
			skip = skip.substring(10);
			int skip2 = Integer.parseInt(skip); 
			if(skip2 == 0) {
				skipLogin = false;
			}else if(skip2 == 1) {
				skipLogin = true;
			}
			input.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Scanner input = new Scanner(new File("data\\userData"));
			String curLine = "";
			while(input.hasNext()) {
				//ToDo: Make this section cleaner 
				//This section looks into the userData file and grabs all users and adds them to the userList 
				curLine=input.next().toString();
				String user = curLine.substring(curLine.indexOf(":")+1);
				user = user.substring(0, user.indexOf(":"));
				String password = curLine.substring(curLine.indexOf(";")+1);
				password = password.substring(0, password.indexOf(";"));
				String isAdmin = curLine.substring(curLine.indexOf(",")+1);
				String value = password + " " + isAdmin;
				userList.put(user, value);
			}
			input.close();
		}catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		JLabel userLabel, passLabel; 
		userLabel = new JLabel("Enter username: ");
		userLabel.setBounds(50,100,100,40);
		f.add(userLabel); 
		
		passLabel = new JLabel("Enter password: ");
		passLabel.setBounds(50,150,100,40);
		f.add(passLabel); 
		
		JTextField userName, password; 
		userName = new JTextField();
		userName.setBounds(150,100,150,40);
		f.add(userName); 
		
		password = new JTextField();
		password.setBounds(150, 150, 150, 40);
		f.add(password);
		
		JLabel statusLabel = new JLabel("Welcome to the home page");
		statusLabel.setBounds(50,50,500,40);
		f.add(statusLabel);
		
		JButton login = new JButton("Login");
		login.setBounds(150, 200, 100, 40);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean userFound = false;
				String user = userName.getText();
				String passWord = password.getText();
				for(Map.Entry entry : userList.entrySet()) {
					String listUser = (String) entry.getKey(); 
					String listPassword = (String) entry.getValue();
					System.out.println(listUser);
					System.out.println(listPassword);
					listPassword = listPassword.substring(0, listPassword.indexOf(" "));
					System.out.println(listPassword);
					if(user.equals(listUser) && passWord.equals(listPassword)) {
						userFound = true;
					}
				}
				if(userFound == false) {
					statusLabel.setText("User or password wrong");
				}else {
					statusLabel.setText("User found");
					mainPage temp = new mainPage(); 
					temp.startMainpage();
					closeFrame();
				}
			}
		});
		f.add(login);
		
		f.setSize(400, 500);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		if(skipLogin == true) {
			mainPage temp = new mainPage();
			temp.startMainpage();
			closeFrame();
		}
	}

	
	public static void main(String[] args) {
		startLogin(); 
	}
}
