/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.FichierService;
import Services.MedecinService;
import entities.Dossier;
import entities.Fichier;
import entities.Medecin;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import utils.DataSource;
import utils.Image;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FichierController implements Initializable {
    @FXML
    private AnchorPane frame;
    @FXML
    private TextField txtDes;
    @FXML
    private Button file;
    @FXML
    private ComboBox<String> comboD;
    @FXML
    private ComboBox<String> comboM;
    @FXML
    private TableView<Fichier> tvBooks;
    @FXML
    private TableColumn<Fichier, Integer> colid;
    @FXML
    private TableColumn<Fichier, String> coldes;
    @FXML
    private TableColumn<Fichier, String> colfile;
    @FXML
    private TableColumn<Fichier,Integer> coldoc;
    @FXML
    private TableColumn<Fichier, Integer> colMed;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnupdate;
     @FXML
    private TextField recp;
    @FXML
    private Button btndelete;
   private ObservableList<String> listnom = FXCollections.observableArrayList();
    private ObservableList<String> listnom1 = FXCollections.observableArrayList();
    private ObservableList<Fichier> fichData = FXCollections.observableArrayList();
      FichierService compservice =  new FichierService();
    
   
    /**
     * Initializes the controller class.
     */
       int c;
       int fil;
    File pDir;
    File pfile;
    String lien;
    ActionEvent e ;
    MouseEvent v;
     DataSource cnx = DataSource.getInstance();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            fillcombo1();
        } catch (SQLException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
         c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
        pDir = new File("src/gui/img/Profile" + c + ".jpg");
        lien = "Profile" + c + ".jpg";
        
         
        afficher_Fichier();
    }  
       @FXML
    private void UploadImageActionPerformed() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        pfile = fileChooser.showOpenDialog(null);
         //Window stage = null;
        //pfile = fileChooser.showOpenDialog(stage);

          
      
            
    
      
    }

    
    @FXML
    private void ButtonSupprimer() throws SQLException {

            tvBooks.setItems(fichData);

             ObservableList<Fichier> allFichiers,SingleFichiers ;
             allFichiers=tvBooks.getItems();
             SingleFichiers=tvBooks.getSelectionModel().getSelectedItems();
             Fichier A = SingleFichiers.get(0);
             compservice.deleteFichier(A);
             SingleFichiers.forEach(allFichiers::remove);
             afficher_Fichier();
            
    }
    public void afficher_Fichier()
    {
         try {
            List<Fichier> listFichier= new ArrayList<Fichier>();
            listFichier = compservice.getAllFichier();
        fichData.clear();
         fichData.addAll(listFichier);
       tvBooks.setItems(fichData);
        
        colid.setCellValueFactory
        (
            new PropertyValueFactory<>("id_fichier")
        );
        coldes.setCellValueFactory
        (
            new PropertyValueFactory<>("description")
        );
        
       coldoc.setCellValueFactory(new Callback<CellDataFeatures<Fichier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(CellDataFeatures<Fichier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_dossier());
    }
});
                
         
         colfile.setCellValueFactory
        (
            new PropertyValueFactory<>("image")
        );
         
          colMed.setCellValueFactory(new Callback<CellDataFeatures<Fichier,Integer>, ObservableValue<Integer>>() {
    public ObservableValue<Integer> call(CellDataFeatures<Fichier,Integer> data) {
        return new ReadOnlyObjectWrapper<>(data.getValue().getId_médecin());}});
    
        
        } catch (SQLDataException ex) {
            Logger.getLogger(FichierController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
                
    @FXML
    private void handleButtonAction(javafx.event.ActionEvent event) throws SQLException {
         System.out.println("You clicked me!");
        if (event.getSource()==btndelete){
         ButtonSupprimer();
    }
        if (event.getSource()== btnadd){
            ajouterfichier();
            
        }
        if (event.getSource()== file){
           UploadImageActionPerformed();
            
        }
        if (event.getSource()== btnupdate){
            
            
                    modif(e);
    }}
   
    public void fillcombo1() throws SQLException{
                
        MedecinService ser = new  MedecinService();
        List<Medecin> list = ser.getAllMedecin();
        for (Medecin aux : list)
        {
          listnom1.add(aux.getNom());
        }
         comboM.setItems(listnom1);
    }
    public void fillcombo() throws SQLException{
                
        DossierService ser = new  DossierService();
        List<Dossier> list = ser.getAllDossier();
        for (Dossier aux : list)
        {
          listnom.add(aux.getDescription());
        }
         comboD.setItems(listnom);
    }
    private void ajouterfichier() throws SQLException {
       String description = txtDes.getText();
       
         DossierService ser = new  DossierService();
         Dossier list = ser.getByNom(comboD.getValue());
       // System.out.println(list.getId_dossier());
        String image = pfile.getName();
        
        MedecinService ser1 = new   MedecinService();
        Medecin list1 = ser1.getByNom(comboM.getValue());
        //System.out.println(list1.getId_médecin());
        FichierService sercha = new  FichierService();
        Fichier t = new Fichier(description, list.getId_dossier(),image ,list1.getId_médecin());
       sercha.addFichier(t);
       afficher_Fichier();
      
    } 
     @FXML
    private void Change_des(TableColumn.CellEditEvent event) throws SQLException {
     Fichier tab_fileselected = tvBooks.getSelectionModel().getSelectedItem();
     tab_fileselected.setDescription(event.getNewValue().toString());
     compservice.ModifierFichier(tab_fileselected);

    }
    
    
    
    
   @FXML
   private void modif(ActionEvent event) throws SQLDataException {
         Fichier fichSelec = (Fichier) tvBooks.getSelectionModel().getSelectedItem();
               

                String desc= txtDes.getText();
              
                DossierService ser = new  DossierService();
         Dossier list = ser.getByNom(comboD.getValue());
       // System.out.println(list.getId_dossier());
        String image = pfile.getName();
        
        MedecinService ser1 = new   MedecinService();
        Medecin list1 = ser1.getByNom(comboM.getValue());
             
               fichSelec.setDescription(desc);
               fichSelec.setImage(image);
               fichSelec.setId_médecin(list1.getId_médecin());
               fichSelec.setId_dossier(list.getId_dossier());
              
                       FichierService Es= new  FichierService();
                Es.ModifierFichier(fichSelec);
                
                
 }
   public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
      FilteredList<Fichier> filteredData = new FilteredList<>(fichData, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
      txtDes.textProperty().addListener((observable, oldValue, newValue) -> {
       filteredData.setPredicate(fichier -> {
				// If filter text is empty, display all persons.
								
	if (newValue == null || newValue.isEmpty()) {
	return true;
  }
				
				// Compare first name and last name of every person with filter text.
	String lowerCaseFilter = newValue.toLowerCase();
				
	if (fichier.getDescription().toLowerCase().contains(lowerCaseFilter) ) {
		return true; // Filter matches first name.
	} else
        
	return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Fichier> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tvBooks.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tvBooks.setItems(sortedData);
               
               
    } 
    
    /*private void Edit_Fichier() throws SQLException {
        
        
        
        
        
        
         Fichier fichSelec = (Fichier) tvBooks.getSelectionModel().getSelectedItem();
          FichierService es = new FichierService();
          DossierService ser = new  DossierService();
        Dossier list = ser.getByNom(comboD.getValue());
   
        
        MedecinService ser1 = new   MedecinService();
        Medecin list1 = ser1.getByNom(comboM.getValue());
        
             
        txtDes.setText(fichSelec.getDescription());
       

        
        
            String value1 = txtDes.getText();
             String value2 = pfile.getName();
            Integer value3 = list.getId_dossier();
           
            Integer value4 = list1.getId_médecin();
           
            fichSelec.setDescription(value1);
            fichSelec.setId_dossier(value3);
            fichSelec.setImage(value2);
            fichSelec.setId_médecin(value4);
            
            System.out.println("updaaaated"+fichSelec);
            es.ModifierFichier(fichSelec);
            
            afficher_Fichier();
     
    }  */
  }
         