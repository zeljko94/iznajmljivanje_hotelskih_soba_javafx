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
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.remote.rmi.RMIServer;

/**
 *
 * @author owner
 */
public class Rezervacija {
    private int id;
    private Date pocetniDatum;
    private Date zavrsniDatum;
    private int idKorisnika;
    private int idSobe;
    private double cijena;
    
    public Rezervacija(){
        this.id = 0;
        this.pocetniDatum = new Date();
        this.zavrsniDatum = new Date();
        this.idKorisnika = 0;
        this.idSobe = 0;
    }

    public Rezervacija(Date pocetniDatum, Date zavrsniDatum, int idKorisnika, int idSobe) {
        this.id = 0;
        this.pocetniDatum = pocetniDatum;
        this.zavrsniDatum = zavrsniDatum;
        this.idKorisnika = idKorisnika;
        this.idSobe = idSobe;
    }
    
    public boolean jeLiSlobodan(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.pocetniDatum, this.zavrsniDatum, this.pocetniDatum, this.zavrsniDatum, this.idSobe);
        db.select("SELECT * FROM rezervacija WHERE ((pocetni_datum BETWEEN ? AND ?) OR (zavrsni_datum BETWEEN ? AND ?)) AND id_sobe=?", params);
        try {
            while(db.getResultSet().next()){
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rezervacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return true;
    }
    public static Rezervacija resultSetToRezervacija(ResultSet rs){
        Rezervacija r = null;
        try {
            r = new Rezervacija(rs.getDate("pocetni_datum"), rs.getDate("zavrsni_datum"), rs.getInt("id_korisnika"), rs.getInt("id_sobe"));
            r.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(Rezervacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public void unesiUBazu(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.pocetniDatum, this.zavrsniDatum, this.idKorisnika, this.idSobe);
        db.insert("INSERT INTO rezervacija(pocetni_datum, zavrsni_datum, id_korisnika, id_sobe) VALUES(?,?,?,?)", params);
        db.disconnect();
    }
    
    public void brisiIzBaze(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.id);
        db.delete("DELETE FROM rezervacija WHERE id=?", params);
        db.disconnect();
    }

        public static ArrayList<Rezervacija> dohvatiSveIzBaze(){
        ArrayList<Rezervacija> rezervacije = new ArrayList<>();
        DB db = new DB();
        List<Object> params = Arrays.asList();
        db.select("SELECT * FROM rezervacija", params);
        try {
            while(db.getResultSet().next()){
                rezervacije.add(resultSetToRezervacija(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rezervacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return rezervacije;
    }
        
    public static ArrayList<Rezervacija> dohvatiKorisnikove(int korisnik_id){
        ArrayList<Rezervacija> rezervacije = new ArrayList<>();
        DB db = new DB();
        List<Object> params = Arrays.asList(korisnik_id);
        db.select("SELECT * FROM rezervacija WHERE id_korisnika=?", params);
        try {
            while(db.getResultSet().next()){
                rezervacije.add(resultSetToRezervacija(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Rezervacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return rezervacije;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPocetniDatum() {
        return pocetniDatum;
    }

    public void setPocetniDatum(Date pocetniDatum) {
        this.pocetniDatum = pocetniDatum;
    }

    public Date getZavrsniDatum() {
        return zavrsniDatum;
    }

    public void setZavrsniDatum(Date zavrsniDatum) {
        this.zavrsniDatum = zavrsniDatum;
    }

    public int getIdKorisnika() {
        return idKorisnika;
    }

    public void setIdKorisnika(int idKorisnika) {
        this.idKorisnika = idKorisnika;
    }

    public int getIdSobe() {
        return idSobe;
    }

    public void setIdSobe(int idSobe) {
        this.idSobe = idSobe;
    }
    
    public double getCijena(){
        return this.cijena;
    }
    
    public void setCijena(double c){
        this.cijena = c;
    }
    
    
    public String getImeIPrezimeKorisnika(){
        User user = User.dohvatiPrekoId(this.idKorisnika);
        return user != null ? user.getIme() + " " + user.getPrezime() : "";
    }
    
    public String getBrojSobe(){
        Soba soba = Soba.dohvatiPrekoId(this.idSobe);
        return soba != null ? soba.toString() : "";
    }
    
}
