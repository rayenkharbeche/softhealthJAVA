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
import tn.esprit.entities.Categorie;
import tn.esprit.services.CategorieServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AddCategoryController implements Initializable {

    @FXML
    private JFXButton btn_ajout_category;
    
    
    @FXML
    private JFXTextField tf_category;
   
    @FXML
    private AnchorPane content_add_category;
     private static CategorieServices categorieServices = new CategorieServices();
    private static Categorie categorie = new Categorie();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter_category(ActionEvent event) throws SQLException, IOException {
        categorie.setType_categorie(tf_category.getText());
        categorieServices.ajouterCategorie(categorie);
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListCategories.fxml"));
				content_add_category.getChildren().clear();
				content_add_category.getChildren().add(newLoadedPane);
    }
    
}
