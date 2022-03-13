package miniprojet;

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


public class AfficherLocationController implements Initializable {

    @FXML
    private TableView<Location> locationTable;
    @FXML
    private TableColumn<Location, Integer> idLocationColumn;
    @FXML
    private TableColumn<Location, Integer> idClientColumn;
    @FXML
    private TableColumn<Location, Integer> idVoitureColumn;
    @FXML
    private TableColumn<Location, Integer> nombreJoursColumn;
    @FXML
    private TableColumn<Location, String> dateLocationColumn;
    @FXML
    private TableColumn<Location, String> dateRetourColumn;
    @FXML
    private Button backButton;

    private ObservableList <Location> listeLocations = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LocationDAO.genererTableLocations(listeLocations);
        idLocationColumn.setCellValueFactory(new PropertyValueFactory<Location,Integer>("NumeroLocation"));
        idClientColumn.setCellValueFactory(new PropertyValueFactory<Location,Integer>("client"));
        idVoitureColumn.setCellValueFactory(new PropertyValueFactory<Location,Integer>("voiture"));
        nombreJoursColumn.setCellValueFactory(new PropertyValueFactory<Location,Integer>("NombreJours"));
        dateLocationColumn.setCellValueFactory(new PropertyValueFactory<Location,String>("DateDeLocation"));
        dateRetourColumn.setCellValueFactory(new PropertyValueFactory<Location,String>("DateDeRetour"));
        locationTable.setItems(listeLocations);
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
