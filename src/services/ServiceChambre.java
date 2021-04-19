/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.IService;
import Utiles.database;
import entites.Chambre;
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
 * @author Amina
 */
public class ServiceChambre implements IService<Chambre> {
    Connection cnx;

    public ServiceChambre() {
        cnx=database.getInstance().getCon();
    }
    
     @Override

    public void ajouter(Chambre t) throws SQLException {
        String requete = "INSERT INTO chambre(num,  numetage,  nbrplace, service,  bloc,category_id)"
                + "VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getNum());
            pst.setInt(2, t.getNumetage());
           pst.setInt(3, t.getNbrplace());
            pst.setString(4, t.getService());
              pst.setString(5, t.getBloc());
               pst.setInt(6, t.getCategory_id());
            
            pst.executeUpdate();
            System.out.println("chambree ajoutee !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Chambre t) throws SQLException {
           try {
 PreparedStatement pt
                = cnx.prepareStatement("delete from chambre where num =?");
        pt.setInt(1, t.getNum());
        pt.executeUpdate();
            System.out.println("chambre Supprimée !");
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

        public void supprimer(int id_cat) throws SQLException {
           try {
 PreparedStatement pt
                = cnx.prepareStatement("delete from chambre where category_id =?");
        pt.setInt(1, id_cat);
        pt.executeUpdate();
            System.out.println("chambre Supprimée !");
              } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    @Override
    public void modifier(Chambre t) throws SQLException {
        try {
            PreparedStatement pt
                = cnx.prepareStatement("update chambre set numetage = ?,nbrplace= ?,service=?,bloc=? where num = ?");
        pt.setInt(1, t.getNumetage());
        pt.setInt(2, t.getNbrplace());
        pt.setString(3, t.getService());
        pt.setString(4, t.getBloc());
        pt.setInt(5, t.getNum());
        

        pt.executeUpdate();
            System.out.println("chambre modifiee !");
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Chambre> afficher() throws SQLException {
      
            List<Chambre> l = new ArrayList<Chambre>();
            PreparedStatement pt = cnx.prepareStatement("select * from chambre");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                l.add(t);
            }
            return l;
        
        
    }
        public List<Chambre> getTrier(String tri) throws SQLException {
        
        
          List<Chambre> l = new ArrayList<Chambre>();
            PreparedStatement pt = cnx.prepareStatement("select * from chambre ORDER BY "+tri);
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                  Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                l.add(t);
            }
            return l;
            }
    
    public List<Chambre> getTrierbyNum() throws SQLException {
        
        
          List<Chambre> l = new ArrayList<Chambre>();
            PreparedStatement pt = cnx.prepareStatement("select * from chambre ORDER BY num DESC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                  Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                l.add(t);
            }
            return l;
            }
   
    public List<Chambre> rechercherchambre(Chambre c) throws SQLException {
        
        
        List<Chambre> l = new ArrayList<Chambre>();
            PreparedStatement pt = cnx.prepareStatement("SELECT * FROM chambre where num like '%"+c.getNum()+ "%'");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                  Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                l.add(t);
            }
            return l;
            }
    
            public List<Chambre> rechercherchambreparservice(String n) throws SQLException {
                     
        List<Chambre> liste = new ArrayList<Chambre>();
        
            Statement stm = cnx.createStatement();

            PreparedStatement pt = cnx.prepareStatement( "SELECT * From chambre where service like '%" + n + "%'");

             ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                  Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                liste.add(t);
            }
            return liste;
            }
         public List<Chambre> getTrierbynbrplace() throws SQLException {
        
        
          List<Chambre> l = new ArrayList<Chambre>();
            PreparedStatement pt = cnx.prepareStatement("select * from chambre ORDER BY nbrplace ASC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                 String service = rs.getString(4);
                  String bloc = rs.getString(5);
                 
                 int category_id = rs.getInt(6);
                  Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id);
                l.add(t);
            }
            return l;
            }
   
        
        
        
        
        
        
        
        
        
            
  
        
        
        
        
        
   
    }
    
    

