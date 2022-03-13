
package miniprojet;

import Data.Client;
import Data.ClientDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddClientController implements Initializable {

    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private Button backButton;
    @FXML
    private Button ajouterButton;
    @FXML
    private TextField CINField;
    @FXML
    private ComboBox<String> sexeComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList <String> list = FXCollections.observableArrayList("Homme","Femme");
        sexeComboBox.setItems(list);
        sexeComboBox.getSelectionModel().select(0);
    }    

    @FXML
    private void backButtonAction(ActionEvent event) throws IOException 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Clients.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));  
        stage.show();
    }

    @FXML
    private void ajouterButtonAction(ActionEvent event) throws IOException, SQLException
    {
        Client client = new Client();
        if(!nomField.getText().equals("") && !prenomField.getText().equals("") && !CINField.getText().equals(""))
        {
        client.setNom(nomField.getText());
        client.setPrenom(prenomField.getText());
        client.setCIN(CINField.getText());
        client.setSexe(sexeComboBox.getValue());
        if(ClientDAO.addClient(client)){
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Client ajouté");
            alert.showAndWait();
        }
        else{
            Alert alert=new Alert(AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Client non ajouté");
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

}