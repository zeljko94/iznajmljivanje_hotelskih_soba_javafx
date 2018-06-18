
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
import javafx.stage.Stage;


public class ModeratorPocetnaController implements Initializable {

    @FXML
    Button btnLogout;
    
    @FXML
    Button btnUsers;
    
    @FXML
    Button btnRezervacije;
    
    @FXML
    Button btnSobe;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnUsersOnClick(ActionEvent e){
                        Parent mainWindow = btnUsers.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorShowUsers.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Moderator - Prikaz korisnika");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    public void btnRezervacijeOnClick(ActionEvent e){
        Parent mainWindow = btnRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorPregledRezervacija.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moderator - Pregled rezervacija");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnSobeOnClick(ActionEvent e){
        Parent mainWindow = btnRezervacije.getParent();
        mainWindow.getScene().getWindow().hide();
        Stage stage = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorPregledSoba.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Moderator - Pregled soba");
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void btnLogoutOnClick(ActionEvent e){
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
