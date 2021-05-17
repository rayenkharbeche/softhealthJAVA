package tn.esprit.pifinal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import tn.esprit.pifinal.LoginController;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class NewPasswordController implements Initializable {

    @FXML
    private Label rec;
    @FXML
    private JFXTextField cd;
     @FXML
    private JFXButton Envoyer;

     public static String mail="a";
    @FXML
    private JFXButton back;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
     @FXML
    private void Envoyer(ActionEvent event) throws IOException {
        if(cd.getText().isEmpty())
        {  Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Champs vide !!!");
            alert.showAndWait();
        }
        else
        { String newPass = cd.getText();
        UserService sc = new UserService ();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login.fxml"));
        LoginController ircc = loader.getController();
        String m=ForgetPasswordController.mails;
        int id = sc.getIdbymail(m);
       
        sc.setNewMotPass(id, newPass);
        FXMLLoader loaderr = new FXMLLoader();
        cd.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loaderr.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loaderr.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);

        prStage.show();
        }
    }

    @FXML
    private void back(ActionEvent event) {
    }
    
}
