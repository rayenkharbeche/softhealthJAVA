/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author ASSOUMA
 */






public class Médicament  {

      public int id;
       private int code;
       public String name;
       public int categorie_id;
       private int prix;
       private int stock;
       private String categorie;

    
    
    
    
    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
   
     
  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    @Override
    public String toString() {
        return "M\u00e9dicament{" + "id=" + id + ", code=" + code + ", name=" + name + ", categorie_id=" + categorie + ", prix=" + prix + ", stock=" + stock + '}';
    }

    public Médicament( int code, String name, int prix, int stock, String categorie) {
     
        this.code = code;
        this.name = name;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
    }
    
     public Médicament( int code, String name, String categorie,int prix, int stock ) {
     
        this.code = code;
        this.name = name;
        this.prix = prix;
        this.stock = stock;
        this.categorie = categorie;
    }

    public Médicament(int id, int code, String name, int categorie_id, int prix, int stock) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.categorie_id = categorie_id;
        this.prix = prix;
        this.stock = stock;
    }

   
    public Médicament() {
    }

    public Médicament(int code, String name, int categorie_id, int prix, int stock) {
        this.code = code;
        this.name = name;
        this.categorie_id = categorie_id;
        this.prix = prix;
        this.stock = stock;
    }
    
    
   
      

}
