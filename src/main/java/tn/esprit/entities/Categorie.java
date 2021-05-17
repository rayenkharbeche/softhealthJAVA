/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.entities;

/**
 *
 * @author rayen
 */
public class Categorie {
    private int id;
    private String type_categorie;

    public Categorie() {
    }

    public Categorie(int id, String type_categorie) {
        this.id = id;
        this.type_categorie = type_categorie;
    }

    public Categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    public int getId_categorie() {
        return id;
    }

    public void setId_categorie(int id_categorie) {
        this.id = id_categorie;
    }

    public String getType_categorie() {
        return type_categorie;
    }

    public void setType_categorie(String type_categorie) {
        this.type_categorie = type_categorie;
    }

    @Override
    public String toString() {
        return "Categorie{" + "id=" + id + ", type_categori=" + type_categorie + '}';
    }
    
}
