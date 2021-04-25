/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.service;

import gestionplanningrendez.entity.User;
import gestionplanningrendez.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class ServiceUser {
     Connection cnx = DataSource.getInstance().getCnx();
     //private Statement ste;
    
     public List<User> afficher() {
         List<User> list = new ArrayList<>();

        try {
            String requete = "SELECT * FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new User(rs.getInt(1), rs.getString("nom_user") , rs.getString("email")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     public List<String> afficherNom() {
         List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom_user FROM user";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("nom_user"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
     
     public int getByName(String nom){
         int id=0;
         try {
            String requete = "SELECT id FROM user where nom_user=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, nom);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id=rs.getInt("id");
                System.out.println("serv"+rs.getInt("id"));
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
            String requete = "SELECT nom_user FROM user where id="+id;
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                nom=rs.getString("nom_user");
            }
            return nom;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return nom;
     }
      
      public Integer getUserByUserName(String username) throws SQLException{
         User user = new User();

        String req = "SELECT * from user where nom_user='" + username + "'";
        PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet res = ste.executeQuery();
        while (res.next()) {
          //  user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
           user = new User(res.getInt(1), res.getString(2), res.getString(3));
        }
        System.out.println(user);
        return user.getId();

    }
      public User getUserById(int id_user) throws SQLException {
        User user = new User();

        String req = "SELECT * from user where id='" + id_user + "'";
         PreparedStatement ste = cnx.prepareStatement(req);
        ResultSet res = ste.executeQuery();
        while (res.next()) {
           // user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
           user = new User(res.getInt(1), res.getString(2), res.getString(3));
        }
        System.out.println(user);
        return user;

    }
    
}
