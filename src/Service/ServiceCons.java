/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Consultation;
import Utils.Database;
import java.sql.Connection;
import java.sql.Date;
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
public class ServiceCons {
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
    
    
    
    public ServiceCons() {
             cnx=Database.getInstance().getCon();

    }
    
    
     public List<Consultation> afficher() throws SQLException {
        
      
        List<Consultation> list=new ArrayList<Consultation>();
          PreparedStatement pt = cnx.prepareStatement("Select * from consultation");
          
            ResultSet rs = pt.executeQuery();
          
            while(rs.next()){
                int num_c =rs. getInt(2);
                Date date_c = rs.getDate(3);
                String text = rs.getString(4);
             

        
             Consultation cons = new Consultation( num_c,  date_c, text );
                list.add(cons);
            }
            return list;
        
}
    
    
    
    
    
    
      public Consultation getByDate(String date) {
          Consultation c = null;
         String requete = " select * from consultation  where date_c='"+date+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            c = new Consultation(res.getInt(1),res.getInt(2),res.getDate(3),res.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c ;
        
    }
      
      
        public Consultation getById(int id) {
          Consultation c = null;
         String requete = " select * from consultation  where id='"+id+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            c = new Consultation(res.getInt(1),res.getInt(2),res.getDate(3),res.getString(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceCons.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c ;
        
    }
    
    
}
