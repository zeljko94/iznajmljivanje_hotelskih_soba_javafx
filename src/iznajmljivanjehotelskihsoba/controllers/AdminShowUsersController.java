/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.EditUserSession;
import iznajmljivanjehotelskihsoba.models.User;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owner
 */
public class AdminShowUsersController implements Initializable {


    @FXML
    TableView<User> tableUsers;
    
    @FXML
    TableColumn<User, String> tableColIme;
    
    @FXML
    TableColumn<User, String> tableColPrezime;
    
    @FXML
    TableColumn<User, String> tableColUsername;
    
    @FXML
    TableColumn<User, String> tableColEmail;
    
    @FXML
    TableColumn<User, String> tableColPassword;
    
    @FXML
    TableColumn<User, String> tableColPrivilegije;

    @FXML
    Button btnDodaj;
    
    @FXML
    Button btnBrisi;
    
    @FXML
    Button btnIzmjeni;
    
    @FXML
    Button btnNazad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> users = User.dohvatiSveIzBaze();
        ObservableList<User> data = FXCollections.observableArrayList(users);
        tableColIme.setCellValueFactory(new PropertyValueFactory<User, String>("Ime"));
        tableColPrezime.setCellValueFactory(new PropertyValueFactory<User, String>("Prezime"));
        tableColUsername.setCellValueFactory(new PropertyValueFactory<User, String>("Username"));
        tableColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
        tableColPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        tableColPrivilegije.setCellValueFactory(new PropertyValueFactory<User, String>("Privilegije"));
        tableUsers.setItems(data);
    }    
    
    public void btnDodajOnClick(ActionEvent e){
                        Parent mainWindow = btnDodaj.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminDodajKorisnika.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Admin - Dodaj korisnika");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    public void btnBrisiOnClick(ActionEvent e){
        if(tableUsers.getSelectionModel().getSelectedItem() != null){
            User user = (User) tableUsers.getSelectionModel().getSelectedItem();
            user.brisiIzBaze();
            
            
            ArrayList<User> users = User.dohvatiSveIzBaze();
            ObservableList<User> data = FXCollections.observableArrayList(users);
            tableUsers.setItems(data);
        }
    }
    
    public void btnIzmjeniOnClick(ActionEvent e){
        if(tableUsers.getSelectionModel().getSelectedItem() != null){
            User user = (User) tableUsers.getSelectionModel().getSelectedItem();
            EditUserSession.setUser(user);
            
            Parent mainWindow = btnDodaj.getParent();
            mainWindow.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminIzmjeniKorisnika.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Admin - Izmjeni korisnika");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void btnNazadOnClick(ActionEvent e){
            Parent mainWindow = btnDodaj.getParent();
            mainWindow.getScene().getWindow().hide();
            Stage stage = new Stage();
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminPocetna.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Admin - Pocetna");
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
