/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.service;

import java.util.List;

/**
 *
 * @author LENOVO
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(T t);
    public List<T> afficher();
    
    
}
