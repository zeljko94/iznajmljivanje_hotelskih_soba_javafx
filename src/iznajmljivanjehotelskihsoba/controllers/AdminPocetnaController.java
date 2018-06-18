/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.LoggedUserSession;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owner
 */
public class AdminPocetnaController implements Initializable {

    @FXML
    ImageView btnLogout;
    
    @FXML
    ImageView btnUsers;
    
    @FXML
    ImageView btnRezervacije;
    
    @FXML
    ImageView btnSobe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void GlowOn(ImageView img){
        Glow glow = new Glow();
        glow.setLevel(0.9);
        img.setEffect(glow);
    }
    
    public void GlowOff(ImageView img){
        img.setEffect(null);
    }
    
    
    
    public void btnUsersOnMouseEnter(){
        GlowOn(btnUsers);
    }
    
    public void btnUsersOnMouseLeave(){
        GlowOff(btnUsers);
    }
    
    public void btnRezervacijeOnMouseEnter(){
        GlowOn(btnRezervacije);
    }
    public void btnRezervacijeOnMouseLeave(){
        GlowOff(btnRezervacije);
    }
    
    public void btnSobeOnMouseEnter(){
        GlowOn(btnSobe);
    }
    public void btnSobeOnMouseLeave(){
        GlowOff(btnSobe);
    }
    
    public void btnLogoutOnMouseEnter(){
        GlowOn(btnLogout);
    }
    public void btnLogoutOnMouseLeave(){
        GlowOff(btnLogout);
    }
    
    
    
    public void btnUsersOnClick(){
                        Parent mainWindow = btnUsers.getParent();
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
    
    public void btnRezervacijeOnClick(){
        Parent mainWindow = btnRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminPregledRezervacija.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin - Pregled rezervacija");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnSobeOnClick(){
        Parent mainWindow = btnRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminPregledSoba.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Admin - Pregled soba");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnLogoutOnClick(){
        LoggedUserSession.setUser(null);
        Parent mainWindow = btnRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/Login.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
