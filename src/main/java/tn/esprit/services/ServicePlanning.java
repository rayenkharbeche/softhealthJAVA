/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import tn.esprit.entities.Planning;
import tn.esprit.entities.User;
import tn.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 *
 * @author LENOVO
 */
public class ServicePlanning implements IService <Planning> {
    Connection cnx = DataSource.getInstance().getConnection();

    //@Override
    public void ajouter(Planning t) {
         try {
             if (t.getDateDebut().compareTo(t.getDateFin())>0){
                 System.out.println("les date et incorrecte"); 
             }else{
                String requete = "INSERT INTO planning (personnel_id,nom_p,date_debut,date_fin,description_p) VALUES (?,?,?,?,?)";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setString(2, t.getNomP());
                pst.setString(5, t.getDescriptionP());
                pst.setDate(3, (Date) t.getDateDebut());
                pst.setDate(4, (Date) t.getDateFin());
                pst.setInt(1, t.getId_user());
                pst.executeUpdate();
                System.out.println("Planning ajoutée !");
             }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    //@Override
    public void supprimer(int id) {
        try {
            String req2 ="SELECT * FROM planning WHERE id="+ id;
            PreparedStatement pst2 = cnx.prepareStatement(req2);
            ResultSet rs = pst2.executeQuery();
            if (!rs.next()){
               System.out.println("Planning introuver !");  
            }
            else{
                String requete = "DELETE FROM planning WHERE id=?";
                PreparedStatement pst = cnx.prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("Planning supprimée !");  
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   // @Override
    public void modifier(Planning t) {
         try {
         //   String req2 ="SELECT * FROM planning WHERE id="+ id;
          //  PreparedStatement pst2 = cnx.prepareStatement(req2); 
            String requete = "UPDATE planning SET personnel_id=?,nom_p=?,date_debut=?,date_fin=?,description_p=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(5, t.getId());
            pst.setInt(1, t.getId_user());
            pst.setString(2, t.getNomP());
            pst.setDate(3, (Date) t.getDateDebut());
            pst.setDate(4, (Date) t.getDateFin());
            pst.setString(5, t.getDescriptionP());
            pst.executeUpdate();
            System.out.println("Planning modifiée !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

   // @Override
    public List<Planning> afficher() {
         List<Planning> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM planning";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               /* String requete2 = "SELECT * FROM rendez_vous where id="+ rs.getInt(2);
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();*/
                list.add(new Planning(rs.getInt(1), rs.getString("nom_p") , rs.getString("description_p"), rs.getDate("date_debut") , rs.getDate("date_fin") , rs.getInt(2)));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    public List <Planning> treePlanning(){
        List <Planning> list = new ArrayList<>();
        try {
            String requete = "SELECT * FROM planning";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
               /* String requete2 = "SELECT * FROM rendez_vous where id="+ rs.getInt(2);
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();*/
                list.add(new Planning(rs.getInt(1), rs.getString("nom_p") , rs.getString("description_p"), rs.getDate("date_debut") , rs.getDate("date_fin") ,rs.getInt(2)));
            }
             Collections.sort(list);
         /* TreeSet<Planning> planning = list.stream()
          .collect(Collectors.toCollection(()-> new TreeSet<Planning>((a,b)->b.getDateDebut().compareTo(a.getDateDebut()))));
          planning.forEach(System.out::println);*/

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;
  
    }
     public void rechercherPlanning(String nom) {
          List<Planning> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM planning";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                /*  String requete2 = "SELECT * FROM rendez_vous where id="+ rs.getInt(2);
                PreparedStatement pst2 = cnx.prepareStatement(requete2);
                ResultSet rs2 = pst2.executeQuery();*/
                list.add(new Planning(rs.getInt(1), rs.getString("nom_p") , rs.getString("description_p"), rs.getDate("date_debut") , rs.getDate("date_fin") ,rs.getInt(2) ));
            }
            list.stream().filter(e -> e.getNomP().startsWith(nom)).collect(Collectors.toList()).forEach(System.out::println);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public int getByName(String nom){
         int id=1;
         try {
            String requete = "SELECT id FROM planning where nom_p="+nom;
            PreparedStatement pst = cnx.prepareStatement(requete);
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
     public String getByID (int id){
         String nom="";
         try {
            String requete = "SELECT nom_p FROM planning where id="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom=rs.getString("nom_p");
            }
            return nom;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return nom;
     }
     
     public String getNomP(int id_P) throws SQLException {
        String req = "SELECT * from planning where id_categorie='" + id_P + "'";
        PreparedStatement pst = cnx.prepareStatement(req);
        ResultSet res = pst.executeQuery();
        String type_categorie = null;
        while (res.next()) {
            type_categorie = res.getString("nom_p");
        }

        return type_categorie;
    }
}
