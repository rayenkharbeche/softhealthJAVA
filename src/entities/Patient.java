/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Asus
 */
public class Patient {
    private int id_patient;
    private String nom;

    public Patient(int id_patient, String nom) {
        this.id_patient = id_patient;
        this.nom = nom;
    }

    public int getId_patient() {
        return id_patient;
    }

    public String getNom() {
        return nom;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
