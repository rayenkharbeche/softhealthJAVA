/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Amina
 */
public interface IService  <T>{
    public void ajouter(T t)throws SQLException;
    public void supprimer(T t)throws SQLException;
    public void modifier(T t)throws SQLException ;
     List<T> afficher() throws SQLException;
    
    
    
}
