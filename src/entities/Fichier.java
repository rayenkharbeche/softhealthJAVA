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
public class Fichier {
    private int id_fichier , id_dossier, id_médecin ;
    private String image , description ;

    public Fichier(int id_fichier,String description, int id_dossier, String image , int id_médecin) {
        this.id_fichier = id_fichier;
        this.id_dossier = id_dossier;
        this.id_médecin = id_médecin;
        this.image = image;
        this.description = description;
    }

    public Fichier(int id_fichier, int id_dossier, int id_médecin, String description) {
        this.id_fichier = id_fichier;
        this.id_dossier = id_dossier;
        this.id_médecin = id_médecin;
        this.description = description;
    }

   

    public Fichier(String description) {
        this.description = description;
    }
    
    

    public Fichier(int id_fichier, String image, String description) {
        this.id_fichier = id_fichier;
        this.image = image;
        this.description = description;
    }

    public Fichier(int id_dossier, int id_médecin, String description) {
        this.id_dossier = id_dossier;
        this.id_médecin = id_médecin;
        this.description = description;
    }

    
    
    
    public Fichier(){}

    public Fichier(String text, int s1, String image, int s2) {
        this.description = text;
        this.id_dossier = s1;
         this.image = image;
        this.id_médecin = s2;
    }

   

    public int getId_fichier() {
        return id_fichier;
    }

    public void setId_fichier(int id_fichier) {
        this.id_fichier = id_fichier;
    }

    public int getId_dossier() {
        return id_dossier;
    }

    public void setId_dossier(int id_dossier) {
        this.id_dossier = id_dossier;
    }

    public int getId_médecin() {
        return id_médecin;
    }

    public void setId_médecin(int id_médecin) {
        this.id_médecin = id_médecin;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   public String toString()
    {
        return "Fichier ["+ " id_fichier=" + id_fichier + ", id_dossier=" + id_dossier + ", id_médecin=" + id_médecin + ", file=" + image + ", description=" + description +  "]";
    }
}
    
    
    

