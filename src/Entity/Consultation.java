/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Date;

/**
 *
 * @author ASSOUMA
 */
public class Consultation {
    int id;
    int num_c;
    Date date_c;
    String text;

    public Consultation() {
    }

    public Consultation(int num_c, Date date_c, String text) {
    
        this.num_c = num_c;
        this.date_c = date_c;
        this.text = text;
    }
public Consultation(int id,int num_c, Date date_c, String text) {
    this.id=id;
        this.num_c = num_c;
        this.date_c = date_c;
        this.text = text;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_c() {
        return num_c;
    }

    public void setNum_c(int num_c) {
        this.num_c = num_c;
    }

    public Date getDate_c() {
        return date_c;
    }

    public void setDate_c(Date date_c) {
        this.date_c = date_c;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Consultation{" + "id=" + id + ", num_c=" + num_c + ", date_c=" + date_c + ", text=" + text + '}';
    }
    
    
    
    
    
}
