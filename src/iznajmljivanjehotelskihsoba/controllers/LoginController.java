/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.IznajmljivanjeHotelskihSoba;
import iznajmljivanjehotelskihsoba.helpers.LoggedUserSession;
import iznajmljivanjehotelskihsoba.models.User;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author owner
 */
public class LoginController implements Initializable {

    @FXML
    TextField txtUsername;
    
    @FXML
    PasswordField txtPassword;
    
    @FXML
    Label lblErrorMsg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void btnLogin_onClick(ActionEvent e){
        String username = String.valueOf(txtUsername.getText());
        String password = String.valueOf(txtPassword.getText());
        
        if(!username.equals("")){
            if(!password.equals("")){
                User user = User.login(username, password);
                
                if(user != null){
                    LoggedUserSession.setUser(user);
                    if(user.getPrivilegije().equals("admin")){
                        Parent mainWindow = txtUsername.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/AdminPocetna.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Admin početna");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(user.getPrivilegije().equals("moderator")){
                        Parent mainWindow = txtUsername.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorPocetna.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Moderator početna");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    else if(user.getPrivilegije().equals("korisnik")){
                        Parent mainWindow = txtUsername.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/KorisnikPocetna.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Početna");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
                else{
                    lblErrorMsg.setText("Greška prilikom logiranja!");
                }
            }
            else{
                lblErrorMsg.setText("Unesite lozinku!");
            }
        }
        else{
            lblErrorMsg.setText("Unesite korisničko ime!");
        }
    }
    
    public void btnRegister_onClick(ActionEvent e){
        
    }
}
