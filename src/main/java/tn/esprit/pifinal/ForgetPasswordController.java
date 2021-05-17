/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.services.UserService;
import tn.utils.CurrentSession;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ForgetPasswordController implements Initializable {
    
  @FXML
    private TextField password;
  @FXML
    private JFXButton btn_password;
   
    @FXML
    private AnchorPane id_forget;
    @FXML
    private TextField email;
    @FXML
    private TextField code1;
    @FXML
    private JFXButton btn_send;
   @FXML 
           private JFXButton btn_confirm;
     @FXML
    private Label rec;
     @FXML
    private Label rec1;
     @FXML
    private Label rec2;
   
    
     public int code;
       public static String mails;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            password.setVisible(false);
            btn_password.setVisible(false);
            rec2.setVisible(false);
            
            btn_confirm.setVisible(false);
            code1.setVisible(false);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/Login.fxml"));
            LoginController ircc = loader.getController();

            code = ircc.codem;

        } catch (Exception ex) {
            Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
 @FXML
    private void confirm_code(ActionEvent event) throws IOException , SQLException {
        ////check if code belongs to the user
        UserService US = new UserService();
        String gencode=code1.getText();
        int checknb=US.checkcode(CurrentSession.userm.getId(), gencode);
        if (checknb!=1){
            mails=email.getText();
            password.setVisible(true);
            btn_password.setVisible(true);
            rec2.setVisible(true);
            code1.setVisible(false);
            rec.setVisible(false);
            rec1.setVisible(false);
            

            
            
        }
        
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Code incorrecte !!!");
            alert.showAndWait();
        }
        
        
       /* int codex = Integer.parseInt(code1.getText());
        UserService sc = new UserService();
        String x="x";
        if (code1.getText().toString().equals(x))
        {Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!!Veuillez taper le code de Verification !!!");
            alert.showAndWait();
        }
        
        else if (code == codex) {
            
            FXMLLoader loader = new FXMLLoader();
            rec.getScene().getWindow().hide();
            Stage prStage = new Stage();
            loader.setLocation(getClass().getResource("/fxml/NewPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            prStage.setScene(scene);
            prStage.setResizable(false);
            prStage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Code incorrecte !!!");
            alert.showAndWait();
        
    } */
    }
      
    @FXML
    private void sendEmail(ActionEvent event) throws IOException, SQLException {
        ////generate code
        String val = ""+((int)(Math.random()*9000)+1000);
        ////send to service
        UserService US=new UserService();
       
        US.Sendcode( CurrentSession.userm.getId() , val);
        
        //// send email
      btn_confirm.setVisible(true);
                   code1.setVisible(true);
                   btn_send.setVisible(false);
                   
                   
                   JavamailUtil mail = new JavamailUtil();
        try {
            mail.sendMail(email.getText());
            
            // code.setVisible(true);
            // btn_confirm.setVisible(true);
        } catch (Exception ex) {
            Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   @FXML
    void confirm_password(ActionEvent event) throws IOException {
 UserService sc = new UserService ();
             String m=email.getText();
             int id = sc.getIdbymail(m);
             String newpass=password.getText();
                     sc.setNewMotPass(id, newpass);
                      FXMLLoader loaderr = new FXMLLoader();
        id_forget.getScene().getWindow().hide();
        Stage prStage = new Stage();
        loaderr.setLocation(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loaderr.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);

        prStage.show();

    }
}
