/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Fichier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import utils.DataSource;
import utils.Image;

/**
 *
 * @author Asus
 */
public class FichierService implements FichierServiceInterface<Fichier> {
private Connection cn;
private Statement st;
private PreparedStatement pst;
private ResultSet rs;
 
    public FichierService (){
    cn=DataSource.getInstance().getCnx();
    }
public void addFichier(Fichier f)
{
      String req="insert into fichier (description,dossier_id,image,medecin_id) values (?,?,?,?)";   
          try
    {
        pst=cn.prepareStatement(req);
        pst.setString(1, f.getDescription());
        pst.setInt(2, f.getId_dossier());
        pst.setString(3, f.getImage());
         pst.setInt(4, f.getId_médecin());
        pst.executeUpdate();
        System.out.println("fichier ajouté");
            }
        
       catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
}

@Override
public void ModifierFichier(Fichier f) throws SQLDataException {
       
      String req="update fichier set description=? , dossier_id=?, image= ? , medecin_id= ?  where id= ?";    
          try
    {
        pst=cn.prepareStatement(req);
        pst.setString(1, f.getDescription());
        pst.setInt(2, f.getId_dossier());
        pst.setString(3, f.getImage());
         pst.setInt(4, f.getId_médecin());
          pst.setInt(5, f.getId_fichier());
         
        pst.executeUpdate();
        System.out.println("fichier modifié");
            }
        
       catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
 
   }

    @Override
    public void deleteFichier(int id) throws SQLDataException {
         try {
            String req2 ="SELECT * FROM fichier WHERE id="+ id ;
            PreparedStatement pst2 = cn.prepareStatement(req2);
            ResultSet rs = pst2.executeQuery();
            if (!rs.next()){
               System.out.println("fichier introuvable !");  
            }
            else{
                String requete = "DELETE FROM fichier WHERE id=?";
                PreparedStatement pst = cn.prepareStatement(requete);
                pst.setInt(1, id);
                pst.executeUpdate();
                System.out.println("fichier supprimée !");  
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }

    @Override
    public List<Fichier> getAllFichier() throws SQLDataException {
       String red="select * from fichier";
         List<Fichier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Fichier(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }

    @Override
    public List<Fichier> FichierStartWithT() throws SQLDataException {
        String red="select * from fichier";
         List<Fichier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Fichier(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list.stream().filter((e)->e.getDescription().startsWith("t"))
                         .collect(Collectors.toList());
    }

    @Override
    public TreeSet<Fichier> FichierSelonDes() throws SQLDataException {
      String red="select * from fichier";
         List<Fichier> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Fichier(rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list.stream().collect(Collectors.toCollection(()-> new TreeSet<Fichier>((a,b)->a.getDescription().compareTo(b.getDescription()))));
    }
    
 public List<String> afficherNom() {
         List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom FROM fichier";
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

        
        

  