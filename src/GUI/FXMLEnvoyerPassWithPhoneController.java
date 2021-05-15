/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FXMLEnvoyerPassWithPhoneController implements Initializable {

    @FXML
    private Button bot1;
    @FXML
    private TextField text;
    @FXML
    private Text txt;
    public static final String ACCOUNT_SID = "ACcdf8f4cb348b2b5ae0954b67e4560c1f";
    public static final String AUTH_TOKEN ="17257405a96e7d3efce7515b5dfee593";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }  
     @FXML
    private void send(ActionEvent event) throws Exception  {
     Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21627020022"),
                new com.twilio.type.PhoneNumber("+17047417815"),
                "This is the ship that made the Kessel Run in fourteen parsecs?")
            .create();
    }
    
}
