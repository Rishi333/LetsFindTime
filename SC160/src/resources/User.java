package resources;

public class User 
{
	private int userID;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private int phone;
	
	public User(int userID, String fname, String lname, String email, String password)
	{
		this.userID = userID;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
	}
	
	public User(String fname, String lname, String email, String password, int phone)
	{
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.phone = phone;
	}
	
	// Getters
	public int getUserID() {
		return userID;
	}
	public String getFname() {
		return fname;
	}
	public String getLname() {
		return lname;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	
	// Setters
	public void setFname(String fname) {
		this.fname = fname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
