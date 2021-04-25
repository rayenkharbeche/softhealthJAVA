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
public class Patient {
    
    int id;
    int cin;
    String nom;
    String prenom;
    String email;
    String password;

    public Patient() {
    }

    public Patient(int cin, String nom, String prenom, String email, String password) {
        
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    
     public Patient(int id,int cin, String nom, String prenom, String email, String password) {
        this.id=id;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Patient{" + "id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password=" + password + '}';
    }
    
    
    
}
