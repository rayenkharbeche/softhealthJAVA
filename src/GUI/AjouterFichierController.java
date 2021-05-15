/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.FichierService;
import Services.MedecinService;
import entities.Fichier;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */

public class AjouterFichierController implements Initializable {
 @FXML
    private TextField des;

    @FXML
    private Button btnadd;

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

    /**
     * Initializes the controller class.
     */
    File pfile;
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
    private void ajouterFichier(ActionEvent event) throws IOException, SQLDataException {
        FichierService sp = new  FichierService();
        if (des.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Remplir tous les Champs");
            alert.showAndWait();
            
        }else{
            DossierService su = new DossierService();
            int s1 =su.getByNom(combodoc.getSelectionModel().getSelectedItem().toString());
            MedecinService mu = new MedecinService();
            int s2 =mu.getByNom(comboMed.getSelectionModel().getSelectedItem().toString());
              String image = pfile.getName();

            //int user =tuser.getSelectionModel().getSelectedIndex();
             sp.addFichier(new Fichier(des.getText(),s1,image,s2));
            JOptionPane.showMessageDialog(null, "fichier ajoutée !");
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFichier.fxml"));
        
            Parent root = loader.load();
            des.getScene().setRoot(root);

            
        }
       
    }
    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/Home.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
    }
    
    @FXML
    public void ActionListD(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListFichier.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
        
    }
    
    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/AjouterFichier.fxml"));
        
        Parent root = loader.load();
        des.getScene().setRoot(root);
        
    }
}
    


