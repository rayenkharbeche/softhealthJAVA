/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.FichierService;
import entities.Dossier;
import entities.Fichier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
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
public class ListFichierController implements Initializable {
public static Fichier or;
   
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
    private TableView<Fichier> table;

    @FXML
    private TableColumn<Fichier, String> cdescriptionR;

    @FXML
    private TableColumn<Fichier, Integer> cdoc;

    @FXML
    private TableColumn<Fichier, String> cFile;

    @FXML
    private TableColumn<Fichier, Integer> cMedecin;

    @FXML
    private TableColumn<Fichier, Fichier> cdelate;

    @FXML
    private TableColumn<Fichier, Fichier> cupdate;
    @FXML
    private AnchorPane anchorpane;

    /**
     * Initializes the controller class.
     */
    private ObservableList<Fichier> docData = FXCollections.observableArrayList();

    FichierService compservice =  new FichierService ();
    final ObservableList<Fichier> data;
    ObservableList<Fichier> oblist ;
     public ListFichierController() throws SQLDataException {
        this.data = FXCollections.observableArrayList(compservice.getAllFichier());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cdescriptionR.setCellValueFactory(new PropertyValueFactory<> ("description"));
 cFile.setCellValueFactory(new PropertyValueFactory<> ("image"));
 cdoc.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Fichier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Fichier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_dossier());}});
 cMedecin.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Fichier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Fichier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_médecin());}}); }
    @FXML
    private void EditDoc(ActionEvent event) throws SQLException {
        Fichier f = table.getSelectionModel().getSelectedItem();
        System.out.println("hhh" + f);
        or = f;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ModifierFichier.fxml"));
            col_search.getScene().setRoot(root);

        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);

        }
    }
   @FXML
    private void ButtonSupprimer() throws SQLException, IOException {

            table.setItems(docData);

             ObservableList<Fichier> allFichiers,SingleFichiers ;
             allFichiers=table.getItems();
             SingleFichiers=table.getSelectionModel().getSelectedItems();
             Fichier A = SingleFichiers.get(0);
             compservice.deleteFichier(A.getId_fichier());
             SingleFichiers.forEach(allFichiers::remove);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ListFichier.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);}
            
       

    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Home.fxml"));
         Parent root = loader.load();
        col_search.getScene().setRoot(root);
    }

    @FXML
    private void ActionListR(ActionEvent event) throws IOException {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("../GUI/ListFichier.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("../GUI/AjouterFichier.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
        
    }

    @FXML
    private void ActionTree(ActionEvent event) {
        Btree.setFont(Font.font("Sensif",15));
      TreeSet<Fichier> treeData = data.stream().collect(Collectors.toCollection(()-> new TreeSet<Fichier>((a,b)->a.getDescription().compareTo(b.getDescription()))));
     ObservableList<Fichier> ob = FXCollections.observableArrayList(treeData);
        SortedList<Fichier> sortedData = new SortedList<> (ob);
       sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems( sortedData);       
    }

    @FXML
    private void SearchFichier(ActionEvent event) {
        col_search.setFont(Font.font("Sensif",15));
     FilteredList<Fichier> filteredData = new FilteredList<>(data, e -> true);
        col_search.setOnKeyReleased(e ->{
            col_search.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super Fichier>) fichier->{
                    if (newValue == null || newValue.isEmpty()){
                        return true; 
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (fichier.getDescription().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    //else if()
                   return false;
                });
            });
        });
        SortedList<Fichier> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems(sortedData);          
        
    }
    
}
