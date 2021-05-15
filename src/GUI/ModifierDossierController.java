/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.PatientService;
import entities.Dossier;
import entities.Patient;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierDossierController implements Initializable {
 @FXML
    private TextField des;
    @FXML
    private Button bmodif;
    @FXML
    private ChoiceBox<String> combopa;
    @FXML
    private DatePicker tesDate;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    @FXML
    private Button Bcalendar;
    @FXML
    private Button Bajout;
    private TableView<Dossier> table;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    PatientService patient = new PatientService();
        ObservableList <String> listpa = FXCollections.observableArrayList(patient.afficherNom()) ;
        combopa.setItems(listpa);
        
    }

    @FXML
    private void modifierDossier(ActionEvent event) throws IOException, SQLDataException {
        Dossier docSelec = (Dossier) table.getSelectionModel().getSelectedItem();
               

                String desc= des.getText();
                String date =tesDate.getValue().toString() ;
                PatientService ser = new  PatientService();
                int p = ser.getByNom(combopa.getSelectionModel().getSelectedItem().toString());
                docSelec.setDescription(desc);
                docSelec.setId_patient(p);
                docSelec.setDateCreéation(date);
                DossierService Es= new  DossierService();
                Es.ModifierDossier(docSelec);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListDossierController.fxml"));
        
            Parent root = loader.load();
            des.getScene().setRoot(root);
            
        }
    
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Home.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }

    @FXML
    public void ActionListP(ActionEvent event) throws IOException {
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
