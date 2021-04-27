/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import Entity.Médicament;
import Service.ServiceCateg;
import Service.ServiceMedic;
import Utils.Database;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASSOUMA
 */
public class MedicController implements Initializable {

    @FXML
    private Label label;

    @FXML
    private TextField TfCode;
    @FXML
    private TextField TfNom;
    @FXML
    private ComboBox<String> CbCateg;
    @FXML
    private TextField TfPx;
    @FXML
    private TextField TfStock;
    @FXML
    private TableView<Médicament> TabMedic;
    @FXML
    private TableColumn<Médicament, Integer> ColCode;
    @FXML
    private TableColumn<Médicament, String> ColNom;
    @FXML
    private TableColumn<Médicament, String> ColCateg;
    @FXML
    private TableColumn<Médicament, Integer> ColPr;
    @FXML
    private TableColumn<Médicament, Integer> ColStock;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button res;

    private ObservableList<String> listnom = FXCollections.observableArrayList();
    private ObservableList<Médicament> fichData = FXCollections.observableArrayList();
    ServiceMedic smedic = new ServiceMedic();
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    @FXML
    private ImageView Home;
    @FXML
    private AnchorPane anchorpane;

    public MedicController() {
        Database cnx = Database.getInstance();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            ShowMedic();

            fillcombo();
        } catch (SQLException ex) {
            Logger.getLogger(MedicController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void reset(ActionEvent event) {

        CbCateg.getSelectionModel().clearSelection();
        TfCode.clear();
        TfNom.clear();
        TfPx.clear();
        TfStock.clear();
    }

    @FXML
      private void take(MouseEvent event) {
      Médicament m = TabMedic.getSelectionModel().getSelectedItem();

        TfCode.setText(""+m.getCode());
        TfNom.setText(m.getName());
        TfPx.setText(""+m.getPrix());
        TfStock.setText(""+m.getStock());
          
    }

    
    
    @FXML
    private void EditMedic(MouseEvent event) throws SQLException {

        cnx = Database.getInstance().getCon();
        ServiceCateg ser = new ServiceCateg();
        Catégorie list = ser.getByNom(CbCateg.getValue());
        String query = "UPDATE medicament SET name = '" + TfNom.getText() + "',categorie_id=" + list.getId()
                + ",prix=" + TfPx.getText() + ",stock=" + TfStock.getText() + "  WHERE code = " + TfCode.getText() + "";
        try {

            PreparedStatement pst = cnx.prepareStatement(query);
            pst.executeUpdate();
            JOptionPane.showConfirmDialog(null, "Médicament édité avec succé", "", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);
        }
        ShowMedic();

    }

    @FXML
    private void DeleteMedic(MouseEvent event) throws SQLException {
        
         Médicament m = TabMedic.getSelectionModel().getSelectedItem();
         System.out.println("kkk"+m);
        ServiceMedic sm = new ServiceMedic();
        sm.supprimer(m.getCode());
        JOptionPane.showConfirmDialog(null, "Medicament Supprimé avec succé", "", JOptionPane.PLAIN_MESSAGE);

        ShowMedic();

     
    }

    @FXML
    private void AddMedic(MouseEvent event) throws SQLException {

        if (Validchamp(TfCode) && Validchamp(TfNom) && Validchamp(TfPx) && Validchamp(TfStock)) {

            int code = Integer.valueOf(TfCode.getText());
            String name = TfNom.getText();
            int prix = Integer.valueOf(TfPx.getText());
            int stock = Integer.valueOf(TfStock.getText());

            ServiceCateg sercat = new ServiceCateg();

            Catégorie list = sercat.getByNom(CbCateg.getValue());
            //
            ServiceMedic smedic = new ServiceMedic();
            Médicament ls=smedic.findByCode(code);
            if(ls==null)
            {
            Médicament m = new Médicament(code, name, list.getId(), prix, stock);

            smedic.ajouter(m);
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Médicament");
            alert.setHeaderText(null);
            alert.setContentText("Added Successfully!");
            alert.showAndWait();
            }
            else{
                System.out.println("hhkkk"+ls);
             cnx = Database.getInstance().getCon();
                String query = "UPDATE medicament SET stock=stock+" +stock+ "  WHERE code = " + code + "";
        try {

            PreparedStatement pst = cnx.prepareStatement(query);
            pst.executeUpdate();
            JOptionPane.showConfirmDialog(null, "Médicament édité avec succé", "", JOptionPane.PLAIN_MESSAGE);

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex, "", JOptionPane.ERROR_MESSAGE);
        }
            }
            ShowMedic();

          

         } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Add Médicament");
            alert.setHeaderText(null);
            alert.setContentText("veuillez saisir tout les champs!");
            alert.showAndWait();

        }
        System.out.println("Hey you get a notification ");
      Notifications notificationBuilder = Notifications.create()
				.title("Notification")
				.text("vous avez une notification ! Un nouveau médicament a ete ajoute!! ").darkStyle()
                              
                                .hideAfter(Duration.seconds(5))
				.position(Pos.TOP_RIGHT);
                
        notificationBuilder.darkStyle();
       notificationBuilder.show();

    }

    private boolean Validchamp(TextField T) {
        return !T.getText().isEmpty();
    }

    public void ShowMedic() throws SQLException {

        List<Médicament> listmedi = new ArrayList<Médicament>();
        listmedi = smedic.afficher();
        fichData.clear();

        for (Médicament ls : listmedi) {
            fichData.add(ls);

        }

        ColCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        ColNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColPr.setCellValueFactory(new PropertyValueFactory<>("prix"));
        ColStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ColCateg.setCellValueFactory(new PropertyValueFactory<Médicament, String>("categorie"));

        TabMedic.setItems(fichData);

    }

    public void fillcombo() throws SQLException {

        ServiceCateg cat = new ServiceCateg();
        List<Catégorie> list = cat.afficher();
        for (Catégorie aux : list) {
            listnom.add(aux.getNom());
        }
        CbCateg.setItems(listnom);
    }

    @FXML
    private void home(MouseEvent event) {
           try {

            AnchorPane root = FXMLLoader.load(getClass().getResource("AcceuilAsma.fxml"));
            anchorpane.getChildren().setAll(root);

        } catch (IOException ex) {

        }
    }

}
