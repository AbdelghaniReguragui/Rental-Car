package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.LocationDAO;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeleteClientController implements Initializable {

    @FXML
    private TextField idClientField;
    @FXML
    private Button afficherClientButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private Button backButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherClientButtonAction(ActionEvent event) throws IOException {
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

    @FXML
    private void supprimerButtonAction(ActionEvent event) throws ClassNotFoundException, IOException {
        if(!idClientField.getText().equals("")){
            Client client = new Client();
            client = ClientDAO.selectClient(Integer.parseInt(idClientField.getText()));
            if (client!=null)
            {
                if (ClientDAO.DeleteClient(Integer.parseInt(idClientField.getText()))){
                    Alert alert=new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("l'identifiant "+client.getIdClient()+" est supprimé");
                    alert.showAndWait();
                }
                else {
                    Alert alert=new Alert(AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("l'identifiant "+client.getIdClient()+" n'est pas supprimé");
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Clients.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }
    
}
