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
 * @author Rihem
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
        String req1 = "INSERT INTO `consultation` (`id_user`,`id_categorie`,`date_creation`,`description`, `nbr_vus`) "
                + "VALUES ('" + consultation.getId_user() + "', '" + consultation.getId_categorie()
                + "', '" + consultation.getDate_creation() + "', '" + consultation.getDescription() + "', '" + 0 + "');";
        ste.executeUpdate(req1);
        System.out.println("consultation ajouté");
    }

    public void modifierConsultation(Consultation consultation) throws SQLException {
        String sql = "UPDATE consultation SET `id_user`=?,`id_categorie`=?,`date_creation`=?,`description`=?, `nbr_vus`=? "
                + " WHERE id_consultation=" + consultation.getId_consultation();
        PreparedStatement ste;
        try {
            ste = con.prepareStatement(sql);

            ste.setInt(1, consultation.getId_user());
            ste.setInt(2, consultation.getId_categorie());
            ste.setString(3, consultation.getDate_creation());
            ste.setString(4, consultation.getDescription());
            ste.setInt(5, consultation.getNbr_vus());
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
            String req = "DELETE FROM `consultation` WHERE `consultation`.`id_consultation` = ?";
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
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getInt(6));
            list.add(consultation);
            System.out.println("all" + list);
        }
        return list;
    }

    public List<Consultation> readAllConsultationByIdCategorie(int id_categorie) throws SQLException {
        List<Consultation> list = new ArrayList<>();
        ResultSet res = ste.executeQuery("select * from consultation WHERE id_categorie='" + id_categorie + "'");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getInt(6));
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
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getInt(6));
            list.add(consultation);
        }
        System.out.println(list);
        return list;
    }

    public Consultation getConsultationByIdConsultation(int id_consultation) throws SQLException {
        Consultation consultation = new Consultation();

        String req = "SELECT * from consultation where id_consultation='" + id_consultation + "'";
        ResultSet res = ste.executeQuery(req);
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getInt(6));
        }
        System.out.println(consultation);
        return consultation;

    }

    public List<Consultation> readAllUserByTypeCategorie(String type_categorie) throws SQLException {
        List<Consultation> list = new ArrayList<>();
        CategorieServices categorieServices = new CategorieServices();
        int id_categorie = categorieServices.getIdCategorie(type_categorie);
        ResultSet res = ste.executeQuery("select * from consultation WHERE id_categorie='" + id_categorie + "'");
        Consultation consultation = null;
        while (res.next()) {
            consultation = new Consultation(res.getInt(1), res.getInt(2), res.getInt(3), res.getString(4), res.getString(5), res.getInt(6));
            list.add(consultation);
        }
        System.out.println("all" + list);

        return list;
    }

    public void incrementerNombreVu(int id_consultation) {
        try {
            PreparedStatement ste = con.prepareStatement("update categorie set nbr_vus=nbr_vus+1  WHERE id_consultation='" + id_consultation + "'");
            ste.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ConsultationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
