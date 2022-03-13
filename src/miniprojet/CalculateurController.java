package miniprojet;

import Data.Location;
import Data.LocationDAO;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CalculateurController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button afficherLocationButton;
    @FXML
    private Button CalculerButton;
    @FXML
    private TextField idLocationField;

    @FXML
    void backButtonAction(ActionEvent event) throws IOException 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherLocationButtonAction(ActionEvent event) throws IOException {
        Location location = new Location();
        location = LocationDAO.selectLocation(Integer.parseInt(idLocationField.getText()));
        if(location != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxLocation.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            DialogBoxLocationController d = fxmlLoader.getController();
            System.out.println(location);
            d.setLocation(location);
            
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxErreur.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }

    @FXML
    private void CalculerButtonAction(ActionEvent event) throws ParseException, IOException {
        if(!idLocationField.getText().equals("")&&LocationDAO.selectLocation(Integer.parseInt(idLocationField.getText()))!=null){
            int prixTotal ;
            prixTotal = LocationDAO.Calculateur(Integer.parseInt(idLocationField.getText()));
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxPrix.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            DialogBoxPrixController d = fxmlLoader.getController();
            System.out.println(prixTotal);
            d.montrerPrix( prixTotal);
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxErreur.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }
}
