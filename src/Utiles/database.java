/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import soft.Soft;

/**
 *
 * @author Amina
 */
public class database { 
    
     private static String url="jdbc:mysql://localhost/soft";
     private static String user="root";
      private static String pwd="";
      
    static database instance;
    private Connection con;

  private database() {
        try {
            // TODO code application logic here
           con = DriverManager.getConnection(url,user,pwd);
            System.out.println("connection etablie");
        } catch (SQLException ex) {
            Logger.getLogger(Soft.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
        
    }
    public static database getInstance(){
        if (instance==null)
            instance=new database();
        return instance;
    }

    public Connection getCon() {
        return con;
    }
    
    
}
