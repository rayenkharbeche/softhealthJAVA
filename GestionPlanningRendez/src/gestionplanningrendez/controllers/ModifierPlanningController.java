/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.controllers;

import gestionplanningrendez.entity.Planning;
import gestionplanningrendez.service.ServicePlanning;
import gestionplanningrendez.service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ModifierPlanningController implements Initializable {

    @FXML
    private AnchorPane tdateF;
    @FXML
    private TextField tnomP;
    @FXML
    private TextField tdescriptionP;
    @FXML
    private DatePicker tdateD;
    @FXML
    private DatePicker dateF;
    @FXML
    private ChoiceBox<String> tuser;
    @FXML
    private Button bmodif;
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
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         ServiceUser user = new ServiceUser();
        ObservableList <String> listUser = FXCollections.observableArrayList(user.afficherNom()) ;
        tuser.setItems(listUser);
        
    }    

    @FXML
    private void modifierPlanning(ActionEvent event) throws IOException {
         ServicePlanning sp = new ServicePlanning();
        if (tnomP.getText().isEmpty()||tdescriptionP.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            //int user =tuser.getSelectionModel().getSelectedIndex();
             sp.modifier(new Planning(tnomP.getText(),tdescriptionP.getText(),Date.valueOf(tdateD.getValue()),Date.valueOf(dateF.getValue()),tuser.getSelectionModel().getSelectedIndex()));
            JOptionPane.showMessageDialog(null, "Planning modifier !");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListPlanning.fxml"));
        
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
    public void ActionListP(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListPlanning.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
    }

    @FXML
    private void ActionCalendar(ActionEvent event) {
    }

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjoutPlanning.fxml"));
        
        Parent root = loader.load();
        tnomP.getScene().setRoot(root);
    }

    

    public void setTnomP(String tnomP) {
        this.tnomP.setText(tnomP);
    }

    public void setTdescriptionP(String tdescriptionP) {
        this.tdescriptionP.setText(tdescriptionP);
    }

    public void setDateF(Date dateF) {
        this.dateF.setValue(dateF.toLocalDate());
    }

    public void setTuser(String user) {
        this.tuser.setValue(user);
    }

    public void setTdateD(Date tdateD) {
        this.tdateD.setValue(tdateD.toLocalDate());
    }
    
    
    
}
