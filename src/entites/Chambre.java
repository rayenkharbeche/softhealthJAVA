/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.util.Comparator;

/**
 *
 * @author Amina
 */
public class Chambre  {
    private int num;
    private int numetage;
    private int nbrplace;
    private String service;
    private String bloc;
    private int category_id;
    private String category;
    private String etat;
    private String traitement;

    

    public Chambre(int num, int numetage, int nbrplace, String service, String bloc, int category_id, String etat, String traitement) {
        this.num = num;
        this.numetage = numetage;
        this.nbrplace = nbrplace;
        this.service = service;
        this.bloc = bloc;
        this.category_id = category_id;
        this.etat = etat;
        this.traitement = traitement;
    }

    public Chambre(int num, int numetage, int nbrplace, String service, String bloc, String category, String etat, String traitement) {
        this.num = num;
        this.numetage = numetage;
        this.nbrplace = nbrplace;
        this.service = service;
        this.bloc = bloc;
        this.category = category;
        this.etat = etat;
        this.traitement = traitement;
    }

    public Chambre(int numetage, int nbrplace, String service, String bloc, String category, String etat, String traitement) {
        this.numetage = numetage;
        this.nbrplace = nbrplace;
        this.service = service;
        this.bloc = bloc;
        this.category = category;
        this.etat = etat;
        this.traitement = traitement;
    }

    public Chambre(int num, int numetage, int nbrplace, String service, String bloc, int category_id, String category, String etat, String traitement) {
        this.num = num;
        this.numetage = numetage;
        this.nbrplace = nbrplace;
        this.service = service;
        this.bloc = bloc;
        this.category_id = category_id;
        this.category = category;
        this.etat = etat;
        this.traitement = traitement;
    }
    
    
    
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
      
    

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    
               

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNumetage() {
        return numetage;
    }

    public void setNumetage(int numetage) {
        this.numetage = numetage;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getBloc() {
        return bloc;
    }

    public void setBloc(String bloc) {
        this.bloc = bloc;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getTraitement() {
        return traitement;
    }

    public void setTraitement(String traitement) {
        this.traitement = traitement;
    }

    
    @Override
    public String toString() {
        return "Chambre{" + "num=" + num + ", numetage=" + numetage + ", nbrplace=" + nbrplace + ", service=" + service + ", bloc=" + bloc + ", category_id=" + category_id + '}';
    }

   
               
    
}
  