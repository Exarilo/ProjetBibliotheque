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
            LoadXML();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public static void LoadXML() throws IOException, SAXException, ParserConfigurationException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream("src/main/resources/com/example/projet_bibliotheque/Biblio.xml"));
        while(reader.hasNext()) {
            int event = reader.next();
            if (event == XMLStreamConstants.START_ELEMENT) {
                if (reader.getLocalName().equals("livre")) {
                    String titre = "";
                    String auteurNom = "";
                    String auteurPrenom = "";
                    String presentation = "";
                    String parution = "";
                    String colonne = "";
                    String rangee = "";

                    while (reader.hasNext()) {
                        event = reader.next();
                        if (event == XMLStreamConstants.START_ELEMENT) {
                            String localName = reader.getLocalName();
                            reader.next();
                            switch (localName) {
                                case "titre":
                                    titre = reader.getText();
                                    break;
                                case "nom":
                                    auteurNom = reader.getText();
                                    break;
                                case "prenom":
                                    auteurPrenom = reader.getText();
                                    break;
                                case "presentation":
                                    presentation = reader.getText();
                                    break;
                                case "parution":
                                    parution = reader.getText();
                                    break;
                                case "colonne":
                                    colonne = reader.getText();
                                    break;
                                case "rangee":
                                    rangee = reader.getText();
                                    break;
                            }
                        }
                        if (event == XMLStreamConstants.END_ELEMENT) {
                            if (reader.getLocalName().equals("livre")) {
                                System.out.println("Titre : " + titre);
                                System.out.println("Auteur : " + auteurNom + " " + auteurPrenom);
                                System.out.println("Presentation : " + presentation);
                                System.out.println("Parution : " + parution);
                                System.out.println("Colonne : " + colonne);
                                System.out.println("Rangee : " + rangee);
                                System.out.println("-------------------------------------");
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        launch();
        System.out.println("test");
    }
    private static File getFileFromResource(String fileName) throws URISyntaxException{

        ClassLoader classLoader = Main.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }
}

