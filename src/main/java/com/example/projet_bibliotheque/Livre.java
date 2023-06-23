package com.example.projet_bibliotheque;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * La class livre gere toutes les proprietes relatives a un livre dans le XML on y retrouve
 * egalement de la gestion d'erreur dans les setteurs au cas ou le format n'est pas le bon
 */
public class Livre {
    public Alert wrongFieldAlert;
    private String titre;
    private String nomAuteur;
    private String prenomAuteur;
    private String presentation;
    private int parution;
    private int colonne ;
    private int rangee;

    /**
     * Constructeur de Livre une alert est predefinit pour les tests de validites des champs
     */
    public Livre(){
        wrongFieldAlert= new Alert(Alert.AlertType.WARNING);
        wrongFieldAlert.setTitle("Erreur de champs de saisie");
    }

    /**
     * Getter de titre
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * Setter de titre
     */
    public void setTitre(String titre) {
        if(titre==null||titre.length()==0){
            wrongFieldAlert.setContentText("Le champ titre doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.titre=titre;
    }

    /**
     * Getter de nom
     */
    public String getNomAuteur() {
        return nomAuteur;
    }

    /**
     * Setter de nom
     */
    public void setNomAuteur(String nomAuteur) {
        if(nomAuteur==null||nomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ nom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.nomAuteur=nomAuteur;
    }

    /**
     * Getter du prenom auteur
     */
    public String getPrenomAuteur() {
        return prenomAuteur;
    }

    /**
     * Setter du prenom auteur
     */
    public void setPrenomAuteur(String prenomAuteur) {
        if(prenomAuteur==null||prenomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ prenom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.prenomAuteur=prenomAuteur;
    }

    /**
     * Getter de la presentation
     */
    public String getPresentation() {
        return presentation;
    }

    /**
     * Setter de la presentation
     */
    public void setPresentation(String presentation) {
        if(presentation==null||presentation.length()==0){
            wrongFieldAlert.setContentText("Le champ presentation auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.presentation=presentation;
    }

    /**
     * Getter de la parution
     */
    public int getParution() {
        return this.parution;
    }

    /**
     * Setter de la presentation
     */
    public void setParution(int parution) {
        this.parution=parution;
    }

    /**
     * Getter de la colonne
     */
    public int getColonne() {
        return colonne;
    }


    /**
     * Setter de la colonne
     */
    public void setColonne(int colonne) {
        this.colonne=colonne;
    }

    /**
     * Getter de la rangee
     */
    public int getRangee() {
        return rangee;
    }

    /**
     * Setter de la rangee
     */
    public void setRangee(int rangee) {
        this.rangee=rangee;
    }

    /**
     * Getter de l'auteur
     */
    public Auteur getAuteur() {
        return new Auteur(nomAuteur, prenomAuteur);
    }

    /**
     * Setter de l'auteur
     */
    public void setAuteur(Auteur auteur) {
        nomAuteur=auteur.getNom();
        prenomAuteur=auteur.getPrenom();
    }



}
