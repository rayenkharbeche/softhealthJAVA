/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.test;

import gestionplanningrendez.entity.get;
import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
 */
public class MainGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent  root;
       try {
           /* root = FXMLLoader.load(getClass().getResource("../views/AjoutPlanning.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Ajout Planning");
            primaryStage.setScene(scene);
            primaryStage.show();*/
        root = FXMLLoader.load(getClass().getResource("../views/Home.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("Gestion Hopital");
            primaryStage.setScene(scene);
            primaryStage.show(); 
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      /*  BorderPane bp = new BorderPane();
        PieChart pc = new PieChart();
        get x = new get();
        pc.setTitle("Statistique Rendez-vous");
        ObservableList <PieChart.Data>ol = FXCollections.observableArrayList(
        
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
        Scene sc = new Scene(bp,800,500);
        primaryStage.setScene(sc);
        primaryStage.setTitle("Statistique");*/
      //  primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
