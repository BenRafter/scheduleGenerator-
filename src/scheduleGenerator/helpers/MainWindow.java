package scheduleGenerator.helpers;

import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainWindow extends JFrame implements PropertyChangeListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//the TextField for typing the date
	JFormattedTextField  textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
	
	public static String _dateToSet;
	public static JLabel _cntp;
	/**public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainWindow window = null;
				try {
					window = new MainWindow();
					window.setVisible(true);
				}
				catch (Exception exp) {
					exp.printStackTrace();
				}
			}
		});
	}**/
		public static String run(JLabel cntp) {
			_cntp = cntp;
			System.out.println(cntp.getText()); 
			MainWindow window = null; 
			try {
				window = new MainWindow(); 
				window.setVisible(true); 
			}catch(Exception exp) {
				exp.printStackTrace();
			}
			return _dateToSet;
		}
		public MainWindow()
		{ 
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setSize(368, 362);
			setTitle("MindFusion.Scheduling Sample: Minimal Application");
			
			Container cp = getContentPane();
			FlowLayout flowLayout = new FlowLayout();
			
			cp.setLayout(flowLayout);			
			 			
			 
			textField.setValue(new Date());
			textField.setPreferredSize(new Dimension(130, 30));
			    
			// display the window with the calendar
			CalendarWindow calendarWindow = new CalendarWindow(); 
			    
			//wire a listener for the PropertyChange event of the calendar window
			calendarWindow.addPropertyChangeListener(this);
			
			
			JButton calendarButton = new JButton("Pick a Date");
			JButton submitButton = new JButton("Submit");		
			JLabel info = new JLabel(); 
			info.setText("When choosing a date be sure to double click the date!");
			
			calendarButton.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
				//render the calendar window below the text field
				calendarWindow.setLocation(textField.getLocationOnScreen().x, (textField.getLocationOnScreen().y + textField.getHeight()));
				//get the Date and assign it to the calendar
				Date d = (Date)textField.getValue();				
					
				calendarWindow.resetSelection(d);				
				calendarWindow.setUndecorated(true);
			    calendarWindow.setVisible(true);
			  }
			});
			
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					_cntp.setText(textField.getText()); 
					dispose(); 
				}
			});

			//add the UI controls to the ContentPane
			cp.add(textField);
			cp.add(calendarButton);
			cp.add(submitButton);
			info.setBounds(50,150,100,40);
			cp.add(info);
			cp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	        
	        
		}
		
        @Override
		public void propertyChange(PropertyChangeEvent event) {
			
        	//get the selected date from the calendar control and set it to the text field
			if (event.getPropertyName().equals("selectedDate")) {
	            
				java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
				Date selDate =  cal.getTime();
				textField.setValue(selDate);
	        }
			
		}
        
        

}
