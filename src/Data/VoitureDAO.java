package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class VoitureDAO 
{
    public static boolean addVoiture(Voiture voiture)
    {
        Connection conn = null;
        Statement stmt = null;
        String query = "";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            query = "INSERT INTO Voiture (Marque,Modele,Transmission,Couleur,Prix,PrixPenalite) "
                    + "VALUES ('"+voiture.getMarque()+"', '"+voiture.getModele()+"', '"+voiture.getTransmission()+"','"+voiture.getCouleur()+"','"+voiture.getPrix()+"','"+voiture.getPrixPenalite()+"');";
            stmt.executeUpdate(query);
            stmt.close();
            conn.commit();
            conn.close();
            return true ;
        } catch (ClassNotFoundException | SQLException e) {
            return false ;
        }
    }
    public static Voiture selectVoiture(int idVoiture)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Voiture WHERE IdVoiture = '"+idVoiture+"';";
            ResultSet rs = stmt.executeQuery(query);
            String marque = rs.getString("Marque");
            String modele = rs.getString("Modele");
            String transmission = rs.getString("Transmission");
            String couleur = rs.getString("Couleur");
            int prix = rs.getInt("Prix");
            int prixPenalite = rs.getInt("PrixPenalite");
            Voiture voiture = new Voiture();
            voiture.setMarque(marque);
            voiture.setModele(modele);
            voiture.setTransmission(transmission);
            voiture.setCouleur(couleur);
            voiture.setPrix(prix);
            voiture.setPrixPenalite(prixPenalite);
            voiture.setIdVoiture(idVoiture);
            rs.close();
            stmt.close();
            conn.close();
            return voiture;
            
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean DeleteVoiture (int id){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "DELETE from voiture WHERE IdVoiture = "+id+";";
            stmt.executeUpdate(sql);
            c.commit();

            stmt.close();
            c.close();
            return true ;
        } catch (Exception e) 
        {
            return false ;
        }
    }
    public static void genererTableVoitures(ObservableList<Voiture> listeVoitures)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Voiture";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String marque = rs.getString("Marque");
                String modele = rs.getString("Modele");
                String transmission = rs.getString("Transmission");
                String couleur = rs.getString("Couleur");
                int prix = rs.getInt("Prix");
                int prixPenalite = rs.getInt("PrixPenalite");
                int idVoiture = rs.getInt("IdVoiture");
                Voiture voiture = new Voiture();
                voiture.setIdVoiture(idVoiture);
                voiture.setMarque(marque);
                voiture.setModele(modele);
                voiture.setCouleur(couleur);
                voiture.setTransmission(transmission);
                voiture.setPrix(prix);
                voiture.setPrixPenalite(prixPenalite);
                listeVoitures.add(voiture);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }
    public static boolean updateVoiture(Voiture voiture) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "UPDATE Voiture SET Marque = '"+voiture.getMarque()+"', Modele = '"+voiture.getModele()+"',Transmission = '"+voiture.getTransmission()+"', Couleur = '"+voiture.getCouleur()+"', Prix = '"+voiture.getPrix()+"', PrixPenalite = '"+voiture.getPrixPenalite()+"' where IdVoiture = '"+voiture.getIdVoiture()+"';";
            stmt.executeUpdate(sql);
            conn.commit();

            stmt.close();
            conn.close();
            return true ;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
           return false ; 
        }
    }
    public static boolean Disponible (int idVoiture,Location loocation) throws ClassNotFoundException{
        Voiture voiture = new Voiture();
        voiture = selectVoiture(idVoiture);
        if(selectVoiture(idVoiture)!=null){
            ArrayList <Location> listLocation = new ArrayList <Location>();
            listLocation = LocationDAO.getListLocation();
            for (Location location : listLocation){
                if (location.getVoiture().getIdVoiture()==idVoiture){
                    String dateRetour = location.getDateDeRetour(); //la date de retour de l'ancienne location
                    String dateLocation = location.getDateDeLocation();//la date de location de l'ancienne location
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    LocalDate DateRetour = LocalDate.parse(dateRetour,format); //la date de retour de la nouvelle location
                    LocalDate DateDebut = LocalDate.parse(dateLocation,format);//la date de debut de la nouvelle location
                    LocalDate DateRetourNVLocation = LocalDate.parse(loocation.getDateDeRetour(),format);
                    LocalDate DateDebutNVLocation = LocalDate.parse(loocation.getDateDeLocation(),format);
                    LocalDate dateNow = LocalDate.now();
                    Period period1 = Period.between(DateRetourNVLocation,DateDebut); //la period entre la date de debut de l'ancienne location et la date de retour de la nouvelle location
                    Period period2 = Period.between(DateRetour,DateDebutNVLocation);
                    if((period1.getDays()<0 && period2.getDays()>0)||(period1.getDays()>0 && period2.getDays()<0)){
                    }
                    else {
                        return false ;
                    }
                }
            }
            return true ;
        }
        else 
        return false ;
        }
}
