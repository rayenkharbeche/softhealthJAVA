/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private JFXTextField username_login;
    @FXML
    private JFXPasswordField password_login;
    @FXML
    private JFXButton btn_login;
    @FXML
    private Label sign_up;
    @FXML JFXButton  facebookbutt;
    
    @FXML
    private Label l;
   // @FXML
  //  private FontAwesomeIconView label1;
   
    public static int  codem;
    
    public static String username;
    public static String motpass;
    
     UserService userService;
     public static User user_connecte = new User();
    public LoginController() {
        userService = new UserService();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("NewPassword.fxml"));
        NewPasswordController ircc = loader.getController();
        String s="a";
        if (!ircc.mail.equals(s)){
            username_login.setText(ircc.mail);
        }
    }    
    

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException {
          User user;
        user = userService.authentifier(username_login.getText(), password_login.getText());
        if (user != null) {
            System.out.println(user);
            //static
           if(user.getRoles().equals("[\"ROLE_ADMIN\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/Home.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("[\"ROLE_MEDECIN\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/Home_Doctor.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("[\"ROLE_PATIENT\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/Home_Patient.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("[\"ROLE_PHARMACIEN\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/Home_Pharmacist.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
           else  if(user.getRoles().equals("[\"ROLE_SECRETAIRE\"]")){
               AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/Home_Secretary.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
           }
            
        } else {
             Notifications notificationBuilder = Notifications.create()
                    .title("wrong email or password")
                    .text("type the correct fields")
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.CENTER);
            notificationBuilder.showError();
            BoxBlur blur = new BoxBlur(3, 3, 3);
            content.setEffect(blur);
           
            content.setEffect(null);
        }
    }

    @FXML
    private void gotosignup(MouseEvent event)throws IOException {
        AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/FirstRegistration.fxml"));
        content.getChildren().clear();
        content.getChildren().add(newLoadedPane);
    }

    @FXML
    private void close_app(MouseEvent event) {
        System.exit(0);
    }
       @FXML
    void btn_forget_password(MouseEvent event) throws IOException {
        
            AnchorPane newLoadedPane =FXMLLoader.load(getClass().getResource("/fxml/ForgetPassword.fxml"));
            content.getChildren().clear();
            content.getChildren().add(newLoadedPane);
    }

  /*public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

 @FXML
    private void MotpassOubliee(ActionEvent event) throws MessagingException, IOException {
        username = username_login.getText();
        UserService sc = new UserService();
   		
        Random r = new Random ();
      codem =r.nextInt(9999-1000+1);
                System.out.println(codem);
                //sc.setCodepass(sc.getIdbymail(username_login.getText()), codem);

     //  System.out.println(sc.getPassbyId(sc.getIdbymail(username_login.getText())));
        if(isValidEmailAddress(username_login.getText())){
    // SendMail.send(username_login.getText(), sc.getPassbyId(sc.getIdbymail(username_login.getText())));
   SendMail.send(username_login.getText(), codem);
          FXMLLoader loader = new FXMLLoader();
        l.getScene().getWindow().hide();
        Stage prStage = new Stage();
         loader.setLocation(getClass().getResource("/fxml/ForgetPassword.fxml"));
            
        Parent root = loader.load();
        Scene scene = new Scene(root);
        prStage.setScene(scene);
        prStage.setResizable(false);
        prStage.show();}
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alerte");
            alert.setHeaderText(null);
            alert.setContentText("!!! Adresse Email Non Valide !!!");
            alert.showAndWait();
        }
       
    }*/
     @FXML
    private void exit(MouseEvent event) {
    }
    @FXML 
    private void connectfacebook(MouseEvent event){/*
         ConfigurationBuilder confBuilder = new ConfigurationBuilder();

    confBuilder.setDebugEnabled(true);
    confBuilder.setOAuthAppId("************");
    confBuilder.setOAuthAppSecret("***************");
    confBuilder.setUseSSL(true);
    confBuilder.setJSONStoreEnabled(true);
    Configuration configuration = confBuilder.build();
    return configuration;
}
public static void main(String[] argv) throws FacebookException {
    Configuration configuration =  createConfiguration();
    FacebookFactory facebookFactory = new FacebookFactory(configuration );
    Facebook facebookClient = facebookFactory.getInstance();
    AccessToken accessToken = null;
    try{
        OAuthSupport oAuthSupport = new OAuthAuthorization(configuration );
        accessToken = oAuthSupport.getOAuthAppAccessToken();

    }catch (FacebookException e) {
        System.err.println("Error while creating access token " + e.getLocalizedMessage());
    }
    facebookClient.setOAuthAccessToken(accessToken);
//results in an error says {An active access token must be used to query information about the current user}
    facebookClient.postStatusMessage("Hello World from Facebook4J."); 
    */}
}
