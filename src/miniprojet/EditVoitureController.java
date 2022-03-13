package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.LocationDAO;
import Data.Voiture;
import Data.VoitureDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class EditVoitureController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TextField idField;
    @FXML
    private TextField marqueField;
    @FXML
    private TextField modeleField;
    @FXML
    private TextField couleurField;
    @FXML
    private TextField prixField;
    @FXML
    private TextField prixPenaliteField;
    @FXML
    private Button afficherVoitureButton;
    @FXML
    private Button modifierButton;
    @FXML
    private ComboBox<String> transmissionComboBox;

    @FXML
    void backButtonAction(ActionEvent event) throws IOException 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Flotte.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList <String> list = FXCollections.observableArrayList("Manuelle","Automatique");
        transmissionComboBox.setItems(list);
        transmissionComboBox.getSelectionModel().select(0);
    }    

    @FXML
    private void afficherVoitureButtonAction(ActionEvent event) throws IOException {
        Voiture voiture = new Voiture();
        voiture = VoitureDAO.selectVoiture(Integer.parseInt(idField.getText()));
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
    private void modifierButtonAction(ActionEvent event) throws IOException {
        if(!idField.getText().equals("")){
            Voiture voiture = new Voiture();
            voiture = VoitureDAO.selectVoiture(Integer.parseInt(idField.getText()));
            if (voiture!=null)
            {
                if(!marqueField.getText().equals(""))
                {
                    voiture.setMarque(marqueField.getText());
                }
                if(!modeleField.getText().equals(""))
                {
                    voiture.setModele(modeleField.getText());
                }
                if(!couleurField.getText().equals(""))
                {
                    voiture.setCouleur(couleurField.getText());
                }
                if(!prixField.getText().equals(""))
                {
                    voiture.setPrix(Integer.parseInt(prixField.getText()));
                }
                if(!prixPenaliteField.getText().equals(""))
                {
                    voiture.setPrixPenalite(Integer.parseInt(prixPenaliteField.getText()));
                }
                if(!transmissionComboBox.getValue().equals("null"))
                {
                    voiture.setTransmission(transmissionComboBox.getValue());
                }
                if(VoitureDAO.updateVoiture(voiture)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Voiture modifiée");
                    alert.showAndWait();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Voiture non modifiée");
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
    
}
