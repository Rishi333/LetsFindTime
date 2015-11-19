package resources;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Login 
{
     
    private String email;
    private String password;
    private String message;
    private User user;
    
	private String fname;
	private String lname;
 
    public Login()
    {
    	this.email = null;
    	this.password = null;
    	this.message = "";
    	this.user = null;
    }
    
    public String userLogin()
    {
    	LetsFindTime lft = new LetsFindTime();
    	boolean ifConnected = lft.connectDB();
    	if(ifConnected)
    	{
    		this.user = lft.userLogin(this.getEmail(), this.getPassword());
    		if(user != null)
    		{
    			return "User";
    		}
    		else
    		{
    			if(lft.getUserNotFound())
    			{
    				this.message = "The email is not registed in the system!";
    				return "index";
    			}
    			this.message = "The password is incorrect!";
    			return "index";
    		}
    	}
    	else
    	{
    		this.message = "The database server is down!";
    		return "index";
    	}
    }

    // Getters
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getMessage() { return this.message; }
	public String getFname() { return this.user.getFname(); }
	public String getLname() { return this.user.getLname(); }
	
	// Setters
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	
}