/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entites.Category;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import services.ServiceCategory;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AjouterCategoryController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private TextField color;
    @FXML
    private Button btnajouter;

    ServiceCategory sc = new ServiceCategory();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
      /*  if(Validchamp(nom)&&Validchamp(desc)&&Validchamp(color))
        {
        Category cat = new Category( nom.getText(),desc.getText(), color.getText());
        sc.ajouter(cat);
        
    }
         }
        else
        {
    
}
              
        }

        
    }
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty();
    }
*/
 if(Validchamp(nom)&&Validchamp(desc)&&Validchamp(color))
        {
       Category cat = new Category( nom.getText(),desc.getText(), color.getText());
        sc.ajouter(cat);
        Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du categorie");
   alert.setHeaderText(null);
   alert.setContentText("Cette catégorie a été ajoutée avec succés !");
   alert.showAndWait();
        
        }
        else
        {
            Alert alert =new Alert (AlertType.INFORMATION);
   alert.setTitle("Ajout du catégorie");
   alert.setHeaderText(null);
   alert.setContentText("veuillez saisir tout les champs!");
   alert.showAndWait();
            
        }

        
    }
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty();
    }

}

