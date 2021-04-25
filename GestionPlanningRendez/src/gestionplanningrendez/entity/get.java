/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.entity;

import gestionplanningrendez.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author LENOVO
 */
public class get {
     Connection cnx = DataSource.getInstance().getCnx();
    public static int i=0;
    
    public int Mars() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-03-01";fin="2021-03-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    public int Jan() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-01-01";fin="2021-01-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    public int Feb() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-02-01";fin="2021-01-29";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    public int Avril() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-04-01";fin="2021-04-30";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        i=j;
        
        return i;
    }
    public int Mai() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-05-01";fin="2021-05-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        i=j;
        
        return i;
    }
    
    public int Juin() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-06-01";fin="2021-06-30";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    
    public int Juillet() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-07-01";fin="2021-07-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
       i=j; 
        return i;
    }
    public int Aout() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-08-01";fin="2021-08-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    public int Sept() throws Exception {
        int j= 0;
      //  Setconnection();
      String deb,fin;
      deb="2021-09-01";fin="2021-09-30";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        i=j;
        
        return i;
    }
    public int Oct() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-10-01";fin="2021-10-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        i=j;
        
        return i;
    }
    public int Nouvbre() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-11-01";fin="2021-11-30";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        
        i=j;
        return i;
    }
    public int Dec() throws Exception {
        int j=0;
      //  Setconnection();
      String deb,fin;
      deb="2021-12-01";fin="2021-12-31";
       Statement stmt = cnx.createStatement();
       String check = "SELECT id FROM rendez_vous WHERE date_rdv>='"+deb+"' and date_rdv<='"+fin+"'";
        ResultSet rs= stmt.executeQuery(check);
        while (rs.next()){
            j++;
        }
        i=j;
        
        return i;
    }
    
}
