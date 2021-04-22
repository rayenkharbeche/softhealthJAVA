/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FXML_Files;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author ASSOUMA
 */
public class OrdoController implements Initializable {

    @FXML
    private AnchorPane anchorpane;
    @FXML
    private Button add;
    @FXML
    private Button Convert;
    @FXML
    private Button add_Product;
    @FXML
    private TextField sarch;
    @FXML
    private TableView<?> Table;
    @FXML
    private TableColumn<?, ?> address;
    @FXML
    private TableColumn<?, ?> nbr;
    @FXML
    private TableColumn<?, ?> phone;
    @FXML
    private TableColumn<?, ?> phone_bis;
    @FXML
    private Button stocks;
    @FXML
    private Button facebook;
    @FXML
    private Button twitter;
    @FXML
    private Button search;
    @FXML
    private Button load;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Add_Entrepot(MouseEvent event) {
    }

    @FXML
    private void ConvertPDF(MouseEvent event) {
    }

    @FXML
    private void Product_List(MouseEvent event) {
    }

    @FXML
    private void seaa(ActionEvent event) {
    }

    @FXML
    private void search1(KeyEvent event) {
    }

    @FXML
    private void take(MouseEvent event) {
    }

    @FXML
    private void Stocks(MouseEvent event) {
    }

    @FXML
    private void facebookRedirect(MouseEvent event) {
    }

    @FXML
    private void TwitterRedirect(MouseEvent event) {
    }

    @FXML
    private void searchByAddress(MouseEvent event) {
    }

    @FXML
    private void load(MouseEvent event) {
    }
    
}
