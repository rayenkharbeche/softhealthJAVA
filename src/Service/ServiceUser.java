/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.User;
import Utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASSOUMA
 */
public class ServiceUser {
     private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    
    
    
    public ServiceUser() {
             cnx=Database.getInstance().getCon();

    }
    
    
    
    
    public List<User> afficher() throws SQLException {
        
      
        List<User> list=new ArrayList<User>();
          PreparedStatement pt = cnx.prepareStatement("Select * from user");
          
            ResultSet rs = pt.executeQuery();
          
            while(rs.next()){
              
                String username = rs.getString(2);
                int tel = rs.getInt(3);
                String role = rs.getString(4);
        
             User usr = new User( username,  tel, role );
                list.add(usr);
            }
            return list;
        
}
    
    
    
    
      public User getByUserName(String nom) {
         User u = null;
         String requete = " select * from user  where username='"+nom+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            u = new User(res.getInt(1),res.getString(2),res.getInt(3),res.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u ;
        
    }
 public User getByUserId(int id) {
         User u = null;
         String requete = " select * from user  where id='"+id+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            u = new User(res.getInt(1),res.getString(2),res.getInt(3),res.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u ;
        
    }
    
    
}
