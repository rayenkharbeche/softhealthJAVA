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
import tn.esprit.entities.User;
import tn.utils.DataSource;

/**
 *
 * @author Rihem
 */
public class UserService {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public UserService() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void inscrire(User user) throws SQLException {

        String req1 = "INSERT INTO `user` (`email`,`roles`,`password`,`cin`, `username`) "
                + "VALUES ('" + user.getEmail() + "', '" + user.getRoles()
                + "', '" + user.getPassword() + "', '" + user.getCin() + "', '" + user.getUsername() + "');";
        ste.executeUpdate(req1);
        System.out.println("Utilisateur ajouté");
    }

    public void modifierUser(User user) throws SQLException {
        String sql = "UPDATE user SET `email`=?,`roles`=?,`password`=?,`cin`=?, `username`=? "
                + " WHERE id=" + user.getId();
        PreparedStatement ste;
        try {
            ste = con.prepareStatement(sql);

            ste.setString(1, user.getEmail());
            ste.setString(2, user.getRoles());
            ste.setString(3, user.getPassword());
            ste.setInt(4, user.getCin());
            ste.setString(5, user.getUsername());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de l'utilisateur : " + user.getUsername() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerUser(User user) {

        try {
            String req = "DELETE FROM `user` WHERE `user`.`id` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, user.getId());
            ste.executeUpdate();
            System.out.println("Utilisateur supprimé");

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<User> readAll() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from user ");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
            list.add(user);
            System.out.println("all" + list);
        }
        return list;
    }

    public String getUserRole(int id_user) throws SQLException {
        String req = "SELECT roles from user where id='" + id_user + "'";
        ResultSet res = ste.executeQuery(req);
        String role = null;

        while (res.next()) {
            role = res.getString("roles");

        }

        return role;
    }

    public List<User> readAllUserByRole(String role) throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from user WHERE roles='" + role + "'");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
            list.add(user);
        }
        System.out.println("all" + list);

        return list;
    }

    public User authentifier(String username, String pwd) throws SQLException {
        String req = "select * from user WHERE username=? and password=?";
        PreparedStatement steprep = con.prepareStatement(req);
        steprep.setString(1, username);
        steprep.setString(2, pwd);

        ResultSet res = steprep.executeQuery();

        User user = null;
        if (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
        }

        return user;
    }

    public List<User> TrierParUserName() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from user ORDER BY username ASC");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
            list.add(user);
        }
        System.out.println(list);
        return list;
    }

    public List<User> TrierParCIN() throws SQLException {
        List<User> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from user ORDER BY cin ASC");
        User user = null;
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
            list.add(user);
        }
        System.out.println(list);
        return list;
    }

    public User getUserById(int id_user) throws SQLException {
        User user = new User();

        String req = "SELECT * from user where id='" + id_user + "'";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            user = new User(res.getInt(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5), res.getString(6));
        }
        System.out.println(user);
        return user;

    }
}
