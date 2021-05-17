/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.Consultation;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieServices;
import tn.esprit.services.ConsultationServices;
import tn.esprit.services.UserService;
import tn.utils.CurrentSession;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AddConsultationController implements Initializable {

    @FXML
    private AnchorPane id_affichage;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXComboBox<String> id_patient;
    @FXML
    private JFXComboBox<String> id_categorie;
 @FXML
    private JFXTextArea description;

    @FXML
    private JFXButton btn_ajouter;

    private static UserService userService = new UserService();
    private static CategorieServices categorieServices = new CategorieServices();
    private static ConsultationServices consultationServices = new ConsultationServices();
    Consultation consultation = new Consultation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            for (User user : userService.readAllUserByRole("ROLE_PATIENT")) {
                id_patient.getItems().add(user.getUsername());
            }
            for (Categorie categorie : categorieServices.readAllCategories()) {
                id_categorie.getItems().add(categorie.getType_categorie());
            }

        } catch (SQLException ex) {
            Logger.getLogger(AddConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void choosepatient(ActionEvent event) {
    }

    @FXML
    private void choosecategorie(ActionEvent event) {
    }

    @FXML
    private void ajouter_consultation(ActionEvent event) throws SQLException, IOException {
        consultation.setTitre(title.getText());
        consultation.setDescription(description.getText());
        consultation.setId_user(userService.getUserByUserName(id_patient.getValue()));
        consultation.setId_categorie(categorieServices.getIdCategorie(id_categorie.getValue()));
            consultation.setId_user(userService.getUserByUserName(id_patient.getValue()));
        consultationServices.ajouterConsultation(consultation);
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListConsultations.fxml"));
				id_affichage.getChildren().clear();
				id_affichage.getChildren().add(newLoadedPane);
               try {
                    String host = "smtp.gmail.com";
                    String user = "rihem.sahbeni30@gmail.com";
                    String pass = "rihem1234";
                    
                    String to = CurrentSession.userm.getEmail();
                    String from = "rihem.sahbeni30@gmail.com";
                    String subject = "la consultation est ajoute a votre dossier ";
                    String messageText = "Welcome " + "Mr." +  CurrentSession.userm.getUsername();

                    boolean sessionDebug = false;

                    Properties props = System.getProperties();

                    props.put("mail.smtp.starttls.enable", "true");
                    props.put("mail.smtp.host", host);
                    props.put("mail.smtp.port", "587");
                    props.put("mail.smtp.auth", "true");
                    props.put("mail.smtp.starttls.required", "true");
         props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                    java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                    Session mailSession = Session.getDefaultInstance(props, null);
                  
                    mailSession.setDebug(sessionDebug);
                    Message msg = new MimeMessage(mailSession);
                    msg.setFrom(new InternetAddress(from));
                    InternetAddress[] address = {new InternetAddress(to)};
                    msg.setRecipients(Message.RecipientType.TO, address);
                    msg.setSubject(subject);
                    msg.setSentDate(new java.util.Date());
                    msg.setText(messageText);

                    Transport transport = mailSession.getTransport("smtp");
                    transport.connect(host, user, pass);
                    transport.sendMessage(msg, msg.getAllRecipients());
                    transport.close();
                    System.out.println("message send successfully");
                } catch (Exception ex) {
                    System.out.println(ex);

    
        
    }
    }
}


