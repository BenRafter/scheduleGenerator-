//This page is where the user will log in to the schedule maker. Can be set to ignore. 
package scheduleGenerator.UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class loginPage {
	
	public static void startLogin() {
		JFrame f = new JFrame("Login");
		
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
		
		JButton login = new JButton("Login");
		login.setBounds(150, 200, 100, 40);
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(userName.getText()); 
				System.out.println(password.getText());
			}
		});
		f.add(login);
		
		f.setSize(400, 500);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		startLogin(); 
	}
}
