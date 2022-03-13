package miniprojet;

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

public class AddVoitureController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private ComboBox<String> transmissionComboBox;
    @FXML
    private TextField marqueField;
    @FXML
    private TextField modeleField;
    @FXML
    private TextField couleurField;
    @FXML
    private TextField prixField;
    @FXML
    private Button addButton;
    @FXML
    private TextField prixPenaliteField;

    
    
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
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list = FXCollections.observableArrayList("Manuelle","Automatique");
        transmissionComboBox.setItems(list);
        transmissionComboBox.getSelectionModel().select(0);
    }    

    @FXML
    private void addButtonAction(ActionEvent event) throws IOException 
    {
        if(!marqueField.getText().equals("") && !modeleField.getText().equals("")&& !transmissionComboBox.getValue().equals("")
                &&!couleurField.getText().equals("")&&!prixField.getText().equals("")&&!prixPenaliteField.getText().equals("")){
        Voiture voiture = new Voiture();
        voiture.setMarque(marqueField.getText());
        voiture.setModele(modeleField.getText());
        voiture.setTransmission(transmissionComboBox.getValue());
        voiture.setCouleur(couleurField.getText());
        voiture.setPrix(Integer.parseInt(prixField.getText()));
        voiture.setPrixPenalite(Integer.parseInt(prixPenaliteField.getText()));
        if(VoitureDAO.addVoiture(voiture)){
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Voiture ajoutée");
            alert.show();
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Voiture non ajoutée");
            alert.show();
        }
        }
        else {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBoxErreur.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
        }
    }
    
}
