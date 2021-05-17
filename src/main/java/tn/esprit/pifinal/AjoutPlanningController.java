/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import tn.esprit.entities.Planning;
import tn.esprit.entities.User;
import tn.esprit.services.ServicePlanning;
import tn.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import static java.util.Collections.list;
import java.util.List;
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
import javafx.scene.control.Tooltip;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class AjoutPlanningController implements Initializable {

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
    private DatePicker tdatefin;
    @FXML
    private Button bsearch;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    @FXML
    private Button Bcalendar;
    @FXML
    private Button Bajout;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         ServiceUser user = new ServiceUser();
         ObservableList <String> listUser = FXCollections.observableArrayList(user.afficherNom()) ;
       
         tuser.setItems(listUser);
         
        
    }    

    @FXML
    private void ajouterPlanning(ActionEvent event) throws IOException {
        ServicePlanning sp = new ServicePlanning();
        if (tnomP.getText().isEmpty()||tdescriptionP.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            ServiceUser su = new ServiceUser();
            int s=su.getByName(tuser.getSelectionModel().getSelectedItem().toString());
            //int user =tuser.getSelectionModel().getSelectedIndex();
             sp.ajouter(new Planning(tnomP.getText(),tdescriptionP.getText(),Date.valueOf(tdatedebut.getValue()),Date.valueOf(tdatefin.getValue()),s));
            JOptionPane.showMessageDialog(null, "Planning ajoutée !");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ListPlanning.fxml"));
        
            Parent root = loader.load();
            tnomP.getScene().setRoot(root);

            
        }
       
    }
     
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/Home1.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
    }
    
    @FXML
    public void ActionListP(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/ListPlanning.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
        
    }
    @FXML
    private void ActionCalendar(ActionEvent event) {
        
        
    }
    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/AjoutPlanning.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
        
    }
    
    
}
