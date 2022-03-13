package miniprojet;

import Data.Client;
import Data.ClientDAO;
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

public class EditClientController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField cinField;
    @FXML
    private ComboBox <String> sexeComboBox;
    @FXML
    private Button afficherClientButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button backButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList <String> list = FXCollections.observableArrayList("Homme","Femme");
        sexeComboBox.setItems(list);
        sexeComboBox.getSelectionModel().select(0);
    }    

    @FXML
    private void afficherClientButtonAction(ActionEvent event) throws IOException {
        Client client = new Client();
        client = ClientDAO.selectClient(Integer.parseInt(idField.getText()));
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
    private void modifierButtonAction(ActionEvent event) throws IOException {
        if(!idField.getText().equals("")){
            Client client = new Client();
            client = ClientDAO.selectClient(Integer.parseInt(idField.getText()));
            if (client!=null)
            {
                if(!nomField.getText().equals(""))
                {
                    client.setNom(nomField.getText());
                }
                if(!prenomField.getText().equals(""))
                {
                    client.setPrenom(prenomField.getText());
                }
                if(!sexeComboBox.getValue().equals("null"))
                {
                    client.setSexe(sexeComboBox.getValue());
                }
                if(!cinField.getText().equals(""))
                {
                    client.setCIN(cinField.getText());
                }
                if(ClientDAO.UpdateClient(client)){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Client modifié");
                    alert.showAndWait();
                }
                else{
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Client non modifié");
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Clients.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    
}
