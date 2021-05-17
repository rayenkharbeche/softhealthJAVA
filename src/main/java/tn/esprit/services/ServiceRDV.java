/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entities.Planning;
import tn.esprit.entities.RendezVous;
import tn.esprit.entities.User;
import tn.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author LENOVO
 */
public class ServiceRDV implements IService <RendezVous> {

     Connection cnx = DataSource.getInstance().getConnection();
    //@Override
    public void ajouter(RendezVous t) {
     try {
         //if (t.getDateRDV().equals(SystemClockFactory.get))
            String requete = "INSERT INTO rendez_vous (plannings_id,user_id,nom_rdv,description,date_rdv,patient_id) VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(2, t.getId_user());
            pst.setInt(1, t.getId_planning());
            pst.setString(3, t.getNomRDV());
            pst.setString(4, t.getDescriptionRDV());
            pst.setDate(5, (Date) t.getDateRDV());
            pst.setInt(6, t.getId_patient());
            pst.executeUpdate();
            System.out.println("Rendez_vous ajoutée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   // @Override
    public void supprimer(int id) {
         try {
             String req2 ="SELECT * FROM rendez_vous WHERE id="+ id;
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            ResultSet rs = pst2.executeQuery();
            if (!rs.next()){
               System.out.println("RendezVous introuver !");  
            }
            else{
                String requete = "DELETE FROM rendez_vous WHERE id=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("RendezVous supprimée !");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   // @Override
    public void modifier(RendezVous t) {
         try {
            String requete = "UPDATE rendez_vous SET plannings_id=?, user_id=?, nom_rdv=?,description=?,date_rdv=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(6, t.getId());
            pst.setInt(1, t.getId_planning());
            pst.setInt(2, t.getId_user());
            pst.setString(3, t.getNomRDV());
            pst.setString(4, t.getDescriptionRDV());
            pst.setDate(5, (Date) t.getDateRDV());
            pst.executeUpdate();
            System.out.println("RendezVous modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

  //  @Override
    public List<RendezVous> afficher() {
        List<RendezVous> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM rendez_vous";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* String requete2 = "SELECT * FROM planning where id="+ rs.getInt("plannings_id");
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();
                String requete3 = "SELECT * FROM user where id="+ rs.getInt("user_id");
                PreparedStatement pst3 = cnx.prepareStatement(requete3);
                ResultSet rs3 = pst3.executeQuery();
                (User)rs3.getObject(1),(Planning)rs2.getObject(1)*/
                list.add(new RendezVous(rs.getInt(1), rs.getString("nom_rdv") , rs.getString("description"), rs.getDate("date_rdv") ,rs.getInt("user_id"), rs.getInt("plannings_id"), rs.getInt("patient_id") ));
            }

        } catch (SQLException ex) {
            System.out.println("probleme service");
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
     public List<RendezVous> treeRDV(){
        List <RendezVous> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM rendez_vous";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* String requete2 = "SELECT * FROM planning where id="+ rs.getInt("plannings_id");
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();
                String requete3 = "SELECT * FROM user where id="+ rs.getInt("iser_id");
                PreparedStatement pst3 = cnx.prepareStatement(requete3);
                ResultSet rs3 = pst3.executeQuery();*/
                list.add(new RendezVous(rs.getInt(1), rs.getString("nom_rdv") , rs.getString("description"), rs.getDate("date_rdv") ,rs.getInt("user_id"),rs.getInt("plannings_id"),rs.getInt("patient_id")));
            }
             Collections.sort(list);
             

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
       
       return list; 
    }
     public void rechercherRDV(String nom) {
          List<RendezVous> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM rendez_vous";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /* String requete2 = "SELECT * FROM planning where id="+ rs.getInt("plannings_id");
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();
                String requete3 = "SELECT * FROM user where id="+ rs.getInt("iser_id");
                PreparedStatement pst3 = cnx.prepareStatement(requete3);
                ResultSet rs3 = pst3.executeQuery();*/
                list.add(new RendezVous(rs.getInt(1), rs.getString("nom_rdv") , rs.getString("description"), rs.getDate("date_rdv") , rs.getInt("user_id"),rs.getInt("plannings_id"), rs.getInt("patient_id")));
            }
            list.stream().filter(e -> e.getNomRDV().startsWith(nom)).collect(Collectors.toList()).forEach(System.out::println); 
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
 
    }
     
     public int findByUser (int user){
         int id=0;
          try {
         String requete = "SELECT id FROM planning where personnel_id=?";
          PreparedStatement pst;
        
             pst = cnx.prepareStatement(requete);
             pst.setInt(1, user);
             ResultSet rs = pst.executeQuery();
        
         while (rs.next()){
             id=rs.getInt("id");
             //System.out.println(rs.getArray(id));
            
         }
         
         } catch (SQLException ex) {
             System.out.println("requete errreuuuuur");
             System.err.println(ex.getMessage());
             Logger.getLogger(ServiceRDV.class.getName()).log(Level.SEVERE, null, ex);
         }
          
         return id;
     }
      public String getByID (int id){
         String nom="";
         try {
            String requete = "SELECT nom_rdv FROM rendez_vous where id="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom=rs.getString("nom_rdv");
            }
            return nom;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return nom;
     }
      
      public int getID(RendezVous t){
          int id=0;
           try {
            String requete = "SELECT id FROM rendez_vous WHERE nom_rdv=? AND date_rdv=? AND description=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getNomRDV());
            pst.setDate(2, (Date) t.getDateRDV());
            pst.setString(3, t.getDescriptionRDV());
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id=rs.getInt("id");
            }
            return id;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
          
          
          return id;
      }
    
}
