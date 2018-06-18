
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.helpers.EditUserSession;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModeratorIzmjeniKorisnikaController implements Initializable {

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
    Button btnSpremi;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @FXML
    Label lblErrorMessage;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtIme.setText(EditUserSession.getUser().getIme());
        txtPrezime.setText(EditUserSession.getUser().getPrezime());
        txtUsername.setText(EditUserSession.getUser().getUsername());
        txtPassword.setText(EditUserSession.getUser().getPassword());
        txtEmail.setText(EditUserSession.getUser().getEmail());
    }    
    
    public void btnSpremiOnClick(ActionEvent e){
                String ime = txtIme.getText();
        String prezime = txtPrezime.getText();
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String email = txtEmail.getText();
        String privilegije = "korisnik";
        
        if(!ime.equals("")){
            if(!prezime.equals(""))
            {
                if(!username.equals("")){
                    if(!email.equals("")){
                        if(!password.equals("")){
                                User user = (User) EditUserSession.getUser();
                                user.setId(user.getId());
                                user.setIme(ime);
                                user.setPrezime(prezime);
                                user.setEmail(email);
                                user.setPassword(password);
                                user.setUsername(username);
                                user.setPrivilegije(privilegije);
                                user.update();
                                
                                Parent mainWindow = txtEmail.getParent();
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
    public void btnResetOnClick(ActionEvent e){}
    
    public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = txtEmail.getParent();
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
    
}
