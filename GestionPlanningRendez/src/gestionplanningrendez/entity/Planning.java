/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionplanningrendez.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class Planning implements Comparable<Planning>{
    
     private int id;
    private String nomP, descriptionP;
    private Date dateDebut, dateFin ;
    private int id_user;
    List <RendezVous> listRDV; ;

    public Planning(int id, String nomP, String descriptionP, Date dateDebut, Date dateFin,int user) {
        this.id = id;
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_user=user;
        listRDV = new ArrayList<>();
        
    }
    public Planning(String nomP, String descriptionP, Date dateDebut, Date dateFin,int user) {
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.id_user=user;
        listRDV = new ArrayList<>();
    }
    
    public Planning(String nomP, String descriptionP, Date dateDebut, Date dateFin) {
        this.nomP = nomP;
        this.descriptionP = descriptionP;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        listRDV = new ArrayList<>();
    }

    public Planning() {
            }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public List<RendezVous> getListRDV() {
        return listRDV;
    }

    public void setListRDV(List<RendezVous> listRDV) {
        this.listRDV = listRDV;
    }

    
    public void ajoutRDV(RendezVous rdv){
        listRDV.add(rdv);
    }

    @Override
    public String toString() {
        return "Planning{" + "id=" + id + ", nomP=" + nomP + ", descriptionP=" + descriptionP + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", id_user=" + id_user + ", listRDV=" + listRDV.toString() + '}';
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Planning p = (Planning) obj;
        if (this.id == p.id) {
            return true;
        }
        if (p == null) {
            return false;
        }
        if (getClass() != p.getClass()) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Planning o) {
        return o.getDateDebut().compareTo(this.getDateDebut()) ;
    }
    
}
