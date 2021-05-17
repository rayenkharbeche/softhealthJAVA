

package tn.esprit.pifinal;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rayen
 */
public class FirstAndSecondRegistrationController implements Initializable{

       @FXML
    private AnchorPane content1;

    @FXML
    private Pane id_reg;

    @FXML
    private JFXTextField cin;

    @FXML
    private JFXTextField userName;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField tf_password;

    @FXML
    private JFXPasswordField tf_repeter;

    @FXML
    private FontAwesomeIconView next1;

    @FXML
    private FontAwesomeIconView retour_first;

    @FXML
    private Label ver_cin;

    @FXML
    private Label ver_user;

    @FXML
    private Label erreurMail;

    @FXML
    private Label ver_mot;

    @FXML
    private Label ver_rep;
    @FXML
    private AnchorPane content2;

    @FXML
    private FontAwesomeIconView retour2;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private JFXButton create_account;

    
    @FXML
    private JFXRadioButton role_pharmacist;

    @FXML
    private JFXRadioButton role_patient;

    @FXML
    private JFXRadioButton role_sercretary;

    @FXML
    private JFXRadioButton role_doctor;
    
    UserService userService = new UserService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    
   
    public static User user = new User();
     String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
            + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
     @FXML
    private void gotopage2(MouseEvent event) throws IOException, SQLException {
          Pattern pattern = Pattern.compile(masque);
        Matcher controler = pattern.matcher(email.getText());
        if (controler.matches()) {
            
            
             if ((userService.getUserByUserName(userName.getText())!=0) || ((!tf_password.getText().equals(tf_repeter.getText())))) {
            if (userService.getUserByUserName(userName.getText())!=0) {

                Notifications notificationBuilder = Notifications.create()
                        .title("User already exists")
                        .text("Choose another user name")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER);

                notificationBuilder.showWarning();
                BoxBlur blur = new BoxBlur(3, 3, 3);
                content1.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type again please");
                alert.setHeaderText("Invalid field");
                alert.setContentText("Check the field please");
                alert.showAndWait();
                content1.setEffect(null);

            } else if ((!tf_password.getText().equals(tf_repeter.getText()))) {

                Notifications notificationBuilder = Notifications.create()
                        .title("Password isn't the same")
                        .text("Type another password")
                        .hideAfter(Duration.seconds(5))
                        .position(Pos.CENTER);

                notificationBuilder.showWarning();
                BoxBlur blur = new BoxBlur(3, 3, 3);
                content1.setEffect(blur);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Type again please");
                alert.setHeaderText("Invalid field");
                alert.setContentText("Check the field please");
                alert.showAndWait();
                content1.setEffect(null);

            }
        } 
          
             else {
                     user.setEmail(email.getText());
             user.setPassword(tf_password.getText());
         user.setCin(Integer.parseInt(cin.getText()));
            user.setUsername(userName.getText());

            AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/SecondRegistration.fxml"));
            content1.getChildren().clear();

            content1.getChildren().add(newLoadedPane);
             }
            
            
        
            
        } else {

            Notifications notificationBuilder = Notifications.create()
                    .title("Bad syntax of Email")
                    .text("Type a correct Email")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationBuilder.showError();
            BoxBlur blur = new BoxBlur(3, 3, 3);
            content1.setEffect(blur);
           
            content1.setEffect(null);

        }
        
        

        
    }
@FXML
    private void retour1(MouseEvent event) throws IOException {
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
            content1.getChildren().clear();

            content1.getChildren().add(newLoadedPane);
    }
    @FXML
    private void fermer(MouseEvent event) {
        System.exit(0);
    }
  

    @FXML
    void backto1(MouseEvent event) throws IOException {
    AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/FirstRegistration.fxml"));
        content2.getChildren().clear();
        content2.getChildren().add(newLoadedPane);
        
    }


    @FXML
    void close(MouseEvent event) {
System.exit(0);
    }

    @FXML
    void doRegistration(MouseEvent event) throws SQLException, IOException {
        userService.ajouterUser(user);
       
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        content2.getChildren().clear();
        content2.getChildren().add(newLoadedPane);
         }

 
    
   

    @FXML
    void chooseRoleDoctor(ActionEvent event) {
    if (role_doctor.isSelected()){
            
                        role_patient.setSelected(false);
            role_pharmacist.setSelected(false);
            role_sercretary.setSelected(false);
            user.setRoles("[\"ROLE_MEDECIN\"]");
        }
    }

    @FXML
    void chooseRolePatient(ActionEvent event) {
 if (role_patient.isSelected()){
            role_doctor.setSelected(false);
                        
            role_pharmacist.setSelected(false);
            role_sercretary.setSelected(false);
            user.setRoles("[\"ROLE_PATIENT\"]");
        }
    }

    @FXML
    void chooseRolePharmacist(ActionEvent event) {
 if (role_pharmacist.isSelected()){
            role_doctor.setSelected(false);
                        role_patient.setSelected(false);
           
            role_sercretary.setSelected(false);
            user.setRoles("[\"ROLE_PHARMACIEN\"]");
        }
    }

    @FXML
    void chooseRoleSecretary(ActionEvent event) {
 if (role_sercretary.isSelected()){
            role_doctor.setSelected(false);
                        role_patient.setSelected(false);
            role_pharmacist.setSelected(false);
         
            user.setRoles("[\"ROLE_SECRETAIRE\"]");
        }
    }
    
    
}
