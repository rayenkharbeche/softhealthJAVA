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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import services.ServiceCategory;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Mejri Wajih
 */
public class AfficherChambreController implements Initializable {

    @FXML
    private ListView<Label> ListChambre;
    ObservableList<Category> listcat = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> combotri;
    ObservableList<String> listtri = FXCollections.observableArrayList("Numero","Etage","Nombre de place","Service","Bloc");


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        combotri.setItems(listtri);
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
            Label lbl = new Label("Numero : "+aux.getNum()+" Numero Etage : "+aux.getNumetage()+" Nombre de place : "+aux.getNbrplace()+" Service : "+aux.getService()+" Bloc : "+aux.getBloc()+" Categorie : "+cat.getNom());
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
            Label lbl = new Label("Numero : "+aux.getNum()+" Numero Etage : "+aux.getNumetage()+" Nombre de place : "+aux.getNbrplace()+" Service : "+aux.getService()+" Bloc : "+aux.getBloc()+" Categorie : "+cat.getNom());
        ListChambre.getItems().add(lbl);
        }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherChambreController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
