/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import tn.esprit.entities.Patient;
import tn.esprit.entities.Planning;
import tn.esprit.entities.RendezVous;
import tn.esprit.entities.User;
 import tn.esprit.services.ServicePatient;
import tn.esprit.services.ServicePlanning;
import tn.esprit.services.ServiceRDV;
import tn.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */

public class AjoutRDVController implements Initializable {

    @FXML
    private TextField tnomP;
    @FXML
    private TextField tdescriptionP;
    @FXML
    private Button bajout;
    @FXML
    private ChoiceBox<String> tuser;
    @FXML
    private DatePicker tdatedebut;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    @FXML
    private Button Bcalendar;
    @FXML
    private Button Bajout;
    @FXML
    private ChoiceBox<String> tpatient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceUser user = new ServiceUser();
        ObservableList <String> listUser = FXCollections.observableArrayList(user.afficherNom()) ;
        tuser.setItems(listUser);
        
        ServicePatient patient = new ServicePatient();
        ObservableList <String> listPatient = FXCollections.observableArrayList(patient.afficherNom()) ;
        tpatient.setItems(listPatient);
    }    

    @FXML
    private void ajouterRDV(ActionEvent event) throws IOException, SQLException {
        
        ServiceRDV sp = new ServiceRDV();
        if (tnomP.getText().isEmpty()||tdescriptionP.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            ServiceUser su = new ServiceUser();
            ServicePatient spa = new ServicePatient();
            
            
            int s=su.getByName(tuser.getSelectionModel().getSelectedItem().toString());
           
            
            int p=spa.getByName(tpatient.getSelectionModel().getSelectedItem().toString());
            int pl=sp.findByUser(s);
            //int user =tuser.getSelectionModel().getSelectedIndex();
             sp.ajouter(new RendezVous(tnomP.getText(),tdescriptionP.getText(),Date.valueOf(tdatedebut.getValue()),s,pl,p));
    
             
             System.out.println(tuser.getSelectionModel().getSelectedItem().toString());
             System.out.println(tpatient.getSelectionModel().getSelectedItem().toString());
             System.out.println(sp.findByUser(s));
             System.out.println("user"+s);
             System.out.println("patient"+p);
             
             
            JOptionPane.showMessageDialog(null, "Rendez-vous ajoutée !");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListRendez.fxml"));
            Parent root = loader.load();
            tnomP.getScene().setRoot(root);

            
        }
    }

    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
    }

    @FXML
    private void ActionListP(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListRendez.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
        
    }

    @FXML
    private void ActionCalendar(ActionEvent event) {
    }

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjoutRDV.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
        
    }
    
}
