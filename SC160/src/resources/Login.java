package resources;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name="login")
public class Login 
{
     
    private String email;
    private String password;
    private String message;
    private User user;
    
	private String fname;
	private String lname;
	
	private String event = "";
	
	private LetsFindTime lft;
 
    public Login()
    {
    	this.email = null;
    	this.password = null;
    	this.message = "";
    	this.user = null;
    	this.event = "";
    }
    
    public String userLogin()
    {
    	lft = new LetsFindTime();
    	boolean ifConnected = lft.connectDB();
    	if(ifConnected)
    	{
    		this.user = lft.userLogin(this.getEmail(), this.getPassword());
    		if(user != null)
    		{
    			this.email = user.getEmail();
    			this.fname = user.getFname();
    			this.lname = user.getLname();
    			this.password = user.getPassword();
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
    
    public String eventSubmit()
    {
    	this.email = FacesContext.getCurrentInstance().
    			getExternalContext().getRequestParameterMap().get("email");
    	this.password = FacesContext.getCurrentInstance().
    			getExternalContext().getRequestParameterMap().get("password");
    	lft = new LetsFindTime();
    	boolean ifConnected = lft.connectDB();
    	
    	if(ifConnected)
    	{
    		this.user = lft.userLogin(this.getEmail(), this.getPassword());
    		if(user != null)
    		{
    			if(this.lft.eventUpdate(user.getUserID(), this.event) && !event.equals("") && event != null )
    	    	{
    	    		this.message = "The calandar has been updated!";
    	    	}
    	    	else
    	    		this.message = "The calandar has not been updated yet!";
    		}
    		else
    		{
    			this.message = "There is something WRONG!";
    			return "index";
    		}
    	}
    	return "User";
    }

    // Getters
	public String getEmail() { return email; }
	public String getPassword() { return password; }
	public String getMessage() { return this.message; }
	public String getFname() { return this.user.getFname(); }
	public String getLname() { return this.user.getLname(); }
	public String getEvent() { return event; }
	
	// Setters
	public void setEmail(String email) { this.email = email; }
	public void setPassword(String password) { this.password = password; }
	public void setEvent(String event) { this.event = event; }
	
}