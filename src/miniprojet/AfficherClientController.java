package miniprojet;

import Data.Client;
import Data.ClientDAO;
import Data.Location;
import Data.LocationDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AfficherClientController implements Initializable {

    @FXML
    private TableColumn<Client, Integer> idColumn;
    @FXML
    private TableColumn<Client, String> nomColumn;
    @FXML
    private TableColumn<Client, String> prenomColumn;
    @FXML
    private TableColumn<Client, String> CINColumn;
    @FXML
    private TableColumn<Client, String> sexeColumn;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Client> clientTable;

    private ObservableList <Client> listeClients = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClientDAO.genererTableClients(listeClients);
        idColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("IdClient"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("Nom"));
        prenomColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("Prenom"));
        CINColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("CIN"));
        sexeColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("Sexe"));
        clientTable.setItems(listeClients);
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
