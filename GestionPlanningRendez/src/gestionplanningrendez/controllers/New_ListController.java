/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.controllers;

import gestionplanningrendez.entity.Planning;
import gestionplanningrendez.service.ServicePlanning;
import gestionplanningrendez.service.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class New_ListController implements Initializable {

    @FXML
    private AnchorPane id_affichage;
    @FXML
    private Button Bhome;
    @FXML
    private Button Blist;
    @FXML
    private Button Bcalendar;
    @FXML
    private Button Bajout;
    @FXML
    private Button Btree;
    @FXML
    private Button stat;
    @FXML
    private TextField col_search;
    @FXML
    private ScrollPane x;
    @FXML
    private VBox pnItems;

    /**
     *
     */
    public  Planning planning_a_modifier;
    public static ServicePlanning sp =new ServicePlanning();
    public static ServiceUser su = new ServiceUser();

    public New_ListController() {
        this.planning_a_modifier = new Planning();
    }
    
   // ObservableList<Planning> Planning = FXCollections.observableArrayList(sp.afficher()); 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadConsultationGrid();
        } catch (SQLException ex) {
            Logger.getLogger(New_ListController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
     private void loadConsultationGrid() throws SQLException {
        List<Planning> plannings = fetchConsultations();
        Node[] nodes = new Node[plannings.size()];

        AtomicInteger i = new AtomicInteger(0);
        plannings.forEach(planning -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                displayConsultationrDetails(node, planning);
            } catch (SQLException ex) {
                Logger.getLogger(ListPlanningController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, planning, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length >= 0) {
            pnItems.setStyle("-fx-background-color : #4B0082");
            pnItems.toFront();
        }
    }

    private List<Planning> fetchConsultations() throws SQLException {
        try {
            return sp.afficher();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private Node loadNewItemNode() {
        try {
            return FXMLLoader.load(getClass().getResource("/fxml/item_consultation.fxml"));
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void displayConsultationrDetails(Node node, Planning planning) throws SQLException {
        Label item_user = (Label) node.lookup(".item_user");
        item_user.setText(su.getUserById(planning.getId_user()).getNom());
        Label item_planning = (Label) node.lookup(".item_planning");
        item_planning.setText(sp.getNomP(planning.getId()));
        Label item_titre = (Label) node.lookup(".item_Description");
        item_titre.setText(planning.getDescriptionP());

        // other properties
        // ...
    }

    private void setupActions(Node node, Planning planning, int index) {

        Button deleteButton = (Button) node.lookup(".item_action_supprimer");
        deleteButton.setOnMouseClicked(DeleteEventHandler(planning, index));
        Button modifierButton = (Button) node.lookup(".item_action_modifier");

        modifierButton.setOnMouseClicked(UpdateEventHandler(planning, index));
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
    public EventHandler<MouseEvent> UpdateEventHandler(Planning consultation, int index) {
        return event -> {

            try {
                planning_a_modifier = consultation;
                //sp.incrementerNombreVu(consultation.getId_user());
                AnchorPane newLoadedPane = FXMLLoader.load(getClass().getResource("/fxml/ModifierPlanning.fxml"));
                id_affichage.getChildren().clear();
                id_affichage.getChildren().add(newLoadedPane);
            } catch (IOException ex) {
                Logger.getLogger(New_ListController.class.getName()).log(Level.SEVERE, null, ex);
            }

        };
    }


    private EventHandler<MouseEvent> DeleteEventHandler(Planning planning, int index) {
        return event -> {

            sp.supprimer(planning.getId());

            pnItems.getChildren().remove(index);

            Notifications notificationBuilder = Notifications.create()
                    .title("Planning Supprimée")
                    .text("Vous avez supprimé un Planning")
                    .graphic(null)
                    .hideAfter(Duration.seconds(5))
                    .position(Pos.TOP_RIGHT);

            notificationBuilder.showError();
        };
    }

   
      private List<Planning> fetchDate() throws SQLException {
        try {
            return sp.treePlanning();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private void loadDate() throws SQLException {
        List<Planning> plannings = (List<Planning>) fetchDate();
        Node[] nodes = new Node[plannings.size()];

        AtomicInteger i = new AtomicInteger(0);
        plannings.forEach(planning -> {
            int j = i.getAndIncrement();
            Node node = nodes[j] = loadNewItemNode();

            try {
                displayConsultationrDetails(node, planning);
            } catch (SQLException ex) {
                Logger.getLogger(ListPlanningController.class.getName()).log(Level.SEVERE, null, ex);
            }

            setupActions(node, planning, j);

            setHoverStyleForNode(nodes, j);

            pnItems.getChildren().add(node);
        });

        if (nodes.length > 0) {
            pnItems.setStyle("-fx-background-color : #53639F");
            pnItems.toFront();
        }
    }
    
    

    @FXML
    private void rechercher(KeyEvent event) throws SQLException {
         pnItems.getChildren().clear();
       // loadNames();
    }

    

    @FXML
    private void ActionHome(ActionEvent event) {
    }

    @FXML
    private void ActionListR(ActionEvent event) {
    }

    @FXML
    private void ActionCalendar(ActionEvent event) {
    }

    @FXML
    private void ActionAjout(ActionEvent event) {
    }

    @FXML
    private void ActionTree(ActionEvent event) {
    }

    @FXML
    private void ActionStatistique(ActionEvent event) {
    }

    @FXML
    private void SearchPlanning(ActionEvent event) {
    }
    
}
