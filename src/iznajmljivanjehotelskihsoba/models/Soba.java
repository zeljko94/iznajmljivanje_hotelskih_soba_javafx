package iznajmljivanjehotelskihsoba.models;

import static iznajmljivanjehotelskihsoba.models.User.resultSetToUser;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Soba {

    private int id;
    private String brojSobe;
    private String opis;
    private int brojKreveta;
    private double cijena;
    
    public Soba(){
        this.id = 0;
        this.brojKreveta = 0;
        this.brojSobe = "";
        this.opis = "";
        this.cijena = 0f;
    }

    public Soba(String broj_sobe, String opis, int broj_kreveta, double cijena) {
        this.id = 0;
        this.brojSobe = broj_sobe;
        this.opis = opis;
        this.brojKreveta = broj_kreveta;
        this.cijena = cijena;
    }
    
    public static Soba resultSetToSoba(ResultSet rs){
        Soba soba = null;
        try {
            soba = new Soba(rs.getString("broj_sobe"), rs.getString("opis"), rs.getInt("broj_kreveta"), rs.getDouble("cijena"));
            soba.setId(rs.getInt("id"));
        } catch (SQLException ex) {
            Logger.getLogger(Soba.class.getName()).log(Level.SEVERE, null, ex);
        }
        return soba;
    }
    
    public static Soba dohvatiPrekoId(int id){
        Soba soba = null;
        DB db = new DB();
        List<Object> params = Arrays.asList(id);
        db.select("SELECT * FROM soba WHERE id=?", params);
        try {
            while(db.getResultSet().next()){
                soba = resultSetToSoba(db.getResultSet());
                return soba;
            }
        } catch (SQLException ex) {
            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return soba;
    }
    
    public void unesiUBazu(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.brojSobe, this.opis, this.brojKreveta, this.cijena);
        db.insert("INSERT INTO soba(broj_sobe, opis, broj_kreveta, cijena) VALUES(?,?,?,?)", params);
        db.disconnect();
    }
    
    public static ArrayList<Soba> dohvatiSveIzBaze(){
        ArrayList<Soba> sobe = new ArrayList<>();
        DB db = new DB();
        List<Object> params = Arrays.asList();
        db.select("SELECT * FROM soba", params);
        try {
            while(db.getResultSet().next()){
                sobe.add(resultSetToSoba(db.getResultSet()));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Soba.class.getName()).log(Level.SEVERE, null, ex);
        }
        db.disconnect();
        return sobe;
    }  
    
    public void update(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.brojSobe, this.opis, this.brojKreveta, this.cijena, this.id);
        db.update("UPDATE soba SET broj_sobe=?, opis=?, broj_kreveta=?, cijena=? WHERE id=?", params);
        db.disconnect();
    }
    
    public void brisiIzBaze(){
        DB db = new DB();
        List<Object> params = Arrays.asList(this.id);
        db.delete("DELETE FROM soba WHERE id=?", params);
        db.disconnect();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrojSobe() {
        return brojSobe;
    }

    public void setBrojSobe(String brojSobe) {
        this.brojSobe = brojSobe;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getBrojKreveta() {
        return brojKreveta;
    }

    public void setBrojKreveta(int brojKreveta) {
        this.brojKreveta = brojKreveta;
    }

    public double getCijena() {
        return cijena;
    }

    public void setCijena(double cijena) {
        this.cijena = cijena;
    }
    
    
    @Override
    public String toString(){
        return "Soba " + this.brojSobe;
    }
}
