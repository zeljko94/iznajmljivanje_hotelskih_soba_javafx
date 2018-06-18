
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.EditSobaSession;
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


public class ModeratorPregledSobaController implements Initializable {

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
    Button btnDodaj;
    
    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnNazad;
    
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
    
    public void btnDodajOnClick(ActionEvent e){
        Parent mainWindow = btnDodaj.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorDodajSobu.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moderator - Dodaj sobu");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void btnIzmjeniOnClick(ActionEvent e){
        Soba soba = (Soba) tableSobe.getSelectionModel().getSelectedItem();
        if(soba != null){
            EditSobaSession.setSoba(soba);

            Parent mainWindow = btnIzmjeni.getParent();
            mainWindow.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorIzmjenaSobe.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Moderator - Izmjeni sobu");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void btnBrisiOnClick(ActionEvent e){
        Soba soba = (Soba)tableSobe.getSelectionModel().getSelectedItem();
        if(soba != null){
            soba.brisiIzBaze();
            
            ArrayList<Soba> sobe = Soba.dohvatiSveIzBaze();
            ObservableList<Soba> data = FXCollections.observableArrayList(sobe);
            tableSobe.setItems(data);
        }
    }
    
    public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = btnDodaj.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorPocetna.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moderator - Pocetna");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
