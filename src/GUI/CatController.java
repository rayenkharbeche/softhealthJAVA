/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import Service.ServiceCateg;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASSOUMA
 */
public class CatController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button Bhome;
    @FXML
    private Button AddCateg;
    @FXML
    private TableView<Catégorie> CategTable;
    @FXML
    private TableColumn<Catégorie, String> colNom;
    
        private ObservableList<Catégorie> cate = FXCollections.observableArrayList();
    @FXML
    private ImageView rech;
    @FXML
    private TextField TfSearch;
    @FXML
    private Button add;
    @FXML
    private Button upd;
    @FXML
    private Button del;
    
    public static Catégorie cat;
    @FXML
    private Button addcat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowCateg();
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(CatController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    


 

    private void Search(ActionEvent event) {
          do {
            FilteredList<Catégorie> filteredData = new FilteredList<>(cate, b -> true);

            TfSearch.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(ct -> {

                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (ct.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    
                    } else {
                        return false;
                    }
                });
            });

            SortedList<Catégorie> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(CategTable.comparatorProperty());
            CategTable.setItems(sortedData);

        } while (rech.isPressed());

    }


     public void ShowCateg() throws SQLException {

       ServiceCateg sc = new ServiceCateg();
         List<Catégorie> listC = new ArrayList<Catégorie>();
        listC = sc.afficher();
        cate.clear();

        for (Catégorie ls : listC) {
            cate.add(ls);

        }

        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
       
        CategTable.setItems(cate);

    }



  
    private void ActionlistCat(ActionEvent event) throws IOException {
        
         AnchorPane root = FXMLLoader.load(getClass().getResource("Cat.fxml"));
        anchorpane.getChildren().setAll(root);
    }

 

    @FXML
    private void UpdateCategMedic(MouseEvent event) {
           Catégorie c = CategTable.getSelectionModel().getSelectedItem();
        
        cat = c;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("EditCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);

        }
        
    }

    @FXML
    private void DelCategMedic(MouseEvent event) throws SQLException {
          Catégorie Cate = CategTable.getSelectionModel().getSelectedItem();
        ServiceCateg sg = new ServiceCateg();
        sg.supprimer(Cate.getId());
        JOptionPane.showConfirmDialog(null, "Catégorie Supprimée avec succée", "", JOptionPane.PLAIN_MESSAGE);

        ShowCateg();
    }

    @FXML
    private void AddCategMedic(ActionEvent event) {
          try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void UpdateCategMedic(ActionEvent event) {
          Catégorie c = CategTable.getSelectionModel().getSelectedItem();
        
        cat = c;
           Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ModifCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void handleMouseAction(MouseEvent event) {
    }

    @FXML
    private void AddCategMedic(MouseEvent event) {
        try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AddCategMedic.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

    @FXML
    private void Home(ActionEvent event) {
        try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AcceuilAsma.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }


}
