/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author Rihem
 */
public class Categorie {
    private int id_categorie;
    private String type_categorie;

    public Categorie() {
    }

    public Categorie(int id_categorie, String type_categorie) {
        this.id_categorie = id_categorie;
        this.type_categorie = type_categorie;
    }

    public Categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public void setType_categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id_categorie=" + id_categorie + ", type_categori=" + type_categorie + '}';
    }
    
}
