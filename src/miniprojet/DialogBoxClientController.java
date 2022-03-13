/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet;

import Data.Client;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class DialogBoxClientController implements Initializable {

    @FXML
    private Label sexeLabel;
    @FXML
    private Label CINLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label idLabel;
    @FXML
    private Button OKButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void setClient(Client client)
{
    this.idLabel.setText(String.valueOf(client.getIdClient()));
    this.nomLabel.setText(client.getNom());
    this.prenomLabel.setText(client.getPrenom());
    this.sexeLabel.setText(client.getSexe());
    this.CINLabel.setText(client.getCIN());
}    

    @FXML
    private void OKButtonAction(ActionEvent event) 
    {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
