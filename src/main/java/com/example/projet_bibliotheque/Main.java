package com.example.projet_bibliotheque;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;


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
            Bibliotheque bibliotheque = LoadXML();
            int a=0;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static Bibliotheque LoadXML() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("src/main/resources/com/example/projet_bibliotheque/Biblio.xml"));
        Bibliotheque bibliotheque = new Bibliotheque();
        while(reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equals("livre")) {
                    Livre livre = new Livre();
                    while (reader.hasNext()) {
                        event = reader.next();
                        if (event == XMLStreamConstants.START_ELEMENT) {
                            String localName = reader.getLocalName();
                            reader.next();

                            switch (localName) {
                                case "titre":
                                    livre.titre=reader.getText();
                                    break;
                                case "nom":
                                    livre.auteur.nom=reader.getText();
                                    break;
                                case "prenom":
                                    livre.auteur.prenom=reader.getText();
                                    break;
                                case "presentation":
                                    livre.presentation=reader.getText();
                                    break;
                                case "parution":
                                    livre.parution= Short.toUnsignedInt(Short.parseShort(reader.getText()));
                                    break;
                                case "colonne":
                                    livre.colonne= Byte.toUnsignedInt(Byte.parseByte(reader.getText()));
                                    break;
                                case "rangee":
                                    livre.rangee= Byte.toUnsignedInt(Byte.parseByte(reader.getText()));
                                    break;
                            }
                        }
                        else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("livre"))
                            break;
                    }
                    bibliotheque.livre.add(livre);
                }

            }
        }
        return bibliotheque;
    }
    public static void main(String[] args) {
        launch();
        System.out.println("test");
    }
}

