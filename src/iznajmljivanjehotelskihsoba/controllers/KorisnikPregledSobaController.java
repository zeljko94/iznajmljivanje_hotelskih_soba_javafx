package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.NovaRezervacijaOdabranaSoba;
import iznajmljivanjehotelskihsoba.models.Soba;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

public class KorisnikPregledSobaController implements Initializable {

    @FXML
    TableView<Soba> tableSobe;
    
    @FXML
    TableColumn<Soba, String> tableColBrojSobe;
    
    @FXML
    TableColumn<Soba, String> tableColOpisSobe;
    
    @FXML
    TableColumn<Soba, Integer> tableColBrojKreveta;
    
    @FXML
    TableColumn<Soba, Double> tableColCijena;
    
    @FXML
    Button btnNazad;
    
    @FXML
    Button btnRezerviraj;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Soba> sobe = Soba.dohvatiSveIzBaze();
        ObservableList<Soba> data = FXCollections.observableArrayList(sobe);
        tableColBrojSobe.setCellValueFactory(new PropertyValueFactory<Soba, String>("BrojSobe"));
        tableColOpisSobe.setCellValueFactory(new PropertyValueFactory<Soba, String>("Opis"));
        tableColBrojKreveta.setCellValueFactory(new PropertyValueFactory<Soba, Integer>("BrojKreveta"));
        tableColCijena.setCellValueFactory(new PropertyValueFactory<Soba, Double>("Cijena"));
        tableSobe.setItems(data);
    }    
    
        public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = tableSobe.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikPocetna.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin - Pocetna");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void btnRezervirajOnClick(ActionEvent e){
        Soba soba = tableSobe.getSelectionModel().getSelectedItem();
        if(soba != null){
            NovaRezervacijaOdabranaSoba.setSoba(soba);
            
            Parent mainWindow = tableSobe.getParent();
            mainWindow.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikNovaRezervacija.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Nova rezervacija");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
