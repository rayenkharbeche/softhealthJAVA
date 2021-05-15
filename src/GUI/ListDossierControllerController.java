/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.PatientService;
import entities.Dossier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ListDossierControllerController implements Initializable {
     public static Dossier or;
@FXML
    private TextField des;
    
    private ChoiceBox<String> combopa;
    private DatePicker tesDate;
   
    @FXML
    private Button Bhome;

    @FXML
    private Button Blist;

    @FXML
    private Button Bajout;

    @FXML
    private Button Btree;

    @FXML
    private TextField col_search;

    @FXML
    private TableView<Dossier> table;

    @FXML
    private TableColumn<Dossier,String > cdescriptionP;

    @FXML
    private TableColumn<Dossier, Integer> cdateD;

    @FXML
    private TableColumn<Dossier, Integer> cpatient;

    @FXML
    private TableColumn<Dossier, Dossier> cdelate;

    @FXML
    private TableColumn<Dossier, Dossier> cupdate;
      

    /**
     * Initializes the controller class.
     */
    private ObservableList<Dossier> docData = FXCollections.observableArrayList();
    DossierService compservice =  new DossierService();
    final ObservableList<Dossier>  data;
    ObservableList<Dossier> oblist ;
    @FXML
    private AnchorPane anchorpane;

    public ListDossierControllerController() throws SQLDataException {
        this.data = FXCollections.observableArrayList(compservice.getAllDossier());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 cdescriptionP.setCellValueFactory(new PropertyValueFactory<> ("description"));
 cdateD.setCellValueFactory(new PropertyValueFactory<> ("date_creation"));
 cpatient.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Dossier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Dossier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_patient());}});
    }
    @FXML
    private void EditDoc(ActionEvent event) throws SQLException {
        Dossier o = table.getSelectionModel().getSelectedItem();
        System.out.println("hhh" + o);
        or = o;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ModifierDosssier.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);

        }

    }
   
    
 @FXML   
     private void ButtonSupprimer() throws SQLException, IOException {

            table.setItems(docData);

             ObservableList<Dossier> allDossiers,SingleDossiers ;
             allDossiers=table.getItems();
             SingleDossiers=table.getSelectionModel().getSelectedItems();
             Dossier A = SingleDossiers.get(0);
             compservice.deleteDossier(A.getId_dossier());
             SingleDossiers.forEach(allDossiers::remove);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ListDossierController.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);}
             
@FXML
    private void SearchDossier()  {
       // oblist = FXCollections.observableArrayList(sp.); 
       col_search.setFont(Font.font("Sensif",15));
     FilteredList<Dossier> filteredData = new FilteredList<>(data, e -> true);
        col_search.setOnKeyReleased(e ->{
            col_search.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Dossier>) dossier->{
                    if (newValue == null || newValue.isEmpty()){
                        return true; 
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (dossier.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    //else if()
                   return false;
                });
            });
        });
        SortedList<Dossier> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);          
        
    }    
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        
        
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
    }
    @FXML
    private void ActionAjout(ActionEvent event)  {
        
          try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AjouterDossierController.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }
     @FXML
    private void ActionTree(ActionEvent event) {
        
        Btree.setFont(Font.font("Sensif",15));
     TreeSet<Dossier> treeData = data.stream().collect(Collectors.toCollection(()-> new TreeSet<Dossier>((a,b)->a.getDescription().compareTo(b.getDescription()))));
     ObservableList<Dossier> ob = FXCollections.observableArrayList(treeData);
        SortedList<Dossier> sortedData = new SortedList<> (ob);
       sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems( sortedData);       
        
        
    }
    @FXML
    public void ActionListP(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListDossierController.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
    }

 }
