/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Patient;
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
public class ServicePat {
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    
    
    
    public ServicePat() {
             cnx=Database.getInstance().getCon();

    }
    
    
    
    
    public List<Patient> afficher() throws SQLException {
        
      
        List<Patient> list=new ArrayList<Patient>();
          PreparedStatement pt = cnx.prepareStatement("Select * from patient");
          
            ResultSet rs = pt.executeQuery();
          
            while(rs.next()){
                int cin =rs. getInt(2);
                String nom = rs.getString(3);
                String prenom = rs.getString(4);
                String email = rs.getString(5);
                String password = rs.getString(6);

        
             Patient p = new Patient( cin,  nom, prenom,email,password );
                list.add(p);
            }
            return list;
        
}
    
    
    
    
    
     public Patient getByNomP(String nom) {
          Patient p = null;
         String requete = " select * from patient  where nom='"+nom+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            p = new Patient(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p ;
        
    }
     
       public Patient getById(int id) {
          Patient p = null;
         String requete = " select * from patient  where id='"+id+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            p = new Patient(res.getInt(1),res.getInt(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServicePat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p ;
        
    }

}
