/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
    private void BtnAjouter(ActionEvent event) {
         cnx = Database.getInstance().getCon();

        String query ="INSERT INTO categorie VALUES ('"+TfName.getText()+"')";
        Statement st;
        ResultSet res;
        try {
             PreparedStatement pst = cnx.prepareStatement(query);
             st = cnx.createStatement();
              pst.executeUpdate();
               JOptionPane.showConfirmDialog(null,"","Catégory added successfully" , JOptionPane.PLAIN_MESSAGE);


        } catch (SQLException ex) {
               JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);

        }
         try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/GUI/Cat.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

}
