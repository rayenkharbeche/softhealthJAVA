/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.DossierService;
import Services.FichierService;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import entities.Dossier;
import entities.Fichier;
import java.sql.Connection;
import java.sql.Date;
import java.time.*;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataSource;

/**
 *
 * @author Asus
 */
public class Pidev extends Application {
    
      @Override
    public void start(Stage Stage) throws Exception {
        //Parent root =FXMLLoader.load(getClass().getResource("Fichier.fxml"));
        Parent root =FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage.setScene(scene);
        Stage.show();
        
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLDataException, SQLException {
        
      launch(args);
     //java.util.Scanner entree = new java.util.Scanner(System.in);
     //System.out.println("Donner id dossier");
     //int id_dossier = entree.nextInt();
     //System.out.println("Donner id patient");
     //int id_patient = entree.nextInt();
     //System.out.println("Donner description fichier");
     //String des = entree.next();
     
        
        
        // TODO code application logic here
         //DataSource cnx = DataSource.getInstance();
         
         //Fichier f = new Fichier(id_dossier,id_patient,des);
          
         //String str="2015-03-31"; 
         //String str1="2021-03-31"; 
         
         //Date d1 = Date.valueOf(str);
         //Date d2 = Date.valueOf(str1);
        //Dossier r = new Dossier(26,"docPatient",d1,1);
        //Dossier k = new Dossier("docPa2",d2,2);
         //Fichier e = new Fichier(45,id_dossier,id_patient,des);
         
         //FichierService fs = new FichierService ();
         //DossierService ds = new DossierService ();
        //Fichier d  = new Fichier("test5");
         //fs.addFichier(f);
         //System.out.println(fs.getAllFichier());
          //System.out.println(fs.FichierSelonDes()); 
          //System.out.println(fs.FichierStartWithT()); 
         //fs.ModifierFichier(e);
         //fs.deleteFichier(13);
         
         //ds.addDossier(r);
         //ds.ModifierDossier(r);
         //System.out.println(ds.DossierStartWithP());
        //System.out.println(ds.dossierSelonDes());
        //System.out.println(ds.getAllDossier());
        //ds.deleteDossier(27);
        


       

//System.out.println(ds.dossierSelonIdPatient());
      
                
        
       //System.out.println(ds.dossierSelonDateCreation());
         //fs.addFichier(e);
         
         
        
               //fs. ModifierFichier(e);
             //System.out.println(fs.afficher());
          
          //fs.ModifierFichier(e);
         
         
          
        
       }

  
    }  

         
    

