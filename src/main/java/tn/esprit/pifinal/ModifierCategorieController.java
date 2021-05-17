/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tn.esprit.services.CategorieServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ModifierCategorieController implements Initializable {

    @FXML
    private AnchorPane content_add_category;
    @FXML
    private JFXTextField tf_category;
    @FXML
    private JFXButton btn_update_category;
    
    private static CategorieServices categorieServices = new CategorieServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tf_category.setText(ListCategoriesController.categorie_a_modifier.getType_categorie());
        
    }    

    @FXML
    private void modifier_category(ActionEvent event) throws IOException, SQLException {
        ListCategoriesController.categorie_a_modifier.setType_categorie(tf_category.getText());
        categorieServices.modifierCategorie(ListCategoriesController.categorie_a_modifier);
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListCategories.fxml"));
				content_add_category.getChildren().clear();
				content_add_category.getChildren().add(newLoadedPane);
    }
    
}
