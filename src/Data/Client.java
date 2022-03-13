package Data;

public class Client 
{
    private int IdClient;
    private String Nom;
    private String Prenom;
    private String Sexe;
    private String CIN;
    public void Client(int IdClient,String Nom,String Prenom,String Sexe,String CIN)
    {
        this.IdClient=IdClient;
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Sexe=Sexe;
        this.CIN=CIN;
    }
    public void Client(String Nom,String Prenom,String Sexe,String CIN)
    {
        this.Nom=Nom;
        this.Prenom=Prenom;
        this.Sexe=Sexe;
        this.CIN=CIN;
    }
    public void Client()
    {
        
    }
    public int getIdClient()
    {
        return IdClient;
    }
    public String getNom()
    {
        return Nom;
    }
    public String getPrenom()
    {
        return Prenom;
    }
    public String getSexe()
    {
        return Sexe;
    }
    public String getCIN()
    {
        return CIN;
    }
    public void setIdClient(int IdClient)
    {
        this.IdClient=IdClient;
    }
    public void setNom(String Nom)
    {
        this.Nom=Nom;
    }
    public void setPrenom(String Prenom)
    {
        this.Prenom=Prenom;
    }
    public void setSexe(String Sexe)
    {
        this.Sexe=Sexe;
    }
    public void setCIN(String CIN)
    {
        this.CIN=CIN;
    }

    @Override
    public String toString() {
        return ""+IdClient+"";
    }
    
    
    
}
