/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entites.Category;
import entites.Chambre;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import services.ServiceCategory;
import services.ServiceChambre;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AfficherChambreController implements Initializable {

    ObservableList<Chambre> cls = FXCollections.observableArrayList();

    @FXML
    private ListView<Label> ListChambre;
   
    ObservableList<Category> listcat = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> combotri;
    ObservableList<String> listtri = FXCollections.observableArrayList("Numero","Etage","Nombre de place","Service","Bloc");
    @FXML
    private Button supp;
    @FXML
    private TextField num;
    @FXML
    private TextField numetage;
    @FXML
    private TextField nbrplace;
    @FXML
    private TextField service;
    @FXML
    private TextField bloc;
    @FXML
    private ComboBox<String> box_category;
    @FXML
    private ComboBox<String> box_etat;
    @FXML
    private ComboBox<String> box_traitement;
    ObservableList<String> listtraitement = FXCollections.observableArrayList("Stérilisée","Non Stérilisée");
    ObservableList<String> listetat = FXCollections.observableArrayList("Disponible","Non Disponible");
    ServiceChambre serch = new ServiceChambre();
    ObservableList<String> listnom = FXCollections.observableArrayList();
    @FXML
    private Button btnmodifer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                try {
            // TODO
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combotri.setItems(listtri);
        box_etat.setItems(listetat);
        box_traitement.setItems(listtraitement);
        fillList();
        combotri.valueProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                 switch(combotri.getValue()){
                     case "Numero" : fillList("num");
                         break;
                     case "Etage" : fillList("numetage");
                         break;
                     case "Nombre de place" : fillList("nbrplace");
                         break;
                     case "Service" : fillList("service");
                         break;
                     case "Bloc" :fillList("bloc");
                         break;

                 }
            }
            
        });
        ListChambre.getSelectionModel().selectedItemProperty().addListener(( ov,  old_val,  new_val) -> {
           String selectedItem = ListChambre.getSelectionModel().getSelectedItem().getText();
            System.out.println(selectedItem);
            try {
                
                Chambre a = serch.getById(selectedItem);
            num.setText(String.valueOf(a.getNum()));
            numetage.setText(String.valueOf(a.getNumetage()));
            nbrplace.setText(String.valueOf(a.getNbrplace()));
            service.setText(a.getService());
            bloc.setText(a.getBloc());
            box_category.setValue(a.getCategory());
            box_etat.setValue(a.getEtat());
            box_traitement.setValue(a.getTraitement());
            } catch (SQLException ex) {
                Logger.getLogger(AfficherChambreController.class.getName()).log(Level.SEVERE, null, ex);
            }
            

          }     
             );
    }    
    
    public void fillcombo() throws SQLException{
                
        ServiceCategory ser = new ServiceCategory();
        List<Category> list = ser.afficher();
        for (Category aux : list)
        {
          listnom.add(aux.getNom());
        }
        box_category.setItems(listnom);
    }

    void fillList()
    {
        ListChambre.getItems().clear();
        
               ServiceChambre sc = new ServiceChambre();
        ServiceCategory scat = new ServiceCategory();
        
        try {
            List<Chambre> list = sc.afficher();
                    for (Chambre aux : list)
        {
            Category cat = new Category();
            cat = scat.getById(aux.getCategory_id());
            Label lbl = new Label(String.valueOf(aux.getNum()));
            //Label lbl2 = new Label (" Numero Etage : "+aux.getNumetage()+" Nombre de place : "+aux.getNbrplace()+" Service : "+aux.getService()+" Bloc : "+aux.getBloc()+" Categorie : "+cat.getNom());
        ListChambre.getItems().add(lbl);
       
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherChambreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    void fillList(String tri)
    {
                ListChambre.getItems().clear();

               ServiceChambre sc = new ServiceChambre();
        ServiceCategory scat = new ServiceCategory();
        
        try {
            List<Chambre> list = sc.getTrier(tri);
                    for (Chambre aux : list)
        {
            Category cat = new Category();
            cat = scat.getById(aux.getCategory_id());
            Label lbl = new Label("Numero : "+aux.getNum());
            
        ListChambre.getItems().add(lbl);
            //Label lbl2 = new Label (" Numero Etage : "+aux.getNumetage()+" Nombre de place : "+aux.getNbrplace()+" Service : "+aux.getService()+" Bloc : "+aux.getBloc()+" Categorie : "+cat.getNom());
        
            
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherChambreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void delete(ActionEvent event) throws SQLException {
       //.setItems(cls);

             ObservableList<Label> allChambres,SingleChambres ;
             allChambres=ListChambre.getItems();
             SingleChambres=ListChambre.getSelectionModel().getSelectedItems();
             String selectedItemm = ListChambre.getSelectionModel().getSelectedItem().getText();
             //Label A = SingleChambres.get(0);
              ServiceChambre sc = new ServiceChambre();
             
             System.out.println(selectedItemm);
             sc.supprimer_id(Integer.valueOf(selectedItemm));
              Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("supp ");
   alert.setHeaderText(null);
   alert.setContentText("Cette chambre a été supprimée avec succés !");
   alert.showAndWait();
             //SingleChambres.forEach(allChambres::remove);
             fillList();
             //RechercheAV();
        
        
    }

    @FXML
    private void modifierchambre(ActionEvent event) throws SQLException {
        if(Validchamp(num)&&Validchamp(numetage)&&Validchamp(nbrplace)&&Validchamp(service)&&Validchamp(bloc))
        {
        int numero = Integer.valueOf(num.getText());
        int numetage = Integer.valueOf(this.numetage.getText());
        int nbrplace = Integer.valueOf(this.nbrplace.getText());
        String serv = service.getText();
        String blocc = bloc.getText();
        ServiceCategory sc = new ServiceCategory();
        
        Category list = sc.getByNom(box_category.getValue());
        //on va exrtai le id
        ServiceChambre sercha = new ServiceChambre();
        Chambre t = new Chambre(numero, numetage, nbrplace, serv, blocc, list.getId(),box_etat.getValue(),box_traitement.getValue());
        sercha.modifierChambre(t);
         Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du chambre");
   alert.setHeaderText(null);
   alert.setContentText("Cette chambre a été modifiée avec succés !");
   alert.showAndWait();
        num.setText("");
        this.numetage.setText("");
        this.nbrplace.setText("");
        service.setText("");
        bloc.setText("");
        fillList();
        }
        else
        {
            
        }
    }

    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty();
    }
    
         
        }
    
        
        
        
        
        
        
    
    
    
    

               
  
       
       
        
    
    
