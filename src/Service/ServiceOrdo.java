/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.Ordonnance;
import IServices.OrdoService;
import Utils.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASSOUMA
 */
public class ServiceOrdo implements OrdoService <Ordonnance> {
        private Connection cnx;
          private Statement stmt;
         private PreparedStatement pst;
        private ResultSet res;

        
        
        
    public ServiceOrdo() {
            cnx=Database.getInstance().getCon();

    }
        
        

    @Override
 public void ajouter(Ordonnance o) throws SQLException {
     String req = "INSERT INTO ordonnance (medicaments_id, categorie_id, consultation_id, patient_id, users_id, description, nbr_jrs, nbr_doses, nbr_fois, nbr_paquets)"
                 +" VALUES (?,?,?,?,?,?,?,?,?,?)";
        try{
            
             PreparedStatement pst = cnx.prepareStatement(req);
            
            
             pst.setInt(1,o.getMedicaments_id());
             pst.setInt(2,o.getCategorie_id());
             pst.setInt(3,o.getConsultation_id());
             pst.setInt(4,o.getPatient_id());
             pst.setInt(5,o.getUsers_id());
             pst.setString(6,o.getDescription());
             pst.setInt(7,o.getNbr_jrs());
             pst.setFloat(8,o.getNbr_doses());
             pst.setInt(9,o.getNbr_fois());
             pst.setInt(10,o.getNbr_paquets());

            
           
            pst.executeUpdate();
            System.out.println("Ordonnance ajoutée avec succée");
 
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    
         public boolean supprimer(int id) throws SQLException  {
       
        String sql="delete from ordonnance where id= ? ";
				
		try {
                    pst=cnx.prepareStatement(sql);
                    pst.setInt(1, id);
                    pst.execute();
                System.out.println("Ordonnance supprimé avec succé");
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
			
    }

    @Override
    public void modifier(Ordonnance o) throws SQLException {
        
        PreparedStatement pst

                = cnx.prepareStatement("update ordonnance set medicaments_id= ? , categorie_id= ?, consultation_id= ? , patient_id= ? , users_id = ? ,description=?, nbr_jrs=?, nbr_doses=?, nbr_fois=?, nbr_paquets=?  where id= ?");    
       
        
        
           pst.setInt(1,o.getMedicaments_id());
             pst.setInt(2,o.getCategorie_id());
             pst.setInt(3,o.getConsultation_id());
             pst.setInt(4,o.getPatient_id());
             pst.setInt(5,o.getUsers_id());
             pst.setString(6,o.getDescription());
             pst.setInt(7,o.getNbr_jrs());
             pst.setFloat(8,o.getNbr_doses());
             pst.setInt(9,o.getNbr_fois());
             pst.setInt(10,o.getNbr_paquets());

           pst.setInt( 11,o.getId());

        
          
            pst.executeUpdate();
            System.out.println("Ordonnance updated");
           
        
    }

    @Override
    public List<Ordonnance> afficher() throws SQLException {
        
        
        List<Ordonnance> list=new ArrayList<Ordonnance>();
          PreparedStatement pt = cnx.prepareStatement("Select * from ordonnance");
          
          
            ResultSet rs = pt.executeQuery();
          
            while(rs.next()){
                
                int id = rs.getInt(1);
                int medicaments_id = rs.getInt(2);
                int categorie_id = rs.getInt(3);
                int consultation_id = rs.getInt(4);
                int patient_id = rs.getInt(5);
                int users_id = rs.getInt(6);
                String description = rs.getString(7);
                int nbr_jrs = rs.getInt(8);
                int nbr_doses = rs.getInt(9);
                int nbr_fois = rs.getInt(10);
                int nbr_paquets = rs.getInt(11);


   
                       
             Ordonnance o = new Ordonnance( id,medicaments_id, categorie_id, consultation_id, patient_id, users_id, description, nbr_jrs, nbr_doses, nbr_fois, nbr_paquets );
                list.add(o);
            }
            return list;
        
    }
    
    
    
    
    
    public List<Ordonnance> ListOrdoOrderByDesc() throws SQLException {
         
        List<Ordonnance> list = new ArrayList<Ordonnance>();
            PreparedStatement pt = cnx.prepareStatement("select * from ordonnance ORDER BY consultation_id  DESC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt(1);
                int medicaments_id = rs.getInt(2);
                int categorie_id = rs.getInt(3);
                int consultation_id = rs.getInt(4);
                int patient_id = rs.getInt(5);
                int users_id = rs.getInt(6);
                String description = rs.getString(7);
                int nbr_jrs = rs.getInt(8);
                int nbr_doses = rs.getInt(9);
                int nbr_fois = rs.getInt(10);
                int nbr_paquets = rs.getInt(11);
                
                          
             Ordonnance o = new Ordonnance( id,medicaments_id, categorie_id, consultation_id, patient_id, users_id, description, nbr_jrs, nbr_doses, nbr_fois, nbr_paquets );
                list.add(o);
            }
            return list;
            }
   
    
    
    
    
    
     public List<Ordonnance> ListOrdoOrderByAsc() throws SQLException {
         
        List<Ordonnance> list = new ArrayList<Ordonnance>();
            PreparedStatement pt = cnx.prepareStatement("select * from ordonnance ORDER BY consultation_id  ASC");
            
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                
                int id = rs.getInt(1);
                int medicaments_id = rs.getInt(2);
                int categorie_id = rs.getInt(3);
                int consultation_id = rs.getInt(4);
                int patient_id = rs.getInt(5);
                int users_id = rs.getInt(6);
                String description = rs.getString(7);
                int nbr_jrs = rs.getInt(8);
                int nbr_doses = rs.getInt(9);
                int nbr_fois = rs.getInt(10);
                int nbr_paquets = rs.getInt(11);
                
                          
             Ordonnance o = new Ordonnance( id,medicaments_id, categorie_id, consultation_id, patient_id, users_id, description, nbr_jrs, nbr_doses, nbr_fois, nbr_paquets );
                list.add(o);
            }
            return list;
            }
   

    
}
