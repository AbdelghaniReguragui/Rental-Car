/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class DialogBoxPrixController implements Initializable {

    @FXML
    private Label prixTotalLabel;
    @FXML
    private Button okButton;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void montrerPrix (int prixTotal){
        prixTotalLabel.setText(String.valueOf(prixTotal));
    }

    @FXML
    private void okButtonAction(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
