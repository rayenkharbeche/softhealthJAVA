/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author Rihem
 */
public class Consultation {
    private int id_consultation;
    private int id_user;
    private int id_categorie;
    private String date_creation;
    private String description;
    private int nbr_vus ;

    public Consultation() {
    }

    public Consultation(int id_consultation, int id_user, int id_categorie, String date_creation, String description, int nbr_vus) {
        this.id_consultation = id_consultation;
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    public Consultation(int id_user, int id_categorie, String date_creation, String description, int nbr_vus) {
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    public Consultation(int id_user, int id_categorie, String description) {
        this.id_user = id_user;
        this.id_categorie = id_categorie;
        this.description = description;
    }
    
    

    public Consultation(String date_creation, String description, int nbr_vus) {
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    
    public int getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(int id_consultation) {
        this.id_consultation = id_consultation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_vus() {
        return nbr_vus;
    }

    public void setNbr_vus(int nbr_vus) {
        this.nbr_vus = nbr_vus;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id_consultation=" + id_consultation + ", id_user=" + id_user + ", id_categorie=" + id_categorie + ", date_creation=" + date_creation + ", description=" + description + ", nbr_vus=" + nbr_vus + '}';
    }
    

   
    
}
