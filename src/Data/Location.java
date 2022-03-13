package Data;

import java.time.LocalDate;
import java.util.Date;

public class Location 
{

    public Location(Client client, Voiture voiture, int NombreJours, String DateDeLocation, String DateDeRetour) {
        this.client = client;
        this.voiture = voiture;
        this.NombreJours = NombreJours;
        this.DateDeLocation = DateDeLocation;
        this.DateDeRetour = DateDeRetour;
    }

    public Location() {
    }

    public Location(int NumeroLocation, Client client, Voiture voiture, int NombreJours, String DateDeLocation, String DateDeRetour) {
        this.NumeroLocation = NumeroLocation;
        this.client = client;
        this.voiture = voiture;
        this.NombreJours = NombreJours;
        this.DateDeLocation = DateDeLocation;
        this.DateDeRetour = DateDeRetour;
    }
    private int NumeroLocation;
    private Client client;
    private Voiture voiture;
    private int NombreJours;
    private String DateDeLocation;
    private String DateDeRetour;
    
    public int getNumeroLocation()
    {
        return NumeroLocation;
    }
    public Client getClient()
    {
        return client;
    }
    public Voiture getVoiture()
    {
        return voiture;
    }
    public int getNombreJours()
    {
        return NombreJours;
    }
    public String getDateDeLocation()
    {
        return DateDeLocation;
    }
    public String getDateDeRetour()
    {
        return DateDeRetour;
    }
    public void setNumeroLocation(int NumeroLocation)
    {
        this.NumeroLocation=NumeroLocation;
    }
    public void setClient(Client client)
    {
        this.client=client;
    }
    public void setVoiture(Voiture voiture)
    {
        this.voiture=voiture;
    }
    public void setNombreJours(int NombreJours)
    {
        this.NombreJours=NombreJours;
    }
    public void setDateDeLocation(String DateDeLocation)
    {
        this.DateDeLocation=DateDeLocation;
    }
    public void setDateDeRetour(String DateDeRetour)
    {
        this.DateDeRetour=DateDeRetour;
    }
}
