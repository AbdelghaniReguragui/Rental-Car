package Data;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientDAO 
{
    public static boolean addClient(Client client) throws SQLException, IOException
    {
        Connection conn = null;
        Statement stmt = null;
        String query = "";
        try {
            if(!client.getCIN().equals("") && !client.getNom().equals("") && !client.getPrenom().equals(""))
            {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
                conn.setAutoCommit(false);

                stmt = conn.createStatement();
                query = "INSERT INTO Client (Nom,Prenom,Sexe,CIN) "+ "VALUES ('"+client.getNom()+"', '"+client.getPrenom()+"', '"+client.getSexe()+"' , '"+client.getCIN()+"');";
                stmt.executeUpdate(query);
                stmt.close();
                conn.commit();
                conn.close();
                return true ;
            }
            else 
                return false ;
        } catch (ClassNotFoundException | SQLException e) {
            return false ;
        }
    }
    public static Client selectClient(int idClient)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Client WHERE IdClient = '"+idClient+"';";
            ResultSet rs = stmt.executeQuery(query);
            String nom = rs.getString("Nom");
            String prenom = rs.getString("Prenom");
            String sexe = rs.getString("Sexe");
            String cin = rs.getString("CIN");
            Client client = new Client();
            client.setIdClient(idClient);
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setSexe(sexe);
            client.setCIN(cin);
            
            rs.close();
            stmt.close();
            conn.close();
            return client;
        } catch (Exception e) {
            return null;
        }
    }
    public static void genererTableClients(ObservableList<Client> listeClients)
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Client";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String CIN = rs.getString("CIN");
                String sexe = rs.getString("Sexe");
                int idClient = rs.getInt("IdClient");
                Client client = new Client();
                client.setIdClient(idClient);
                client.setNom(nom);
                client.setPrenom(prenom);
                client.setCIN(CIN);
                client.setSexe(sexe);
                listeClients.add(client);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
        }
    }
    public static boolean UpdateClient(Client client) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "UPDATE Client set Nom = '"+client.getNom()+"' , Prenom = '"+client.getPrenom()+"', CIN = '"+client.getCIN()+"' , Sexe = '"+client.getSexe()+"' where IdClient ='"+client.getIdClient()+"';";
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
    public static boolean DeleteClient (int id) throws ClassNotFoundException{
        Connection conn = null;
        Statement stm = null;
        
        try {
           
           Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stm = conn.createStatement();
            conn.setAutoCommit(false);
            
            stm.executeUpdate("DELETE from client where IdClient ='"+id+"';");
            
            stm.close();
            conn.commit();
            conn.close();
            return true ;

        }catch (SQLException e) {
            return false ;
    }
}
}
