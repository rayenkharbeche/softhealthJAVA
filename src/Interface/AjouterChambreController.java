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
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.ServiceCategory;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AjouterChambreController implements Initializable {

    @FXML
    private TextField num;
    @FXML
    private TextField numetage;
    @FXML
    private TextField nbrplace;
    @FXML
    private TextField service;
    @FXML
    private TextField bloc;
    @FXML
    private ComboBox<String> box_category;
    ObservableList<String> listnom = FXCollections.observableArrayList();
    ObservableList<String> listtraitement = FXCollections.observableArrayList("Stérilisée","Non Stérilisée");
    ObservableList<String> listetat = FXCollections.observableArrayList("Disponible","Non Disponible");

    @FXML
    private Button btnajouter;
    @FXML
    private ComboBox<String> box_etat;
    @FXML
    private ComboBox<String> box_traitement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        box_etat.setItems(listetat);
        box_traitement.setItems(listtraitement);

    }    

    public void fillcombo() throws SQLException{
                
        ServiceCategory ser = new ServiceCategory();
        List<Category> list = ser.afficher();
        for (Category aux : list)
        {
          listnom.add(aux.getNom());
        }
        box_category.setItems(listnom);
    }
    
    @FXML
    private void ajouterchambre(ActionEvent event) throws SQLException {
        
        if(Validchamp(num)&&Validchamp(numetage)&&Validchamp(nbrplace)&&Validchamp(service)&&Validchamp(bloc))
        {
        int numero = Integer.valueOf(num.getText());
        int numetage = Integer.valueOf(this.numetage.getText());
        int nbrplace = Integer.valueOf(this.nbrplace.getText());
        String serv = service.getText();
        String blocc = bloc.getText();
        ServiceCategory sc = new ServiceCategory();
        
        Category list = sc.getByNom(box_category.getValue());
        //on va exrtai le id
        ServiceChambre sercha = new ServiceChambre();
        Chambre t = new Chambre(numero, numetage, nbrplace, serv, blocc, list.getId(),box_etat.getValue(),box_traitement.getValue());
        sercha.ajouter(t);
        num.setText("");
        this.numetage.setText("");
        this.nbrplace.setText("");
        service.setText("");
        bloc.setText("");
        Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du chambre");
   alert.setHeaderText(null);
   alert.setContentText("Cette chambre a été ajoutée avec succés !");
   alert.showAndWait();
        
        }
        else
        {
            Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du chambre");
   alert.setHeaderText(null);
   alert.setContentText("veuillez saisir tout les champs!");
   alert.showAndWait();
            
        }

        
    }
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty();
    }
    
    
    
    
    
                    private int controleDeSaisi() {  
                 
        if (num.getText().isEmpty() || numetage.getText().isEmpty()
                || nbrplace.getText().isEmpty()|| service.getText().isEmpty()||bloc.getText().isEmpty()) {
            
            return 0;
        } else 
            
            if (!Pattern.matches("[0-10]*", numetage.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de places ! ");
               numetage.requestFocus();
                numetage.selectEnd();
                return 1;
            }
            
           else if (!Pattern.matches("[0-9]*", num.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de places ! ");
              num.requestFocus();
               num.selectEnd();
                return 2;
//            } else if (!Pattern.matches("[0-9]*", tfcin.getText())) {
//                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez la cin! ");
//                tfcin.requestFocus();
//                tfcin.selectEnd();
//                return 3;
            } else if (!Pattern.matches("[0-9]*", nbrplace.getText())) {
                showAlert(Alert.AlertType.ERROR, "Données ", "Verifier les données", "Vérifiez le nombre de places ! ");
               nbrplace.requestFocus();
                nbrplace.selectEnd();
                 return 4;
            }
            else {return 5;
            }  
        
        
    }

    private void showAlert(AlertType alertType, String données_, String verifier_les_données, String vérifiez_le_nombre_de_places__) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
