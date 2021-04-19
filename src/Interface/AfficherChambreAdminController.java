/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utiles.database;
import entites.Category;
import entites.Chambre;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;
import services.ServiceCategory;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AfficherChambreAdminController implements Initializable {

    @FXML
    private TableColumn<Chambre, Integer> numtab;
    @FXML
    private TableColumn<Chambre, Integer> etagetab;
    @FXML
    private TableColumn<Chambre, Integer> nbplacetab;
    @FXML
    private TableColumn<Chambre, String> sercvicetab;
    @FXML
    private TableColumn<Chambre, String> bloctab;
    @FXML
    private TableColumn<Chambre, String> categorietab;
    @FXML
    private TextField recherche;
    @FXML
    private Button supp;
    @FXML
    private TableView<Chambre> tabchambre;

            
    ObservableList<Chambre> cls = FXCollections.observableArrayList();
    ServiceChambre sc = new ServiceChambre();
    ServiceCategory scat = new ServiceCategory();    
    private Statement ste;
    private Connection con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Aff();
        RechercheAV();

    }    

   public void Aff(){
                        try {
            con = database.getInstance().getCon();
            ste = con.createStatement();
            cls.clear();

            ResultSet res = ste.executeQuery("select * from Chambre");
            while(res.next()){
            Category cat = new Category();
            cat = scat.getById(res.getInt(6));
            
                Chambre f=new Chambre(res.getInt(1),res.getInt(2),res.getInt(3),res.getString(4),res.getString(5),cat.getNom());
                cls.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            numtab.setCellValueFactory(new PropertyValueFactory<>("num"));
            etagetab.setCellValueFactory(new PropertyValueFactory<>("numetage"));
            nbplacetab.setCellValueFactory(new PropertyValueFactory<>("nbrplace"));
            sercvicetab.setCellValueFactory(new PropertyValueFactory<>("service"));
            bloctab.setCellValueFactory(new PropertyValueFactory<>("bloc"));
            categorietab.setCellValueFactory(new PropertyValueFactory<>("category"));
            
            tabchambre.setItems(cls);
            tabchambre.setEditable(true);
            etagetab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            nbplacetab.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
            categorietab.setCellFactory(TextFieldTableCell.forTableColumn());
            sercvicetab.setCellFactory(TextFieldTableCell.forTableColumn());
            bloctab.setCellFactory(TextFieldTableCell.forTableColumn());

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Chambre> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(chambre -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (chambre.getBloc().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(chambre.getNum()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Chambre> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabchambre.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabchambre.setItems(sortedData);
    }

    @FXML
    private void Change_Etage(TableColumn.CellEditEvent event) throws SQLException {
     Chambre tab_Chambreselected = tabchambre.getSelectionModel().getSelectedItem();
     tab_Chambreselected.setNumetage(Integer.valueOf(event.getNewValue().toString()));
     sc.modifier(tab_Chambreselected);
    }

    @FXML
    private void Change_Nbplace(TableColumn.CellEditEvent event) throws SQLException {
     Chambre tab_Chambreselected = tabchambre.getSelectionModel().getSelectedItem();
     tab_Chambreselected.setNbrplace(Integer.valueOf(event.getNewValue().toString()));
     sc.modifier(tab_Chambreselected);
    }

    @FXML
    private void Change_Service(TableColumn.CellEditEvent event) throws SQLException {
     Chambre tab_Chambreselected = tabchambre.getSelectionModel().getSelectedItem();
     tab_Chambreselected.setService(event.getNewValue().toString());
     sc.modifier(tab_Chambreselected);
    }

    @FXML
    private void Change_Bloc(TableColumn.CellEditEvent event) throws SQLException {
     Chambre tab_Chambreselected = tabchambre.getSelectionModel().getSelectedItem();
     tab_Chambreselected.setBloc(event.getNewValue().toString());
     sc.modifier(tab_Chambreselected);
    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
             
            tabchambre.setItems(cls);

             ObservableList<Chambre> allChambres,SingleChambres ;
             allChambres=tabchambre.getItems();
             SingleChambres=tabchambre.getSelectionModel().getSelectedItems();
             Chambre A = SingleChambres.get(0);
             sc.supprimer(A);
             SingleChambres.forEach(allChambres::remove);
             Aff();
             RechercheAV();
    }
    
}
