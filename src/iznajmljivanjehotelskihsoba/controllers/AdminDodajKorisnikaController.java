/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.models.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AdminDodajKorisnikaController implements Initializable {

    @FXML
    TextField txtIme;
    
    @FXML
    TextField txtPrezime;
    
    @FXML
    TextField txtUsername;
    
    @FXML
    TextField txtEmail;
    
    @FXML
    TextField txtPassword;
    
    @FXML
    ComboBox<String> comboPrivilegije;
    
    @FXML
    Button btnSpremi;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    Label lblErrorMessage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<String> privilegije = new ArrayList<>();
        privilegije.add("admin");
        privilegije.add("korisnik");
        privilegije.add("moderator");
        comboPrivilegije.setItems(FXCollections.observableArrayList(privilegije));
    }    
    
    public void btnSpremiOnClick(ActionEvent e){
        String ime = txtIme.getText();
        String prezime = txtPrezime.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String privilegije = comboPrivilegije.getSelectionModel().getSelectedItem() != null ? comboPrivilegije.getSelectionModel().getSelectedItem() : "";
        
        if(!ime.equals("")){
            if(!prezime.equals(""))
            {
                if(!username.equals("")){
                    if(!email.equals("")){
                        if(!password.equals("")){
                            if(!privilegije.equals("")){
                                User user = new User(username, password, email, ime, prezime, privilegije);
                                user.unesiUBazu();
                                
                            Parent mainWindow = txtEmail.getParent();
                            mainWindow.getScene().getWindow().hide();
                            Stage stage = new Stage();
                            Parent root;
                            try {
                                root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminShowUsers.fxml"));

                                Scene scene = new Scene(root);
                                stage.setScene(scene);
                                stage.setTitle("Admin - Prikaz korisnika");
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            }
                            else{
                                lblErrorMessage.setText("Odaberite privilegije korisnika!");
                            }
                        }
                        else{
                            lblErrorMessage.setText("Unesite lozinku!");
                        }
                    }
                    else{
                        lblErrorMessage.setText("Unesite email!");
                    }
                }
                else{
                    lblErrorMessage.setText("Unesite korisničko ime!");
                }
            }
            else{
                lblErrorMessage.setText("Unesite prezime!");
            }
        }
        else{
            lblErrorMessage.setText("Unesite ime!");
        }
    }
    public void btnResetOnClick(ActionEvent e){
    }
    
    public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = txtEmail.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminShowUsers.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin - Prikaz korisnika");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
