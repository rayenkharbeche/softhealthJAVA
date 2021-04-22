/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;


import Entity.Catégorie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASSOUMA
 */
public interface CategServices  <C>{
    
    public abstract void ajouter(C c) throws SQLException;
    public boolean supprimer(int id) throws SQLException;
    public void modifier(C c) throws SQLException;
    public  List<C> afficher() throws SQLException;
    
    
     public List<C> findByNom(String nom);
     public List<C> ListNomDESC() throws SQLException;
     public List<C> ListNomASC() throws SQLException;
  
    
}
