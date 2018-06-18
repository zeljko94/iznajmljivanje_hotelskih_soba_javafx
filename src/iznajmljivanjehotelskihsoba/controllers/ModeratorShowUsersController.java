
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ModeratorShowUsersController implements Initializable {

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
        //tableColPassword.setCellValueFactory(new PropertyValueFactory<User, String>("Password"));
        tableColPrivilegije.setCellValueFactory(new PropertyValueFactory<User, String>("Privilegije"));
        tableUsers.setItems(data);
    }    
    
    public void btnDodajOnClick(ActionEvent e){
                        Parent mainWindow = btnDodaj.getParent();
                        mainWindow.getScene().getWindow().hide();
                        Stage stage = new Stage();
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorDodajKorisnika.fxml"));
                            
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.setTitle("Moderator - Dodaj korisnika");
                            stage.show();
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
    }
    
    public void btnBrisiOnClick(ActionEvent e){
        if(tableUsers.getSelectionModel().getSelectedItem() != null){
            User user = (User) tableUsers.getSelectionModel().getSelectedItem();
            if(user.getPrivilegije().equals("korisnik")){
                user.brisiIzBaze();


                ArrayList<User> users = User.dohvatiSveIzBaze();
                ObservableList<User> data = FXCollections.observableArrayList(users);
                tableUsers.setItems(data);
            }
        }
    }
    
    public void btnIzmjeniOnClick(ActionEvent e){
        if(tableUsers.getSelectionModel().getSelectedItem() != null){
            User user = (User) tableUsers.getSelectionModel().getSelectedItem();
            
            if(user.getPrivilegije().equals("korisnik")){
                EditUserSession.setUser(user);

                Parent mainWindow = btnDodaj.getParent();
                mainWindow.getScene().getWindow().hide();
                Stage stage = new Stage();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("iznajmljivanjehotelskihsoba/views/ModeratorIzmjeniKorisnika.fxml"));

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.setTitle("Moderator - Izmjeni korisnika");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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
