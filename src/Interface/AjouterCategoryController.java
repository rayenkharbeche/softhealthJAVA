/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entites.Category;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceCategory;

/**
 * FXML Controller class
 *
 * @author Mejri Wajih
 */
public class AjouterCategoryController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField color;
    @FXML
    private Button btnajouter;

    ServiceCategory sc = new ServiceCategory();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        Category cat = new Category( nom.getText(),desc.getText(), color.getText());
        sc.ajouter(cat);
        
    }
    
}
