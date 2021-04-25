/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import IServices.IService;
import Utiles.database;
import entites.Category;
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
        ServiceCategory scat = new ServiceCategory();    

     @Override

    public void ajouter(Chambre t) throws SQLException {
        String requete = "INSERT INTO chambre(num,  numetage,  nbrplace, service,  bloc,category_id,etat,traitement)"
                + "VALUES (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, t.getNum());
            pst.setInt(2, t.getNumetage());
           pst.setInt(3, t.getNbrplace());
            pst.setString(4, t.getService());
              pst.setString(5, t.getBloc());
               pst.setInt(6, t.getCategory_id());
               pst.setString(7, t.getEtat());
               pst.setString(8, t.getTraitement());
            
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

    public void supprimer_id(int id) throws SQLException {
           try {
 PreparedStatement pt
                = cnx.prepareStatement("delete from chambre where num =?");
        pt.setInt(1, id);
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

        public void modifierChambre(Chambre t) throws SQLException {
        try {
            PreparedStatement pst = cnx.prepareStatement("update chambre set numetage = ?,nbrplace= ?,service=?,bloc=?,category_id=?,etat=?,traitement=? where num = ?");
            pst.setInt(1, t.getNumetage());
            pst.setInt(2, t.getNbrplace());
            pst.setString(3, t.getService());
            pst.setString(4, t.getBloc());
            pst.setInt(5, t.getCategory_id());
            pst.setString(6, t.getEtat());
            pst.setString(7, t.getTraitement());
            pst.setInt(8, t.getNum());
            pst.executeUpdate();
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
                                

               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
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
                 String etat = rs.getString(7);
                 String traitement = rs.getString(8);
               
                
             Chambre t = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,etat,traitement);
                l.add(t);
            }
            return l;
            }
   
          public Chambre getById(String id) throws SQLException {
          Chambre a = null;
            PreparedStatement pt = cnx.prepareStatement("select* from chambre where num='"+id+"'");
        try {
                       ResultSet rs = pt.executeQuery();

            if (rs.next())
            {
                int num = rs.getInt(1);
                int numetage = rs.getInt(2);
                int nbrplace = rs.getInt(3);
                String service = rs.getString(4);
                String bloc = rs.getString(5);
                int category_id = rs.getInt(6);
                String etat = rs.getString(7);
                String traitement = rs.getString(8);
                            Category cat = new Category();
                 cat = scat.getById(category_id);
                 
             a = new Chambre( num, numetage,  nbrplace,  service,  bloc,category_id,cat.getNom(),etat,traitement);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
                  
          
    public int calculer(String id) {
          int l = 0 ;
         String requete = "SELECT COUNT(*) FROM chambre WHERE category_id="+id ;
        try {
           Statement st =cnx.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println(l);}
        } catch (SQLException ex) {
            Logger.getLogger(ServiceChambre.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }
    }
    
    

