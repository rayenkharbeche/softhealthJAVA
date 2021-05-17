/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import tn.esprit.entities.Planning;
import tn.esprit.entities.RendezVous;
import tn.esprit.services.ServicePatient;
import tn.esprit.services.ServicePlanning;
import tn.esprit.services.ServiceRDV;
import tn.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
public class ModifierRDVController implements Initializable {

    @FXML
    private AnchorPane tdateF;
    @FXML
    private TextField tnomP;
    @FXML
    private TextField tdescriptionP;
    @FXML
    private DatePicker tdateD;
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
    @FXML
    private ChoiceBox<String> tpatient;
    ServiceUser suser = new ServiceUser();
    ServicePatient spatient = new ServicePatient();
    ServiceRDV srdv = new ServiceRDV();
    ServicePlanning splanning = new ServicePlanning();
    RendezVous rdvs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ObservableList <String> listUser = FXCollections.observableArrayList(suser.afficherNom()) ;
        tuser.setItems(listUser);
        
        
        ObservableList <String> listPatient = FXCollections.observableArrayList(spatient.afficherNom()) ;
        tpatient.setItems(listPatient);
    }    

    @FXML
    private void modifierRDV(ActionEvent event) throws IOException {
        
         if (tnomP.getText().isEmpty()||tdescriptionP.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            
            int s=suser.getByName(tuser.getSelectionModel().getSelectedItem().toString());
            
            int p=spatient.getByName(tpatient.getSelectionModel().getSelectedItem().toString());
            int pl=srdv.findByUser(s);
             srdv.modifier(new RendezVous(srdv.getID(gettRDV()),tnomP.getText(),tdescriptionP.getText(),Date.valueOf(tdateD.getValue()),s,pl,p));
            JOptionPane.showMessageDialog(null, "RDV modifier !");
            
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

   

    public void setTnomP(String tnomP) {
        this.tnomP.setText(tnomP);
    }

    public void setTdescriptionP(String tdescriptionP) {
        this.tdescriptionP.setText(tdescriptionP);
    }

    public void setTdateD(Date tdateD) {
        this.tdateD.setValue(tdateD.toLocalDate());
    }

    public void setTuser(String user) {
        this.tuser.setValue(user);
    }

    public void setTpatient(String patient) {
        this.tpatient.setValue(patient);
    }
    
    public void setRDV(String nom,String description,Date date){
        rdvs=new RendezVous(nom, description, date);
    }
    public RendezVous gettRDV(){
        return rdvs;
    }
    
}
