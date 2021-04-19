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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.ServiceCategory;
import services.ServiceChambre;

/**
 * FXML Controller class
 *
 * @author Mejri Wajih
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

    @FXML
    private Button btnajouter;

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
        int numero = Integer.valueOf(num.getText());
        int numetage = Integer.valueOf(this.numetage.getText());
        int nbrplace = Integer.valueOf(this.nbrplace.getText());
        String serv = service.getText();
        String blocc = bloc.getText();
        ServiceCategory sc = new ServiceCategory();
        Category list = sc.getByNom(box_category.getValue());
        System.out.println(list.getId());
        ServiceChambre sercha = new ServiceChambre();
        Chambre t = new Chambre(numero, numetage, nbrplace, serv, blocc, list.getId());
        sercha.ajouter(t);
        
    }
    
}
