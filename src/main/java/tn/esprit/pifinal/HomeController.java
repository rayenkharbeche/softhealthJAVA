/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class HomeController implements Initializable {

       @FXML
    private JFXDrawer menu;

    @FXML
    private AnchorPane menuBar;

    @FXML
    private AnchorPane loginRegisterProfie;

    @FXML
    private HBox profileHB;

    @FXML
    private Circle photoProfilMenu;

    @FXML
    private HBox menuHB;

    @FXML
    private VBox profileVB;

    @FXML
    private JFXButton profilMenu;

    @FXML
    private JFXButton settingsProfile;

    @FXML
    private Label nameUserMenu;

    @FXML
    private Label labelLastLoginMenu;

    @FXML
    private Label LastLoginMenu;

    @FXML
    private JFXButton logoutMenu;

    @FXML
    private JFXButton admin;

    @FXML
    private AnchorPane barreRecherche;

    @FXML
    private JFXButton eventButton1;

    @FXML
    private VBox menuVB;

    @FXML
    private JFXButton id_tous_categories;

    @FXML
    private VBox eventVB;

    @FXML
    private JFXButton eventButton;

    @FXML
    private VBox sousMenuEvent;

    @FXML
    private JFXButton id_ajouter_consultation;

    @FXML
    private VBox blogVB;

    @FXML
    private JFXButton blogButton;

    @FXML
    private VBox sousMenuBlog;

    @FXML
    private JFXButton id_planning;

    @FXML
    private JFXButton id_all_users;

    @FXML
    private VBox dealVB;

    @FXML
    private VBox sousMenuDeal;

    @FXML
    private JFXButton catButton;

    @FXML
    private JFXButton id_ajouter_categorie;

   

    @FXML
    private Pane content;
    @FXML
    private JFXButton id_all_consultations;
    @FXML
    private FontAwesomeIconView retour_first;
     @FXML
    private FontAwesomeIconView planning1;
    
   
    

    @FXML
    private AnchorPane content1;
    @FXML
    private AnchorPane content2;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    void btn_afficherTous(MouseEvent event) {
        
       

 if (event.getTarget() == id_all_users) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AllUsers.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }

 if (event.getTarget() == id_ajouter_consultation) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AddConsultation.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }

 if (event.getTarget() == id_ajouter_categorie) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/AddCategory.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
  if (event.getTarget() == id_tous_categories) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListCategories.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
  if (event.getTarget() == id_all_consultations) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListConsultations.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }
  
    }
    
   
   /* if (event.getTarget() == logout) {
			try {
				AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
				content.getChildren().clear();
				content.getChildren().add(newLoadedPane);
			} catch (IOException ex) {
				Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
			}
         }*/
   @FXML
    private void retour1(MouseEvent event) throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            content1.getChildren().clear();

            content1.getChildren().add(newLoadedPane); 
    }
    
 @FXML
    private void planning1 (MouseEvent event) throws IOException { AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListPlanning.fxml"));
            content1.getChildren().clear();

            content1.getChildren().add(newLoadedPane); 
    }
    }

 
    
 
    
     
  
    
    
   

