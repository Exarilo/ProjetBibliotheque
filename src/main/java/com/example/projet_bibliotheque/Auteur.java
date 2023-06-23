package com.example.projet_bibliotheque;



/**
 * La class Auteur contient tout ce qui est en relation avec l'ateur d'un livre
 * a savoir son nom / prenom
 */
public class Auteur {
    private String nom;
    private String prenom;

    /**
     * Constructeur d'un auteur a partir de son nom prenom
     */
    public Auteur(String nom, String prenom) {
        this.nom=nom;
        this.prenom=prenom;
    }

    /**
     * getter du nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * setter du nom
     */
    public void setNom(String nouveauNom) {
        this.nom=nouveauNom;
    }

    /**
     * getter du prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setter du prenom
     */
    public void setPrenom(String nouveauPrenom) {
        this.prenom=nouveauPrenom;
    }
}
