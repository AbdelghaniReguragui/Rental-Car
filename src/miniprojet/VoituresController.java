
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VoituresController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private TableView<Voiture> voitureTable;
    @FXML
    private TableColumn<Voiture, Integer> idColumn;
    @FXML
    private TableColumn<Voiture, String> marqueColumn;
    @FXML
    private TableColumn<Voiture, String> modeleColumn;
    @FXML
    private TableColumn<Voiture, String> transmissionColumn;
    @FXML
    private TableColumn<Voiture, String> couleurColumn;
    @FXML
    private TableColumn<Voiture, Integer> prixColumn;
    @FXML
    private TableColumn<Voiture, Integer> prixPenaliteColumn;
    
    private ObservableList <Voiture> listeVoiture = FXCollections.observableArrayList();

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
        VoitureDAO.genererTableVoitures(listeVoiture);
        idColumn.setCellValueFactory(new PropertyValueFactory<Voiture,Integer>("IdVoiture"));
        marqueColumn.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Marque"));
        modeleColumn.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Modele"));
        couleurColumn.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Couleur"));
        transmissionColumn.setCellValueFactory(new PropertyValueFactory<Voiture,String>("Transmission"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<Voiture,Integer>("Prix"));
        prixPenaliteColumn.setCellValueFactory(new PropertyValueFactory<Voiture,Integer>("PrixPenalite"));
        voitureTable.setItems(listeVoiture);
        
    }    
    
}
