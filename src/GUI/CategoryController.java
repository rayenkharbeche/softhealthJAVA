/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entity.Catégorie;
import IServices.CategServices;
import Service.ServiceCateg;
import Utils.Database;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author ASSOUMA
 */
public class CategoryController implements Initializable {

    @FXML
    private Button edit;
    @FXML
    private Button Insert;
    @FXML
    private Button reset;
    @FXML
    private TableView<Catégorie> CategTable;
    @FXML
    private TableColumn<Catégorie, Integer> colCategId;
    @FXML
    private TableColumn<Catégorie, String> colNom;
    @FXML
    private TextField TfId;
    @FXML
    private TextField TfName;

    
    
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    @FXML
    private Button del;
    @FXML
    private Button rech;
    @FXML
    private TextField TfSearch;
   
    public CategoryController() {
    cnx=Database.getInstance().getCon();

    }
    
       @Override
    public void initialize(URL location, ResourceBundle resources) {
        ShowCateg();
    }
    
     @FXML
    private void handleButtonAction(ActionEvent event) {
         
         if (event.getSource()== edit){
            UpdateCateg();
            
        }else if (event.getSource()==del){
          DeleteCateg();  
        }
    }
        

    

    @FXML
    private void InsertCateg(ActionEvent event) {
                      cnx = Database.getInstance().getCon();

        String query ="INSERT INTO categorie VALUES ("+TfId.getText()+",'"+TfName.getText()+"')";
        Statement st;
        ResultSet res;
        try {
                         PreparedStatement pst = cnx.prepareStatement(query);

             st = cnx.createStatement();
                         pst.executeUpdate();

            System.out.println("Catégory added successfully");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         ShowCateg();

    }
    
    private void UpdateCateg(){
        
                       cnx = Database.getInstance().getCon();

        String query ="UPDATE categorie SET nom = '"+ TfName.getText()+"' WHERE id = " + TfId.getText() +"";
     try {
         
                     PreparedStatement pst = cnx.prepareStatement(query);
                      pst.executeUpdate();
                      System.out.println("category updated");

     }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      ShowCateg();
   
    }
    
    
    
     public ObservableList<Catégorie> getListCat(){
         ObservableList<Catégorie> ListCatg = FXCollections.observableArrayList();
         
              cnx = Database.getInstance().getCon();
         
         String query ="select * from categorie ";
         Statement st;
         ResultSet res;
     try{
         st = cnx.createStatement();
         res=st.executeQuery(query);
         Catégorie c;
         
          while(res.next()){
              
              c = new Catégorie(res.getInt("id"),res.getString("nom"));
             ListCatg.add(c);
          }
     }catch(Exception ex){
         ex.printStackTrace();
         
     }
     return ListCatg;
     
     }
     
     
    
     public void ShowCateg(){
     
    ObservableList<Catégorie> list = getListCat();
    colCategId.setCellValueFactory(new PropertyValueFactory<Catégorie,Integer>("id"));
    colNom.setCellValueFactory(new PropertyValueFactory<Catégorie,String>("nom"));
   
    CategTable.setItems(list);
    
     }
    
    
    
     private void DeleteCateg(){
        cnx = Database.getInstance().getCon();

        String query ="DELETE FROM categorie WHERE id =" + TfId.getText() + "";
        try {
                     PreparedStatement pst = cnx.prepareStatement(query);
                      pst.executeUpdate();
   JOptionPane.showConfirmDialog(null,"catégorie supprimé avec succé","" , JOptionPane.PLAIN_MESSAGE);
     }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         ShowCateg();
    }

     
     
    @FXML
    private void reset(ActionEvent event) {
           TfId.clear();
         TfName.clear();

    }
   
     

   
    @FXML
    private void handleMouseAction(MouseEvent event) {
        
    Catégorie c = new Catégorie();
    CategTable.getSelectionModel().getSelectedItem();
    TfId.setText(""+c.getId());
    TfName.setText(c.getNom());
   
    
    }

    @FXML
    private List<Catégorie> Search(ActionEvent event) {
        
     String sql="Select* from categorie";
         List<Catégorie> list =new ArrayList<>();
          
         try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                list.add(new Catégorie(res.getInt(1),res.getString(2)));
            }
                    return list.stream().filter(x->x.getNom().equals(TfSearch.getText())).collect(Collectors.toList());
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCateg.class.getName()).log(Level.SEVERE, null, ex);
                     return null;
        }
    }

    
        
           
   
}
