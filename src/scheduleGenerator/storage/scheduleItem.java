//This is the actual item that holds what you are adding to the schedule 
package scheduleGenerator.storage;

import java.time.LocalDate;
import java.time.LocalTime;

public class scheduleItem {
	String _taskName;//Name of the task 
	String _taskDescription;//Description of the task 
	LocalDate _completionDate;//Date the task should be completed 
	LocalTime _completionTime;//Time the task should be completed 
	
	public scheduleItem(String taskName, String taskDescription, LocalDate completionDate, LocalTime completionTime) {
		_taskName = taskName; 
		_taskDescription = taskDescription; 
		_completionDate = completionDate;
		_completionTime = completionTime;
	}
	
	public String getName() {
		return _taskName;
	}
	
	public void editName(String name) {
		_taskName = name;
	}
	
	public String getTaskDescription() {
		return _taskDescription;
	}
	
	public void editDescription(String description) {
		_taskDescription = description;
	}
	
	public LocalDate getCompletionDate() {
		return _completionDate; 
	}
	
	public void editDate(LocalDate date) {
		_completionDate = date;
	}
	
	public LocalTime getCompletionTime() {
		return _completionTime;
	}
	
	public void editTime(LocalTime time) {
		_completionTime = time;
	}
	
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(_taskName + " ");
		ret.append(_completionDate + " " + _completionTime + " ");
		ret.append(_taskDescription);
		return ret.toString();
	}
}
