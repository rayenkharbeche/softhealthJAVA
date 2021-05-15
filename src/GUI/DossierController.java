/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.PatientService;
import entities.Dossier;
import entities.Fichier;
import entities.Patient;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DossierController implements Initializable {

    @FXML
    private TableColumn<Dossier, Integer> colid;
    @FXML
    private TableColumn<Dossier,String > coldes;
    @FXML
    private TableColumn<Dossier, Integer> cold;
    @FXML
    private TableColumn<Dossier, Integer> colpa;
     @FXML
    private Button btnadd;
  
    
    @FXML
    private Button btnupdate;
    @FXML
    private Button btndelete;
     @FXML
    private ComboBox<String> combopa;
    @FXML
    private TableView<Dossier> tvdoc;
     @FXML
    private TextField des;

    @FXML
    private  DatePicker tesDate =new DatePicker();
     
    /**
     * Initializes the controller class.
     */ private ObservableList<Dossier> docData = FXCollections.observableArrayList();
      DossierService compservice =  new DossierService();
     private ObservableList<String> listnom2 = FXCollections.observableArrayList();
      ActionEvent e ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillcombo1();
        } catch (SQLException ex) {
            Logger.getLogger(DossierController.class.getName()).log(Level.SEVERE, null, ex);
        }
      afficher_Dossier();
     //RechercheAV();
    }    
    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws SQLException {
         System.out.println("You clicked me!");
        if (event.getSource()==btndelete){
         ButtonSupprimer();
    }
        if (event.getSource()== btnadd){
           ajouterdossier();
            
        }
       
        if (event.getSource()== btnupdate){
         modif(e);
        
    }
       
    }
    private void ButtonSupprimer() throws SQLException {

            tvdoc.setItems(docData);

             ObservableList<Dossier> allDossiers,SingleDossiers ;
             allDossiers=tvdoc.getItems();
             SingleDossiers=tvdoc.getSelectionModel().getSelectedItems();
             Dossier A = SingleDossiers.get(0);
             compservice.deleteDossier(A.getId_dossier());
             SingleDossiers.forEach(allDossiers::remove);
             afficher_Dossier();
            
    }
     public void fillcombo1() throws SQLException{
                
        PatientService ser = new   PatientService ();
        List<Patient> list = ser.getAllPatient();
        for (Patient aux : list)
        {
          listnom2.add(aux.getNom());
        }
         combopa.setItems(listnom2);
    }
   
     public void afficher_Dossier()
    {
         try {
            List<Dossier> listDossier= new ArrayList<Dossier>();
            listDossier = compservice.getAllDossier();
        docData.clear();
         docData.addAll( listDossier );
       tvdoc.setItems(docData);
        
        colid.setCellValueFactory
        (
            new PropertyValueFactory<>("id_dossier")
        );
        coldes.setCellValueFactory
        (
            new PropertyValueFactory<>("description")
        );
        cold.setCellValueFactory
        (
            new PropertyValueFactory<>("date_creation")
        );
            colpa.setCellValueFactory(new Callback<CellDataFeatures<Dossier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(CellDataFeatures<Dossier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_patient());}});
    
        
        } catch (SQLDataException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }    
   private void ajouterdossier() throws SQLException {
       PatientService ser = new  PatientService();
     
         int p2 = ser.getByNom(combopa.getValue());
         
         String desc = des.getText();
       String date =tesDate.getValue().toString() ;
      
        DossierService doc = new  DossierService();
        Dossier k = new Dossier(desc,date,p2);
        doc.addDossier(k);
     
       afficher_Dossier();
       
    } 
     
    
   private void modif(ActionEvent event) throws SQLDataException {
         Dossier docSelec = (Dossier) tvdoc.getSelectionModel().getSelectedItem();
               

                String desc= des.getText();
                String date =tesDate.getValue().toString() ;
                PatientService ser = new  PatientService();
                int p = ser.getByNom(combopa.getValue());
                docSelec.setDescription(desc);
                docSelec.setId_patient(p);
                docSelec.setDateCreéation(date);
                DossierService Es= new  DossierService();
                Es.ModifierDossier(docSelec);
                
}
   /*
   private boolean searchFindsdossier(Dossier dossier, String searchText){
    return (dossier.getDescription().toLowerCase().contains(searchText.toLowerCase()));
            
}
   private ObservableList<Dossier> filterList(List<Dossier> list, String searchText){
    List<Dossier> filteredList = new ArrayList<>();
    for (Dossier dossier : list){
        if(searchFindsdossier(dossier, searchText)) filteredList.add(dossier);
    }
    return FXCollections.observableList(filteredList);
}*/
         
   /*public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
      FilteredList<Dossier> filteredData = new FilteredList<>(docData, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
      des.textProperty().addListener((observable, oldValue, newValue) -> {
       filteredData.setPredicate(dossier -> {
				// If filter text is empty, display all persons.
								
	if (newValue == null || newValue.isEmpty()) {
	return true;
  }
				
				// Compare first name and last name of every person with filter text.
	String lowerCaseFilter = newValue.toLowerCase();
				
	if (dossier.getDescription().toLowerCase().contains(lowerCaseFilter) ) {
		return true; // Filter matches first name.
	} else
        
	return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Dossier> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvdoc.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvdoc.setItems(sortedData);
               
               
    } 
    
   */      
        
   
   
}
