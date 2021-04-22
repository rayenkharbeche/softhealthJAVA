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
public class Ordonnance {
    
private int id;
public int medicaments_id;
public int categorie_id;
public int consultation_id;
public int patient_id;
public int  users_id;
private String description;
private int nbr_jrs ;
private float nbr_doses;
private int nbr_fois;
private int nbr_paquets;



    public Ordonnance() {
    }

   

    public int getId() {
        return id;
    }

    public int getMedicaments_id() {
        return medicaments_id;
    }

    public void setMedicaments_id(int medicaments_id) {
        this.medicaments_id = medicaments_id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getConsultation_id() {
        return consultation_id;
    }

    public void setConsultation_id(int consultation_id) {
        this.consultation_id = consultation_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }
  

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbr_jrs() {
        return nbr_jrs;
    }

    public void setNbr_jrs(int nbr_jrs) {
        this.nbr_jrs = nbr_jrs;
    }

    public float getNbr_doses() {
        return nbr_doses;
    }

    public void setNbr_doses(float nbr_doses) {
        this.nbr_doses = nbr_doses;
    }

    public int getNbr_fois() {
        return nbr_fois;
    }

    public void setNbr_fois(int nbr_fois) {
        this.nbr_fois = nbr_fois;
    }

    public int getNbr_paquets() {
        return nbr_paquets;
    }

    public void setNbr_paquets(int nbr_paquets) {
        this.nbr_paquets = nbr_paquets;
    }

    public Ordonnance(int id, int medicaments_id, int categorie_id, int consultation_id, int patient_id, int users_id, String description, int nbr_jrs, float nbr_doses, int nbr_fois, int nbr_paquets) {
        this.id = id;
        this.medicaments_id = medicaments_id;
        this.categorie_id = categorie_id;
        this.consultation_id = consultation_id;
        this.patient_id = patient_id;
        this.users_id = users_id;
        this.description = description;
        this.nbr_jrs = nbr_jrs;
        this.nbr_doses = nbr_doses;
        this.nbr_fois = nbr_fois;
        this.nbr_paquets = nbr_paquets;
    }

    @Override
    public String toString() {
        return "Ordonnance{" + "id=" + id + ", medicaments_id=" + medicaments_id + ", categorie_id=" + categorie_id + ", consultation_id=" + consultation_id + ", patient_id=" + patient_id + ", users_id=" + users_id + ", description=" + description + ", nbr_jrs=" + nbr_jrs + ", nbr_doses=" + nbr_doses + ", nbr_fois=" + nbr_fois + ", nbr_paquets=" + nbr_paquets + '}';
    }

   
    
    
}
