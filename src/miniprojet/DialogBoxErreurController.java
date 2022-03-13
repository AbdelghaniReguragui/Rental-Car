
package miniprojet;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;


public class DialogBoxErreurController implements Initializable {

    @FXML
    private Button OKButton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void OKButtonAction(ActionEvent event) 
    {
    ((Node) (event.getSource())).getScene().getWindow().hide();
    }
    
}
