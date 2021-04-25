/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.controllers;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import gestionplanningrendez.entity.Planning;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import net.fortuna.ical4j.model.Recur;
import net.fortuna.ical4j.model.WeekDay;
import net.fortuna.ical4j.model.WeekDayList;

import java.lang.ref.WeakReference;
import java.text.MessageFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class CalendarController implements Initializable {

    @FXML
    private Button B_flechH;
    @FXML
    private Button B_flechB;
    @FXML
    private Label l_Month;
    @FXML
    private Label TODAY;
    @FXML
    private Label DAILY;
    @FXML
    private Label WEEKLY;
    @FXML
    private Label MONTHLY;
    @FXML
    private Label l_dimanche;
    @FXML
    private Label l_lundi;
    @FXML
    private Label l_mardi;
    @FXML
    private Label l_mercredi;
    @FXML
    private Label l_jeudi;
    @FXML
    private Label l_vendredi;
    @FXML
    private AnchorPane paneCalendar;
    @FXML
    private Pane b00;
    @FXML
    private Pane b50;
    @FXML
    private Pane b30;
    @FXML
    private Pane b10;
    @FXML
    private Pane b60;
    @FXML
    private Pane b40;
    @FXML
    private Pane b20;
    @FXML
    private Pane b01;
    @FXML
    private Pane b51;
    @FXML
    private Pane b31;
    @FXML
    private Pane b11;
    @FXML
    private Pane b61;
    @FXML
    private Pane b41;
    @FXML
    private Pane b21;
    @FXML
    private Pane b02;
    @FXML
    private Pane b52;
    @FXML
    private Pane b32;
    @FXML
    private Pane b12;
    @FXML
    private Pane b62;
    @FXML
    private Pane b42;
    @FXML
    private Pane b2;
    @FXML
    private Label b22;
    @FXML
    private Pane b03;
    @FXML
    private Pane b53;
    @FXML
    private Pane b33;
    @FXML
    private Pane b13;
    @FXML
    private Pane b63;
    @FXML
    private Pane b43;
    @FXML
    private Pane b23;
    @FXML
    private Pane b04;
    @FXML
    private Pane b54;
    @FXML
    private Pane b34;
    @FXML
    private Pane b14;
    @FXML
    private Pane b64;
    @FXML
    private Pane b44;
    @FXML
    private Pane b24;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
       
// ObservableList<String> list =FXCollections.observableArrayList();
   
                  // b00.getChildren().add();
               
            
           /* Label l = new Label("aaaaaaaa");
            paneCalendar.setLayoutX(i);
            paneCalendar.getChildren().add(p);*/
        //pour le nbr de mois   
   //     l_Month.setText(""+LocalDate.now().getDayOfMonth());
        
         //pour le jour de semaine   
    //    l_Month.setText(""+LocalDate.now().getDayOfWeek());
        
       //l_Month.setText(""+LocalDate.now());
       /* Pane p = new Pane();
            p.prefWidth(113);
            p.prefHeight(85);
             Label l = new Label("aaaaaaaa");
            p.getChildren().add(l);
            list.add(p);*/
    }    

    @FXML
    private void ActionFH(ActionEvent event) {
    }

    @FXML
    private void ActionFB(MouseEvent event) {
    }

    @FXML
    private void B_flechB(ActionEvent event) {
    }

    @FXML
    private void ActionToday(MouseEvent event) {
    }

    @FXML
    private void ActionDAILY(MouseEvent event) {
    }

    @FXML
    private void ActionWEEKLY(MouseEvent event) {
    }

    @FXML
    private void ActionMONTHLY(MouseEvent event) {
    }
    
}
