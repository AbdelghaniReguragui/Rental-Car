package Data;

public class Login 
{
    private String Username;
    private String Password;
    private int IdLogin;
    public Login(String Username, String Password, int IdLogin)
    {
        this.Username=Username;
        this.Password=Password;
        this.IdLogin=IdLogin;
    }
    public Login(String Username, String Password)
    {
        this.Username=Username;
        this.Password=Password;
    }
    public String getUsername()
    {
        return Username;
    }
    public String getPassword()
    {
        return Password;
    }
    public int getIdLogin()
    {
        return IdLogin;
    }
    public void setUsername(String Username)
    {
        this.Username=Username;
    }
    public void setPassword(String Password)
    {
        this.Password=Password;
    }
    public void setIdLogin(int IdLogin)
    {
        this.IdLogin=IdLogin;
    }
}
