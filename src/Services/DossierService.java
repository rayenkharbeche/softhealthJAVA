/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Dossier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import utils.DataSource;

/**
 *
 * @author Asus
 */
public class DossierService implements DossierServiceInterface <Dossier> {
private Connection cn;
private Statement st;
private PreparedStatement pst;
private ResultSet rs;
public DossierService (){
    cn=DataSource.getInstance().getCnx();
    }
    @Override
    public void addDossier(Dossier d) throws SQLDataException {
   String query ="INSERT INTO dossier VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cn.prepareStatement(query);
                st.setInt(1,d.getId_dossier());
                st.setString(2,d.getDescription());
                st.setString(3,d.getDateCreéation());
                st.setInt(4,d.getId_patient());
                
                st.executeUpdate();
                System.out.println("dossier ajouté");

        } catch (SQLException ex) {
            Logger.getLogger(DossierService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override

    public void deleteDossier(int id) throws SQLDataException {
          try {
            String req2 ="SELECT * FROM dossier WHERE id="+ id ;
            PreparedStatement pst2 = cn.prepareStatement(req2);
            ResultSet rs = pst2.executeQuery();
            if (!rs.next()){
               System.out.println("dossier introuvable !");  
            }
            else{
                String requete = "DELETE FROM dossier WHERE id=?";
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("dossier supprimée !");  
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void ModifierDossier(Dossier d) throws SQLDataException {
         String query = "UPDATE dossier SET `description`=?,`date_creation`=?,`patient_id`=? WHERE `id` =?";
		PreparedStatement st;
        try {
                st = cn.prepareStatement(query);
                st.setString(1,d.getDescription());
                st.setString(2,d.getDateCreéation());
                st.setInt(3,d.getId_patient());
                st.setInt(4,d.getId_dossier());
                st.executeUpdate();
                System.out.println("dossier modifié");
                
        } catch (SQLException ex) {
            Logger.getLogger(DossierService.class.getName()).log(Level.SEVERE, null, ex);
            
        }
                
              
    }

    @Override
    public List<Dossier> getAllDossier() throws SQLDataException {
         String red="select * from dossier";
         List<Dossier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Dossier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }

    @Override
    public List<Dossier> DossierStartWithP() throws SQLDataException {
        String red="select * from dossier";
         List<Dossier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Dossier(rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
 return list.stream().filter((e)->e.getDescription().startsWith("p"))
                         .collect(Collectors.toList());
}

     public int getByNom(String description) {
         int id=0;
         try {
            String requete = "SELECT id FROM dossier where description=?";
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, description);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                id=rs.getInt("id");
                System.out.println("serv"+rs.getInt("id"));
            }
            return id;
        
         
         } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
         return id;


    }
    @Override
    public TreeSet<Dossier> dossierSelonDes() throws SQLDataException {
        String red="select * from dossier";
         List<Dossier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Dossier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
 return list.stream().collect(Collectors.toCollection(()-> new TreeSet<Dossier>((a,b)->a.getDescription().compareTo(b.getDescription()))));
                
    }

    @Override
    public TreeSet<Dossier> dossierSelonIdPatient() throws SQLDataException {
         String red="select * from dossier";
         List<Dossier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Dossier(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4)));
        }
        } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    
 return  (TreeSet<Dossier>) list.stream().sorted((a, b) -> a.getId_patient() - b.getId_patient());
    }

     public List<String> afficherNom() {
         List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom FROM dossier";
            PreparedStatement pst = cn.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("nom"));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return list;
    }
    
    
}
    

