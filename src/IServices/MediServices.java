/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entity.Médicament;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASSOUMA
 */
public interface MediServices <M>{
    
    
    public abstract void ajouter(M m) throws SQLException;
    
     public boolean supprimer(int id) throws SQLException;
    public void modifier(M m) throws SQLException;
    public  List<M> afficher() throws SQLException;
   
   
   public  List<M> findByName(String name);
   public M findById(int id);
   public M findByCode(int code);
   public List<M> findByCategory(int categorie_id);
   public List<M> findByStock(int stock);
   
     public List<M> ListMediOrderByDESC() throws SQLException ;
     public List<M> ListMediOrderByASC() throws SQLException ;
     public List<M> ListPrixDESC() throws SQLException ;
     public List<M> ListPrixASC() throws SQLException ;


   
    
}
