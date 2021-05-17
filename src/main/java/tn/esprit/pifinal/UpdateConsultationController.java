/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.twilio.Twilio; 
import com.twilio.converter.Promoter; 
import com.twilio.rest.api.v2010.account.Message; 
import com.twilio.type.PhoneNumber;  
import java.net.URI; 
import java.math.BigDecimal; 
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.User;
import tn.esprit.services.CategorieServices;
import tn.esprit.services.ConsultationServices;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class UpdateConsultationController implements Initializable {

    @FXML
    private AnchorPane id_affichage;
    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXButton btn_modifier;
    @FXML
    private JFXComboBox<String> id_patient;
    @FXML
    private JFXComboBox<String> id_categorie;
    
     public static final String ACCOUNT_SID = "AC7b9b15ed412328b74b830e2c01df6cb1"; 
    public static final String AUTH_TOKEN = "b0c9a76fe854043185c621c03b413a74";
    private static UserService userService = new UserService();
    private static CategorieServices categorieServices = new CategorieServices();
    private static ConsultationServices consultationServices = new ConsultationServices();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            for (User user : userService.readAllUserByRole("patient")) {
                id_patient.getItems().add(user.getUsername());
            }
              for (Categorie categorie : categorieServices.readAllCategories()) {
                id_categorie.getItems().add(categorie.getType_categorie());
            }
              id_categorie.setValue(categorieServices.getCategorieType(ListConsultationsController.consultation_a_modifier.getId_categorie()));
              title.setText(ListConsultationsController.consultation_a_modifier.getTitre());
              description.setText(ListConsultationsController.consultation_a_modifier.getDescription());
              id_patient.setValue(userService.getUserById(ListConsultationsController.consultation_a_modifier.getId_user()).getUsername());
        } catch (SQLException ex) {
            Logger.getLogger(UpdateConsultationController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }    

    @FXML
    private void modifier_consultation(ActionEvent event) throws SQLException, IOException {
        
         Twilio.init(ACCOUNT_SID, AUTH_TOKEN); 
        Message message = Message.creator( 
                new com.twilio.type.PhoneNumber("+21628896633"),  
                "MG8e9ba3c3a536c361b09f217983341031", 
                "your info has been modified")      
            .create(); 
 
        System.out.println(message.getSid()); 
    
         ListConsultationsController.consultation_a_modifier.setTitre(title.getText());
        ListConsultationsController.consultation_a_modifier.setDescription(description.getText());
        ListConsultationsController.consultation_a_modifier.setId_user(userService.getUserByUserName(id_patient.getValue()));
        ListConsultationsController.consultation_a_modifier.setId_categorie(categorieServices.getIdCategorie(id_categorie.getValue()));
        consultationServices.modifierConsultation(ListConsultationsController.consultation_a_modifier);
         AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ListConsultations.fxml"));
				id_affichage.getChildren().clear();
				id_affichage.getChildren().add(newLoadedPane);
                                                
                                                
    }
    
}
