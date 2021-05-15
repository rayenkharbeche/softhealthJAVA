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
public class Medecin {
    private int id_médecin;
    private String nom;

    public Medecin(String nom) {
        this.nom = nom;
    }

    public Medecin(int id_médecin, String nom) {
        this.id_médecin = id_médecin;
        this.nom = nom;
    }

    public void setId_médecin(int id_médecin) {
        this.id_médecin = id_médecin;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId_médecin() {
        return id_médecin;
    }

    public String getNom() {
        return nom;
    }
    
}
