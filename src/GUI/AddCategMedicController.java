/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import Service.ServiceCateg;
import Utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
public class AddCategMedicController implements Initializable {


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
    
    private Connection cnx;
        
    @FXML
    private Button add;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void ActionListCat(ActionEvent event) throws IOException {
          AnchorPane root = FXMLLoader.load(getClass().getResource("Cat.fxml"));
        anchorpane.getChildren().setAll(root);
    }

   
    @FXML
    private void retour(MouseEvent event) throws IOException {
         AnchorPane root = FXMLLoader.load(getClass().getResource("Cat.fxml"));
        anchorpane.getChildren().setAll(root);
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
    private void AddCategMedic(ActionEvent event) {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
        
       
    }

    @FXML
    private void BtnAjouter(ActionEvent event) throws SQLException {
        
        
        if (Validchamp(TfName)){
        
        String nom = TfName.getText();
        ServiceCateg cts = new ServiceCateg();
        
        
            Catégorie c = new Catégorie(nom);

            cts.ajouter(c);
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Catégorie");
            alert.setHeaderText(null);
            alert.setContentText("Added Successfully!");
            alert.showAndWait();
         
            
          try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/GUI/Cat.fxml"));
            anchorpane.getChildren().setAll(root);

            } catch (IOException ex) {

            }
        }else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Catégorie");
            alert.setHeaderText(null);
            alert.setContentText("veuillez saisir tout les champs!");
            alert.showAndWait();

         
        }}
    
      private boolean Validchamp(TextField T) {
        return !T.getText().isEmpty();
    }


}
