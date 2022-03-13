/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package miniprojet;

import Data.Login;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Data.LoginDAO;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button fermetureButton;
    
    @FXML
    private Button connexionButton;
    
    @FXML
    private TextField utilisateurField;
    
    @FXML
    private PasswordField mdpField;
    @FXML
    private Label label;
    @FXML
    private Label titreLabel;
    @FXML
    private ImageView carImage;
    @FXML
    private Label utilisateurLabel;
    @FXML
    private Label mdpLabel;
    @FXML
    private Label inscriptionLabel;
    @FXML
    private Hyperlink inscriptionLink;
    
    @FXML
    private void fermetureButtonAction(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void connexionButtonAction(ActionEvent event) throws IOException {
        if(mdpField.getText().equals(LoginDAO.selectLogin(utilisateurField.getText())))
        {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HomePage.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
        }
        else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DialogBox.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));  
            stage.show();
            utilisateurField.clear();
            mdpField.clear();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
