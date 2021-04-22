/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IServices;

import Entity.Ordonnance;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASSOUMA
 */
public interface OrdoService <O> {
       public abstract void ajouter(O o) throws SQLException;
     public boolean supprimer(int id) throws SQLException;
    public void modifier(O o) throws SQLException;
    public  List<O> afficher() throws SQLException;
    
    public List<Ordonnance> ListOrdoOrderByDesc() throws SQLException;
    public List<Ordonnance> ListOrdoOrderByAsc() throws SQLException;

    
}
