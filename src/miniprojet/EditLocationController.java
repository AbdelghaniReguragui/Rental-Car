package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.Location;
import Data.LocationDAO;
import Data.Voiture;
import Data.VoitureDAO;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditLocationController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField idClientField;
    @FXML
    private TextField idVoitureField;
    @FXML
    private TextField nombreJoursField;
    @FXML
    private DatePicker dateLocationPicker;
    @FXML
    private DatePicker dateRetourPicker;
    @FXML
    private Button afficherLocationButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button backButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    

    @FXML
    private void afficherLocationButtonAction(ActionEvent event) throws IOException {
        Location location = new Location();
        location = LocationDAO.selectLocation(Integer.parseInt(idField.getText()));
        if(location != null)
        {
            DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            dateLocationPicker.setValue(LocalDate.parse(location.getDateDeRetour(), Formatter));
            dateRetourPicker.setValue(LocalDate.parse(location.getDateDeRetour(), Formatter));
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
    private void modifierButtonAction(ActionEvent event) throws IOException, ClassNotFoundException {
        if(!idField.getText().equals("")){
            Location location = new Location();
            location = LocationDAO.selectLocation(Integer.parseInt(idField.getText()));
            if (location!=null)
            {
                if(!idClientField.getText().equals(""))
                {
                    Client client = new Client();
                    client = ClientDAO.selectClient(Integer.parseInt(idClientField.getText()));
                    if (client != null)
                    {
                        location.setClient(client);
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
                if(!idVoitureField.getText().equals(""))
                {
                    Voiture voiture = new Voiture();
                    voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureField.getText()));
                    if (voiture != null)
                    {
                        location.setVoiture(voiture);
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
                if(!nombreJoursField.getText().equals(""))
                {
                    location.setNombreJours(Integer.parseInt(nombreJoursField.getText()));
                }
                if(dateLocationPicker.getValue() != null)
                {
                    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    location.setDateDeLocation(dateLocationPicker.getValue().format(Formatter));
                }
                if(dateRetourPicker.getValue() != null)
                {
                    DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    location.setDateDeRetour(dateRetourPicker.getValue().format(Formatter));
                }
                if(VoitureDAO.Disponible(Integer.parseInt(idVoitureField.getText()),location)){
                    if(LocationDAO.updateLocation(location)){
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Location modifiée");
                        alert.showAndWait();
                    }
                    else {
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setContentText("Location non modifiée");
                        alert.showAndWait();
                    }
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Voiture indisponible en cette periode");
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
    private void backButtonAction(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Locations.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    
}
