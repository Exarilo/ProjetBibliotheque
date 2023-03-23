package com.example.projet_bibliotheque;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**Class Livre**/
@XmlAccessorType(XmlAccessType.FIELD)
public class Livre {

    private String titre;

    private Auteur auteur;

    private String presentation;

    private int parution;

    private int colonne;

    private int rangee;

    // getters et setters
}