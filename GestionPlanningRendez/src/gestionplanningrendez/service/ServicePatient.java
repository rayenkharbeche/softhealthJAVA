/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.service;

import gestionplanningrendez.entity.Patient;
import gestionplanningrendez.entity.User;
import gestionplanningrendez.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServicePatient {
    Connection cnx = DataSource.getInstance().getCnx();
    
     public List<Patient> afficher() {
         List<Patient> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM patient";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Patient(rs.getInt(1), rs.getString("nom_patient")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     public List<String> afficherNom() {
         List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom_patient FROM patient";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("nom_patient"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     public int getByName(String nom){
         int id=0;
         try {
            String requete = "SELECT id FROM patient where nom_patient=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nom);
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
            String requete = "SELECT nom_patient FROM patient where id="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom=rs.getString("nom_patient");
            }
            return nom;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return nom;
     }
    
}
