/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.Dossier;
import java.sql.SQLDataException;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * @author Asus
 */
public interface DossierServiceInterface <D> {
    public void addDossier (Dossier d) throws SQLDataException;
    public void deleteDossier(int d) throws SQLDataException;
    public void ModifierDossier(Dossier d) throws SQLDataException;
    public List <Dossier> getAllDossier() throws SQLDataException;
    public List <Dossier>DossierStartWithP() throws SQLDataException;
    public TreeSet <Dossier>dossierSelonDes() throws SQLDataException;
    public TreeSet <Dossier>dossierSelonIdPatient() throws SQLDataException;
    
}
