package resources;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Registration 
{
	private String fname, lname, email, password, rePassword;
	private int phone;
	private String message;
	private User user;
	
	public Registration()
	{
		this.fname = null;
		this.lname = null;
		this.email = null;
		this.password = null;
		this.rePassword = null;
		this.phone = -1;
	}
	
    public String userSignUp()
    {
    	LetsFindTime lft = new LetsFindTime();
    	boolean ifConnected = lft.connectDB();
    	if(ifConnected)
    	{
    		if(!lft.checkEmail(this.email))
    		{
    			if(this.fname != null || this.lname != null || this.email != null || this.password != null || this.rePassword != null)
    			{
    				if(this.password.equals(this.rePassword))
    				{
    					lft.userSignUp(new User(this.fname, this.lname, this.email, this.password, this.phone));
    					this.message = "The account has been created!";
    				}
    				else
    				{
    					this.message = "The passwords are not match!";
    				}
    			}
    		}
    		else
    		{
    			this.message = "The email has been already existed in the system!";
    		}
    	}
    	else
    	{
    		this.message = "The system is down!";
    	}
    	return "register";
    }
    
    
    // Getters
	public String getFname() { return fname; }
	public String getLname() { return lname; }
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getRePassword() {	return rePassword; }
	public int getPhone() { return phone; }
	public String getMessage() { return message; }
	public User getUser() { return user; }

	// Setters
	public void setFname(String fname) { this.fname = fname; }
	public void setLname(String lname) { this.lname = lname; }
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setRePassword(String rePassword) { this.rePassword = rePassword; }
	public void setPhone(int phone) { this.phone = phone; }
	public void setMessage(String message) { this.message = message; }
	public void setUser(User user) { this.user = user;}
	
}
