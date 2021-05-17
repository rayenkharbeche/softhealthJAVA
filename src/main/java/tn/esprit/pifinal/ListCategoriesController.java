/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import tn.esprit.entities.Categorie;
import tn.esprit.entities.User;
import static tn.esprit.pifinal.AllUsersController.user_a_modifier;
import tn.esprit.services.CategorieServices;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class ListCategoriesController implements Initializable {

    @FXML
    private AnchorPane id_affichage;
    @FXML
    private JFXTextField id_recherche;
    @FXML
    private ScrollPane x;
    @FXML
    private VBox pnItems;
    
    public static CategorieServices categorieServices = new CategorieServices();
    public static Categorie categorie_a_modifier = new Categorie();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCategoryGrid();
        } catch (SQLException ex) {
            Logger.getLogger(ListCategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    
      private void loadCategoryGrid() throws SQLException {
        List<Categorie> categories = fetchCategories();
        Node[] nodes = new Node[categories.size()];

        AtomicInteger i = new AtomicInteger(0);
        categories.forEach(categorie -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            displayCategorieDetails(node, categorie);

            setupActions(node, categorie, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length >= 0) {
            pnItems.setStyle("-fx-background-color : #4B0082");
            pnItems.toFront();
        }
    }

    private List<Categorie> fetchCategories() throws SQLException {
        try {
            return categorieServices.readAllCategories();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private Node loadNewItemNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/item_category.fxml"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void displayCategorieDetails(Node node, Categorie categorie) {
        Label item_id = (Label) node.lookup(".item_id");
        item_id.setText(String.valueOf(categorie.getId_categorie()));
        Label item_type = (Label) node.lookup(".item_type");
        item_type.setText(categorie.getType_categorie());
       

        // other properties
        // ...
    }

    private void setupActions(Node node, Categorie categorie, int index) {

        Button deleteButton = (Button) node.lookup(".item_action_supprimer");
        deleteButton.setOnMouseClicked(DeleteEventHandler(categorie, index));
        Button modifierButton = (Button) node.lookup(".item_action_modifier");

        modifierButton.setOnMouseClicked(UpdateEventHandler(categorie, index));
    }

    private void setHoverStyleForNode(Node[] nodes, int i) {
        final int j = i;
        nodes[i].setOnMouseEntered(even -> {
            nodes[j].setStyle("-fx-background-color : #0A0E3F");
        });
        nodes[i].setOnMouseExited(even -> {
            nodes[j].setStyle("-fx-background-color : #02030A");
        });
    }

    private EventHandler<MouseEvent> DeleteEventHandler(Categorie categorie, int index) {
        return event -> {

            categorieServices.supprimerCategorie(categorie);

            pnItems.getChildren().remove(index);

            Notifications notificationBuilder = Notifications.create()
                    .title("categorie Supprimé")
                    .text("Vous avez supprimé une categorie")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.showError();
        };
    }

    public EventHandler<MouseEvent> UpdateEventHandler(Categorie categorie, int index) {
        return event -> {

            try {
                categorie_a_modifier = categorie;
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierCategorie.fxml"));
                id_affichage.getChildren().clear();
                id_affichage.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(AllUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
    }

    
     private void loadTypes() throws SQLException {
        List<Categorie> categories = fetchCategories();
        Node[] nodes = new Node[categories.size()];

        AtomicInteger i = new AtomicInteger(0);
        categories.forEach(category -> {
            int j = i.getAndIncrement();
            if (category.getType_categorie().contains(id_recherche.getCharacters())) {
                Node node = nodes[j] = loadNewItemNode();

                displayCategorieDetails(node, category);

                setupActions(node, category, j);

                setHoverStyleForNode(nodes, j);

                pnItems.getChildren().add(node);
            }
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }


    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
        pnItems.getChildren().clear();
        loadTypes();
    }
    
}
