/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASSOUMA
 */
public class AcceuilAsmaController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button ListMed;
    @FXML
    private Button ListCat;
    @FXML
    private Button listOrd;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


 
    @FXML
    private void Medicament(ActionEvent event) {
         try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("Medic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException e) {
            
     JOptionPane.showConfirmDialog(null, e, "", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void CategorieLis(ActionEvent event) { 
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("Cat.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException e1) {
              JOptionPane.showConfirmDialog(null, e1, "", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void OrdonnanceLis(ActionEvent event) {
         try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("Ordo.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
            
        JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);


        }
    }
    
}
