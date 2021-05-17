/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Categorie;
import tn.utils.DataSource;

/**
 *
 * @author rayen
 */
public class CategorieServices {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public CategorieServices() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterCategorie(Categorie categorie) throws SQLException {

        String req1 = "INSERT INTO `categorie` (`type_categorie`) "
                + "VALUES ('" + categorie.getType_categorie() + "');";
        ste.executeUpdate(req1);
        System.out.println("categorie ajoutée");
    }

    public void modifierCategorie(Categorie categorie) throws SQLException {
        String sql = "UPDATE categorie SET `type_categorie`=? "
                + " WHERE id=" + categorie.getId_categorie();
        PreparedStatement ste;
        try {
            ste = con.prepareStatement(sql);
            ste.setString(1, categorie.getType_categorie());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de categorie : " + categorie.getType_categorie() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerCategorie(Categorie categorie) {

        try {
            String req = "DELETE FROM `categorie` WHERE `categorie`.`id` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, categorie.getId_categorie());
            ste.executeUpdate();
            System.out.println("categorie supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Categorie> readAllCategories() throws SQLException {
        List<Categorie> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from categorie ");
        Categorie categorie = null;
        while (res.next()) {
            categorie = new Categorie(res.getInt(1), res.getString(2));
            list.add(categorie);
            System.out.println("all" + list);
        }
        return list;
    }

    public String getCategorieType(int id_categorie) throws SQLException {
        String req = "SELECT * from categorie where id='" + id_categorie + "'";
        ResultSet res = ste.executeQuery(req);
        String type_categorie = null;
        while (res.next()) {
            type_categorie = res.getString("type_categorie");
        }

        return type_categorie;
    }

    public int getIdCategorie(String type_categorie) throws SQLException {
        String req = "SELECT * from categorie where type_categorie='" + type_categorie + "'";
        ResultSet res = ste.executeQuery(req);
        int id_categorie = 0;
        while (res.next()) {
            id_categorie = res.getInt("id");
        }

        return id_categorie;
    }

}
