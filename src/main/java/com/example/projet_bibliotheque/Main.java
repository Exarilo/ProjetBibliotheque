package com.example.projet_bibliotheque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 950, 600);
        stage.setTitle("Bibliotheque");
        stage.setResizable(false);
        Image icon = new Image("https://cdn-icons-png.flaticon.com/512/5606/5606075.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

        try{
            LoadXML();
        }
        catch (JAXBException e) {
            throw new RuntimeException(e);
        }


    }

    public static void LoadXML() throws JAXBException {


        JAXBContext jaxbContext = JAXBContext.newInstance(Bibliotheque.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        Bibliotheque bibliotheque = (Bibliotheque) jaxbUnmarshaller.unmarshal(new File("F:\\AgileJava\\Biblio.xml"));

    }
    public static void main(String[] args) {
        launch();
        System.out.println("test");
    }
}

