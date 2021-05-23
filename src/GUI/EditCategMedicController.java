/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import Utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

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
    @FXML
    private Button up;
      private Connection cnx;
         
   public Catégorie c=CatController.cat;
   
    public EditCategMedicController() {
            cnx=Database.getInstance().getCon();

    }
   
   public void setc(Catégorie c) {
        this.c = c;
    }

    public Catégorie getc() {
        return c;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         TfName.setText(c.getNom());
        // TODO
    }    

    @FXML
    private void ActionListCat(ActionEvent event) {
    }

    private void ActionAddCat(ActionEvent event) {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    
    }


    @FXML
    private void retour(MouseEvent event) {
    }

    @FXML
    private void update(ActionEvent event) {
        Connection cnx = Database.getInstance().getCon();
      
     String query ="UPDATE categorie SET nom = '"+TfName.getText()+ " ' WHERE id = " + c.getId()+"";

     try {
         
                     PreparedStatement pst = cnx.prepareStatement(query);
                      pst.executeUpdate();
                      JOptionPane.showConfirmDialog(null,"Catégorie éditée avec succée","" , JOptionPane.PLAIN_MESSAGE);


     }catch (SQLException ex) {
         JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);
        }
      Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Cat.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
                     JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);

        }

      
   
        
    }

    @FXML
    private void Home(ActionEvent event) {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AcceuilAsma.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void AjouterCat(ActionEvent event) {
          try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }
    
}
