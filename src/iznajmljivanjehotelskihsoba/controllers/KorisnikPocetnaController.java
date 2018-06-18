
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class KorisnikPocetnaController implements Initializable {

    @FXML
    ImageView btnLogout;
    
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
    
    public void btnRezervacijeOnMouseEnter(){
        this.GlowOn(this.btnRezervacije);
    }
    
    public void btnRezervacijeOnMouseLeave(){
        this.GlowOff(this.btnRezervacije);
    }
    
    public void btnSobeOnMouseEnter(){
        this.GlowOn(this.btnSobe);
    }
    
    public void btnSobeOnMouseLeave(){
        this.GlowOff(this.btnSobe);
    }
    
    public void btnLogoutOnMouseEnter(){
        this.GlowOn(this.btnLogout);
    }
    
    public void btnLogoutOnMouseLeave(){
        this.GlowOff(this.btnLogout);
    }
    
    public void btnRezervacijeOnClick(){
        Parent mainWindow = btnRezervacije.getParent();
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
    
    public void btnSobeOnClick(){
        Parent mainWindow = btnSobe.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikPregledSoba.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Pregled soba");
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
