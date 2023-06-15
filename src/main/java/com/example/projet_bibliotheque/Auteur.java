package com.example.projet_bibliotheque;

import javafx.beans.property.SimpleStringProperty;

public class Auteur {
    private String nom;
    private String prenom;

    public Auteur(String nom, String prenom) {
        this.nom=nom;
        this.prenom=prenom;
    }

    public String getNom() {
        return this.nom;
    }


    public void setNom(String nouveauNom) {
        this.nom=nouveauNom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String nouveauPrenom) {
        this.prenom=nouveauPrenom;
    }
}
