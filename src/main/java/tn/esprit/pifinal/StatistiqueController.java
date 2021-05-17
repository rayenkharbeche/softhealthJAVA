/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pifinal;

import tn.esprit.entities.RendezVous;
import tn.esprit.entities.get;
import tn.esprit.services.ServiceRDV;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BorderPane bp;
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
    private Label soft;
    ServiceRDV sp =new ServiceRDV();
    final ObservableList<RendezVous>  data = FXCollections.observableArrayList(sp.afficher());

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         PieChart pc = new PieChart();
        get x = new get();
        pc.setTitle("Statistique Rendez-vous");
        ObservableList <PieChart.Data>ol;
        try {
            ol = FXCollections.observableArrayList(
                    
                    new PieChart.Data("Janvier",x.Jan()),
                    new PieChart.Data("Fevrier",x.Feb()),
                    new PieChart.Data("Mars",x.Mars()),
                    new PieChart.Data("Avril", x.Avril()),
                    new PieChart.Data("Mays", x.Mai()),
                    new PieChart.Data("Juin", x.Juin()),
                    new PieChart.Data("Juillet", x.Juillet()),
                    new PieChart.Data("Aout", x.Aout()),
                    new PieChart.Data("Septembre", x.Sept()),
                    new PieChart.Data("October", x.Oct()),
                    new PieChart.Data("Nouvembre", x.Nouvbre()),
                    new PieChart.Data("Decembre", x.Dec())
            );
                pc.setData(ol);
        bp.setCenter(pc);
        pc.setLegendSide(Side.LEFT);
        
        FadeTransition f = new FadeTransition(Duration.seconds(4),pc);
        f.setFromValue(0);
        f.setToValue(1);
        f.play();
        
        for (PieChart.Data data : pc.getData()){
            data.nameProperty().set(data.getName()+ " : "+(int)data.getPieValue()+ " Rendez-vous");
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    JOptionPane.showMessageDialog(null,"Country -- "+ data.getName()+ "\nTotal Rendez-vous --" +(int)data.getPieValue());              
                }    }   );
        }
        } catch (Exception ex) {
            Logger.getLogger(StatistiqueController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    

    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
        
        Parent root = loader.load();
        soft.getScene().setRoot(root);
    }

    @FXML
    private void ActionListR(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListRendez.fxml"));
        
        Parent root = loader.load();
        soft.getScene().setRoot(root);
    }

    @FXML
    private void ActionCalendar(ActionEvent event) {
    }

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjoutRDV.fxml"));
        
        Parent root = loader.load();
        soft.getScene().setRoot(root);
    }

    

    @FXML
    private void ActionStatistique(ActionEvent event) {
    }
    
}
