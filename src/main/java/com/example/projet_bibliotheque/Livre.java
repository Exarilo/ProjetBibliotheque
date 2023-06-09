package com.example.projet_bibliotheque;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**Class Livre**/
public class Livre {
    private SimpleStringProperty titre = new SimpleStringProperty();
    private SimpleStringProperty nomAuteur = new SimpleStringProperty();
    private SimpleStringProperty prenomAuteur = new SimpleStringProperty();
    private SimpleStringProperty presentation = new SimpleStringProperty();
    private SimpleIntegerProperty parution = new SimpleIntegerProperty();
    private SimpleIntegerProperty colonne = new SimpleIntegerProperty();
    private SimpleIntegerProperty rangee = new SimpleIntegerProperty();

    public String getTitre() {
        return titre.get();
    }

    public SimpleStringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre.set(titre);
    }

    public String getNomAuteur() {
        return nomAuteur.get();
    }

    public SimpleStringProperty nomAuteurProperty() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur.set(nomAuteur);
    }

    public String getPrenomAuteur() {
        return prenomAuteur.get();
    }

    public SimpleStringProperty prenomAuteurProperty() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        this.prenomAuteur.set(prenomAuteur);
    }

    public String getPresentation() {
        return presentation.get();
    }

    public SimpleStringProperty presentationProperty() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation.set(presentation);
    }

    public int getParution() {
        return parution.get();
    }

    public SimpleIntegerProperty parutionProperty() {
        return parution;
    }

    public void setParution(int parution) {
        this.parution.set(parution);
    }

    public int getColonne() {
        return colonne.get();
    }

    public SimpleIntegerProperty colonneProperty() {
        return colonne;
    }

    public void setColonne(int colonne) {
        this.colonne.set(colonne);
    }

    public int getRangee() {
        return rangee.get();
    }

    public SimpleIntegerProperty rangeeProperty() {
        return rangee;
    }

    public void setRangee(int rangee) {
        this.rangee.set(rangee);
    }

    public Auteur getAuteur() {
        return new Auteur(nomAuteur.get(), prenomAuteur.get());
    }

    public void setAuteur(Auteur auteur) {
        nomAuteur.set(auteur.getNom());
        prenomAuteur.set(auteur.getPrenom());
    }

}
