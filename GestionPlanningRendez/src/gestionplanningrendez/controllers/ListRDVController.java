/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.controllers;


import gestionplanningrendez.entity.RendezVous;
import gestionplanningrendez.entity.get;
import gestionplanningrendez.service.ServicePatient;


import gestionplanningrendez.service.ServiceRDV;
import gestionplanningrendez.service.ServiceUser;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;

import java.util.ResourceBundle;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author LENOVO
 */
public class ListRDVController implements Initializable {

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
    private TextField col_search;
    @FXML
    private TableView<RendezVous> table;
    @FXML
    private TableColumn<RendezVous, String> cnomR;
    @FXML
    private TableColumn<RendezVous, String> cdescriptionR;
    @FXML
    private TableColumn<RendezVous, String> cdate;
    @FXML
    private TableColumn<RendezVous, String> cPl;
    @FXML
    private TableColumn<RendezVous, String> cuser;
     @FXML
    private TableColumn<RendezVous, String> cpatient;
    @FXML
    private TableColumn<RendezVous, RendezVous> cdelate;
    @FXML
    private TableColumn<RendezVous, RendezVous> cupdate;
    
     ServiceRDV sp =new ServiceRDV();
     ServiceUser su=new ServiceUser();
     ServicePatient spt = new ServicePatient();
     
    final ObservableList<RendezVous>  data = FXCollections.observableArrayList(sp.afficher());
    @FXML
   
   
    ObservableList<RendezVous> oblist ;  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  ServiceRDV sp= new ServiceRDV();
        oblist= FXCollections.observableArrayList(sp.afficher());
       // cidR.setCellValueFactory(new PropertyValueFactory<> ("id"));
        cnomR.setCellValueFactory(new PropertyValueFactory<> ("nomRDV"));
        cdescriptionR.setCellValueFactory(new PropertyValueFactory<> ("descriptionRDV"));
        cdate.setCellValueFactory(new PropertyValueFactory<> ("dateRDV"));
        cPl.setCellValueFactory(new PropertyValueFactory<> ("id_planning"));
        cuser.setCellValueFactory(new PropertyValueFactory<> ("id_user"));
        cpatient.setCellValueFactory(new PropertyValueFactory<> ("id_patient"));
       // caction.setCellFactory((Callback<TableColumn<Planning, String>, TableCell<Planning, String>>)delate);
        cdelate.setCellFactory((TableColumn<RendezVous, RendezVous> param) -> {
            return new TableCell<RendezVous, RendezVous>() {
                private final Button deleteButton = new Button("   Delete   ");
                @Override
                protected void updateItem(RendezVous rdv, boolean empty) {
                    super.updateItem(rdv, empty);
                    
                   // if (patient == null) {
                   //     setGraphic(null);
                    //    return;
                   // }
                   
                    deleteButton.setOnAction(event -> {
                    RendezVous getRDV = getTableView().getItems().get(getIndex());
                //    System.out.println(getPlanning.getNomP() + "   " + getPlanning.getDescriptionP());
                   // ServicePlanning sp= new ServicePlanning();
                    sp.supprimer(getRDV.getId());
                    JOptionPane.showMessageDialog(null, "Rendez-Vous Supprimer !");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ListRendez.fxml"));
        
                     Parent root;
                        try {
                            root = loader.load();
                            col_search.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(ListRDVController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    });

                    setGraphic(deleteButton);//<<<---------------add button 1
                    }
                    };
                    });
        cupdate.setCellFactory((TableColumn<RendezVous, RendezVous> param) -> {
            return new TableCell<RendezVous, RendezVous>() {
                private final Button editButton = new Button("    Edit    ");
             
                @Override
                protected void updateItem(RendezVous rdv, boolean empty) {
                    super.updateItem(rdv, empty);
                 
                    editButton.setOnAction(event -> {
                    RendezVous getRDV = getTableView().getItems().get(getIndex());
                //    System.out.println(getPlanning.getNomP() + "   " + getPlanning.getDescriptionP());
                    //FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierRendez.fxml"));
        
                     Parent root;
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierRDV.fxml"));
                            root = loader.load();
                            col_search.getScene().setRoot(root);
                            ModifierRDVController dpc = loader.getController();
                            
                            dpc.setTnomP(getRDV.getNomRDV());
                            dpc.setTdescriptionP(getRDV.getDescriptionRDV());
                            dpc.setTdateD((Date) getRDV.getDateRDV());
                          
                            
                            String user=su.getByID(getRDV.getId_user());
                           dpc.setTuser(user);
                           dpc.setTpatient(spt.getByID(getRDV.getId_patient()));
                           dpc.setRDV(getRDV.getNomRDV(), getRDV.getDescriptionRDV(), (Date) getRDV.getDateRDV());
                        } catch (IOException ex) {
                            Logger.getLogger(ListPlanningController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    setGraphic(editButton);//<<------------------add button 2
                    }
                    };
                    });      
        table.setItems(oblist);
    }    

    @FXML
    private void ActionHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Home.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
    }

    @FXML
    private void ActionListR(ActionEvent event) {
    }

    @FXML
    private void ActionCalendar(ActionEvent event) {
    }

    @FXML
    private void ActionAjout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjoutRDV.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
        
    }

    @FXML
    private void SearchPlanning() {
         col_search.setFont(Font.font("Sensif",15));
     FilteredList<RendezVous> filteredData = new FilteredList<>(data, e -> true);
        col_search.setOnKeyReleased(e ->{
            col_search.textProperty().addListener((ObservableValue, oldValue, newValue)->{
                filteredData.setPredicate((Predicate<? super RendezVous>) rdv->{
                    if (newValue == null || newValue.isEmpty()){
                        return true; 
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (rdv.getNomRDV().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                    //else if()
                   return false;
                });
            });
        });
        SortedList<RendezVous> sortedData = new SortedList<> (filteredData);
        sortedData.comparatorProperty().bind(table.comparatorProperty());
       table.setItems(sortedData);    
    }
    
    @FXML
    private void ActionTree(ActionEvent event) {
        
         Btree.setFont(Font.font("Sensif",15));
     TreeSet<RendezVous> treeData = data.stream().collect(Collectors.toCollection(()->new TreeSet<RendezVous>((a,b)->a.getDateRDV().compareTo(b.getDateRDV()))));
     ObservableList<RendezVous> ob = FXCollections.observableArrayList(treeData);
        SortedList<RendezVous> sortedData = new SortedList<> (ob);
       sortedData.comparatorProperty().bind(table.comparatorProperty());
        table.setItems( sortedData);       
        
    }
    
     @FXML
    private void ActionStatistique(ActionEvent event) throws Exception {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Statistique.fxml"));
        
        Parent root = loader.load();
        col_search.getScene().setRoot(root);
    
      /*  XYSeries s =  new XYSeries("data");
        get x = new get();
    
        try {
            s.add(1,x.Jan());
            s.add(2,x.Feb());
            s.add(3,x.Mars());
            s.add(4,x.Avril());
            s.add(5,x.Mai());
            s.add(6,x.Juin());
            s.add(7,x.Juillet());
            s.add(8,x.Aout());
            s.add(9,x.Sept());
            s.add(10,x.Oct());
            s.add(11,x.Nouvbre());
            s.add(12,x.Dec());
           
                  
        } catch (Exception ex) {
            Logger.getLogger(ListRDVController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         XYSeriesCollection datest;
         datest = new XYSeriesCollection(s);
         JFreeChart ch = ChartFactory.createXYBarChart("flux de revenus", "les Mois", false,"Revenue", datest, PlotOrientation.VERTICAL,false,false,false);
         
         ChartFrame frame = new ChartFrame("Statistique", ch);
         frame.pack();
         frame.setVisible(true);   */
     
    /* BorderPane bp = new BorderPane();
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
        }*/
       // Scene sc = new Scene(bp,800,500);
      //  primaryStage.setScene(sc);
       // primaryStage.setTitle("Statistique");
       // primaryStage.show(); 
        
        
    }
    
}
