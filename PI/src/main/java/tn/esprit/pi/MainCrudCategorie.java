/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pi;

import java.sql.SQLException;
import tn.esprit.entities.Categorie;
import tn.esprit.services.CategorieServices;

/**
 *
 * @author Rihem
 */
public class MainCrudCategorie {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        CategorieServices categorieServices = new CategorieServices();

        // ajouter une catégorie
       Categorie categorie = new Categorie("aaa");
        categorieServices.ajouterCategorie(categorie);

        //modifier categorie 
        categorie.setId_categorie(3);
        categorie.setType_categorie("teeest");
        categorieServices.modifierCategorie(categorie);

        //afficher les catégories
        categorieServices.readAllCategories();
        
        // rechercher catégorie selon type
        categorieServices.getIdCategorie("test_rihem");
        
        //
    }
    
}
