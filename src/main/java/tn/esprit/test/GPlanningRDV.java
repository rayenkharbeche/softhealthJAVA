/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.test;


import tn.esprit.entities.Planning;
import tn.esprit.entities.RendezVous;
import tn.esprit.entities.User;
import tn.esprit.services.ServicePlanning;
import tn.esprit.services.ServiceRDV;
import java.sql.Date;
import java.text.SimpleDateFormat;


/**
 *
 * @author LENOVO
 */
public class GPlanningRDV {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        //Test pour le planning
            
     /*   ServicePlanning sp = new ServicePlanning();
        String date1 = "2021-04-14";
        java.sql.Date d1 = java.sql.Date.valueOf(date1);
        String date2 = "2021-04-18";
        java.sql.Date d2 = java.sql.Date.valueOf(date2);
      //  User s = new User("sahar", "sahar@esprit.tn");
        Planning t = new Planning ("Plan java 13", "pour la comparaison test de date", d1 , d2,2);
       // Planning t2 = new Planning (18,"Plan java 3", "test pour le modification", d1 , d2,4);
       // sp.ajouter(t);
      //  sp.afficher().forEach(System.out::println);
     //   sp.supprimer(18);
       // sp.modifier(t2);
        //sp.afficher().forEach(System.out::println);
        System.out.println("aaaaaaaaaaaaa!!!!");
       //sp.treePlanning().forEach(System.out::println);
        sp.rechercherPlanning("Plan");
       */
        System.out.println("§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§!"); 
        //Test pour les RendezVous
        ServiceRDV srdv = new ServiceRDV();
          
       // String date3 = "2021-04-13";
        //java.sql.Date d3 = java.sql.Date.valueOf(date1);
      
        //RendezVous r1 = new RendezVous(27,"Rendez Java 11", "pour le test de modification ", d3 ,3,4);
       //   srdv.ajouter(r1);
      // srdv.afficher().forEach(System.out::println);
      
      // srdv.supprimer(28);
       // srdv.modifier(r1);
       srdv.afficher().forEach(System.out::println);
       /*  System.out.println("aaaaaaaaaaaaa!!!!");
       srdv.treeRDV().forEach(System.out::println);
         
          srdv.afficher().forEach(System.out::println);
         
       srdv.rechercherRDV("Rendez");*/
     
       
    }
    
}
