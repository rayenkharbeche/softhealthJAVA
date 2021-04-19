/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.IService;
import Utiles.database;
import entites.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Amina
 */
public class ServiceCategory implements IService<Category> {
     Connection cnx;

    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet res ;
    public ServiceCategory() {
        cnx=database.getInstance().getCon();
    }
    
    
  
    public void ajouter(Category t) throws SQLException {
        String requete = "INSERT INTO category(id,nom,description,colors)"
                + "VALUES (?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.setString(2, t.getNom());
            pst.setString(3, t.getDescription());
            pst.setString(4, t.getColor());
            
            pst.executeUpdate();
            System.out.println("categorie ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Category t) throws SQLException {
        String requete = "DELETE FROM category WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("category Supprimée !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Category t) throws SQLException {
        String requete = "UPDATE category SET nom=?,description=?,colors=?  WHERE id=?";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(4, t.getId());
            pst.setString(1, t.getNom());
            pst.setString(2, t.getDescription());
            pst.setString(3, t.getColor());
           
            pst.executeUpdate();
            System.out.println("catego Modfié !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }


    @Override
    public List<Category> afficher() throws SQLException {

        List<Category> l = new ArrayList<Category>();
        PreparedStatement pt = cnx.prepareStatement("select * from category");

        ResultSet rs = pt.executeQuery();
        while (rs.next()) {

            Category p = new Category(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4));
            l.add(p);
        }
        return l;
        

    }
    
       public Category getByNom(String nom) {
          Category a = null;
         String requete = " select* from category  where nom='"+nom+"'" ;
        try {
           
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Category(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
     
       
       public Category getById(int id) {
          Category a = null;
         String requete = " select * from category  where id='"+id+"'" ;
        try {
           
            ste = cnx.createStatement();
            res=ste.executeQuery(requete);
            if (res.next())
            {a=new Category(res.getInt(1),res.getString(2),res.getString(3),res.getString(4));}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
     
    }






  