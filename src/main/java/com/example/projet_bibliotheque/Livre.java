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
 * également de la gestion d'erreur dans les setteurs au cas ou le format n'est pas le bon
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

    public Livre(){
        wrongFieldAlert= new Alert(Alert.AlertType.WARNING);
        wrongFieldAlert.setTitle("Erreur de champs de saisie");
    }
    public String getTitre() {
        return this.titre;
    }

    public void setTitre(String titre) {
        if(titre==null||titre.length()==0){
            wrongFieldAlert.setContentText("Le champ titre doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.titre=titre;
    }

    public String getNomAuteur() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        if(nomAuteur==null||nomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ nom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.nomAuteur=nomAuteur;
    }

    public String getPrenomAuteur() {
        return prenomAuteur;
    }
    public void setPrenomAuteur(String prenomAuteur) {
        if(prenomAuteur==null||prenomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ prenom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.prenomAuteur=prenomAuteur;
    }

    public String getPresentation() {
        return presentation;
    }


    public void setPresentation(String presentation) {
        if(presentation==null||presentation.length()==0){
            wrongFieldAlert.setContentText("Le champ presentation auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.presentation=presentation;
    }

    public int getParution() {
        return this.parution;
    }

    public void setParution(int parution) {
        this.parution=parution;
    }

    public int getColonne() {
        return colonne;
    }



    public void setColonne(int colonne) {
        this.colonne=colonne;
    }

    public int getRangee() {
        return rangee;
    }



    public void setRangee(int rangee) {
        this.rangee=rangee;
    }

    public Auteur getAuteur() {
        return new Auteur(nomAuteur, prenomAuteur);
    }

    public void setAuteur(Auteur auteur) {
        nomAuteur=auteur.getNom();
        prenomAuteur=auteur.getPrenom();
    }



}
