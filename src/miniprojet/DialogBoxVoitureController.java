
package miniprojet;

import Data.Voiture;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DialogBoxVoitureController implements Initializable {

    @FXML
    private Label prixPenaliteLabel;
    @FXML
    private Label prixLabel;
    @FXML
    private Label couleurLabel;
    @FXML
    private Label marqueLabel;
    @FXML
    private Label transmissionLabel;
    @FXML
    private Label modeleLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button OKButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    public void setVoiture(Voiture voiture)
    {
        this.idLabel.setText(String.valueOf(voiture.getIdVoiture()));
        this.marqueLabel.setText(voiture.getMarque());
        this.modeleLabel.setText(voiture.getModele());
        this.transmissionLabel.setText(voiture.getTransmission());
        this.couleurLabel.setText(voiture.getCouleur());
        this.prixLabel.setText(String.valueOf(voiture.getPrix()));
        this.prixPenaliteLabel.setText(String.valueOf(voiture.getPrixPenalite()));
    }

    @FXML
    private void OKButtonAction(ActionEvent event) 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
