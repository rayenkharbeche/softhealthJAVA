/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import tn.esprit.entities.Consultation;
import tn.utils.DataSource;

/**
 *
 * @author rayen
 */
public class ConsultationServices {

    private Connection con = DataSource.getInstance().getConnection();
    private Statement ste;

    public ConsultationServices() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public void ajouterConsultation(Consultation consultation) throws SQLException {
        Date date1 = new Date();
        String account_Date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        consultation.setDate_creation(account_Date);
        String req1 = "INSERT INTO `consultation` (`users_id`,`categorie_id`,`date_creation`,`titre`,`description`, `nbr_vus`) "
                + "VALUES ('" + consultation.getId_user() + "', '" + consultation.getId_categorie()
                + "', '" + consultation.getDate_creation() + "','" + consultation.getTitre() + "', '" + consultation.getDescription() + "', '" + 0 + "');";
        ste.executeUpdate(req1);
        System.out.println("consultation ajouté");
    }

    public void modifierConsultation(Consultation consultation) throws SQLException {
        String sql = "UPDATE consultation SET `users_id`=?,`categorie_id`=?,`date_creation`=?,`titre`=?,`description`=?, `nbr_vus`=? "
                + " WHERE id=" + consultation.getId_consultation();
        PreparedStatement ste;
        try {
            ste = con.prepareStatement(sql);

            ste.setInt(1, consultation.getId_user());
            ste.setInt(2, consultation.getId_categorie());
            ste.setString(3, consultation.getDate_creation());
            ste.setString(4,consultation.getTitre());
            ste.setString(5, consultation.getDescription());
            ste.setInt(6, consultation.getNbr_vus());
            ste.executeUpdate();
            int rowsUpdated = ste.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La modification de consultation : " + consultation.getId_consultation() + " a été éffectuée avec succès ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerConsultation(Consultation consultation) {

        try {
            String req = "DELETE FROM `consultation` WHERE `consultation`.`id` = ?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, consultation.getId_consultation());
            ste.executeUpdate();
            System.out.println("consultation supprimée");

        } catch (SQLException ex) {
            Logger.getLogger(ConsultationServices.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Consultation> readAllConsultation() throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation ");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
            System.out.println("all" + list);
        }
        return list;
    }

    public List<Consultation> readAllConsultationByIdCategorie(int id_categorie) throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation WHERE categorie_id='" + id_categorie + "'");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
        }
        System.out.println("all" + list);

        return list;
    }
     public List<Consultation> readAllConsultationByPatient(int id_user) throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation WHERE users_id='" + id_user + "'");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
        }
        System.out.println("all" + list);

        return list;
    }

    public List<Consultation> TrierParDateCreation() throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation ORDER BY date_creation ASC");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
        }
        System.out.println(list);
        return list;
    }
     public List<Consultation> TrierParNbreVu() throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation ORDER BY nbr_vus ASC");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
        }
        System.out.println(list);
        return list;
    }

    public Consultation getConsultationByIdConsultation(int id_consultation) throws SQLException {
        Consultation consultation = new Consultation();

        String req = "SELECT * from consultation where id='" + id_consultation + "'";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
        }
        System.out.println(consultation);
        return consultation;

    }

    public List<Consultation> readAllUserByTypeCategorie(String type_categorie) throws SQLException {
        List<Consultation> list = new ArrayList<>();
        CategorieServices categorieServices = new CategorieServices();
        int id_categorie = categorieServices.getIdCategorie(type_categorie);
        ResultSet res = ste.executeQuery("select * from consultation WHERE categorie_id='" + id_categorie + "'");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getString(6), res.getInt(7));
            list.add(consultation);
        }
        System.out.println("all" + list);

        return list;
    }

    public void incrementerNombreVu(int id_consultation) {
        try {
            PreparedStatement ste = con.prepareStatement("update consultation set nbr_vus=nbr_vus+1  WHERE id='" + id_consultation + "'");
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
