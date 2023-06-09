package com.example.projet_bibliotheque;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;

public class Bibliotheque {
    public String path;
    public List<Livre> livre;

    public Bibliotheque()  {
        this.livre  =new ArrayList<>();
    }
    public Bibliotheque(String path) throws XMLStreamException, IOException {
        this.livre  =new ArrayList<>();
        this.path=path;
        LoadXML(path);
    }
    public void LoadXML(String filepath) throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filepath));
        while (reader.hasNext()) {
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
                                    livre.setTitre(reader.getText());
                                    break;
                                case "nom":
                                    livre.setNomAuteur(reader.getText());
                                    break;
                                case "prenom":
                                    livre.setPrenomAuteur(reader.getText());
                                    break;
                                case "presentation":
                                    livre.setPresentation(reader.getText());
                                    break;
                                case "parution":
                                    livre.setParution(Short.toUnsignedInt(Short.parseShort(reader.getText())));
                                    break;
                                case "colonne":
                                    livre.setColonne(Byte.toUnsignedInt(Byte.parseByte(reader.getText())));
                                    break;
                                case "rangee":
                                    livre.setRangee(Byte.toUnsignedInt(Byte.parseByte(reader.getText())));
                                    break;
                            }

                        } else if (event == XMLStreamConstants.END_ELEMENT && reader.getLocalName().equals("livre"))
                            break;
                    }
                    this.livre.add(livre);
                }
            }
        }

        // Constructeur, getters, setters, etc.

    }
    public void toXml(String SavingPath) throws IOException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(SavingPath));

        writer.writeStartDocument();
        writer.writeStartElement("bibliotheque");

        for (Livre livre : livre) {
            writer.writeStartElement("livre");

            writer.writeStartElement("titre");
            writer.writeCharacters(livre.getTitre());
            writer.writeEndElement();

            writer.writeStartElement("Nom Auteur");
            writer.writeCharacters(livre.getNomAuteur());
            writer.writeEndElement();

            writer.writeStartElement("Prénom Auteur");
            writer.writeCharacters(livre.getPrenomAuteur());
            writer.writeEndElement();

            writer.writeStartElement("Présentation");
            writer.writeCharacters(String.valueOf(livre.getPresentation()));
            writer.writeEndElement();

            writer.writeStartElement("Parution");
            writer.writeCharacters(String.valueOf(livre.getParution()));
            writer.writeEndElement();

            writer.writeStartElement("Colonne");
            writer.writeCharacters(String.valueOf(livre.getColonne()));
            writer.writeEndElement();

            writer.writeStartElement("Range");
            writer.writeCharacters(String.valueOf(livre.getRangee()));
            writer.writeEndElement();

            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }
}





