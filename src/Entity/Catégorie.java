/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author ASSOUMA
 */
public class Catégorie {
    
     public int id;
     public String nom;

    public Catégorie() {
    }

    public Catégorie(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Cat\u00e9gorie{" + "id=" + id + ", nom=" + nom + '}';
    }
     
     
     
     
     
    
}
