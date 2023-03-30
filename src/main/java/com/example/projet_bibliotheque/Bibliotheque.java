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
    private String path;
    public List<Livre> livre;

    public Bibliotheque(String path) throws XMLStreamException, IOException {
        this.livre  =new ArrayList<>();
        this.path=path;
        LoadXML(path);
    }
    public void LoadXML(String filepath) throws IOException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(filepath));
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





