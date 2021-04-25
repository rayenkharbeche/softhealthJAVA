/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import Entity.Consultation;
import Entity.Médicament;
import Entity.Ordonnance;
import Entity.Patient;
import Entity.User;
import Service.ServiceCateg;
import Service.ServiceCons;
import Service.ServiceMedic;
import Service.ServiceOrdo;
import Service.ServicePat;
import Service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
public class Add_OrdonnanceController implements Initializable {

    @FXML
    private AnchorPane add_form;
    @FXML
    private Button Add_btn;
    @FXML
    private Button reset;
    @FXML
    private ComboBox<String> NomMed;
    @FXML
    private ComboBox<String> NomPat;
    @FXML
    private ComboBox<String> DateCons;
    @FXML
    private ComboBox<String> Medic;
    @FXML
    private ComboBox<String> categ;
    @FXML
    private TextField nbdose;
    @FXML
    private TextField nbfois;
    @FXML
    private TextField nbjrs;
    @FXML
    private TextField nbpaq;
    @FXML
    private TextField desc;

    ObservableList<String> listMed = FXCollections.observableArrayList();
    ObservableList<String> listnomPat = FXCollections.observableArrayList();
    ObservableList<String> listCons = FXCollections.observableArrayList();
    ObservableList<String> listMedic = FXCollections.observableArrayList();
    ObservableList<String> listCateg = FXCollections.observableArrayList();
    @FXML
    private ImageView retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            fillcomboMed();
            fillcomboCateg();
            fillcomboCons();
            fillcomboPat();
            fillcomboMedic();

        } catch (SQLException ex) {
            Logger.getLogger(Add_OrdonnanceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void fillcomboMed() throws SQLException {

        ServiceUser us = new ServiceUser();
        List<User> listU = us.afficher();
        for (User aux : listU) {
            listMed.add(aux.getUsername());
        }
        NomMed.setItems(listMed);
    }

    public void fillcomboPat() throws SQLException {

        ServicePat pt = new ServicePat();
        List<Patient> listP = pt.afficher();
        for (Patient aux : listP) {
            listnomPat.add(aux.getNom());
        }
        NomPat.setItems(listnomPat);
    }

    public void fillcomboCons() throws SQLException {

        ServiceCons co = new ServiceCons();
        List<Consultation> listC = co.afficher();
        for (Consultation aux : listC) {
            listCons.add(aux.getDate_c().toString());
        }
        DateCons.setItems(listCons);
    }

    public void fillcomboMedic() throws SQLException {

        ServiceMedic sm = new ServiceMedic();
        List<Médicament> listM = sm.afficher();
        for (Médicament aux : listM) {
            listMedic.add(aux.getName());
        }
        Medic.setItems(listMedic);
    }

    public void fillcomboCateg() throws SQLException {

        ServiceCateg ser = new ServiceCateg();
        List<Catégorie> listCA = ser.afficher();
        for (Catégorie aux : listCA) {
            listCateg.add(aux.getNom());
        }
        categ.setItems(listCateg);
    }

    @FXML
    private void Reset(MouseEvent event) {

        NomMed.getSelectionModel().clearSelection();
        NomPat.getSelectionModel().clearSelection();
        DateCons.getSelectionModel().clearSelection();
        Medic.getSelectionModel().clearSelection();
        categ.getSelectionModel().clearSelection();
        nbdose.clear();
        nbfois.clear();
        nbjrs.clear();
        nbpaq.clear();
        desc.clear();

    }

    @FXML
    private void Add_Ordo(MouseEvent event) throws SQLException {

        String description = desc.getText();
        int nbr_jrs = Integer.valueOf(nbjrs.getText());
        int nbr_doses = Integer.valueOf(nbdose.getText());
        int nbr_fois = Integer.valueOf(nbfois.getText());
        int nbr_paquets = Integer.valueOf(nbpaq.getText());

        ServiceCateg ser = new ServiceCateg();
        Catégorie list = ser.getByNom(categ.getValue());

        ServiceMedic sm = new ServiceMedic();
        Médicament listm = sm.getByMedic(Medic.getValue());

        ServiceCons co = new ServiceCons();
        Consultation listc = co.getByDate(DateCons.getValue());

        ServicePat pt = new ServicePat();
        Patient listP = pt.getByNomP(NomPat.getValue());

        ServiceUser us = new ServiceUser();
        User listUsr = us.getByUserName(NomMed.getValue());

        ServiceOrdo sOrd = new ServiceOrdo();
        Ordonnance ord = new Ordonnance(listm.getId(), list.getId(), listc.getId(), listP.getId(), listUsr.getId(), description, nbr_jrs, nbr_doses, nbr_fois, nbr_paquets);
        sOrd.ajouter(ord);

        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/GUI/Ordo.fxml"));
            add_form.getChildren().setAll(root);

        } catch (IOException ex) {

        }

    }
 
    
   
    
    
    
    @FXML
    private void retour(MouseEvent event) throws IOException {

        Parent root;

        try {
            root = FXMLLoader.load(getClass().getResource("Ordo.fxml"));
            add_form.getChildren().setAll(root);

        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);

        }

    }
}
