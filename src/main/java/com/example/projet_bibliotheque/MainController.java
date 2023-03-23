package com.example.projet_bibliotheque;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/** Controller principal de l'application. */
public class MainController {
    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Nouveau Livre".
     Elle affiche une boîte de dialogue d'information pour dire qu'un livre a bien été rajouté.
     */
    void handleNouveauLivre(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Et boom c'est validé");
        alert.showAndWait();
    }

    @FXML
    /**
     Méthode appelée lorsqu'on clique sur le bouton "Ouvrir de la menu bar".
     Elle ouvre une boîte de dialogue de sélection de fichier.
     */
    void handleOuvrirMenuItemAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Fichiers XML (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null)
            System.out.println("fichier selectionné");
    }
    @FXML
    /**
     Méthode permettant de quitter l'application
     */
    void handleQuitterMenuItemAction(ActionEvent event) {
        Platform.exit();
    }


    /**
     Méthode permettant d'ouvrir une form avec 3 images dedans (les notres)
     */
    @FXML
    void handleInfosMenuItemAction(ActionEvent event) {
        Stage stage = new Stage();
        stage.setTitle("C'est nous !");
        stage.setResizable(false);

        VBox vbox = new VBox();
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));
        vbox.getChildren().add(new ImageView(new Image("https://static.vecteezy.com/ti/vecteur-libre/t2/2002332-ablack-man-avatar-character-isolated-icon-gratuit-vectoriel.jpg")));

        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        stage.show();
    }
}