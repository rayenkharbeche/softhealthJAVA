/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Fichier;
import java.sql.SQLDataException;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Asus
 */
public interface FichierServiceInterface <F> {
    public void addFichier (Fichier f) throws SQLDataException;
    public void deleteFichier(int id) throws SQLDataException;
    public void ModifierFichier(Fichier f) throws SQLDataException;
    public List <Fichier> getAllFichier() throws SQLDataException;
    public List <Fichier>FichierStartWithT()throws SQLDataException;
    public TreeSet <Fichier>FichierSelonDes() throws SQLDataException;
    

}
