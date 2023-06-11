package com.example.projet_bibliotheque;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;


public class Main extends Application {
    @Override
    /**
     * La methode start est le point d'entré de l'application. elle vient construire l'IHM en se
     * basant sur main-view.fxml (construit avec Scene Builder)
     */
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Bibliotheque");
        stage.setResizable(false);
        Image icon = new Image("https://cdn-icons-png.flaticon.com/512/5606/5606075.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
