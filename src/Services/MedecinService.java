/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Medecin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author Asus
 */
public class MedecinService {
private Connection cn;
private Statement st;
private PreparedStatement pst;
private ResultSet rs;
public MedecinService (){
    cn=DataSource.getInstance().getCnx();
    }
     public List<Medecin> getAllMedecin() throws SQLDataException {
         String red="select * from medecin";
         List<Medecin> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Medecin(rs.getInt(1),rs.getString(2)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(FichierService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
     
       public int getByNom(String nom) {
         
      int id=0;
         try {
            String requete = "SELECT id FROM medecin where nom=?";
            PreparedStatement pst = cn.prepareStatement(requete);
            pst.setString(1, nom);
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
       
        public List<String> afficherNom() {
         List<String> list = new ArrayList<>();

        try {
            String requete = "SELECT nom FROM medecin";
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
