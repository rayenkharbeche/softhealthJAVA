/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

import java.util.Date;
import tn.esprit.services.ServiceUser;

/**
 *
 * @author LENOVO
 */
public class RendezVous implements Comparable<RendezVous>{
    private int id;
    private String nomRDV, descriptionRDV;
    private Date dateRDV;
    private int id_planning;
    private int id_user;
    private int id_patient;
    ServiceUser su= new ServiceUser();

    public RendezVous(int id, String nomRDV, String descriptionRDV, Date dateRDV ,int user, int planning,int id_patient) {
        this.id = id;
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        this.id_planning= planning;
        this.id_user=user;
        this.id_patient=id_patient;
    }
    public RendezVous(String nomRDV, String descriptionRDV, Date dateRDV, int user, int planning,int id_patient) {
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
        this.id_planning= planning;
        this.id_user=user;
        this.id_patient=id_patient;
        
    }
     public RendezVous(String nomRDV, String descriptionRDV, Date dateRDV) {
        
        this.nomRDV = nomRDV;
        this.descriptionRDV = descriptionRDV;
        this.dateRDV = dateRDV;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomRDV() {
        return nomRDV;
    }

    public void setNomRDV(String nomRDV) {
        this.nomRDV = nomRDV;
    }

    public String getDescriptionRDV() {
        return descriptionRDV;
    }

    public void setDescriptionRDV(String descriptionRDV) {
        this.descriptionRDV = descriptionRDV;
    }

    public Date getDateRDV() {
        return dateRDV;
    }

    public void setDateRDV(Date dateRDV) {
        this.dateRDV = dateRDV;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_planning() {
        return id_planning;
    }

    public void setId_planning(int id_planning) {
        this.id_planning = id_planning;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "RendezVous{" + "id=" + id + ", nomRDV=" + nomRDV + ", descriptionRDV=" + descriptionRDV + ", dateRDV=" + dateRDV + ", id_planning=" + id_planning + ", id_user=" + su.getByID(id_user) + ", id_patient=" + id_patient + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RendezVous other = (RendezVous) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(RendezVous o) {
        return this.getDateRDV().compareTo(o.getDateRDV()) ;
    }
    
}
