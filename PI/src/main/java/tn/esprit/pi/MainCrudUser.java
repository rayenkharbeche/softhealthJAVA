/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.pi;

import java.sql.SQLException;
import tn.esprit.entities.User;
import tn.esprit.services.UserService;

/**
 *
 * @author Rihem
 */
public class MainCrudUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserService();

        //ajouter un utilisateur 
        User user2 = new User("ok@esprit.tn", "Admin", "12345678", 11223344, "ok");
        userService.inscrire(user2);

        //modifier un utilisateur 
      /*  User user1 = new User();
        user1 = userService.getUserById(4);

        user1.setEmail("rihem.rihem@esprit.tn");
        userService.modifierUser(user1);
*/
        //afficher les utilisateurs
        userService.readAll();

        //supprimer un utilisateur ;
     /*   User user3 = new User();
        user3 = userService.getUserById(1);
        userService.supprimerUser(user3);
*/
    }

}
