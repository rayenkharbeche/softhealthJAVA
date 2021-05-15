/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Patient;
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
public class PatientService {
    private Connection cn;
private Statement st;
private PreparedStatement pst;
private ResultSet rs;
public PatientService (){
    cn=DataSource.getInstance().getCnx();
    }
     public List<Patient> getAllPatient() throws SQLDataException {
         String red="select * from patient";
         List<Patient> list=new ArrayList<>();
    try {
        st=cn.createStatement();
        rs=st.executeQuery(red);
        while(rs.next()){
            list.add(new Patient(rs.getInt(1),rs.getString(2)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(PatientService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
    }
     
       public int getByNom(String nom) {
          int id=0;
         try {
            String requete = "SELECT id FROM patient where nom=?";
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
            String requete = "SELECT nom FROM patient";
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
