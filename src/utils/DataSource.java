/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class DataSource {
     private static DataSource instance ;
     private Connection cnx ;
    public String user= "root" ;
    public String password = "";
    public String url ="jdbc:mysql://localhost:3306/pidevbd";
    
    private DataSource()
    {
        try 
        {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion etablie ");
        } 
        catch (SQLException ex) 
        {System.out.println(ex);
            //Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DataSource getInstance() {
        
        if (instance==null)
           instance = new DataSource();
        return instance ;
    }

    public Connection getCnx() {
        return cnx;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public static void setInstance(DataSource instance) {
        DataSource.instance = instance;
    }

    public void setCnx(Connection cnx) {
        this.cnx = cnx;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
}
