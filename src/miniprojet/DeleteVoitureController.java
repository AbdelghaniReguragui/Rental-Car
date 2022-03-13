package miniprojet;

import Data.Voiture;
import Data.VoitureDAO;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class DeleteVoitureController implements Initializable {

    @FXML
    private Button backButton;
    @FXML
    private Button afficherVoitureButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private TextField idVoitureField;

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
        
    }    

    @FXML
    private void afficherVoitureButtonAction(ActionEvent event) throws IOException {
        Voiture voiture = new Voiture();
        voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureField.getText()));
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
    private void supprimerButtonAction(ActionEvent event) throws IOException 
    {
        if(!idVoitureField.getText().equals("")){
            Voiture voiture = new Voiture();
            voiture = VoitureDAO.selectVoiture(Integer.parseInt(idVoitureField.getText()));
            if (voiture!=null)
            {
                if(VoitureDAO.DeleteVoiture(Integer.parseInt(idVoitureField.getText()))){
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Voiture supprimée");
                    alert.showAndWait();
                }
                else {
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Voiture non supprimée");
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
    
}
