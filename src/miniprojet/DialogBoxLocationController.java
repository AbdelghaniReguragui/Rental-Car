/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.Location;
import Data.Voiture;
import Data.VoitureDAO;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class DialogBoxLocationController implements Initializable {

    @FXML
    private Label idLocationLabel;
    @FXML
    private Label idClientLabel;
    @FXML
    private Label idVoitureLabel;
    @FXML
    private Label nombreJoursLabel;
    @FXML
    private Label dateLocationLabel;
    @FXML
    private Label dateRetourLabel;
    @FXML
    private Button montrerClientButton;
    @FXML
    private Button montrerVoitureButton;
    @FXML
    private Button okButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void montrerClientButtonAction(ActionEvent event) throws IOException {
        Client client = new Client();
        client = ClientDAO.selectClient(Integer.parseInt(idClientLabel.getText()));
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

    @FXML
    private void montrerVoitureButtonAction(ActionEvent event) throws IOException {
        Voiture voiture = new Voiture();
        voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureLabel.getText()));
        if (voiture!=null)
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
    private void okButtonAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
    public void setLocation(Location location)
{
    Client client;
    Voiture voiture;
    client = location.getClient();
    voiture = location.getVoiture();
    System.out.println(voiture);
    System.out.println(client);
    
    System.out.println(client.getIdClient());
    System.out.println(voiture.getIdVoiture());
    System.out.println(location.getNombreJours());
    System.out.println(location.getDateDeRetour());
    System.out.println(location.getDateDeLocation());
    System.out.println(location.getNumeroLocation());
    this.idLocationLabel.setText(String.valueOf(location.getNumeroLocation()));
    this.idClientLabel.setText(String.valueOf(location.getClient().getIdClient()));
    this.idVoitureLabel.setText(String.valueOf(location.getVoiture().getIdVoiture()));
    this.nombreJoursLabel.setText(String.valueOf(location.getNombreJours()));
    this.dateLocationLabel.setText(location.getDateDeLocation());
    this.dateRetourLabel.setText(location.getDateDeRetour());
}    
    
}
