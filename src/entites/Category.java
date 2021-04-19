/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

/**
 *
 * @author Amina
 */
public class Category {
     private int id;
     private String nom;
     private String description;
     private String color;

     
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Category(int id, String nom, String description,String color) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.color = color;
    }
        public Category( String nom, String description,String color) {
        this.nom = nom;
        this.description = description;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Category{" + "id=" + id + ", nom=" + nom + ", description=" + description + '}';
    }
    
       
    
}
