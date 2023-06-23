package com.example.projet_bibliotheque;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * Controller permettant de gerer les acces users (connexion/inscription)
 */
public class ConnexionController implements Initializable  {
    private Map<String, String> validUsers = new HashMap<String, String>();

    @FXML private Label labelConnexionInscription;
    @FXML private Label btMdpOublie;
    @FXML private Button btInscription;
    @FXML private TextField fieldUsername;
    @FXML private PasswordField fieldPassword;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        validUsers.put("admin", "admin");
    }
    @FXML
    void handleInscription(ActionEvent event){
        if(labelConnexionInscription.getText() =="Inscription")
        {
            labelConnexionInscription.setText("Connexion");
            btMdpOublie.setVisible(true);
            btInscription.setText("S'inscrire");
        }
        else
        {
            labelConnexionInscription.setText("Inscription");
            btInscription.setText("Se connecter");
            btMdpOublie.setVisible(false);
        }
    }


    public void handleOublieMDP(javafx.scene.input.MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Username : admin\nPassword : admin");
        alert.showAndWait();
    }

    @FXML
    void handleValider(ActionEvent event) throws IOException {
        if(labelConnexionInscription.getText() =="Inscription")
        {
            if(fieldUsername.getText().equals("")||fieldPassword.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Vous devez saisir tout les champs");
                alert.showAndWait();
                return;
            }
            validUsers.put(fieldUsername.getText(), fieldPassword.getText());
            labelConnexionInscription.setText("Connexion");
            btMdpOublie.setVisible(true);
            btInscription.setText("S'inscrire");
            fieldUsername.setText("");
            fieldPassword.setText("");
        }
        else
        {
            if(fieldUsername.getText().equals("")||fieldPassword.getText().equals("")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Le nom de compte ou mot de passe n'existe pas");
                alert.showAndWait();
                return;
            }
            if(validUsers.get(fieldUsername.getText()).equals(fieldPassword.getText())){
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Stage newStage = new Stage();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
                Parent root = fxmlLoader.load();

                Scene newScene = new Scene(root, 950, 600);

                newStage.setTitle("Bibliotheque");
                newStage.setResizable(false);
                Image icon = new Image("https://cdn-icons-png.flaticon.com/512/5606/5606075.png");
                newStage.getIcons().add(icon);
                newStage.setScene(newScene);

                newStage.show();
                currentStage.close();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Mauvais username ou password");
                alert.showAndWait();
            }
        }
    }



}
