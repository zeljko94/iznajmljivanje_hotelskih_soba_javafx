
package iznajmljivanjehotelskihsoba.controllers;

import iznajmljivanjehotelskihsoba.models.Soba;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModeratorDodajSobuController implements Initializable {

        @FXML
    TextField txtBrojSobe;
    
    @FXML
    TextField txtBrojKreveta;
    
    @FXML
    TextField txtCijena;
    
    @FXML
    TextField txtOpis;
    
    @FXML
    Label lblErrorMessage;
    
    @FXML
    Button btnSpremi;
    
    @FXML
    Button btnReset;
    
    @FXML
    Button btnNazad;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
        public void btnSpremiOnClick(ActionEvent e){
        String brojSobe = txtBrojSobe.getText();
        String opis = txtOpis.getText();
        Integer brojKreveta = 0;
        Double cijena = 0.0;
        
        try{
            brojKreveta = Integer.parseInt(txtBrojKreveta.getText());
        } catch(NumberFormatException ex){
            lblErrorMessage.setText("Pogrešan unos za broj kreveta!");
            return;
        }
        
        try {
            cijena = Double.parseDouble(txtCijena.getText());
        } catch (NumberFormatException ex) {
            lblErrorMessage.setText("Pogrešan unos za cijenu sobe!");
            return;
        }
        
        
        if(!brojSobe.equals("")){
            if(!brojKreveta.equals(0)){
                if(!cijena.equals(0.0)){
                    if(!opis.equals("")){
                        
                        Soba soba = new Soba(brojSobe, opis, brojKreveta, cijena);
                        soba.unesiUBazu();
                        
                        Parent mainWindow = btnSpremi.getParent();
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
                    else{
                        lblErrorMessage.setText("Unesite opis sobe!");
                    }
                }
                else{
                    lblErrorMessage.setText("Unesite cijenu!");
                }
            }
            else{
                lblErrorMessage.setText("Unesite broj kreveta!");
            }
        }
        else{
            lblErrorMessage.setText("Unesite broj sobe!");
        }
    }
    public void btnResetOnClick(ActionEvent e){}
    
    public void btnNazadOnClick(ActionEvent e){
        Parent mainWindow = btnNazad.getParent();
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
}
