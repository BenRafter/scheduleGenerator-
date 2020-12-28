//This class holds all the information of the current user 
package scheduleGenerator.storage;

public class overseer {
	String userName;
	String passWord;
	Boolean admin;
	
	public overseer(String user, String password, Boolean isAdmin) {
		userName = user;
		passWord = password;
		admin = isAdmin;
	}
	
	public String getUsername() {
		return userName;
	}
	
	public String getPassword() {
		return passWord;
	}
	
	public Boolean getAdminStatus() {
		return admin;
	}
}
