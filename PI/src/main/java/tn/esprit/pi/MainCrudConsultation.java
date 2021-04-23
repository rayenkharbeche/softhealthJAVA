/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pi;

import java.sql.SQLException;
import tn.esprit.entities.Consultation;
import tn.esprit.services.CategorieServices;
import tn.esprit.services.ConsultationServices;

/**
 *
 * @author Rihem
 */
public class MainCrudConsultation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        ConsultationServices consultationServices = new ConsultationServices();
        CategorieServices categorieServices = new CategorieServices();

        // ajouter une consultation 
        Consultation consultation = new Consultation(5, 5, "fifth consultation");
        consultationServices.ajouterConsultation(consultation);

        //modifier une consultation
        consultation.setId_consultation(2);
        consultation.setNbr_vus(2);
        consultationServices.modifierConsultation(consultation);

        //afficher les consultations
        consultationServices.readAllConsultation();
        
        // trier par date création
        consultationServices.TrierParDateCreation();
        
        // supprimer une consultation
       // consultationServices.supprimerConsultation(consultation);

        // Nombre de vue 
       // consultationServices.incrementerNombreVu(0);
    }

}
