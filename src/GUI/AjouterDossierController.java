/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.PatientService;
import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterDossierController implements Initializable {

    @FXML
    private TextField des;
    
    private ChoiceBox<String> combopa;
    private DatePicker tesDate;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    
    @FXML
    private Button Bajout;
    @FXML
    private ChoiceBox<?> combodoc;
    @FXML
    private Button Blist1;
    @FXML
    private Button tfile;
    @FXML
    private ChoiceBox<?> comboMed;
    private Button bexporter;
     private Connection cn;
     private Statement st;
     private PreparedStatement pst;
     private ResultSet rs;
    @FXML
    private Button bajout;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   PatientService patient = new PatientService();
        ObservableList <String> listpa = FXCollections.observableArrayList(patient.afficherNom()) ;
        combopa.setItems(listpa);
        
        
    }    
    private void ajouterDossier(ActionEvent event) throws IOException, SQLDataException {
        DossierService sp = new DossierService();
        if (des.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            PatientService su = new PatientService();
            int s =su.getByNom(combopa.getSelectionModel().getSelectedItem().toString());
            //int user =tuser.getSelectionModel().getSelectedIndex();
             sp.addDossier(new Dossier(des.getText(),tesDate.getValue().toString(),s));
            JOptionPane.showMessageDialog(null, "dossier ajoutée !");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListDossierController.fxml"));
        
            Parent root = loader.load();
            des.getScene().setRoot(root);

            
        }
       
    }
    
    private void exporterDossier()  {
       // oblist = FXCollections.observableArrayList(sp.); 
       bexporter.setFont(Font.font("Sensif",15));
        bexporter.setOnAction(e->{
           try {
               String query = "select * from dossier";
               pst = cn.prepareStatement(query);
               rs = pst.executeQuery();
               
           } catch (SQLException ex) {
               Logger.getLogger(AjouterDossierController.class.getName()).log(Level.SEVERE, null, ex);
           }
            
        });
    
    }
    
    
     
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Home.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }
    
    @FXML
    public void ActionListD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListDossierController.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
        
    }
   
    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjouterDossierController.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
        
    }

   
    
       
    
}
