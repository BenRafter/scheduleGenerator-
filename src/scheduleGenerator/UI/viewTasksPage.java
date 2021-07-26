package scheduleGenerator.UI;

import java.awt.Color;

import javax.swing.JFrame;
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
