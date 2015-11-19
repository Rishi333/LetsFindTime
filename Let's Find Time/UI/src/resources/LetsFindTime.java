package resources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LetsFindTime 
{
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	
	// Login variables
	private boolean userNotFound;
	
	// Sign-up variables
	private boolean userExisted;
	
	private User user;
	
	public LetsFindTime()
	{
		this.connection = null;
		this.statement = null;
		this.resultSet = null;
		this.user = null;
		this.userNotFound = true;
		this.userExisted = false;
	}
	
	public boolean connectDB()
	{
		try 
		{
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LetFindTime","cs160", "qweasdzxc");
	 
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		if (this.connection != null)
			return true;
		return false;
	}
	
	public boolean checkEmail(String email)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM users WHERE email=?");
			this.statement.setString(1, email);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
				return true;
			else
			{
				return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public User userLogin(String email, String password)
	{
		try
		{
			this.statement = this.connection.prepareStatement("SELECT * FROM users WHERE email=?");
			this.statement.setString(1, email);
			this.resultSet = this.statement.executeQuery();
			if(resultSet.next())
				this.userNotFound = false;
			else
			{
				return null;
			}
			user = new User(resultSet.getInt("userID"), resultSet.getString("fname"), resultSet.getString("lname"), resultSet.getString("email"), resultSet.getString("password"));
			if(!user.getPassword().equals(password))
				return null;
			else
				return user;
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
		return user;
	}
	
	public boolean getUserNotFound()
	{
		return this.userNotFound;
	}
	
	public boolean userSignUp(User user)
	{
		try
		{
			if(this.checkEmail(user.getEmail()))
				return false;
			else
			{
				this.statement = this.connection.prepareStatement("INSERT INTO Users (fname, lname, email, password) VALUES (?, ?, ?, ?)");
				this.statement.setString(1, user.getFname());
				this.statement.setString(2, user.getLname());
				this.statement.setString(3, user.getEmail());
				this.statement.setString(4, user.getPassword());
				this.statement.executeUpdate();
				if(this.checkEmail(user.getEmail()))
					return true;
				else
					return false;
			}
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public boolean getUserExisted()
	{
		return this.userExisted;
	}
}
