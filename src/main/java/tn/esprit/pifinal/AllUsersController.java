/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.event.ActionEvent;
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
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 * FXML Controller class
 *
 * @author rayen
 */
public class AllUsersController implements Initializable {

    @FXML
    private AnchorPane id_affichage;


    @FXML
    private JFXComboBox<String> id_role;

    @FXML
    private JFXTextField id_recherche;

    @FXML
    private ScrollPane x;

    @FXML
    private VBox pnItems;

    @FXML
    private JFXCheckBox id_cin;

    @FXML
    private JFXCheckBox id_alphabet;

    public static User user_a_modifier = new User();
    private static UserService userService = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
              id_role.getItems().addAll("admin","doctor","pharmacist","patient","secretary");
            loadUserGrid();
        } catch (SQLException ex) {
            Logger.getLogger(AllUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadUserGrid() throws SQLException {
        List<User> users = fetchUsers();
        Node[] nodes = new Node[users.size()];

        AtomicInteger i = new AtomicInteger(0);
        users.forEach(user -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            displayUserDetails(node, user);

            setupActions(node, user, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length >= 0) {
            pnItems.setStyle("-fx-background-color : #4B0082");
            pnItems.toFront();
        }
    }

    private List<User> fetchUsers() throws SQLException {
        try {
            return userService.readAll();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private Node loadNewItemNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/item_user.fxml"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void displayUserDetails(Node node, User user) {
        Label item_user = (Label) node.lookup(".item_user");
        item_user.setText(user.getUsername());
        Label item_role = (Label) node.lookup(".item_role");
        item_role.setText(user.getRoles());
        Label item_email = (Label) node.lookup(".item_email");
        item_email.setText(user.getEmail());

        // other properties
        // ...
    }

    private void setupActions(Node node, User user, int index) {

        Button deleteButton = (Button) node.lookup(".item_action_supprimer");
        deleteButton.setOnMouseClicked(DeleteEventHandler(user, index));
        Button modifierButton = (Button) node.lookup(".item_action_modifier");

        modifierButton.setOnMouseClicked(UpdateEventHandler(user, index));
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

    private EventHandler<MouseEvent> DeleteEventHandler(User user, int index) {
        return event -> {

            userService.supprimerUser(user);

            pnItems.getChildren().remove(index);

            Notifications notificationBuilder = Notifications.create()
                    .title("utilisateur Supprimé")
                    .text("Vous avez supprimé un utilisateur")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.showError();
        };
    }

    public EventHandler<MouseEvent> UpdateEventHandler(User user, int index) {
        return event -> {

            try {
                user_a_modifier = user;
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierUser.fxml"));
                id_affichage.getChildren().clear();
                id_affichage.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(AllUsersController.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
    }

    private List<User> fetchRoles(String role) throws SQLException {
        try {
            return userService.readAllUserByRole(role);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadRoles() throws SQLException {
        List<User> roles = fetchRoles(id_role.getSelectionModel().getSelectedItem());
        Node[] nodes = new Node[roles.size()];

        AtomicInteger i = new AtomicInteger(0);
        roles.forEach(role -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            displayUserDetails(node, role);

            setupActions(node, role, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

    private void loadNames() throws SQLException {
        List<User> users = fetchUsers();
        Node[] nodes = new Node[users.size()];

        AtomicInteger i = new AtomicInteger(0);
        users.forEach(user -> {
            int j = i.getAndIncrement();
            if (user.getUsername().contains(id_recherche.getCharacters())) {
                Node node = nodes[j] = loadNewItemNode();

                displayUserDetails(node, user);

                setupActions(node, user, j);

                setHoverStyleForNode(nodes, j);

                pnItems.getChildren().add(node);
            }
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

    private List<User> fetchCIN() throws SQLException {
        try {
            return userService.TrierParCIN();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadCin() throws SQLException {
        List<User> users = fetchCIN();
        Node[] nodes = new Node[users.size()];

        AtomicInteger i = new AtomicInteger(0);
        users.forEach(role -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            displayUserDetails(node, role);

            setupActions(node, role, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }

      private List<User> fetchAlphabet() throws SQLException {
        try {
            return userService.TrierParUserName();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadAlphabet() throws SQLException {
        List<User> users = fetchAlphabet();
        Node[] nodes = new Node[users.size()];

        AtomicInteger i = new AtomicInteger(0);
        users.forEach(role -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            displayUserDetails(node, role);

            setupActions(node, role, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }
    
    
    @FXML
    void rechercher(KeyEvent event) throws SQLException {
        pnItems.getChildren().clear();
        loadNames();
    }

    @FXML
    void afficherbyrole(ActionEvent event) throws SQLException {
        pnItems.getChildren().clear();
        loadRoles();
    }

    @FXML
    void trier_par_alphabet(ActionEvent event) throws SQLException {
 if (id_alphabet.isSelected()) {
            id_cin.setSelected(false);
            pnItems.getChildren().clear();
            loadAlphabet();
 }
    }

    @FXML
    void trier_par_cin(ActionEvent event) throws SQLException {
        if (id_cin.isSelected()) {
            id_alphabet.setSelected(false);
            pnItems.getChildren().clear();
            loadCin();
        }
    }
}
