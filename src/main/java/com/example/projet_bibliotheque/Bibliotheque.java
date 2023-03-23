package com.example.projet_bibliotheque;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    public List<Livre> livre;

    public Bibliotheque() {
        this.livre  =new ArrayList<>();
    }
    public void LoadXML(String filepath) throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filepath));
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
                    this.livre.add(livre);
                }
            }
        }
    }
}





