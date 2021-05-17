/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import tn.esprit.entities.Planning;
import tn.esprit.services.ServicePlanning;
import tn.esprit.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListPlanningController implements Initializable {

    @FXML
    private TableView<Planning> table;
    
    @FXML
    private TableColumn<Planning,String> cnomP;
    @FXML
    private TableColumn<Planning,String> cdescriptionP;
    @FXML
    private TableColumn<Planning,String> cdateD;
    @FXML
    private TableColumn<Planning,String> cdateF;
    @FXML
    private TableColumn<Planning,String> cuser;
    @FXML
    private TableColumn<Planning,Planning> cdelate;
    @FXML
    private TableColumn<Planning,Planning> cupdate;
    @FXML
    private TextField col_search;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    @FXML
    private Button Bcalendar;
    @FXML
    private Button Bajout;
    @FXML
    private Button Btree;
     ServicePlanning sp =new ServicePlanning();
     ServiceUser su = new ServiceUser();
    final ObservableList<Planning>  data = FXCollections.observableArrayList(sp.afficher());
   
   
    ObservableList<Planning> oblist ;  

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // sp = new ServicePlanning();
        oblist= FXCollections.observableArrayList(sp.afficher());
       // cidP.setCellValueFactory(new PropertyValueFactory<> ("id"));
        cnomP.setCellValueFactory(new PropertyValueFactory<> ("nomP"));
        cdescriptionP.setCellValueFactory(new PropertyValueFactory<> ("descriptionP"));
        cdateD.setCellValueFactory(new PropertyValueFactory<> ("dateDebut"));
        cdateF.setCellValueFactory(new PropertyValueFactory<> ("dateFin"));
        cuser.setCellValueFactory(new PropertyValueFactory<> ("id_user"));
       // caction.setCellFactory((Callback<TableColumn<Planning, String>, TableCell<Planning, String>>)delate);
        
       
       // Initialiser les Buttons
       
       cdelate.setCellFactory((TableColumn<Planning, Planning> param) -> {
            return new TableCell<Planning, Planning>() {
                private final Button deleteButton = new Button("   Delete   ");
                @Override
                protected void updateItem(Planning planning, boolean empty) {
                    super.updateItem(planning, empty);
                    
                   // if (patient == null) {
                   //     setGraphic(null);
                    //    return;
                   // }
                   
                    deleteButton.setOnAction(event -> {
                    Planning getPlanning = getTableView().getItems().get(getIndex());
                //    System.out.println(getPlanning.getNomP() + "   " + getPlanning.getDescriptionP());
                  
                    sp.supprimer(getPlanning.getId());
                    JOptionPane.showMessageDialog(null, "Planning Supprimer !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListPlanning.fxml"));
        
                     Parent root;
                        try {
                            root = loader.load();
                            col_search.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ListPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    });

                    setGraphic(deleteButton);//<<<---------------add button 1
                    }
                    };
                    });
        cupdate.setCellFactory((TableColumn<Planning, Planning> param) -> {
            return new TableCell<Planning, Planning>() {
                private final Button editButton = new Button("    Edit    ");
             
                @Override
                protected void updateItem(Planning planning, boolean empty) {
                    super.updateItem(planning, empty);
                 
                    editButton.setOnAction(event -> {
                    Planning getPlanning = getTableView().getItems().get(getIndex());
                //    System.out.println(getPlanning.getNomP() + "   " + getPlanning.getDescriptionP());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierPlanning.fxml"));
        
                     Parent root;
                        try {
                            root = loader.load();
                            col_search.getScene().setRoot(root);
                            ModifierPlanningController dpc = loader.getController();
                            
                            dpc.setTnomP(getPlanning.getNomP());
                            dpc.setTdescriptionP(getPlanning.getDescriptionP());
                            dpc.setTdateD((Date) getPlanning.getDateDebut());
                            dpc.setDateF((Date) getPlanning.getDateFin());
                           dpc.setTuser(su.getByID(getPlanning.getId_user()));
                        } catch (IOException ex) {
                            Logger.getLogger(ListPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    setGraphic(editButton);//<<------------------add button 2
                    }
                    };
                    });
          col_search.setFont(Font.font("Sensif",15));
     FilteredList<Planning> filteredData = new FilteredList<>(data, e -> true);
        col_search.setOnKeyReleased(e ->{
            col_search.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Planning>) planning->{
                    if (newValue == null || newValue.isEmpty()){
                        return true; 
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (planning.getNomP().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    //else if()
                   return false;
                });
            });
        });
        SortedList<Planning> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);          
        
      
        table.setItems(oblist);
    }    

    @FXML
    private void SearchPlanning()  {
       // oblist = FXCollections.observableArrayList(sp.); 
       col_search.setFont(Font.font("Sensif",15));
     FilteredList<Planning> filteredData = new FilteredList<>(data, e -> true);
        col_search.setOnKeyReleased(e ->{
            col_search.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Planning>) planning->{
                    if (newValue == null || newValue.isEmpty()){
                        return true; 
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (planning.getNomP().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    //else if()
                   return false;
                });
            });
        });
        SortedList<Planning> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);          
        
    }
    
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
    }
    
    public void ActionListP(ActionEvent event) throws IOException {
       
        
    }
    @FXML
    private void ActionCalendar(ActionEvent event) {
        
        
    }
    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjoutPlanning.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
        
    }
     @FXML
    private void ActionTree(ActionEvent event) {
        
        Btree.setFont(Font.font("Sensif",15));
     TreeSet<Planning> treeData = data.stream().collect(Collectors.toCollection(()->new TreeSet<Planning>((a,b)->b.getDateDebut().compareTo(a.getDateDebut()))));
     ObservableList<Planning> ob = FXCollections.observableArrayList(treeData);
        SortedList<Planning> sortedData = new SortedList<> (ob);
       sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems( sortedData);       
        
        
    }

    
}
