/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author owner
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private String ime;
    private String prezime;
    private String privilegije;

    public User(String username, String password, String email, String ime, String prezime, String privilegije) {
        this.id = 0;
        this.username = username;
        this.password = password;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
        this.privilegije = privilegije;
    }

    public User() {
        this.id = 0;
        this.username = "";
        this.password = "";
        this.email = "";
        this.ime = "";
        this.prezime = "";
        this.privilegije = "";
    }

    public static User resultSetToUser(ResultSet rs){
        User user = null;
        try {
            user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("ime"), rs.getString("prezime"), rs.getString("privilegije"));
            user.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }
    
    public static User dohvatiPrekoId(int id){
        User user = null;
        DB db = new DB();
        List<Object> params = Arrays.asList(id);
        db.select("SELECT * FROM user WHERE id=?", params);
        try {
            while(db.getResultSet().next()){
                user = resultSetToUser(db.getResultSet());
                return user;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return user;
    }
    public static ArrayList<User> dohvatiSveIzBaze(){
        ArrayList<User> users = new ArrayList<>();
        List<Object> params = Arrays.asList();
        DB db = new DB();
        db.select("SELECT * FROM user", params);
        
        try {
            while(db.getResultSet().next()){
                users.add(resultSetToUser(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return users;
    }
    
    public static User login(String username, String password){
        User user = null;
        
        DB db = new DB();
        List<Object> params = Arrays.asList(username, password);
        db.select("SELECT * FROM user WHERE username=? AND password=?", params);
        try {
            while(db.getResultSet().next()){
                user = resultSetToUser(db.getResultSet());
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return user;
    }
    
    public void brisiIzBaze(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.id);
        db.delete("DELETE FROM user WHERE id=?", params);
        db.disconnect();
    }
    
    public void unesiUBazu(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.ime, this.prezime, this.username, this.password, this.email, this.privilegije);
        db.insert("INSERT INTO user(ime, prezime, username, password, email, privilegije) VALUES(?,?,?,?,?,?)", params);
        db.disconnect();
    }
    
    public void update(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.ime, this.prezime, this.username, this.password, this.email, this.privilegije, this.id);
        db.update("UPDATE user SET ime=?, prezime=?, username=?, password=?, email=?, privilegije=? WHERE id=?", params);
        db.disconnect();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getPrivilegije() {
        return privilegije;
    }

    public void setPrivilegije(String privilegije) {
        this.privilegije = privilegije;
    }
    
 
    
}
