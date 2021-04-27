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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASSOUMA
 */
public class EditCategMedicController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button Bhome;
    @FXML
    private Button listCat;
    @FXML
    private Button AddCateg;
    @FXML
    private TextField TfSearch;
    @FXML
    private ImageView rech;
    @FXML
    private TextField TfName;
    @FXML
    private ImageView retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ActionListCat(ActionEvent event) {
    }

    @FXML
    private void ActionAddCat(ActionEvent event) {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    
    }

    @FXML
    private void ActionModifCat(ActionEvent event) {
    }

    @FXML
    private void retour(MouseEvent event) {
    }
    
}
