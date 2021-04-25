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
import Service.ServicePat;
import Service.ServiceUser;
import Utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class EditOrdonnanceController implements Initializable {

    @FXML
    private AnchorPane add_form;
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
    private TextField desc;
    @FXML
    private TextField nbpaq;
    @FXML
    private Button reset;
    
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    
     ObservableList<String> listMed = FXCollections.observableArrayList();
     ObservableList<String> listnomPat = FXCollections.observableArrayList();
     ObservableList<String> listCons = FXCollections.observableArrayList();
     ObservableList<String> listMedic = FXCollections.observableArrayList();
     ObservableList<String> listCateg = FXCollections.observableArrayList();
     
     
   public Ordonnance o=OrdoController.or;
   
    @FXML
    private Button Upd_btn;
    @FXML
    private ImageView retour;
    public EditOrdonnanceController() {
            cnx=Database.getInstance().getCon();

    }
 public void setO(Ordonnance o) {
        this.o = o;
    }

    public Ordonnance getO() {
        return o;
    }
     
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  try {
            
            fillcomboMed(o);
            fillcomboCateg(o);
            fillcomboCons(o);
            fillcomboPat(o);
            fillcomboMedic(o);
        nbdose.setText(""+o.getNbr_doses());
        nbfois.setText(""+o.getNbr_fois());
        nbjrs.setText(""+o.getNbr_jrs());
        nbpaq.setText(""+o.getNbr_paquets());
        desc.setText(o.getDescription());
                   
        } catch (SQLException ex) {
            Logger.getLogger(EditOrdonnanceController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }        

    @FXML
    private void Edit_Ordo(MouseEvent event) {
        
         cnx = Database.getInstance().getCon();
         ServiceCateg ser = new ServiceCateg();
         ServiceUser serU = new ServiceUser();
         ServiceMedic serM = new ServiceMedic();
         ServiceCons serC = new ServiceCons();
         ServicePat serP = new ServicePat();


        Catégorie list = ser.getByNom(categ.getValue());
        User listU = serU.getByUserName(NomMed.getValue());
        Médicament listM = serM.getByMedic(Medic.getValue());
        Consultation listC = serC.getByDate(DateCons.getValue());
        Patient listP = serP.getByNomP(NomPat.getValue());

        System.out.println("hh"+list);
        
        String query ="UPDATE ordonnance SET medicaments_id = '"+ listM.getId()+"',categorie_id='"+list.getId()+
                "',consultation_id='"+listC.getId()+"',patient_id='"+listP.getId()+"',users_id='"+listU.getId()+"',description='"+desc.getText()+
                "' ,nbr_jrs="+nbjrs.getText()+",nbr_doses="+nbdose.getText()+",nbr_fois="+nbfois.getText()+",nbr_paquets="+nbpaq.getText()+ "  WHERE id = " + o.getId()+"";

     try {
         
                     PreparedStatement pst = cnx.prepareStatement(query);
                      pst.executeUpdate();
                      JOptionPane.showConfirmDialog(null,"Ordonnance éditée avec succée","" , JOptionPane.PLAIN_MESSAGE);


     }catch (SQLException ex) {
         JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);
        }
      Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Ordo.fxml"));
            add_form.getChildren().setAll(root);

        } catch (IOException ex) {
                     JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);

        }

      
   
        
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
    
    
      public void fillcomboMed(Ordonnance o) throws SQLException{
                
        ServiceUser us = new ServiceUser();
        List<User> listU = us.afficher();
        for (User aux : listU)
        {
          listMed.add(aux.getUsername());
        }
        
        NomMed.setValue(o.getUsers());
        NomMed.setItems(listMed);
        
      }
     
      public void fillcomboPat(Ordonnance o) throws SQLException{
                
        ServicePat pt = new ServicePat();
        List<Patient> listP = pt.afficher();
        for (Patient aux : listP)
        {
          listnomPat.add(aux.getNom());
        }
        NomPat.setValue(o.getPatient());
        NomPat.setItems(listnomPat);
    }
    
        public void fillcomboCons(Ordonnance o) throws SQLException{
                
        ServiceCons co = new ServiceCons();
        List<Consultation> listC = co.afficher();
        for (Consultation aux : listC)
        {
          listCons.add(aux.getDate_c().toString());
        }
        DateCons.setValue(o.getConsultation());
        DateCons.setItems(listCons);
    }  
        
        
        public void fillcomboMedic(Ordonnance o) throws SQLException{
                
        ServiceMedic sm = new ServiceMedic();
        List<Médicament> listM = sm.afficher();
        for (Médicament aux : listM)
        {
          listMedic.add(aux.getName());
        }
        Medic.setValue(o.getMedicaments());
        Medic.setItems(listMedic);
    }
     
     public void fillcomboCateg(Ordonnance o) throws SQLException{
                
        ServiceCateg ser = new ServiceCateg();
        List<Catégorie> listCA = ser.afficher();
        for (Catégorie aux : listCA)
        {
          listCateg.add(aux.getNom());
        }
        categ.setValue(o.getCategorie());
        categ.setItems(listCateg);
    }

    @FXML
    private void retour(MouseEvent event) {
        
          Parent root;

          try {
            root = FXMLLoader.load(getClass().getResource("Ordo.fxml"));
            add_form.getChildren().setAll(root);

        } catch (IOException ex) {
                     JOptionPane.showConfirmDialog(null,ex,"" , JOptionPane.ERROR_MESSAGE);

        }

    }
    
   
    
}
