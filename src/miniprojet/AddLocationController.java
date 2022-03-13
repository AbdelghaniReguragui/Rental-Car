package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.Location;
import Data.LocationDAO;
import Data.Voiture;
import Data.VoitureDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
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


public class AddLocationController implements Initializable {

    @FXML
    private TextField idClientField;
    @FXML
    private TextField idVoitureField;
    @FXML
    private Button backButton;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button afficherVoitureButton;
    @FXML
    private Button afficherClientButton;
    @FXML
    private DatePicker dateDeLocationPicker;
    @FXML
    private DatePicker dateDeRetourPicker;
    @FXML
    private TextField nombreJoursField;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Locations.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }

    @FXML
    private void ajouterButtonAction(ActionEvent event) throws IOException, SQLException, ClassNotFoundException 
    {
        DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        if(!idVoitureField.getText().equals("")&&!idClientField.getText().equals("")&&!nombreJoursField.getText().equals("")&&
                !dateDeLocationPicker.getValue().format(Formatter).equals("")){
            Voiture voiture = new Voiture();
            voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureField.getText()));
            Client client = new Client();
            client = ClientDAO.selectClient(Integer.parseInt(idClientField.getText()));
        
        
            if(client !=null && voiture!=null)
            {
                    Location location= new Location();
                    location.setClient(client);
                    location.setVoiture(voiture);
                    location.setDateDeLocation(dateDeLocationPicker.getValue().format(Formatter));
                    location.setDateDeRetour(dateDeRetourPicker.getValue().format(Formatter));
                    location.setNombreJours(Integer.parseInt(nombreJoursField.getText()));
                    String dateLocation = dateDeLocationPicker.getValue().format(Formatter);
                    String dateRetour = dateDeRetourPicker.getValue().format(Formatter);
                    LocalDate DateLocation = LocalDate.parse(dateLocation,Formatter);
                    LocalDate DateRetour = LocalDate.parse(dateRetour,Formatter);
                    Period period = Period.between(DateLocation, DateRetour);
                    if(period.getDays()>0){
                        if(VoitureDAO.Disponible(Integer.parseInt(idVoitureField.getText()),location)){
                            if(LocationDAO.addLocation(location)){
                                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("Location ajoutée");
                                alert.showAndWait();
                            }
                            else{
                                Alert alert=new Alert(Alert.AlertType.INFORMATION);
                                alert.setHeaderText(null);
                                alert.setContentText("Location non ajoutée");
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
                else{
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setContentText("Dates incohérentes");
                    alert.showAndWait();
                }
            }
            else
            {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxErreur.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root1));  
                stage.show();
            }
        }
        else{
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Entrez des données valides");
            alert.showAndWait();
        }
    }

    @FXML
    private void afficherVoitureButtonAction(ActionEvent event) throws IOException 
    {
        Voiture voiture = new Voiture();
        voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureField.getText()));
        if(voiture != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxVoiture.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            DialogBoxVoitureController d = fxmlLoader.getController();
            d.setVoiture(voiture);
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
    private void afficherClientButtonAction(ActionEvent event) throws IOException 
    {
        Client client = new Client();
        client = ClientDAO.selectClient(Integer.parseInt(idClientField.getText()));
        if(client != null)
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxClient.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
            DialogBoxClientController d = fxmlLoader.getController();
            d.setClient(client);
            
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

    
}
