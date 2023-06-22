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
import java.util.List;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;

public class Bibliotheque {
    public String path;
    public List<Livre> livre;
    /**
     * La class Bibliotheque permet de stocker tout se qui concerne les livres et leur gestion
     * Elle possede deux constructeurs. Le constructeur sans parametre sert a initialiser
     * la liste de livres afin de ne pas avoir de crash lorsque l'on ajoute un livre dans
     * le tableau de l'IHM.
     * Celle avec paramettre permet de charger les livres depuis le chemin d'un fichier XML
     */
    public Bibliotheque()  {
        this.livre  =new ArrayList<>();
    }
    public Bibliotheque(String path) throws XMLStreamException, IOException {
        this.livre  =new ArrayList<>();
        this.path=path;
        LoadXML(path);
    }
    /**
     * A cause de problèmes de librairies la fonctionnilté LoadXML utilise un reader au lieu de JaxB
     * Le reader vient boucler sur toutes les lignes du fichier pour lire toutes les balises
     * Ce mode de fonctionnement entraine des problèmes pour lire les fichiers XML mal formattés
     * (ce qui est le cas avec la fonction toXml() qui sauvegarde tout sur une seul ligne
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
                    this.livre.add(livre);
                }
            }
        }
    }

    /**
     * A cause de problèmes de librairies la fonctionnilté toXML utilise un writter au lieu de JaxB
     * Le writter vient boucler sur tout les livres presents dans la bibliotheque et enregistre les balises associés
     * Ce mode de fonctionnement entraine des problèmes car nous n'avons pas trouver comment formatter le fichier l'enregistrement
     * se fait sur une seule et même ligne ce qui pose probleme avec la fonction de relecture LoadXML()
     */
    public void toXml(String savingPath) throws IOException, XMLStreamException {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(savingPath));

        writer.writeStartDocument("1.0");
        writer.writeStartElement("bibliotheque");


        for (Livre livre : livre) {
            writer.writeStartElement("livre");

            writer.writeStartElement("titre");
            writer.writeCharacters(livre.getTitre());
            writer.writeEndElement();

            writer.writeStartElement("auteur");

            writer.writeStartElement("nom");
            writer.writeCharacters(livre.getNomAuteur());
            writer.writeEndElement();

            writer.writeStartElement("prenom");
            writer.writeCharacters(livre.getPrenomAuteur());
            writer.writeEndElement();

            writer.writeEndElement();

            writer.writeStartElement("presentation");
            writer.writeCharacters(livre.getPresentation());
            writer.writeEndElement();

            writer.writeStartElement("parution");
            writer.writeCharacters(String.valueOf(livre.getParution()));
            writer.writeEndElement();

            writer.writeStartElement("colonne");
            writer.writeCharacters(String.valueOf(livre.getColonne()));
            writer.writeEndElement();

            writer.writeStartElement("rangee");
            writer.writeCharacters(String.valueOf(livre.getRangee()));
            writer.writeEndElement();

            writer.writeEndElement();
        }

        writer.writeEndElement();
        writer.writeEndDocument();

        writer.flush();
        writer.close();
    }



    public void toDocx(String savingPath) throws Exception {
        try (XWPFDocument doc = new XWPFDocument()) {
            for (Livre livre : livre) {
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

    public void toPDF(String savingPath) throws Exception {
        try (PDDocument pdfDoc = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            pdfDoc.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(pdfDoc, page, PDPageContentStream.AppendMode.APPEND, false)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(100, 700);
                contentStream.setLeading(14.5f);

                for (Livre livre : livre) {
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





