
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.LoggedUserSession;
import iznajmljivanjehotelskihsoba.helpers.NovaRezervacijaOdabranaSoba;
import iznajmljivanjehotelskihsoba.models.Rezervacija;
import iznajmljivanjehotelskihsoba.models.Soba;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class KorisnikNovaRezervacijaController implements Initializable {
    
    @FXML
    DatePicker datePickerPocetniDatum;
    
    @FXML
    DatePicker datePickerZavrsniDatum;
    
    @FXML
    ComboBox<Soba> comboBoxSoba;
    
    @FXML
    Label lblTotal;
    
    @FXML
    Label lblErrorMessage;
    
    @FXML
    Button btnRezerviraj;
    
    @FXML
    Button btnNazad;
    
    double TOTAL = 0.0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Soba> sobe = Soba.dohvatiSveIzBaze();
        ObservableList data = FXCollections.observableArrayList(sobe);
        comboBoxSoba.setItems(data);
        comboBoxSoba.setValue(NovaRezervacijaOdabranaSoba.getSoba());
        lblTotal.setText("TOTAL: " + TOTAL + " KM");
        
        
        comboBoxSoba.valueProperty().addListener(new ChangeListener<Soba>() {
            @Override 
            public void changed(ObservableValue ov, Soba t, Soba t1) {
                calculateTotal();
            }    
        });
        
        datePickerPocetniDatum.valueProperty().addListener((ov, oldValue, newValue) -> {
            calculateTotal();
        });
        
        datePickerZavrsniDatum.valueProperty().addListener((ov, oldValue, newValue) -> {
            calculateTotal();
        });
    }    
    
    public static long betweenDates(Date firstDate, Date secondDate)
    {
         return ChronoUnit.DAYS.between(Instant.ofEpochMilli(firstDate.getTime()), Instant.ofEpochMilli(secondDate.getTime())) + 1;
    }
    
    public void calculateTotal(){
        double total = 0f;
        String pDat = datePickerPocetniDatum.getValue() != null ? datePickerPocetniDatum.getValue().toString() : "";
        String zDat = datePickerZavrsniDatum.getValue() != null ? datePickerZavrsniDatum.getValue().toString() : "";
        Date pocDat = null;
        Date zavDat = null;
        Soba soba = comboBoxSoba.getSelectionModel().getSelectedItem();
        
        if(!pDat.equals("")){
            if(!zDat.equals("")){
                if(soba != null){
                    pocDat = Date.valueOf(pDat);
                    zavDat = Date.valueOf(zDat);
                    long days = betweenDates(pocDat, zavDat);
                    double cijena = soba.getCijena();
                    
                    total = days * cijena;
                }
            }
        }
        
        TOTAL = total;
        lblTotal.setText("TOTAL: " + TOTAL + " KM");
    }
    
    public void btnRezervirajOnClick(ActionEvent e){
        Soba soba = comboBoxSoba.getSelectionModel().getSelectedItem();
        String pocDatum = datePickerPocetniDatum.getValue() != null ? datePickerPocetniDatum.getValue().toString() : "";
        String zavDatum = datePickerZavrsniDatum.getValue() != null ? datePickerZavrsniDatum.getValue().toString() : "";
        Date pocetniDatum = null;
        Date zavrsniDatum = null;

        if(!pocDatum.equals("")){
            pocetniDatum = Date.valueOf(pocDatum);
            if(!zavDatum.equals("")){
                zavrsniDatum = Date.valueOf(zavDatum);
                if(soba != null){
                    if(zavrsniDatum.compareTo(pocetniDatum) >= 0){
                        Rezervacija nova = new Rezervacija(pocetniDatum, zavrsniDatum, LoggedUserSession.getUser().getId(), soba.getId());
                        
                        if(nova.jeLiSlobodan()){
                            nova.unesiUBazu();
                            
                            lblErrorMessage.setText("Uspješna rezervacija!");
                            
                        Parent mainWindow = lblErrorMessage.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikPregledRezervacija.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Pregled rezervacija");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                        else{
                            lblErrorMessage.setText(soba.toString() + " je zauzeta za zadani period!");
                        }
                    }
                    else{
                        lblErrorMessage.setText("Pogrešan unos za datume!");
                    }
                }
                else{
                    lblErrorMessage.setText("Odaberite sobu!");
                }
            }
            else{
                lblErrorMessage.setText("Unesite završni datum!");
            }
        }
        else{
            lblErrorMessage.setText("Unesite početni datum!");
        }
    }
    
    public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = btnNazad.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikPregledSoba.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Pregled rezervacija");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
