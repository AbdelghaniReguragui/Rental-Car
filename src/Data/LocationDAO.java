package Data;

import static Data.VoitureDAO.selectVoiture;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class LocationDAO 
{
    public static boolean addLocation(Location location) throws SQLException
    {
        Connection conn = null;
        Statement stmt = null;
        String query = "";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            query = "INSERT INTO Location (IdClient,IdVoiture,NombreJours,DateDeLocation,DateDeRetour) "
                    + "VALUES ('"+location.getClient().getIdClient()+"', '"+location.getVoiture().getIdVoiture()+"', '"+location.getNombreJours()+"','"+location.getDateDeLocation()+"','"+location.getDateDeRetour()+"');";
            stmt.executeUpdate(query);
            stmt.close();
            conn.commit();
            conn.close();
            return true ;
        } catch (ClassNotFoundException | SQLException e) {
            return false ;
        }

    }
    
    public static Location selectLocation(int idLocation)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Location WHERE IdLocation = '"+idLocation+"';";
            
            ResultSet rs = stmt.executeQuery(query);
            int idClient = rs.getInt("IdClient");
            int idVoiture = rs.getInt("IdVoiture");
            int nombreJours = rs.getInt("NombreJours");
            String dateDeLocation = rs.getString("DateDeLocation");
            String dateDeRetour = rs.getString("DateDeRetour");
            
            Location location = new Location();
            location.setNumeroLocation(idLocation);
            Client client = new Client ();
            Voiture voiture = new Voiture();
            client.setIdClient(idClient);
            voiture.setIdVoiture(idVoiture);
            location.setClient(client);
            location.setVoiture(voiture);
            location.setNombreJours(nombreJours);
            location.setDateDeLocation(dateDeLocation);
            location.setDateDeRetour(dateDeRetour);
            rs.close();
            stmt.close();
            conn.close();
            return location;
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean DeleteLocation (int id){
        Connection c = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "DELETE from Location WHERE IdLocation = "+id+";";
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
    public static ArrayList<Location> getListLocation() throws ClassNotFoundException{
        Connection conn = null ;
        conn = null ;
        Statement stm = null ;
        
        ArrayList <Location> ListLocation = new ArrayList <>();
       
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            
            ResultSet rs = stm.executeQuery("SELECT * From Location;");
            
            while (rs.next()==true){

                String dateLocation = rs.getString("DateDeLocation");
                String dateRetour = rs.getString("DateDeRetour");
                int idLocation = rs.getInt("IdLocation");
                int idClient = rs.getInt("IdClient");
                int idVoiture = rs.getInt("IdVoiture");
                int nombreJours = rs.getInt("NombreJours");
                Location location = new Location();
                Client client = new Client();
                Voiture voiture = new Voiture();
                client.setIdClient(idClient);
                voiture.setIdVoiture(idVoiture);
                location.setClient(client);
                location.setVoiture(voiture);
                location.setNumeroLocation(idLocation);
                location.setNombreJours(nombreJours);
                location.setDateDeLocation(dateLocation);
                location.setDateDeRetour(dateRetour);

                ListLocation.add (location) ;                
            }
            stm.close();
            rs.close();
            conn.close();
                    
        }
        catch (SQLException e) {
            System.out.println("getListVoiture Error");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return ListLocation ;
    }
    public static void genererTableLocations(ObservableList<Location> listeLocations)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Location";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String dateLocation = rs.getString("DateDeLocation");
                String dateRetour = rs.getString("DateDeRetour");
                int idLocation = rs.getInt("IdLocation");
                int idClient = rs.getInt("IdClient");
                int idVoiture = rs.getInt("IdVoiture");
                int nombreJours = rs.getInt("NombreJours");
                Location location = new Location();
                Client client = new Client();
                Voiture voiture = new Voiture();
                client.setIdClient(idClient);
                voiture.setIdVoiture(idVoiture);
                location.setClient(client);
                location.setVoiture(voiture);
                location.setNumeroLocation(idLocation);
                location.setNombreJours(nombreJours);
                location.setDateDeLocation(dateLocation);
                location.setDateDeRetour(dateRetour);
                listeLocations.add(location);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            
        }
    }
    public static boolean updateLocation(Location location) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "UPDATE Location set IdClient = '"+location.getClient().getIdClient()+"' , IdVoiture = '"+location.getVoiture().getIdVoiture()+"', NombreJours = '"+location.getNombreJours()+"' , DateDeLocation = '"+location.getDateDeLocation()+"' , DateDeRetour = '"+location.getDateDeRetour()+"' where IdLocation ='"+location.getNumeroLocation()+"';";
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
    public static int Calculateur(int idLocation) throws ParseException{
        int prix=0;
        int prixPenalite=0;
        int prixTotal=0 ;
        int joursRetard = 0;
        Location location = new Location();
        location = LocationDAO.selectLocation(idLocation);
        if(location!=null){
        Voiture voiture = new Voiture ();
        voiture= selectVoiture(location.getVoiture().getIdVoiture());
        String dateLocation = location.getDateDeLocation();
        String dateRetour = location.getDateDeRetour();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        LocalDate DateLocation = LocalDate.parse(dateLocation,format);
        LocalDate DateRetour = LocalDate.parse(dateRetour,format);
        Period period = Period.between(DateLocation, DateRetour);
        if(period.getYears()<0||period.getMonths()<0||period.getDays()<0){
            return -1 ;
        }
        else {
            if(period.getDays()+period.getMonths()*30+period.getYears()*365<=location.getNombreJours()){
                prixTotal =location.getNombreJours()*voiture.getPrix();
            }
            else if(period.getDays()+period.getMonths()*30+period.getYears()*365>location.getNombreJours()){
                joursRetard = (period.getDays()+period.getMonths()*30+period.getYears()*365)-location.getNombreJours();
                prix=location.getNombreJours()*voiture.getPrix();
                prixPenalite = joursRetard*voiture.getPrixPenalite() ;
                prixTotal = prix+prixPenalite;
            }
        }
        }
        return prixTotal ;
    }
}
