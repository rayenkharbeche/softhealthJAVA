/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Médicament;
import IServices.MediServices;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Utils.Database;
import java.sql.Statement;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author ASSOUMA
 */


public class ServiceMedic implements MediServices  <Médicament> {
    
    private Connection cnx;
    private Statement stmt;
    private PreparedStatement pst;
    private ResultSet res;
     
   public ServiceMedic() {
        
     cnx=Database.getInstance().getCon();
    }
     

   

  
     
    @Override
    public void ajouter(Médicament m) throws SQLException{
      
        String req = "INSERT INTO medicament (code,name,categorie_id,prix,stock)" +" VALUES (?,?,?,?,?)";
        try{
            
             PreparedStatement pst = cnx.prepareStatement(req);
            
            
            pst.setInt(1,m.getCode());
            pst.setString(2,m.getName());
            pst.setInt(3,m.getCategorie_id());
            pst.setInt(4,m.getPrix());
            pst.setInt(5,m.getStock());
           
            pst.executeUpdate();
            System.out.println("Medicament added successfully");
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
    
  
  
   public boolean supprimer(int code) throws SQLException  {
       
        String sql="delete from medicament where code= ? ";
				
		try {
                    pst=cnx.prepareStatement(sql);
                    pst.setInt(1, code);
                    pst.execute();
                System.out.println("Médicament supprimé avec succé");
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			return false;
		}
    }
			
    
  
@Override
    public boolean modifier(Médicament m) throws SQLException {

        PreparedStatement pst
                = cnx.prepareStatement("update medicament set code= ? , name= ?, categorie_id= ? , prix= ? , stock = ?  where id= ?");    
         try {
            pst.setInt( 1,m.getCode());
            pst.setString(2,m.getName());
            pst.setInt(3,m.getCategorie_id());
            pst.setInt(4,m.getPrix());
            pst.setInt(5,m.getStock());
            pst.setInt(6, (int) m.getId());

            pst.executeUpdate();
            System.out.println("medicament updated");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }

 @Override
    public List<Médicament> afficher() throws SQLException {
        
      
        List<Médicament> list=new ArrayList<Médicament>();

          PreparedStatement pt = cnx.prepareStatement("Select  m.id, m.code, m.name, cat.nom, m.prix, m.stock from medicament m join Categorie cat where m.categorie_id=cat.id");
          
          
            ResultSet rs = pt.executeQuery();
          
            while(rs.next()){
              
                int code = rs.getInt(2);
                String name = rs.getString(3);
                 String categorie = rs.getString(4);
                int prix = rs.getInt(5);
                int stock = rs.getInt(6);

   
                       
             Médicament m = new Médicament( code,  name, categorie,  prix, stock );
             
                list.add(m);
            }
            return list;
        
}
    
    
    
    
       public List<Médicament> findByName(String name) {
   
           String sql="Select* from medicament";
         List<Médicament> list =new ArrayList<>();
         try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                list.add(new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6)));
            }
                    return list.stream().filter(x->x.getName().equals(name)).collect(Collectors.toList());
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        
        }    }



   
    public Médicament findById(int id) {
        String sql="Select* from medicament where id="+id+";";
        Médicament m=null;
        try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
                m= new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6));
            }
            return m; 
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
      public Médicament findByIdd(String name ) throws SQLException {
              
          String sql="Select id from medicament where name="+name+";";
           Médicament m=null;
        
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
                m= new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6));
            }
            return m; 
        

          
          }
         

  
    public List<Médicament> findByCategory(int categorie_id) {
        
         String sql="Select* from medicament where categorie_id='"+categorie_id+"'";
         
         List<Médicament> li =new ArrayList<>();
         try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                li.add(new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6)));
            }
            return li;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        
        }     }

    
    
     
     
    public Médicament findByCode(int code) {
        
        String sql="Select * from medicament where code='"+code+"'";
        Médicament m=null;
         try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            if(res.next()){
                m= new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6));
            }
            return m; 
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }    }


 
 public List<Médicament> findByStock(int stock) {
        
         String sql="Select* from medicament where stock='"+stock+"'";
         List<Médicament> l =new ArrayList<>();
         try {
            stmt=cnx.createStatement();
            res=stmt.executeQuery(sql);
            while(res.next()){
                l.add(new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6)));
            }
            return l;
            
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        
        }     }
 

    public List<Médicament> ListMediOrderByASC() throws SQLException {
         
        List<Médicament> list = new ArrayList<Médicament>();
            PreparedStatement pt = cnx.prepareStatement("select * from medicament ORDER BY stock ASC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
               int id = rs.getInt(1);
                int code = rs.getInt(2);
                String name = rs.getString(3);
                 int categorie_id = rs.getInt(4);
                int prix = rs.getInt(5);
                int stock = rs.getInt(6);
                
                                        

             Médicament me = new Médicament(id,code, name, categorie_id, prix, stock );
                list.add(me);
            }
            return list;
            }
    
     public List<Médicament> ListMediOrderByDESC() throws SQLException {
         
        List<Médicament> list = new ArrayList<Médicament>();
            PreparedStatement pt = cnx.prepareStatement("select * from medicament ORDER BY stock DESC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
               int id = rs.getInt(1);
                int code = rs.getInt(2);
                String name = rs.getString(3);
                 int categorie_id = rs.getInt(4);
                int prix = rs.getInt(5);
                int stock = rs.getInt(6);
                
                                        

             Médicament me = new Médicament(id,code, name, categorie_id, prix, stock );
                list.add(me);
            }
            return list;
            }

      public List<Médicament> ListPrixASC() throws SQLException {
         
        List<Médicament> list = new ArrayList<Médicament>();
            PreparedStatement pt = cnx.prepareStatement("select * from medicament ORDER BY prix ASC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
               int id = rs.getInt(1);
                int code = rs.getInt(2);
                String name = rs.getString(3);
                 int categorie_id = rs.getInt(4);
                int prix = rs.getInt(5);
                int stock = rs.getInt(6);
                
                                        

             Médicament me = new Médicament(id,code, name, categorie_id, prix, stock );
                list.add(me);
            }
            return list;
            }
    
     public List<Médicament> ListPrixDESC() throws SQLException {
         
        List<Médicament> list = new ArrayList<Médicament>();
            PreparedStatement pt = cnx.prepareStatement("select * from medicament ORDER BY prix DESC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
               int id = rs.getInt(1);
                int code = rs.getInt(2);
                String name = rs.getString(3);
                int categorie_id = rs.getInt(4);
                int prix = rs.getInt(5);
                int stock = rs.getInt(6);
                
                                        

             Médicament me = new Médicament(id,code, name, categorie_id, prix, stock );
                list.add(me);
            }
            return list;
            }
     
     public Médicament getByMedic(String name) {
          Médicament m = null;
         String requete = " select * from medicament  where name='"+name+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            m = new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m ;
        
    }
     
     
      public Médicament getById(int id) {
          Médicament m = null;
         String requete = " select * from medicament  where id='"+id+"'" ;
        try {
           
            stmt = cnx.createStatement();
            res=stmt.executeQuery(requete);
            if (res.next()){
            m = new Médicament(res.getInt(1),res.getInt(2),res.getString(3),res.getInt(4),res.getInt(5),res.getInt(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceMedic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return m ;
        
    }

    
}
