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
public class User {
    int id;
    String username;
    int tel;
    String role;

    public User() {
    }

    public User( String username, int tel, String role) {
    
        this.username = username;
        this.tel = tel;
        this.role = role;
    }
    public User(int id, String username, int tel, String role) {
        this.id=id;
        this.username = username;
        this.tel = tel;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", tel=" + tel + ", role=" + role + '}';
    }

   
    
    
    
    
    
}
