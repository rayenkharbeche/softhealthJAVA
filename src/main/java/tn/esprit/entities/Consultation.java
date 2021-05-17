/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.sql.Date;

/**
 *
 * @author rayen
 */
public class Consultation {
    private int id;
    private int users_id;
    private int categorie_id;
        private String titre;

    private String date_creation;
    private String description;
    private int nbr_vus ;

    public Consultation() {
    }

    public Consultation(int id_consultation, int id_user, int id_categorie, String titre, String date_creation, String description, int nbr_vus) {
        this.id = id_consultation;
        this.users_id = id_user;
        this.categorie_id = id_categorie;
        this.titre = titre;
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    public Consultation(int id_consultation, int id_user, int id_categorie, String date_creation, String description, int nbr_vus) {
        this.id = id_consultation;
        this.users_id = id_user;
        this.categorie_id = id_categorie;
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    public Consultation(int id_user, int id_categorie, String date_creation, String description, int nbr_vus) {
        this.users_id = id_user;
        this.categorie_id = id_categorie;
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    public Consultation(int id_user, int id_categorie, String description) {
        this.users_id = id_user;
        this.categorie_id = id_categorie;
        this.description = description;
    }

  

    public Consultation(String date_creation, String description, int nbr_vus) {
        this.date_creation = date_creation;
        this.description = description;
        this.nbr_vus = nbr_vus;
    }

    
    public int getId_consultation() {
        return id;
    }

    public void setId_consultation(int id_consultation) {
        this.id = id_consultation;
    }

    public int getId_user() {
        return users_id;
    }

    public void setId_user(int id_user) {
        this.users_id = id_user;
    }

    public int getId_categorie() {
        return categorie_id;
    }

    public void setId_categorie(int id_categorie) {
        this.categorie_id = id_categorie;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id_consultation=" + id + ", id_user=" + users_id + ", id_categorie=" + categorie_id + ", date_creation=" + date_creation + ", titre=" + titre + ", description=" + description + ", nbr_vus=" + nbr_vus + '}';
    }

  
    

   
    
}
