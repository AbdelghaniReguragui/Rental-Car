package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.Location;
import Data.LocationDAO;
import Data.Voiture;
import Data.VoitureDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteLocationController implements Initializable {

    @FXML
    private TextField idLocationField;
    @FXML
    private Button afficherLocationButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void afficherLocationButtonAction(ActionEvent event) throws IOException 
    {
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
    private void supprimerButtonAction(ActionEvent event) throws IOException {
        if(!idLocationField.getText().equals("")){
            Location location = new Location();
            location = LocationDAO.selectLocation(Integer.parseInt(idLocationField.getText()));
            if (location!=null)
            {
                if(LocationDAO.DeleteLocation(Integer.parseInt(idLocationField.getText()))){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Location supprimée");
                    alert.showAndWait();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Location non supprimée");
                    alert.showAndWait();
                }
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
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxErreur.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
    }

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Locations.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    
}
