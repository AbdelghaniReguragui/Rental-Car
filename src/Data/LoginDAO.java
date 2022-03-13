package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginDAO {

    public static String selectLogin(String user) 
    {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            stmt = conn.createStatement();
            String query = "SELECT * FROM Login WHERE Username = '"+user+"';";
            ResultSet rs = stmt.executeQuery(query);
            String password = rs.getString("Password");
            rs.close();
            stmt.close();
            conn.close();
            return password;
        } catch (Exception e) {
            String password = null;
            return password;
        }
    }
    
    public void setLogin(Login login) 
    {
        Connection conn = null;
        Statement stmt = null;
        String query = "";
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:MiniProjetDB.db");
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            query = "INSERT INTO Login (Username,Password) "
                    + "VALUES ('"+login.getUsername()+"', '"+login.getPassword()+"');";
            stmt.executeUpdate(query);
            stmt.close();
            conn.commit();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }
}
