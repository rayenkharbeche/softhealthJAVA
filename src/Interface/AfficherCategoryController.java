/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Utiles.database;
import entites.Category;
import entites.Chambre;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.IntegerStringConverter;
import services.ServiceCategory;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AfficherCategoryController implements Initializable {

    @FXML
    private TextField recherche;
    @FXML
    private Button supp;
    @FXML
    private TableView<Category> tabcategory;
    @FXML
    private TableColumn<Category, Integer> idtab;
    @FXML
    private TableColumn<Category, String> nomtab;
    @FXML
    private TableColumn<Category, String> desctab;
    @FXML
    private TableColumn<Category, String> colortab;

        
    ObservableList<Category> cls = FXCollections.observableArrayList();
    ServiceCategory scat = new ServiceCategory();    
    ServiceChambre sc = new ServiceChambre();    
    private Statement ste;
    private Connection con;
    @FXML
    private Button modi;
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField color;
    @FXML
    private Label idlabel;
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

            ResultSet res = ste.executeQuery("select * from category");
            while(res.next()){
                Category f=new Category(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));
                cls.add(f);
            }

        } catch (Exception e) {
                //Logger.getLogger(tab)
        }
               
            
            idtab.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomtab.setCellValueFactory(new PropertyValueFactory<>("nom"));
            desctab.setCellValueFactory(new PropertyValueFactory<>("description"));
            colortab.setCellValueFactory(new PropertyValueFactory<>("color"));
            
            tabcategory.setItems(cls);
            tabcategory.setEditable(true);
            nomtab.setCellFactory(TextFieldTableCell.forTableColumn());
            desctab.setCellFactory(TextFieldTableCell.forTableColumn());
            colortab.setCellFactory(TextFieldTableCell.forTableColumn());

    }
     
      public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Category> filteredData = new FilteredList<>(cls, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		recherche.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(category -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (category.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(category.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Category> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tabcategory.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tabcategory.setItems(sortedData);
    }
    @FXML
    private void ButtonSupprimer(ActionEvent event) throws SQLException {
                    tabcategory.setItems(cls);

             ObservableList<Category> allCategorys,SingleCategorys ;
             allCategorys=tabcategory.getItems();
             SingleCategorys=tabcategory.getSelectionModel().getSelectedItems();
             Category A = SingleCategorys.get(0);
             
             sc.supprimer(A.getId());
             scat.supprimer(A);
              Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("supp");
   alert.setHeaderText(null);
   alert.setContentText("Cette catégorie a été supprimeé!");
   alert.showAndWait();
             SingleCategorys.forEach(allCategorys::remove);
             Aff();
             RechercheAV();
    }

    @FXML
    private void Change_Nom(TableColumn.CellEditEvent event) throws SQLException {
     Category tab_Categoryselected = tabcategory.getSelectionModel().getSelectedItem();
     tab_Categoryselected.setNom(event.getNewValue().toString());
     scat.modifier(tab_Categoryselected);

    }

    @FXML
    private void Change_Desc(TableColumn.CellEditEvent event) throws SQLException {
     Category tab_Categoryselected = tabcategory.getSelectionModel().getSelectedItem();
     tab_Categoryselected.setDescription(event.getNewValue().toString());
     scat.modifier(tab_Categoryselected);

    }

    @FXML
    private void buttonModifier(ActionEvent event) throws SQLException {
         if(Validchamp(nom)&&Validchamp(desc)&&Validchamp(color)){
        
        Category cat = new Category(Integer.valueOf(idlabel.getText()), nom.getText(),desc.getText(), color.getText());
        scat.modifier(cat);
         Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("mod du categorie");
   alert.setHeaderText(null);
   alert.setContentText("Cette catégorie a été modifiée avec succés !");
   alert.showAndWait();
             Aff();
             RechercheAV();
         }
         
         
              else
        {
            Alert alert =new Alert (Alert.AlertType.INFORMATION);
   alert.setTitle("Ajout du catégorie");
   alert.setHeaderText(null);
   alert.setContentText("veuillez saisir tout les champs!");
   alert.showAndWait();}
             
             
             
     }
    
    
    
    

    @FXML
    private void showdetails(MouseEvent event) {
     Category tab_Categoryselected = tabcategory.getSelectionModel().getSelectedItem();
     idlabel.setText(String.valueOf(tab_Categoryselected.getId()));
     nom.setText(tab_Categoryselected.getNom());
     desc.setText(tab_Categoryselected.getDescription());
     color.setText(tab_Categoryselected.getColor());
    }
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty();
    }
    
    
    
    
    
    
    
    
    
    
    
}



