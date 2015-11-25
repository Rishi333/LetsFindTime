
public class loginBean
{
    private String name;
    private String password;


    public String getName ()
    {
    	System.out.println(name);
        return name;
    }


    public void setName (final String name)
    {
    	System.out.println("set name");
        this.name = name;
    }


    public String getPassword ()
    {
        return password;
    }


    public void setPassword (final String password)
    {
    	System.out.println("set password");
        this.password = password;
    }
}
