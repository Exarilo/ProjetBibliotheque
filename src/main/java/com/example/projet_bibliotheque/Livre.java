package com.example.projet_bibliotheque;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;

/**Class Livre**/
public class Livre {
    public Alert wrongFieldAlert = new Alert(Alert.AlertType.WARNING);
    private SimpleStringProperty titre = new SimpleStringProperty();
    private SimpleStringProperty nomAuteur = new SimpleStringProperty();
    private SimpleStringProperty prenomAuteur = new SimpleStringProperty();
    private SimpleStringProperty presentation = new SimpleStringProperty();
    private SimpleIntegerProperty parution = new SimpleIntegerProperty();
    private SimpleIntegerProperty colonne = new SimpleIntegerProperty();
    private SimpleIntegerProperty rangee = new SimpleIntegerProperty();

    public Livre(){
        wrongFieldAlert.setTitle("Erreur de champs de saisie");
    }
    public String getTitre() {
        return titre.get();
    }

    public SimpleStringProperty titreProperty() {
        return titre;
    }

    public void setTitre(String titre) {
        if(titre==null||titre.length()==0){
            wrongFieldAlert.setContentText("Le champ titre doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.titre.set(titre);
    }

    public String getNomAuteur() {
        return nomAuteur.get();
    }

    public SimpleStringProperty nomAuteurProperty() {
        return nomAuteur;
    }

    public void setNomAuteur(String nomAuteur) {
        if(nomAuteur==null||nomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ nom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.nomAuteur.set(nomAuteur);
    }

    public String getPrenomAuteur() {
        return prenomAuteur.get();
    }

    public SimpleStringProperty prenomAuteurProperty() {
        return prenomAuteur;
    }

    public void setPrenomAuteur(String prenomAuteur) {
        if(prenomAuteur==null||prenomAuteur.length()==0){
            wrongFieldAlert.setContentText("Le champ prenom auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
        this.prenomAuteur.set(prenomAuteur);
    }

    public String getPresentation() {
        return presentation.get();
    }

    public SimpleStringProperty presentationProperty() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        if(presentation==null||presentation.length()==0){
            wrongFieldAlert.setContentText("Le champ presentation auteur doit être renseigné");
            wrongFieldAlert.showAndWait();
        }
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
