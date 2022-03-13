package Data;

public class Voiture 
{
    private int IdVoiture;
    private String Marque;
    private String Modele;
    private String Transmission;
    private String Couleur;
    private int Prix;
    private int PrixPenalite;

    public void Voiture(int IdVoiture,String Marque,String Modele,String Transmission,String Couleur,int Prix,int PrixPenalite)
    {
        this.IdVoiture=IdVoiture;
        this.Marque=Marque;
        this.Modele=Modele;
        this.Transmission=Transmission;
        this.Couleur=Couleur;
        this.Prix=Prix;
        this.PrixPenalite=PrixPenalite;
    }
    
    public void Voiture(String Marque,String Modele,String Transmission,String Couleur,int Prix,int PrixPenalite)
    {
        this.Marque=Marque;
        this.Modele=Modele;
        this.Transmission=Transmission;
        this.Couleur=Couleur;
        this.Prix=Prix;
        this.PrixPenalite=PrixPenalite;
    }
    public void Voiture()
    {
    }
    public int getIdVoiture()
    {
        return IdVoiture;
    }
    public String getMarque()
    {
        return Marque;
    }
    public String getModele()
    {
        return Modele;
    }
    public String getTransmission()
    {
        return Transmission;
    }
    public String getCouleur()
    {
        return Couleur;
    }
    public int getPrix()
    {
        return Prix;
    }
    public int getPrixPenalite()
    {
        return PrixPenalite;
    }
    
    public void setIdVoiture(int IdVoiture)
    {
        this.IdVoiture=IdVoiture;
    }
    public void setMarque(String Marque)
    {
        this.Marque=Marque;
    }
    public void setModele(String Modele)
    {
        this.Modele=Modele;
    }
    public void setTransmission(String Transmission)
    {
        this.Transmission=Transmission;
    }
    public void setCouleur(String Couleur)
    {
        this.Couleur=Couleur;
    }
    public void setPrix(int Prix)
    {
        this.Prix=Prix;
    }
    public void setPrixPenalite(int PrixPenalite)
    {
        this.PrixPenalite=PrixPenalite;
    }

    @Override
    public String toString() {
        return ""+IdVoiture +"";
    }
    
    
}
