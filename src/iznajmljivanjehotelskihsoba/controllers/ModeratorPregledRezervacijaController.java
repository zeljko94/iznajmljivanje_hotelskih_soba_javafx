
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.models.Rezervacija;
import iznajmljivanjehotelskihsoba.models.Soba;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class ModeratorPregledRezervacijaController implements Initializable {

    
    @FXML
    TableView<Rezervacija> tableRezervacije;
    
    @FXML
    TableColumn<Rezervacija, Date> tblColPocetniDatum;
    
    @FXML
    TableColumn<Rezervacija, Date> tblColZavrsniDatum;
    
    @FXML
    TableColumn<Rezervacija, String> tblColImeIPrezimeKorisnika;
    
    @FXML
    TableColumn<Rezervacija, Double> tblColCijena;
    
    @FXML
    TableColumn<Rezervacija, String> tblColBrojSobe;
    
    @FXML
    Button btnNazad;
    
        private static long daysBetweenDates(java.util.Date d1, java.util.Date d2){
        if(d1.equals(d2)) return 1;
        return ChronoUnit.DAYS.between(Instant.ofEpochMilli(d1.getTime()), Instant.ofEpochMilli(d2.getTime()))+1;
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Rezervacija> rezervacije = Rezervacija.dohvatiSveIzBaze();
        for(Rezervacija r: rezervacije){
            r.setCijena(daysBetweenDates(r.getPocetniDatum(), r.getZavrsniDatum()) * Soba.dohvatiPrekoId(r.getIdSobe()).getCijena());
        }
        ObservableList<Rezervacija> data = FXCollections.observableArrayList(rezervacije);
        tblColPocetniDatum.setCellValueFactory(new PropertyValueFactory<Rezervacija, Date>("PocetniDatum"));
        tblColZavrsniDatum.setCellValueFactory(new PropertyValueFactory<Rezervacija, Date>("ZavrsniDatum"));
        tblColCijena.setCellValueFactory(new PropertyValueFactory<Rezervacija, Double>("Cijena"));
        tblColImeIPrezimeKorisnika.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("ImeIPrezimeKorisnika"));
        tblColBrojSobe.setCellValueFactory(new PropertyValueFactory<Rezervacija, String>("BrojSobe"));
        tableRezervacije.setItems(data);
    }    
    
        public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = tableRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorPocetna.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moderator - Početna");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
