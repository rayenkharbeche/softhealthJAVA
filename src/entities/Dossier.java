/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;



/**
 *
 * @author Asus
 */
public class Dossier {
    private int id_dossier , id_patient;
    private String description;
    private String dateCréation; 

    public Dossier(int id_dossier, String description, String dateCréation, int id_patient  ) {
        this.id_dossier = id_dossier;
        this.id_patient = id_patient;
        this.description = description;
        this.dateCréation = dateCréation ;
        }

    public Dossier(String description,String dateCréation ,int id_patient) {
       
        this.description = description;
        this.dateCréation = dateCréation;
         this.id_patient = id_patient;
    }

    public Dossier(int id_patient) {
        this.id_patient = id_patient;
       
    }

    public Dossier( String description,int id_patient) {
        this.id_patient = id_patient;
        this.description = description;
    }
    
    public Dossier(){      
}
    

    public int getId_dossier() {
        return id_dossier;
    }

    public int getId_patient() {
        return id_patient;
    }

    public String getDescription() {
        return description;
    }

    public String getDateCreéation() {
        return dateCréation;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreéation(String dateCreéation) {
        this.dateCréation = dateCreéation;
    }

    public String toString()
    {
        return "Dossier ["+ " id_dossier=" + id_dossier + ", id_patient =" + id_patient + ", description=" + description + ",description ="+description+ "]";
    }
    
    
    
    
    
    
}
