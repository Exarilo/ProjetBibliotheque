package com.example.projet_bibliotheque;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.poi.xwpf.usermodel.*;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.util.Map;

/**
 * La class Bibliotheque contient une liste de livres
 * Les methodes presentent dedans permettent en majoriter de convertir les livres en fichier
 */
public class Bibliotheque {
    public String path;
    private static final Map<String, Integer> indentationDictionary = new HashMap<>();
    static {
        indentationDictionary.put("bibliotheque", 0);
        indentationDictionary.put("livre", 0);
        indentationDictionary.put("titre", 1);
        indentationDictionary.put("auteur", 1);
        indentationDictionary.put("nom", 2);
        indentationDictionary.put("prenom", 2);
        indentationDictionary.put("presentation", 1);
        indentationDictionary.put("parution", 1);
        indentationDictionary.put("colonne", 1);
        indentationDictionary.put("rangee", 1);
    }

    public List<Livre> livres;
    /**
     * La class Bibliotheque permet de stocker tout se qui concerne les livres et leur gestion
     * Elle possede deux constructeurs. Le constructeur sans parametre sert a initialiser
     * la liste de livres afin de ne pas avoir de crash lorsque l'on ajoute un livre dans
     * le tableau de l'IHM.
     * Celle avec paramettre permet de charger les livres depuis le chemin d'un fichier XML
     */
    public Bibliotheque()  {
        this.livres  =new ArrayList<>();
    }
    public Bibliotheque(String path) throws XMLStreamException, IOException {
        this.livres  =new ArrayList<>();
        this.path=path;
        LoadXML(path);
    }
    /**
     * A cause de problèmes de librairies la fonctionnilté LoadXML utilise un reader au lieu de JaxB
     * Le reader vient boucler sur toutes les lignes du fichier pour lire toutes les balises
     * Ce mode de fonctionnement entraine des problèmes pour lire les fichiers XML mal formattés
     */
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
                    this.livres.add(livre);
                }
            }
        }
    }

    /**
     * A cause de problèmes de librairies la fonctionnilté toXML utilise un writter au lieu de JaxB
     * Le writter vient boucler sur tout les livres presents dans la bibliotheque et enregistre les balises associés
     * a partir d'un dictionnaire d'indentation le xml est formaté
     */
    public void toXml(String savingPath) throws IOException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(savingPath));

        writer.writeStartDocument("1.0");
        writer.writeCharacters("\n");
        writer.writeComment(" fichier livre.xml ");
        writer.writeCharacters("\n");
        indent(writer, "bibliotheque");
        writer.writeStartElement("bibliotheque");

        for (Livre livre : livres) {
            indent(writer, "livre");
            writer.writeCharacters("\n");
            writer.writeStartElement("livre");
            writer.writeCharacters("\n");

            indent(writer, "titre");
            writer.writeStartElement("titre");
            writer.writeCharacters(livre.getTitre());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "auteur");
            writer.writeStartElement("auteur");
            writer.writeCharacters("\n");

            indent(writer, "nom");
            writer.writeStartElement("nom");
            writer.writeCharacters(livre.getNomAuteur());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "prenom");
            writer.writeStartElement("prenom");
            writer.writeCharacters(livre.getPrenomAuteur());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "auteur");
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "presentation");
            writer.writeStartElement("presentation");
            writer.writeCharacters(livre.getPresentation());
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "parution");
            writer.writeStartElement("parution");
            writer.writeCharacters(String.valueOf(livre.getParution()));
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "colonne");
            writer.writeStartElement("colonne");
            writer.writeCharacters(String.valueOf(livre.getColonne()));
            writer.writeEndElement();
            writer.writeCharacters("\n");

            indent(writer, "rangee");
            writer.writeStartElement("rangee");
            writer.writeCharacters(String.valueOf(livre.getRangee()));
            writer.writeEndElement();

            indent(writer, "livre");
            writer.writeCharacters("\n");
            writer.writeEndElement();
        }
        writer.writeCharacters("\n");
        indent(writer, "bibliotheque");
        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }

    /**
     * Méthode utilitaire pour l'indentation
     */
    private void indent(XMLStreamWriter writer, String elementName) throws XMLStreamException {
        int indentLevel = indentationDictionary.get(elementName);
        String indentation = getIndentation(indentLevel);
        writer.writeCharacters(indentation);
    }
    /**
     * Méthode utilitaire pour l'indentation
     */
    private String getIndentation(int indentLevel) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            sb.append("  ");
        }
        return sb.toString();
    }

    /**
     * Permet de convertir les livres stockes dans la bibliotheque en fichier docx
     */
    public void toDocx(String savingPath) throws Exception {
        try (XWPFDocument doc = new XWPFDocument()) {
            for (Livre livre : livres) {
                XWPFParagraph p = doc.createParagraph();

                // Titre du livre
                XWPFRun r1 = p.createRun();
                r1.setBold(true);
                r1.setText(livre.getTitre());
                r1.addCarriageReturn();

                // Auteur du livre
                XWPFRun r2 = p.createRun();
                r2.setText("Auteur: " + livre.getNomAuteur() + " " + livre.getPrenomAuteur());
                r2.addCarriageReturn();

                // Présentation du livre
                XWPFRun r3 = p.createRun();
                r3.setText("Présentation: " + livre.getPresentation());
                r3.addCarriageReturn();

                // Parution du livre
                XWPFRun r4 = p.createRun();
                r4.setText("Parution: " + livre.getParution());
                r4.addCarriageReturn();

                // Position du livre
                XWPFRun r5 = p.createRun();
                r5.setText("Position: Colonne " + livre.getColonne() + ", Rangée " + livre.getRangee());
                r5.addCarriageReturn();

                doc.createParagraph().setPageBreak(true);
            }

            try (FileOutputStream out = new FileOutputStream(savingPath)) {
                doc.write(out);
            }
        }
    }

    /**
     * Permet de convertir les livres stockes dans la bibliotheque en fichier pdf
     */
    public void toPDF(String savingPath) throws Exception {
        try (PDDocument pdfDoc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            pdfDoc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page, PDPageContentStream.AppendMode.APPEND, false)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.setLeading(14.5f);

                for (Livre livre : livres) {
                    contentStream.showText("Titre: " + livre.getTitre());
                    contentStream.newLineAtOffset(0, -15);

                    contentStream.showText("Auteur: " + livre.getNomAuteur() + " " + livre.getPrenomAuteur());
                    contentStream.newLineAtOffset(0, -15);

                    contentStream.showText("Présentation: " + livre.getPresentation());
                    contentStream.newLineAtOffset(0, -15);

                    contentStream.showText("Parution: " + livre.getParution());
                    contentStream.newLineAtOffset(0, -15);

                    contentStream.showText("Position: Colonne " + livre.getColonne() + ", Rangée " + livre.getRangee());
                    contentStream.newLineAtOffset(0, -15);
                }

                contentStream.endText();
            }
            pdfDoc.save(savingPath);
        }
    }
}





