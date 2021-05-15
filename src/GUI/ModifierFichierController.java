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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierFichierController implements Initializable {
@FXML
    private TextField des;


    @FXML
    private ChoiceBox<String> combodoc;

    @FXML
    private Button Bhome;

    @FXML
    private Button Blist;

    @FXML
    private Button Bajout;

    @FXML
    private Button Blist1;

    @FXML
    private Button tfile;
 
    @FXML
    private ChoiceBox<String> comboMed;
    
    private TableView<Fichier> table;
 File pfile;
    @FXML
    private Button bmodif;
    /**
     * Initializes the controller class.
     */
 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DossierService dossier = new DossierService();
        ObservableList <String> listd = FXCollections.observableArrayList(dossier.afficherNom()) ;
        combodoc.setItems(listd);
        MedecinService med = new MedecinService();
        ObservableList <String> listM = FXCollections.observableArrayList(med.afficherNom()) ;
        comboMed.setItems(listM); 
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
    }    
    
    
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Home.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }

    public void ActionListP(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFichier.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }

    

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjoutFichier.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }
    
    @FXML
    private void modifierFichier(ActionEvent event) throws IOException, SQLDataException {
        Fichier docSelec = (Fichier) table.getSelectionModel().getSelectedItem();
               

                String desc= des.getText();
                MedecinService ser1 = new  MedecinService();
                int d = ser1.getByNom(combodoc.getSelectionModel().getSelectedItem().toString());
                String image = pfile.getName();
                DossierService ser = new  DossierService();
                int m = ser.getByNom(comboMed.getSelectionModel().getSelectedItem().toString());
                docSelec.setDescription(desc);
                docSelec.setId_dossier(d);
                docSelec.setId_médecin(m);
                docSelec.setImage(image);
                
               
                FichierService Es= new  FichierService();
                Es.ModifierFichier(docSelec);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFichier.fxml"));
        
            Parent root = loader.load();
            des.getScene().setRoot(root);
            
        }

    @FXML
    private void ActionListD(ActionEvent event) {
    }

    @FXML
    private void modifierFichier(MouseEvent event) {
    }
    

    
    
}
